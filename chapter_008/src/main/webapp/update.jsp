<%@ page import="ru.job4j.h2http.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update the user</title>
</head>
<body>
    <h2 align="center" style="color: darkred">Update the user</h2>
    <%final User user = (User) request.getAttribute("user");%>
    <table align="center">
        <form action="<%=request.getContextPath()%>/list?action=update&id=<%=user.getId()%>" method="post">
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" value="<%=user.getName()%>"></td>
            </tr>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login" value="<%=user.getLogin()%>"></td>
            </tr>
            <tr>
                <td>E-mail: </td>
                <td><input type="text" name="email" value="<%=user.getEmail()%>"></td>
            </tr>
            <tr>
                <td>Date of creation: </td>
                <td><input type="text" name="createDate" value="<%=user.getCreateDate()%>"></td>
            </tr>
            <tr>
                <td></td>
                <td align="center"><input type="submit" value="Update"></td>
            </tr>
        </form>
    </table>
</body>
</html>