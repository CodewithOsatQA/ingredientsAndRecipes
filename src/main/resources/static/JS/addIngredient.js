
const params = new URLSearchParams(window.location.search);

for (let param of params){
    console.log("here i am", param)
    let id = param[1];

    getSingleRecord(id);
}



function getSingleRecord(id){
fetch('http://localhost:8001/ingredient/read/'+id)
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
        
        document.getElementById("name").value = data.name;
        document.getElementById("foodgroup").value = data.foodGroup;
        document.getElementById("price").value = data.price;
        document.getElementById("weight").value = data.weight;
        //document.getElementById("noOfStrings").value = data.strings;
        //document.getElementById("type").value = data.type;
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
}

document.querySelector("form.ingredientRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.ingredientRecord").elements;
    //let id = parseInt(formElements["id"].value);
    //let difficulty = formElements["difficulty"].value;
    let name = formElements["name"].value;
    let foodgroup = formElements["foodgroup"].value;
    let price = formElements["price"].value;
    let weight = formElements["weight"].value;
    
    //let name = formElements["name"].value;
    //let strings = parseInt(formElements["noOfStrings"].value);
    //let type = formElements["type"].value;
    updateRecord(name,foodgroup,price,weight);
})




function updateRecord(name,foodgroup,price,weight){
    console.log(foodgroup);
    //let finalID = parseInt(id);
    fetch("http://localhost:8001/ingredient/create", {
        method: 'POST',
        headers: {
          "Content-type": "application/json"
        },
        
        body: json = JSON.stringify( {
            
            "name": name,
            "foodGroup": foodgroup,
            "price": price,
            "weight": weight
            // "strings": strings,
            // "type": type,
            // "recipe": {
            //      "id": 1
            //  }
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