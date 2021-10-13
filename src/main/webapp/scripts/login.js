function comeIn(event) {
    event.preventDefault();

    let originPath = window.location.origin;

    let login = document.getElementById("come_in_login_field").value;
    let password = document.getElementById("come_in_password_field").value;

    if (login !== null && password !== null) {
        fetch(`${originPath}/login?login=${login}&password=${password}`)
            .then(response =>  {
                if (response.redirected === true) {
                    window.location.replace(response.url);
                }
            })
            .then(text => text);
    }
}

function goToRegistration(event) {
    event.preventDefault();

    let originPath = window.location.origin;
    let updatedURL = `${originPath}/registration`;

    window.location.replace(updatedURL);
}

function register(event) {
    event.preventDefault();

    let originPath = window.location.origin;

    let login = document.getElementById("login_field").value;
    let password = document.getElementById("password_field").value;
    let email = document.getElementById("email_field").value;

    if (login !== null && password !== null && email !== null) {
        fetch(`${originPath}/registration?login=${login}&password=${password}&email=${email}`)
            .then(response =>  {
                if (response.redirected === true) {
                    window.location.replace(response.url);
                }
            })
            .then(text => text);
    }
}

function goToComeIn() {
    event.preventDefault();

    let originPath = window.location.origin;
    let updatedURL = `${originPath}/login`;

    window.location.replace(updatedURL);
}
