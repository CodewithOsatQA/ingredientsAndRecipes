fetch('http://localhost:8001/ingredient/read')
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
      let text2 = document.createTextNode("view");
      th2.appendChild(text2);
      row.appendChild(th2);
  }
  function createTableBody(table, data){
      for (let myRecord of data){
          let row = table.insertRow();
          for (let info in myRecord){
              console.log(myRecord[info]);
              let cell = row.insertCell();
              let text = document.createTextNode(myRecord[info]);
              cell.appendChild(text);
          }
          let newCell = row.insertCell();
          let viewButton = document.createElement("a");
          let myButtonValue = document.createTextNode("view");
          viewButton.className = "btn btn-danger"

          
          viewButton.href = "ingredients.html?id="+ myRecord.id;
          viewButton.appendChild(myButtonValue);
          newCell.appendChild(viewButton);
      }
  }