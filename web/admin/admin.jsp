<%-- 
    Document   : admin
    Created on : Feb 11, 2015, 4:54:36 PM
    Author     : kelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Home</title>
        <link type="text/css" href="../styler/design.css" rel="stylesheet"/>
        <script src="../js/jquery-2.1.3.min.js"></script>
    </head>
    <body>
        <%@include file="../templates/cheader.html" %>

        <div class="login_panel">
            <form action="login_control" method="POST">
                <div class="title_bar">Admin Login</div>
                <div style="color: #ff0000; font-size: small; font-family: sans-serif; margin:3px;">${login_error}</div>
                <input type="text" class="text_box" name="tx_admin_user" placeholder="email address"/><br>
                <input type="password" class="text_box" name="tx_admin_password" placeholder="password"/><br>
                <input  type="submit" class="btn" id="btn_login" value="Login"/><br>
            </form>
        </div>
        <%@include file="../templates/footer.html" %>
    </body>
    <script>
        $(document).ready(function (){
        $('.login-menu').hide();
        });
    </script>
</html>
