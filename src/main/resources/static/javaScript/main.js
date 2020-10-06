fetch('http://localhost:8080/task/read')
  .then(
    function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        return;
      }

      // Examine the text in the response
      response.json().then(function(taskData) {
        console.log(taskData);

        let table = document.querySelector("table");
        let data = Object.keys(taskData[0]);

        createTableHead(table,data);
        createTableBody(table,taskData);

      });
    }
  )
  .catch(function(err) {
    console.log('Fetch Error :-S', err);
  });

  function createTableHead(table,data){
    let tableHead = table.createTHead();
    let row = tableHead.insertRow();
    for(let keys of data){
        let th = document.createElement("th");
        let text = document.createTextNode(keys);
        th.appendChild(text);
        row.appendChild(th);
    }
    let th2 = document.createElement("th");
    let text2 = document.createTextNode("View")
    th2.appendChild(text2);
    row.appendChild(th2);

    let th3 = document.createElement("th");
    let text3 = document.createTextNode("Delete")
    th3.appendChild(text3);
    row.appendChild(th3);
}

function createTableBody(table,taskData){
    for(let taskRecord of taskData){
        let row = table.insertRow();
        for(let values in taskRecord){
            console.log(taskRecord[values]);
            let cell = row.insertCell();
            let text = document.createTextNode(taskRecord[values]);
            cell.appendChild(text);
        }
        let newCell = row.insertCell();
        let viewButton = document.createElement("a");
        viewButton.className="btn btn-info";
        viewButton.innerHTML="View";
        viewButton.href="singleTask.html?id=" + taskRecord.id;
        newCell.appendChild(viewButton);

        let delCell = row.insertCell();
        let delButton = document.createElement("a");
        delButton.type="submit";
        delButton.className="btn btn-danger";
        delButton.innerHTML="Delete";
        delCell.appendChild(delButton);
    }
}

function deleteTask(id) {

  deleteId = parseInt(id);

  fetch('http://localhost:8080/task/delete/'+deleteId, {
    method: 'DELETE'
  })
    .then(json)
    .then(function (data) {
      console.log('Request succeeded with JSON response', data);
    })
    .catch(function (error) {
      console.log('Request failed', error);
    });
}