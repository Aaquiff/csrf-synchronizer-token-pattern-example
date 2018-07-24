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
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <%@include file='header.jsp'%>
    </head>
    <body>
        <br>

        <div class="container">
            <h2>Add User</h2>
            <p>Adding a new user to the application</p>
            <form id='frmAddUser'>
                <div class="form-group">
                    <input class="form-control" type="text" id="username" name="username" placeholder="username"/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" id="password" name="password" placeholder="password"/>
                </div>
                <input class="form-control" type="text" id="token" name="token" hidden="true"/>
                <button class="btn btn-primary mb-2" value="submit">Add User</button>
            </form>
        </div>

        <script>
            $('#frmAddUser').submit((e) => {
                var url = './User';

                $.ajax({
                    type: "POST",
                    url: url,
                    data: $("#frmAddUser").serialize(), // serializes the form's elements.
                    success: function (data)
                    {
                        alert('User added');
                    },
                    error: function (err) {
                        alert("Adding user failed");
                    }
                });

                e.preventDefault();

            });

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
