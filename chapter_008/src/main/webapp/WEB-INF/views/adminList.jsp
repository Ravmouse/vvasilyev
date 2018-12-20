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
                                <td><c:out value="${user.role.name}"/></td>
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
                        <tr><td style="border: hidden"></td><td style="border: hidden"><h4>Add a new user</h4></td></tr>
                        <form action="${pageContext.servletContext.contextPath}/?action=add" method="post">
                            <tr>
                                <td style="border: hidden">Name: </td>
                                <td style="border: hidden"><input name="name"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Login: </td>
                                <td style="border: hidden"><input name="login"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">E-mail: </td>
                                <td style="border: hidden"><input name="email"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Comments: </td>
                                <td style="border: hidden"><input name="comments"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Password: </td>
                                <td style="border: hidden"><input name="password"></td>
                            </tr>
                            <tr>
                                <td style="border: hidden">Role: </td>
                                <td style="border: hidden" align="left">
                                    <select name="role">
                                        <c:forEach items="${roles}" var="role">
                                            <option value="${role.name}">${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: hidden"></td>
                                <td align="center" style="border: hidden"><input type="submit" value="Add"></td>
                            </tr>
                        </form>
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