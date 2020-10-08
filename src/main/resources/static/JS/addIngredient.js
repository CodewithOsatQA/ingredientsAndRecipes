fetch('http://localhost:8001/recipe/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }
      response.json().then(function(data) {
    addToRecipe(data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });
const params = new URLSearchParams(window.location.search);



document.querySelector("form.ingredientRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.ingredientRecord").elements;
    let name = formElements["name"].value;
    let foodgroup = formElements["foodgroup"].value;
    let price = formElements["price"].value;
    let weight = formElements["weight"].value;
    let recipe = formElements["recipe"].value;
   
    updateRecord(name,foodgroup,price,weight,recipe);
})




function updateRecord(name,foodgroup,price,weight,recipe){
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
        window.open('index.html');
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