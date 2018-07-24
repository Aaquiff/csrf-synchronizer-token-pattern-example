<%-- 
    Document   : adduser
    Created on : Jul 24, 2018, 4:39:01 PM
    Author     : aaralk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
        <script src="jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <form action="User" method="POST">
            <input type="text" id="username" name="username" placeholder="username"/>
            <input type="password" id="password" name="password" placeholder="password"/>
            <input type="text" id="token" name="token" hidden="true"/>
            <button value="submit">Add User</button>
        </form>
        
        <script>
        console.log('Getting token');
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var responseObject = JSON.parse(this.responseText);
                $('#token').val(responseObject.token);
            }
        };
        xhttp.open("POST", "./Token", true);
        xhttp.send();
    </script>
    </body
    
</html>
