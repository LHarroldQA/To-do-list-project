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