<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script>
            function validate() {
                var rsl = true;
                var elements = document.querySelectorAll('input');
                for (var i = 0; i < elements.length; i++) {
                    if (elements[i].value === "") {
                        rsl = false;
                        alert('The next field is empty: ' + $(elements[i]).attr('name'));
                        break;
                    }
                }
                return rsl;
            }
        </script>
    </head>
    <body>
        <div class="container col-md-2 col-centered">
            <form action="${pageContext.servletContext.contextPath}/login" method="post">
                <div class="form-group">
                    <label>Login:</label>
                    <input class="form-control" id="login" placeholder="Enter login" name="login">
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
                </div>
                <button type="button" class="btn btn-outline-success" onclick="if (validate() === true) this.form.submit()">Login</button>
            </form>
            <c:if test="${message != ''}">
                <div class="alert" style="color: red">
                    <strong>${message}</strong>
                </div>
            </c:if>
        </div>
    </body>
</html>