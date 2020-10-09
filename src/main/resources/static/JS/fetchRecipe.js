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
        console.log(data);
        let table = document.querySelector("table");
        let myData = Object.keys(data[0]);

        createTableHead(table,myData);
        createTableBody(table,data);
      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table, data){
      let tableHead = table.createTHead();
      let row = tableHead.insertRow();

      for (let keys of data){
          let th = document.createElement("th");
          let text = document.createTextNode(keys);
          th.appendChild(text);
          row.appendChild(th);
      }
      let th2 = document.createElement("th");
      let text2 = document.createTextNode("price");
      th2.appendChild(text2);
      row.appendChild(th2);

      th2 = document.createElement("th");
      text2 = document.createTextNode("view");
      th2.appendChild(text2);
      row.appendChild(th2);
  }
  function createTableBody(table, data){
    
      for (let myRecord of data){
          let row = table.insertRow();
          let count = 0;
          for (let info in myRecord){
              count ++;
              
              
              let cell = row.insertCell();
              
            
              
              if (count === 4){
                for (let ingredient of myRecord["ingredients"]){
                  let text = document.createTextNode(ingredient.name+" " +"\n");
                  
                  cell.appendChild(text);
              }
            }
            else{
              let text = document.createTextNode(myRecord[info]);
              cell.appendChild(text);
            }
              
            
              
          }
          let price = row.insertCell();
          let total = 0;
          for (let ingredient of myRecord["ingredients"]){
            let priceIngredient = ingredient.price;
            total += priceIngredient;
            
        }
          price.appendChild(document.createTextNode("£"+total))

          let newCell = row.insertCell();
          let viewButton = document.createElement("a");
          let myButtonValue = document.createTextNode("view");
          viewButton.className = "btn btn-danger"

          
          viewButton.href = "recipes.html?id="+ myRecord.id;
          viewButton.appendChild(myButtonValue);
          newCell.appendChild(viewButton);
      }
  }