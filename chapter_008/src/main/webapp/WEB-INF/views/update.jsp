<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update the user</title>
</head>
<body>
    <h2 align="center" style="color: darkred">Update the user</h2>
    <c:set var="currentUser" value="${user}"/>
    <table align="center">
        <form action="${pageContext.servletContext.contextPath}/?action=update&id=${currentUser.id}" method="post">
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" value="${currentUser.name}" size="30"></td>
            </tr>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login" value="${currentUser.login}" size="30"></td>
            </tr>
            <tr>
                <td>E-mail: </td>
                <td><input type="text" name="email" value="${currentUser.email}" size="30"></td>
            </tr>
            <tr>
                <td>Create date: </td>
                <td><input type="text" name="createDate" value="${currentUser.createDate}" disabled size="30"></td>
            </tr>
            <tr>
                <td>Comments: </td>
                <td><input type="text" name="comments" value="${currentUser.comments}" size="30"></td>
            </tr>
            <tr>
                <td></td>
                <td align="center"><input type="submit" value="Update"></td>
            </tr>
        </form>
    </table>
</body>
</html>