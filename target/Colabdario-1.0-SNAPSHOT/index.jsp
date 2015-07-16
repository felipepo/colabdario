<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="codebase/index.js"></script>
        <title>ColabDário</title>
    </head>
    <body>
        <div id="login_div">
            Login: <input type="text" name="login"><br>
            Senha: <input type="text" name="senha"><br>
            <button type="submit" value="Submit">Entrar</button><br>
            <br>
            <br>
            <br>
            Não cadastrado? <br>
            <a href="http://localhost:8080/colabdario/calendar.jsp">
                <button type="submit" value="Submit">Cadastrar</button><br></a>
        </div>
        
        <div id="cadastro">
            Nome:   <input type="text" name="nome"><br>
            e-mail: <input type="text" name="email"><br>
            Login:  <input type="text" name="login"><br>
            Senha:  <input type="password" name="senha"><br>
            <button type="submit" value="Submit" onclick="Script:SignUp()">Enviar</button><br>
        </div>
    </body>
</html>
