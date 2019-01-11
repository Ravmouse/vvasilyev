<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>All Users</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid">
            <h1 class="text-center">User App</h1>
            <h3 class="text-right">Hello! You're logged in as <mark>${login}</mark></h3>
            <table class="table table-striped" id="table">
                <h4 class="text-center">All users</h4>
                <thead>
                    <tr class="text-center">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Login</th>
                        <th>E-mail</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>Create date</th>
                        <th>Comments</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr class="text-center">
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.name}"/></td>
                            <td><c:out value="${user.login}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.country}"/></td>
                            <td><c:out value="${user.city}"/></td>
                            <td><c:out value="${user.createDate}"/></td>
                            <td><c:out value="${user.comments}"/></td>
                            <td><c:out value="${user.password}"/></td>
                            <td><c:out value="${user.role.name}"/></td>
                            <td>
                                <%--Поместить кнопку Update только напротив одного юзера--%>
                                <c:if test="${user.login == login}">
                                    <form action="${pageContext.servletContext.contextPath}/update?id=${user.id}" method="post">
                                        <button class="btn btn-outline-primary">Update</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form action="${pageContext.servletContext.contextPath}/logout" method="post">
                <input type="submit" value="Exit" class="btn btn-outline-warning">
            </form>
        </div>
    </body>
</html>