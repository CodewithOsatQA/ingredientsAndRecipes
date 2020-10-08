
const params = new URLSearchParams(window.location.search);

for (let param of params){
    console.log("here i am", param)
    let id = param[1];

    getSingleRecord(id);
}



function getSingleRecord(id){
fetch('http://localhost:8001/recipe/read/'+id)
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
        response.json().then(function(data) {
        console.log(data);
        document.getElementById("id").value = data.id;
        document.getElementById("name").value = data.name;
        document.getElementById("difficulty").value = data.difficulty;
        //document.getElementById("noOfStrings").value = data.strings;
        //document.getElementById("type").value = data.type;
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

document.querySelector("form.recipeRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.recipeRecord").elements;
    let id = parseInt(formElements["id"].value);
    let difficulty = formElements["difficulty"].value;
    let name = formElements["name"].value;
    
    //let name = formElements["name"].value;
    //let strings = parseInt(formElements["noOfStrings"].value);
    //let type = formElements["type"].value;
    updateRecord(id,name,difficulty);
})




function updateRecord(id,name,difficulty){
    let finalID = parseInt(id);
    fetch("http://localhost:8001/recipe/update/"+finalID, {
        method: 'PUT',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify( {
            "id": finalID,
            "name": name,
            "difficulty": difficulty
            // "strings": strings,
            // "type": type,
            // "band": {
            //     "id": 1
            // }
        })
      })
      .then(json)
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });




}

function deleteRecord(){
  
  console.log("hello my friends")
  let finalID = parseInt(document.getElementById("id").value);
    fetch("http://localhost:8001/recipe/delete/"+finalID, {
        method: 'DELETE',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify( {
            "id": finalID,
            "name": name,
            "difficulty": difficulty
            // "strings": strings,
            // "type": type,
            // "band": {
            //     "id": 1
            // }
        })
      })
      .then(json)
      .then(function (data) {
        console.log('Request succeeded with JSON response', data);
      })
      .catch(function (error) {
        console.log('Request failed', error);
      });
}