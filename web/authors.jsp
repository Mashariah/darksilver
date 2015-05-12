<%-- 
    Document   : authors
    Created on : Feb 28, 2015, 9:16:45 PM
    Author     : kelli
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styler/design.css"  />
        <script src="js/jquery-2.1.3.min.js"></script>
        <%@include file="templates/cheader.html" %>
        <%@include  file="templates/main-menu.html" %>
    </head>
    <body>
        <div class="author-menu-bar">
            <div class="author-menu-item"><a href="new_author.jsp">New</a></div>
            <div class="author-menu-item"><a href="#">Edit</a></div>
            <div class="author-menu-item"><a href="#">Email</a></div>
            <div class="author-menu-item"><a href="#">Delete</a></div>
        </div>
        <div id="author-success-popup"><img src="resc/icons/ic_action_tick.png" height="25px" width="25px">new author saved</div>
        <table class="authors-table">
            <tr style="background: #9999ff; font-weight:700;"><td></td><td>Author ID</td><td>Name</td>
                <td>Affiliation </td><td>Email</td><td>Phone</td></tr>
                <c:forEach  items="${author_list}" var="author" end="14">
                <tr>
                    <td><input type="checkbox" name="action" value="OFF" /></td>
                    <td ><c:out value="${author.authorId}"></c:out></td>
                    <td ><c:out value="${author.surname}, ${author.initials}"></c:out></td>
                    <td ><c:out value="${author.affiliation}"></c:out></td>
                    <td ><c:out value="${author.email}"></c:out></td>
                    <td ><c:out value="${author.phone}"></c:out></td>
                    </tr>
            </c:forEach>
            <tr >
                <td colspan="6" style="text-align: right;">
                <%-- previous link exept for first page--%>
                <c:if test="${currentPage != 1}">
                    <strong><a href="main.sx?page=${currentPage - 1} " style="text-decoration: none;">Previous</a></strong>
                </c:if>
                <%-- Display next link--%>
                <c:if test="${currentPage lt  totalPages}" >
                    <strong><a href="main.sx?page=${currentPage +1} " style="text-decoration: none;">Next</a></strong>
                </c:if>
                </td>
                <%--<c:out value="${totalPages}" ></c:out>--%>
        </tr>
    </table>
                <c:if test="${author_save != null}">
                        <script>
    $('document').ready(showSucess());
    
    function showSucess(){
        $('#author-success-popup').slideDown(1500);
        $('#author-success-popup').fadeOut(4000);
    }
</script>
                </c:if>
</body>

<!--If page request contains attribute 'save_success' its a forward from new author... create pop up with success
message-->


</html>
