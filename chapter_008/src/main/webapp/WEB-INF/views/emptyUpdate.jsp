<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Warning</title>
</head>
<body>
    <h2 align="center" style="color: darkred">Warning!</h2>
    <h4 align="center">Unable to update the user containing at least one empty field!</h4>
    <table align="center">
        <tr>
            <td>
                <form action="${pageContext.servletContext.contextPath}/">
                    <input type="submit" value="Back"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>