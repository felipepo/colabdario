<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<title>ColabDário</title>

	<script src="codebase/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
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

    <body onload="init();">

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
    
    <div id="course_div" style='width:100%; height:100%; display:none'>
        Nome da disciplina:   <input type="text" name="nome_disciplina"><br>
        Código da disciplina: <input type="text" name="codigo_disciplina"><br>
        Data de início:  <input type="date" name="data_inicio"><br>
        Data de fim:  <input type="date" name="data_fim"><br>
        Número de aulas semanais:   <select id="classes" name="classes" onChange="Script:showClassOption();">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                        <option>6</option>
                                        <option>7</option>
                                    </select> <br>
                                    <div id="class_div">
                                    </div>
        <button type="submit" value="Submit" onclick="Script:newCourse();Script:showDiv('user_div');">Enviar</button>
    </div>


    <div id="user_div" style='width:15%; height:100%; float:left;'>
        USUÁRIO VIRÁ AQUI</br></br></br>
        LYANG</br>
        MATHEUS
        <button type='submit' value='Nova Disciplina' name='new_course' onclick="Script:showDiv('course_div')">Nova Disciplina</button>
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
