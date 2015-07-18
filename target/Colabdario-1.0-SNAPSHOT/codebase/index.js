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

function showDiv(divID){
    document.getElementById(divID).style.display = "inline";
    
    if(divID === 'user_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "inline";
    }
    if(divID === 'course_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'login_div'){
        document.body.style.backgroundImage = "url(assets/images/login_background.jpg)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'signup_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'search_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    
}

function login(){
    var login = document.getElementsByName("login")[0].value;
    var password = document.getElementsByName("senha")[0].value;
    var sendData = {
        "login": login
    };
    console.log("=== sendData: " + sendData);
    var data = JSON.stringify(sendData);
    console.log("=== data: " + data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", "loginServlet");
    ajaxRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajaxRequest.onreadystatechange =
            function () {
                if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                    var respostaJSON = JSON.parse(ajaxRequest.responseText);
                    if (password !== respostaJSON.password){
                        alert("Senha incorreta!")
                    }
                    else{
                        DisplayLoginInformation(respostaJSON);
                    }
                }
            };
    ajaxRequest.send(data);
}

function DisplayLoginInformation(response){
    document.getElementById('user_info').innerHTML = "Nome: " + response.name + "<br>" +
            "e-mail: " + response.email;
    document.getElementsByName('user_id_field').value = response.user_id;
    showDiv('user_div');
}