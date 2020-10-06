document.querySelector("form.taskRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.taskRecord").elements;
    let category = formElements["taskCategory"].value;
    let description = formElements["taskDesc"].value;
    let userId = formElements["userId"].value;
    createTask(category,description,userId);
})

function createTask(category,description,userId){

    let createId = parseInt(userId);

    fetch("http://localhost:8080/task/create", {
        method: 'POST',
        headers: {
          "Content-type": "application/json"
        },
        body:json = JSON.stringify ({
            "category": category,
            "description": description,
            "user":{
                "id": createId
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