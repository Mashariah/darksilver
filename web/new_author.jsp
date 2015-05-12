<%-- 
    Document   : new_author
    Created on : Feb 17, 2015, 8:38:08 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Author</title>
        <link type="text/css" href="styler/design.css" rel="stylesheet"/>
        <script src="js/jquery-2.1.3.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <%@include file="templates/cheader.html" %>
        <%@include  file="templates/menu.html" %>
    </head>
     <body>
        <div class="form-layout">
            <form action="new_author" method="post" id="frm_author">
                <label>Surname</label>
                <input class="required author_txfield" type="text" name="tx_surname" id="txsurname" title="surname missing"/><br>
                <label >Initials</label>
                <input type="text" class="author_txfield required"   name="tx_initials" id="txinitials" title="initials missing"/><br>
                <label>Affiliation</label>
                <input class="author_txfield required"  type="text" name="tx_affiliation" id="txaffiliation" title="affiliation missing"/><br>
                <label>Other</label>
                <input type="text" class="author_txfield required" name="tx_surname" id="txaffiliation"/><br>
                <label>Email</label>
                <input class="author_txfield required email"  type="email" name="tx_email" id="txemail"/><br>
                    <label>Phone</label>
                    <input type="tel" class="author_txfield"   name="tx_phone" id="txphone"/><br>
                    <button type="submit" name="bt_submit" class="btn">Save Author</button>
            </form>
        </div>
            <%@include file="templates/footer.html" %>
    </body>
    <script>
        //wrap script in document.ready to ensure it runs after the page's HTML loads
        $(document).ready(function(){
            $('#frm_author').validate();
});
    </script>
</html>
