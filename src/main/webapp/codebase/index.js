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
                        alert("Cadastro realizado com sucesso!");
                    
                }
            };
    ajaxRequest.send(data);
}

function showDiv(divID){
    document.getElementById(divID).style.display = "inline";
    
    if(divID === 'user_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('redirect_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "inline";
        populateEvents();
    }
    if(divID === 'course_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('redirect_div').style.display = "none";
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
        document.getElementById('redirect_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'signup_div'){
        document.body.style.backgroundImage = "url(assets/images/login_background.jpg)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.getElementById('search_div').style.display = "none";
        document.getElementById('redirect_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'search_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('redirect_div').style.display = "none";
        document.getElementById('course_div').style.display = "none";
        document.getElementById('login_div').style.display = "none";
        document.getElementById('user_div').style.display = "none";
        document.getElementById('scheduler_here').style.display = "none";
    }
    if(divID === 'redirect_div'){
        document.body.style.backgroundImage = "";
        document.getElementById('signup_div').style.display = "none";
        document.getElementById('search_div').style.display = "none";
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
                        document.getElementsByName("user_id_field")[0].value = respostaJSON.user_id;
                        console.log("Setting user ID = " + document.getElementsByName("user_id_field")[0].value );
                        DisplayLoginInformation(respostaJSON);
                    }
                }
            };
    ajaxRequest.send(data);
}

function DisplayLoginInformation(response){
    document.getElementById('user_info').innerHTML = "<table><tr><td>Nome: " + response.name + "</td></tr><tr><td>" +
            "e-mail: " + response.email + "</td></tr></table><br>";
    showDiv('user_div');
}

function searchCourse(){
    var criteria = document.getElementById("search_course_options").value;
    var info = document.getElementsByName("info")[0].value;
    var sendData = {
        "criteria": criteria,
        "info": info
    };
    console.log("=== sendData: " + sendData);
    var data = JSON.stringify(sendData);
    console.log("=== data: " + data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", "searchServlet");
    ajaxRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajaxRequest.onreadystatechange =
            function () {
                if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                        var respostaJSON = JSON.parse(ajaxRequest.responseText);
                        displaySearchInformation(respostaJSON);
                }
            };
    ajaxRequest.send(data);
}

function clearTable() {
    $("#tableSearch>tbody").html("");
}

function displaySearchInformation(response){
    clearTable();
    var tbody = $("#tableSearch>tbody");
    $.each(response, function (index, value) {
        var columns = "";
        columns += '<td class="search_results">' + value.course_id + '</td>';
        columns += '<td class="search_results">' + value.name + '</td>';
        columns += '<td class="search_results">' + value.code + '</td>';
        columns += '<td class="search_results"><button type="submit" class="dhx_cal_today_button" style="background-color:white;" value="Submit" onclick="courseJoin(' + value.course_id + ')">Adicionar</button></td>';
        var row = '<tr id="row' + value.course_id + '">' + columns + '</tr>';
        tbody.append(row);
    });
}

function courseJoin(course_id){
    var user_id = document.getElementsByName("user_id_field")[0].value;
    var sendData = {
        "user_id": user_id,
        "course_id": course_id
    };
    console.log("=== sendData: " + sendData);
    var data = JSON.stringify(sendData);
    console.log("=== data: " + data);
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", "courseJoinServlet");
    ajaxRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    ajaxRequest.onreadystatechange =
            function () {
                if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                    //Will have to populate calendar again!
                    showDiv('user_div');
                }
            };
    ajaxRequest.send(data);
}
