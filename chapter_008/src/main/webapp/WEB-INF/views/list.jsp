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
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.id}"/></td>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.login}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><c:out value="${user.createDate}"/></td>
                                <td><c:out value="${user.comments}"/></td>
                                <td style="border: hidden">
                                    <form action="${pageContext.servletContext.contextPath}/update?id=${user.id}" method="post">
                                        <input type="submit" value="Update"/>
                                    </form>
                                </td>
                                <td style="border: hidden">
                                    <form action="${pageContext.servletContext.contextPath}/?action=delete&id=${user.id}" method="post">
                                        <input type="submit" value="Delete"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td style="border: hidden; vertical-align: top">
                    <table style="border: hidden">
                        <h4>Add a new user</h4>
                        <form action="${pageContext.servletContext.contextPath}/?action=add" method="post">
                            <tr>
                                <td style="border: hidden">Name: </td>
                                <td style="border: hidden"><input type="text" name="name"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Login: </td>
                                <td style="border: hidden"><input type="text" name="login"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">E-mail: </td>
                                <td style="border: hidden"><input type="text" name="email"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Comments: </td>
                                <td style="border: hidden"><input type="text" name="comments"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden"></td>
                                <td align="center" style="border: hidden"><input type="submit" value="Add"></td>
                            </tr>
                        </form>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>