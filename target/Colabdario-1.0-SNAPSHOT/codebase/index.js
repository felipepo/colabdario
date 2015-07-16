function SignUp(){
    var name = document.getElementsByName("nome")[0].value;
    var email = document.getElementsByName("email")[0].value;
    var login = document.getElementsByName("login")[1].value;
    var password = document.getElementsByName("senha")[1].value;
    if (name === "") {
        return;
    }
    if (email === "") {
        return;
    }
    if (login === "") {
        return;
    }
    if (password === "") {
        return;
    }
    var sendData = {
        "name": name,
        "email": email,
        "login": login,
        "password": password
    };
    console.log("=== sendData: " + sendData);
    var data = JSON.stringify(sendData);
    console.log("=== data: " + data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", "signupservlet");
    ajaxRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajaxRequest.onreadystatechange =
            function () {
                if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                        var respostaJSON = JSON.parse(ajaxRequest.responseText);
                        popularCamposComRespostaJSON(respostaJSON);
                    
                }
            };
    ajaxRequest.send(data);
}