function saveFile(event) {
    let originPath = window.location.origin;
    let fileName = event.target.innerHTML;
    let path = getParameters().get("path");
    let newPath = `${originPath}/download?path=${path}/${fileName}`;

    console.log(window.location.host);
    console.log(this);
    console.log(newPath);
}

function updatePath() {

}

function getParameters() {
    let parameters = window.location.search
        .substr(1)
        .split("&");

    let parameterValuePair = new Map();

    parameters.forEach((parameter) => {
        let pair = parameter.split("=");
        parameterValuePair.set(pair[0], pair[1]);
    })

    return parameterValuePair;
}