<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>ColabDário</title>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

	<script src="codebase/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="codebase/index.js"></script>
        <link rel="stylesheet" href="codebase/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8">

	
        <style type="text/css" media="screen">
            html, body{
                    margin:0px;
                    padding:0px;
                    height:100%;
                    overflow:hidden;
            }	
        </style>
    </head>

    <body onload="init();Script:showDiv('login_div');">

    <script type="text/javascript" charset="utf-8">
            function init() {
                    scheduler.config.xml_date="%Y-%m-%d %H:%i";
                    scheduler.init('scheduler_here',new Date(scheduler.config.now_date ? new Date(scheduler.config.now_date) : new Date),"month");

                    scheduler.templates.xml_date = function(value){ return new Date(value); };
                    scheduler.load("/data", "json");	

                    var dp = new dataProcessor("/data");
                    dp.init(scheduler);
                    dp.setTransactionMode("POST", false);
            }
    </script>
    
    <div id="login_div" style="text-align:center;vertical-align:middle;width:100%;height:100%;float:left;">
        <img src="assets/images/colabdario.png" width="300px"><br>
        <table border="0" style="margin: 0 auto;">
                <tr>
                    <td>
                        Login:
                    </td>
                    <td>
                        <input type="text" name="login">
                    </td>
                </tr>
                <tr>
                    <td>
                        Senha:
                    </td>
                    <td>
                        <input type="password" name="senha">
                    </td>
                </tr>
        </table>
        <button type="submit" class="dhx_cal_today_button" style="background-color:white;" value="Submit" onclick="Script:login()">Entrar</button><br>
        <br>
        <br>
        <br>
        Não cadastrado? <br>
        <button type="submit" class="dhx_cal_today_button" style="background-color:white;" value="Submit" onclick="Script:showDiv('signup_div')">Cadastrar</button><br>
    </div>

    <div id="signup_div" style="text-align:center;width:100%;height:100%;float:left;">
        <img src="assets/images/colabdario.png" style="display:block;margin: 0 0;" width="150px">
        <img src="assets/images/cadastro.png">
        <table style="margin: 0 auto;">
            <tr>
                <td>
                    Nome:
                </td>
                <td>
                    <input type="text" name="nome">
                </td>
            </tr>
            <tr>
                <td>
                    e-mail:
                </td>
                <td>
                    <input type="text" name="email">
                </td>
            </tr>
            <tr>
                <td>
                    Login:
                </td>
                <td>
                    <input type="text" name="login">
                </td>
            </tr>
            <tr>
                <td>
                    Senha:
                </td>
                <td>
                    <input type="password" name="senha">
                </td>
            </tr>
        </table>
        <button type="submit" class="dhx_cal_today_button" style="background-color:white;" value="Submit" onclick="Script:SignUp();showDiv('login_div')">Enviar</button><br>
    </div>
    
    <div id="search_div" class="dhx_cal_date" style="text-align:center;width:100%;height:100%;float:left;">
        <input type="image" src="assets/images/colabdario.png" style="display:inline-block;margin: 0 0;" width="150px" onclick="showDiv('user_div')">
        <img src="assets/images/pesquisar_disciplina.png" style="display:inline-block;margin: 0 auto;"><br>
        <select id="search_course_options">
            <option>Nome</option>
            <option>Código</option>
        </select>
        <input type="text" name="info" onkeyup="Script:searchCourse();"><br>
        <table id="tableSearch" class="search_results" style="margin: 0 auto;left: 10px;">
            <thead>
                <tr>
                    <th class="search_results">ID</th>
                    <th class="search_results">Nome</th>
                    <th class="search_results">Código</th>
                    <th class="search_results">Adicionar</th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
        <br>
        <button type="submit" class="dhx_cal_today_button" value="Submit" onclick="showDiv('user_div')">Voltar</button>
        <button type="submit" class="dhx_cal_today_button" value="Submit" onclick="clearTable();">Limpar</button>
        
    </div>
    
    <div id="course_div" class="dhx_cal_date" style='text-align:center;width:100%; height:100%; display:none;float:left;'>
        <input type="image" src="assets/images/colabdario.png" style="display:inline-block;margin: 0 0;" width="150px" onclick="showDiv('user_div')">
        <img src="assets/images/nova_disciplina2.png" style="display:inline-block;margin: 0 auto;"><br>
        <table style="margin: 0 auto;">
            <tr>
                <td>
                    Nome da disciplina:
                </td>
                <td>
                    <input type="text" name="nome_disciplina">
                </td>
            </tr>
            <tr>
                <td>
                    Código da disciplina:
                </td>
                <td>
                    <input type="text" name="codigo_disciplina">
                </td>
            </tr>
            <tr>
                <td>
                    Data de início:
                </td>
                <td>
                    <input type="date" name="data_inicio">
                </td>
            </tr>
            <tr>
                <td>
                    Data de fim:
                </td>
                <td>
                    <input type="date" name="data_fim">
                </td>
            </tr>
            <tr>
                <td>
                    Número de aulas semanais:
                </td>
                <td>
                    <select id="classes" class="dhx_cal_today_button" style="width:35px" name="classes" onChange="Script:showClassOption();">
                        <option>0</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                    </select> 
                </td>
            </tr>
        </table>
        <br>
        <div id="class_div">
        </div>
        <button type="submit" class="dhx_cal_today_button" value="Submit" onclick="Script:newCourse();Script:showDiv('user_div');">Enviar</button>
    </div>


    <div id="user_div" style='width:15%; height:100%;float:right;text-align:center;'>
        <img src="assets/images/colabdario.png" width="150px">
        <div id="user_info" style="margin: 0 0;float:left">
            
        </div>
        <div id='user_id_div' style='display:none'>
            <input type='text' name='user_id_field'>
        </div>
        
        <div style="float:bottom;">
        <button type='submit' class="dhx_cal_today_button" style="width:150px;height:32px;" value='Nova Disciplina' name='new_course' onclick="Script:showDiv('course_div')">Nova Disciplina</button><br>
        <br>
        <button type='submit' class="dhx_cal_today_button" style="width:150px;height:35px;" value='Pesquisar Disciplina' name='search_course' onclick="Script:showDiv('search_div')">Pesquisar Disciplina</button><br>
        <br>
        </div>
    </div>
    <div id="scheduler_here" class="dhx_cal_container" style='width:85%; height:100%; float:left'>
        <div class="dhx_cal_navline">
            <div class="dhx_cal_prev_button">&nbsp;</div>
            <div class="dhx_cal_next_button">&nbsp;</div>
            <div class="dhx_cal_today_button"></div>
            <div class="dhx_cal_date"></div>
            <div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
            <div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
            <div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
        </div>
        <div class="dhx_cal_header">
        </div>
        <div class="dhx_cal_data">
        </div>
    </div>
    </body>
</html>
