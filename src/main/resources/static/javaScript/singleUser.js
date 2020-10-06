const params = new URLSearchParams(window.location.search);

for(let param of params){
    console.log("here i am",param);
    let id = param[1];
    console.log(id);
    getSingleUser(id);
}

function getSingleUser(id){
    fetch('http://localhost:8080/user/read/'+id)
    .then(
        function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
            response.status);
            return;
        }

      // Examine the text in the response
        response.json().then(function(userData) {
            console.log(userData);

            document.getElementById("userId").value=userData.id;
            document.getElementById("userFirstName").value=userData.firstName;
            document.getElementById("userSurname").value=userData.surname;
            document.getElementById("userAge").value=userData.userAge;
        });
        }
    )
    .catch(function(err) {
        console.log('Fetch Error :-S', err);
    });
}

document.querySelector("form.userRecord").addEventListener("submit",function(stop){
    stop.preventDefault();

    let formElements = document.querySelector("form.userRecord").elements;
    let id = formElements["userId"].value;
    let firstName = formElements["userFirstName"].value;
    let surname = formElements["userSurname"].value;
    let age = formElements["userAge"].value;
    updateTask(id,firstName,surname,age);
})

function updateTask(id,firstName,surname,age){

    let updateId = parseInt(id);
    let updateAge = parseInt(age);

    fetch("http://localhost:8080/user/update/"+id, {
        method: 'PUT',
        headers: {
          "Content-type": "application/json"
        },
        body:json = JSON.stringify ({
            "id": updateId,
            "firstName":firstName,
            "surname":surname,
            "userAge": updateAge
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