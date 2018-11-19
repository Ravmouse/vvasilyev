<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create a new user</title>
</head>
<body>
    <h2 align="center" style="color: darkred">Create a new user</h2>
    <table align="center">
        <form action="<%=request.getContextPath()%>/create?action=add" method="post">
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Login: </td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td>E-mail: </td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>Comments: </td>
                <td><input type="text" name="comments"></td>
            </tr>
            <tr>
                <td></td>
                <td align="center"><input type="submit" value="Add"></td>
            </tr>
        </form>
        <form action="<%=request.getContextPath()%>/">
            <tr>
                <td></td>
                <td align="center"><input type="submit" value="Show all users"></td>
            </tr>
        </form>
    </table>
</body>
</html>