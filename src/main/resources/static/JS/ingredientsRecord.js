fetch('http://localhost:8001/recipe/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }
      // Examine the text in the response
      response.json().then(function(data) {
        //console.log(data);
        let table = document.querySelector("table");
        let myData = Object.keys(data[0]);
        //createTableHead(table,myData);
        //createTableBody(table,data);
    addToRecipe(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
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
        document.getElementById("id").value = data.id;
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
    let id = parseInt(formElements["id"].value);
    //let difficulty = formElements["difficulty"].value;
    let name = formElements["name"].value;
    let foodgroup = formElements["foodgroup"].value;
    let price = formElements["price"].value;
    let weight = formElements["weight"].value;
    let recipe = formElements["recipe"].value;
    //let name = formElements["name"].value;
    //let strings = parseInt(formElements["noOfStrings"].value);
    //let type = formElements["type"].value;
    updateRecord(id,name,foodgroup,price,weight,recipe);
})




function updateRecord(id,name,foodgroup,price,weight,recipe){
    let finalID = parseInt(id);
    fetch("http://localhost:8001/ingredient/update/"+finalID, {
        method: 'PUT',
        headers: {
          "Content-type": "application/json"
        },
        body: json = JSON.stringify( {
            "id": finalID,
            "name": name,
            "foodgroup": foodgroup,
            "price": price,
            "weight": weight,
            // "strings": strings,
            // "type": type,
            "recipe": {
               "id": recipe
            }
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
function addToRecipe(data){
    let select = document.getElementById("recipe");
    for (let recipe of data){
        let text = document.createTextNode(recipe.name);
        let option = document.createElement("option")
        option.appendChild(text);
        option.value = recipe.id;
        select.appendChild(option);
        console.log(recipe.id);
        console.log(recipe.name);
    }
}