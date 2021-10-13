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
    let path = getParameters().get("path");

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

function comeBack(event) {
    event.preventDefault();

    let originPath = window.location.origin;
    let path = getParameters().get("path");

    let pathArray = path.split("/");

    if (pathArray.length > 2 && pathArray[pathArray.length - 1] === "") {
        pathArray.pop();
    }
    pathArray.pop();

    let updatedPath;
    if (pathArray.length > 1) {
        updatedPath = pathArray.join("/");
        updatedPath = `${updatedPath}/`;
    } else {
        updatedPath = "/";
    }

    let updatedURL = `${originPath}/files?path=${updatedPath}`;
    window.location.replace(updatedURL);
}

function moveOn(event) {
    event.preventDefault();

    let originPath = window.location.origin;
    let directoryName = event.target.innerHTML;
    let path = getParameters().get("path") ?? "/";
    let updatedURL = `${originPath}/files?path=${path}${directoryName}`;

    window.location.replace(updatedURL);
}