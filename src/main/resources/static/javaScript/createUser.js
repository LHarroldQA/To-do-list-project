document.querySelector("form.userRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.userRecord").elements;
    let firstName = formElements["userFirstName"].value;
    let surname = formElements["userSurname"].value;
    let age = formElements["userAge"].value;
    createTask(firstName,surname,age);
})

function createTask(firstName,surname,age){

    let createAge = parseInt(age);

    fetch("http://localhost:8080/user/create", {
        method: 'POST',
        headers: {
          "Content-type": "application/json"
        },
        body:json = JSON.stringify ({
            "firstName": firstName,
            "surname": surname,
            "userAge": createAge
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