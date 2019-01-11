<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>All Users</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/script.js"/>"></script>

        <script>
            $($.ajax({
                url: './cc',
                method: 'GET',
                dataType:'json',
                success: function (data) {
                    $('#country').append('<option value=\"\" disabled selected>--Select country--</option>');
                    $('#city').append('<option value=\"\" disabled selected>--Select city--</option>');
                    $.each(data.countries, function (k, v) {
                        var opt_data = "<option value=\"" + v.name +"\">" + v.name + "</option>";
                        $(opt_data).appendTo('#country');
                    })
                }
            }));
        </script>

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">User App</h1>
            <div class="media">
                <div class="media-right">
                    <h3 class="text-right">Hello! You're logged in as <mark>${login}</mark></h3>
                    <img src="<c:url value="/images/img_avatar1.jpg"/>" class="media-object" style="width:60px"/>
                </div>

            </div>
            <h4 class="text-center">Add a new user</h4>
            <form action="${pageContext.servletContext.contextPath}/list?action=add" method="post">
                <div class="form-group">
                    <label>Name:</label>
                    <input class="form-control" id="name" placeholder="Enter name" name="name">
                </div>
                <div class="form-group">
                    <label>Login:</label>
                    <input class="form-control" id="login" placeholder="Enter login" name="login">
                </div>
                <div class="form-group">
                    <label>E-mail:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter e-mail" name="email">
                </div>
                <div class="form-group">
                    <label for="country">Country:</label>
                    <select class="form-control" id="country" name="country" onchange="load(this)"></select>
                </div>
                <div class="form-group">
                    <label for="city">City:</label>
                    <select class="form-control" id="city" name="city"></select>
                </div>
                <div class="form-group">
                    <label>Comments:</label>
                    <textarea class="form-control" rows="3" id="comments" name="comments" placeholder="Enter comments"></textarea>
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input class="form-control" id="password" placeholder="Enter password" name="password">
                </div>
                <div class="form-group">
                    <label for="role">Role:</label>
                    <select class="form-control" id="role" name="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.name}">${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="button" class="btn btn-outline-success" onclick="if (validate() === true) this.form.submit()" id="btn_add">Add</button>
            </form>
            <form action="${pageContext.servletContext.contextPath}/logout" method="post">
                <input type="submit" class="btn btn-outline-warning" value="Exit">
            </form>
        </div>
        <div class="container-fluid">
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
                                <form action="${pageContext.servletContext.contextPath}/update?id=${user.id}" method="post">
                                    <button class="btn btn-outline-primary">Update</button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.servletContext.contextPath}/list?action=delete&id=${user.id}" method="post">
                                    <button class="btn btn-outline-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>