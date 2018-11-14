<%@ page import="ru.job4j.h2http.User" %>
<%@ page import="ru.job4j.h2http.UserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <h2 align="center" style="color: darkred">All Users</h2>
    <table align="center">
        <%for (User user : UserServlet.logic.findAll()) {%>
        <tr>
            <td>
                <%=user%>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/update?id=<%=user.getId()%>" method="post">
                    <input type="submit" value="Update" style="margin-top: 25%"/>
                </form>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/list?action=delete&id=<%=user.getId()%>" method="post">
                    <input type="submit" value="Delete" style="margin-top: 25%"/>
                </form>
            </td>
        </tr>
        <%}%>
        <tr align="center">
            <td>
                <form action="<%=request.getContextPath()%>/create">
                    <input type="submit" value="Create a new user"/>
                </form>
            </td>
        </tr>
    </table>
</body>
</html>