<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>ColabDário</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">      
        <link rel="stylesheet" href="/resources/demos/style.css">
        <link rel="stylesheet" href="codebase/quill.base.css">
        <link rel="stylesheet" href="codebase/quill.snow.css">
        <link rel="stylesheet" type="text/css" href="codebase/advanced.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!--        <script src="//code.jquery.com/jquery-1.10.2.js"></script>-->
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="codebase/quill.js"></script>
        <script type="text/javascript" src="codebase/websocket.js"></script>
        <script>
        $(function() {
          $( "#datepicker" ).datepicker({ dateFormat: "dd/mm/yy" });
          $( "#datepicker2" ).datepicker({ dateFormat: "dd/mm/yy" });
        });
        </script>

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
    
    <div id="search_div" style="text-align:center;width:100%;height:100%;float:left;">
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
    
    <script>
            scheduler.attachEvent("onDblClick", function (id, e){
                            
                            var eventObj = scheduler.getEvent(id);
                            var login = document.getElementsByName("login")[0].value;
                            showDiv('redirect_div');
                            document.getElementById('class_info').innerHTML = "<table><tr><td> Matéria: " + eventObj.text + "</td></tr><tr><td> Aluno: " + login + "</td></tr></table><br>";
            })
        </script>
    
     <div id="redirect_div" style='width:15%; height:100%;float:right;text-align:center;'>
        <input type="image" src="assets/images/colabdario.png" style="display:inline-block;margin: 0 0;" width="150px" onclick="showDiv('user_div')">
        <h1>Diário de Aula</h1>
        <div id="class_info" style="float:top;text-align:left;">
            
        </div>
        <h>Adicionar Arquivo:</h><br><br><br>
        <input type="button" value="voltar" onclick="showDiv('user_div')">
    </div>
    
    <div id="course_div" style='text-align:center;width:100%; height:100%; display:none;float:left;'>
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
                    <input type="text" id="datepicker" name="data_inicio">
                </td>
            </tr>
            <tr>
                <td>
                    Data de fim:
                </td>
                <td>
                    <input type="text" id="datepicker2" name="data_fim">
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
        <div id="user_info" style="float:top;">
            
        </div>
        <div id='user_id_div' style='display:none'>
            <input type='text' name='user_id_field'>
        </div>
        
        <div style="float:bottom;">
        <button type='submit' class="dhx_cal_today_button" style="width:150px;height:32px;" value='Nova Disciplina' name='new_course' onclick="Script:showDiv('course_div')">Nova Disciplina</button><br>
        <br>
        <button type='submit' class="dhx_cal_today_button" style="width:150px;height:35px;" value='Pesquisar Disciplina' name='search_course' onclick="Script:showDiv('search_div')">Pesquisar Disciplina</button><br>
        <br>
        <a class="dhx_cal_today_button" style="width:150px;height:35px;" href='colaborative.jsp'> Espaço Colaborativo</a>
        <br>
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

    <div id="colaborative_div" style='width:85%; height:100%; float:left'>
        <div class="advanced-wrapper">
            <div class="toolbar-container"><span class="ql-format-group">
                    <select title="Font" class="ql-font">
                        <option value="sans-serif" selected>Sans Serif</option>
                        <option value="Georgia, serif">Serif</option>
                        <option value="Monaco, 'Courier New', monospace">Monospace</option>
                    </select>
                    <select title="Size" class="ql-size">
                        <option value="10px">Small</option>
                        <option value="13px" selected>Normal</option>
                        <option value="18px">Large</option>
                        <option value="32px">Huge</option>
                    </select></span><span class="ql-format-group"><span title="Bold" class="ql-format-button ql-bold"></span><span class="ql-format-separator"></span><span title="Italic" class="ql-format-button ql-italic"></span><span class="ql-format-separator"></span><span title="Underline" class="ql-format-button ql-underline"></span></span><span class="ql-format-group">
                    <select title="Text Color" class="ql-color">
                        <option value="rgb(0, 0, 0)" selected></option>
                        <option value="rgb(230, 0, 0)"></option>
                        <option value="rgb(255, 153, 0)"></option>
                        <option value="rgb(255, 255, 0)"></option>
                        <option value="rgb(0, 138, 0)"></option>
                        <option value="rgb(0, 102, 204)"></option>
                        <option value="rgb(153, 51, 255)"></option>
                        <option value="rgb(255, 255, 255)"></option>
                        <option value="rgb(250, 204, 204)"></option>
                        <option value="rgb(255, 235, 204)"></option>
                        <option value="rgb(255, 255, 204)"></option>
                        <option value="rgb(204, 232, 204)"></option>
                        <option value="rgb(204, 224, 245)"></option>
                        <option value="rgb(235, 214, 255)"></option>
                        <option value="rgb(187, 187, 187)"></option>
                        <option value="rgb(240, 102, 102)"></option>
                        <option value="rgb(255, 194, 102)"></option>
                        <option value="rgb(255, 255, 102)"></option>
                        <option value="rgb(102, 185, 102)"></option>
                        <option value="rgb(102, 163, 224)"></option>
                        <option value="rgb(194, 133, 255)"></option>
                        <option value="rgb(136, 136, 136)"></option>
                        <option value="rgb(161, 0, 0)"></option>
                        <option value="rgb(178, 107, 0)"></option>
                        <option value="rgb(178, 178, 0)"></option>
                        <option value="rgb(0, 97, 0)"></option>
                        <option value="rgb(0, 71, 178)"></option>
                        <option value="rgb(107, 36, 178)"></option>
                        <option value="rgb(68, 68, 68)"></option>
                        <option value="rgb(92, 0, 0)"></option>
                        <option value="rgb(102, 61, 0)"></option>
                        <option value="rgb(102, 102, 0)"></option>
                        <option value="rgb(0, 55, 0)"></option>
                        <option value="rgb(0, 41, 102)"></option>
                        <option value="rgb(61, 20, 102)"></option>
                    </select><span class="ql-format-separator"></span>
                    <select title="Background Color" class="ql-background">
                        <option value="rgb(0, 0, 0)"></option>
                        <option value="rgb(230, 0, 0)"></option>
                        <option value="rgb(255, 153, 0)"></option>
                        <option value="rgb(255, 255, 0)"></option>
                        <option value="rgb(0, 138, 0)"></option>
                        <option value="rgb(0, 102, 204)"></option>
                        <option value="rgb(153, 51, 255)"></option>
                        <option value="rgb(255, 255, 255)" selected></option>
                        <option value="rgb(250, 204, 204)"></option>
                        <option value="rgb(255, 235, 204)"></option>
                        <option value="rgb(255, 255, 204)"></option>
                        <option value="rgb(204, 232, 204)"></option>
                        <option value="rgb(204, 224, 245)"></option>
                        <option value="rgb(235, 214, 255)"></option>
                        <option value="rgb(187, 187, 187)"></option>
                        <option value="rgb(240, 102, 102)"></option>
                        <option value="rgb(255, 194, 102)"></option>
                        <option value="rgb(255, 255, 102)"></option>
                        <option value="rgb(102, 185, 102)"></option>
                        <option value="rgb(102, 163, 224)"></option>
                        <option value="rgb(194, 133, 255)"></option>
                        <option value="rgb(136, 136, 136)"></option>
                        <option value="rgb(161, 0, 0)"></option>
                        <option value="rgb(178, 107, 0)"></option>
                        <option value="rgb(178, 178, 0)"></option>
                        <option value="rgb(0, 97, 0)"></option>
                        <option value="rgb(0, 71, 178)"></option>
                        <option value="rgb(107, 36, 178)"></option>
                        <option value="rgb(68, 68, 68)"></option>
                        <option value="rgb(92, 0, 0)"></option>
                        <option value="rgb(102, 61, 0)"></option>
                        <option value="rgb(102, 102, 0)"></option>
                        <option value="rgb(0, 55, 0)"></option>
                        <option value="rgb(0, 41, 102)"></option>
                        <option value="rgb(61, 20, 102)"></option>
                    </select><span class="ql-format-separator"></span>
                    <select title="Text Alignment" class="ql-align">
                        <option value="left" selected></option>
                        <option value="center"></option>
                        <option value="right"></option>
                        <option value="justify"></option>
                    </select></span><span class="ql-format-group"><span title="Link" class="ql-format-button ql-link"></span><span class="ql-format-separator"></span><span title="Image" class="ql-format-button ql-image"></span><span class="ql-format-separator"></span><span title="List" class="ql-format-button ql-list"></span></span></div>
            <div class="editor-container"></div>
        </div>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.js"></script>
        <script type="text/javascript" src="codebase/events.js"></script>
    </div>    
    </body>
</html>
