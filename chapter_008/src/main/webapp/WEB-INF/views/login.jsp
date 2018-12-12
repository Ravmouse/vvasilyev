<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
        <style>
            table {
                margin: 0 auto;
                margin-top: 13%;
                border: hidden;
            }
            td {
            }
            input[type=text] {
                size: 20em;
            }
            div {
                background-color: indianred;
            }
        </style>
    </head>
    <body>
        <table>
            <tr>
                <td></td>
                <td>
                    <c:if test="${message != ''}">
                        <div>
                            <c:out value="${message}"/>
                        </div>
                    </c:if>
                </td>
            </tr>
            <form action="${pageContext.servletContext.contextPath}/login" method="post">
                <tr>
                    <td>Login: </td>
                    <td><input name="login"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center"><input type="submit" value="Login"></td>
                </tr>
            </form>
        </table>
    </body>
</html>