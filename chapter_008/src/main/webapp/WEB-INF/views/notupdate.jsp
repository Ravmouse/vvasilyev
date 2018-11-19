<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Warning</title>
</head>
<body>
    <h2 align="center" style="color: darkred">Warning!</h2>
    <h4 align="center">Nothing to update, please select a user first or create a new one!</h4>
    <table align="center">
        <tr>
            <td>
                <form action="${pageContext.servletContext.contextPath}/">
                    <input type="submit" value="Show all users"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>