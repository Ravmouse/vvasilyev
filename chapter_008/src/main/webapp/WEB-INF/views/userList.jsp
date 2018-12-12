<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>All Users</title>
        <style>
            table {
                border: 1px solid darkslateblue;
                text-align: center;
            }
            tr {  border: 1px solid darkslateblue;  }
            td {
                border: 1px solid darkslateblue;
                vertical-align: middle;
            }
        </style>
    </head>
    <body>
        <h2 align="center" style="color: darkred">User App</h2>
        <h4 align="center" style="color: darkblue">Hello! You're logged in as ${login}</h4>
        <table style="border: hidden">
            <tr>
                <td style="border: hidden">
                    <table style="border: hidden">
                        <h4>All users</h4>
                        <tr style="background-color: darksalmon">
                            <td>ID</td>
                            <td>Name</td>
                            <td>Login</td>
                            <td>E-mail</td>
                            <td>Create date</td>
                            <td>Comments</td>
                            <td>Password</td>
                            <td>Role</td>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.login}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.createDate}"/></td>
                                <td><c:out value="${user.comments}"/></td>
                                <td><c:out value="${user.password}"/></td>
                                <td><c:out value="${user.role}"/></td>
                                <td style="border: hidden">
                                    <%--Поместить кнопку Update только напротив одного юзера--%>
                                    <c:if test="${user.login == login}">
                                        <form action="${pageContext.servletContext.contextPath}/update?id=${user.id}" method="post">
                                            <input type="submit" value="Update"/>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td style="border: hidden; vertical-align: top">
                    <table style="border: hidden">
                        <form action="${pageContext.servletContext.contextPath}/logout" method="post">
                            <tr>
                                <td style="border: hidden"></td>
                                <td align="center" style="border: hidden"><input type="submit" value="Exit"></td>
                            </tr>
                        </form>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>