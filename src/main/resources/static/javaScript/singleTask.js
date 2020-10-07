const params = new URLSearchParams(window.location.search);

for(let param of params){
    console.log("here i am",param);
    let id = param[1];
    console.log(id);
    getSingleTask(id);
}

function getSingleTask(id){
    fetch('http://localhost:8080/task/read/'+id)
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

            document.getElementById("taskId").value=taskData.id;
            document.getElementById("taskCategory").value=taskData.category;
            document.getElementById("taskDesc").value=taskData.description;
        });
        }
    )
    .catch(function(err) {
        console.log('Fetch Error :-S', err);
    });
}

document.querySelector("form.taskRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.taskRecord").elements;
    let id = formElements["taskId"].value;
    let category = formElements["taskCategory"].value;
    let description = formElements["taskDesc"].value;
    updateTask(id,category,description);
})

function updateTask(id,category,description){

    let updateId = parseInt(id);

    fetch("http://localhost:8080/task/update/"+id, {
        method: 'PUT',
        headers: {
          "Content-type": "application/json"
        },
        body:json = JSON.stringify ({
            "id": updateId,
            "category": category,
            "description": description
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