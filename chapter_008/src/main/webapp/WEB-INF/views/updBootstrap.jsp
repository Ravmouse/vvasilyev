<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Update the user</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/script.js"/>"></script>

        <c:set var="userCountry" value="${user.country}"/>  <%--Для использования тегов JSTL в JavaScript--%>
        <c:set var="userCity" value="${user.city}"/>        <%--Для использования тегов JSTL в JavaScript--%>
        <script>
            var userCountry = '${userCountry}';             <%--Верхняя переменная присваивается здесь--%>
            var userCity = '${userCity}';                   <%--Верхняя переменная присваивается здесь--%>
            $($.ajax({
                url: './cc',
                method: 'GET',
                dataType:'json',
                success: function (data) {
                    $('#country').append('<option value=\"\" disabled>--Select country--</option>');
                    $('#city').append('<option value=\"\" disabled>--Select city--</option>');
                    $.each(data.countries, function (k, v) {
                        <%--Если название страны из JSON равно названию страны юзера--%>
                        if (v.name === userCountry) {
                            var sel_country = "<option selected value=\"" + userCountry +"\">" + userCountry + "</option>";
                            <%--то значение добавляется, и оно становится selected--%>
                            $(sel_country).appendTo('#country');
                            <%--Тогда для этой страны нужны все соответствующие города из JSON--%>
                            $.each(v.cities, function (key, val) {
                                <%--Если название города из JSON равно названию города юзера--%>
                                if (val === userCity) {
                                    var sel_city = "<option selected value=\"" + val +"\">" + val +  "</option>";
                                    <%--то значение добавляется, и оно становится selected--%>
                                    $(sel_city).appendTo('#city');
                                } else {
                                    <%--остальные значения просто добавляются--%>
                                    var simp_city = "<option value=\"" + val +"\">" + val +  "</option>";
                                    $(simp_city).appendTo('#city');
                                }
                            })
                        } else {
                            <%--остальные значения просто добавляются--%>
                            var sim_country = "<option value=\"" + v.name +"\">" + v.name + "</option>";
                            $(sim_country).appendTo('#country');
                        }
                    })
                }
            }));
        </script>

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">User App</h1>
            <h4 class="text-center">Update the user</h4>
            <form action="${pageContext.servletContext.contextPath}/list?action=update&id=${user.id}" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input class="form-control" id="name" value="${user.name}" name="name">
                </div>
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input class="form-control" id="login" value="${user.login}" name="login">
                </div>
                <div class="form-group">
                    <label for="email">E-mail:</label>
                    <input type="email" class="form-control" id="email" value="${user.email}" name="email">
                </div>
                <div class="form-group">
                    <label for="createDate">Create date:</label>
                    <input class="form-control" id="createDate" value="${user.createDate}" name="createDate" disabled>
                </div>
                <div class="form-group">
                    <label for="country">Country:</label>
                    <select class="form-control" id="country" name="country" onchange="load(this)"></select>
                </div>
                <div class="form-group">
                    <label for="city">City:</label>
                    <select class="form-control" id="city" name="city" ></select>
                </div>
                <div class="form-group">
                    <label for="comments">Comments:</label>
                    <textarea class="form-control" rows="3" id="comments" name="comments">${user.comments}</textarea>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input class="form-control" id="password" value="${user.password}" name="password">
                </div>
                <div class="form-group">
                    <%--Строка роль есть как у администратора, так и у юзера--%>
                    <label for="role">Role:</label>
                    <%--Выбрать--%>
                    <c:choose>
                        <%--Если роль юзера не равна User (Administrator, Anonymous, Moderator)--%>
                        <c:when test="${loginRole.name != 'User'}">
                            <select class="form-control" id="role" name="role">
                                    <%--Раскрывающийся список появляется с тем значением, которое присутствует у юзера--%>
                                <option selected value="${user.role.name}">${user.role.name}</option>
                                    <%--Это - цикл по тем значениям, которые содержатся в БД в таблице roles--%>
                                <c:forEach items="${roles}" var="role">
                                    <%--Для каждого значения из таблицы roles, если это значение не равно значению юзера--%>
                                    <c:if test="${role.name != user.role.name}">
                                        <%--То это значение поместить в раскрывающийся список, а повторяющееся значение не помещать--%>
                                        <option value="${role.name}">${role.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </c:when>
                        <%--Если роль юзера равна User--%>
                        <c:when test="${loginRole.name == 'User'}">
                            <select class="form-control" id="role" name="role" disabled>
                                <option selected value="${user.role.name}">${user.role.name}</option>
                            </select>
                            <%--Элемент нужен только для того, чтобы присвоить ему имя и значение неактивного select--%>
                            <input type="hidden" name="role" value="${user.role.name}"/>
                        </c:when>
                    </c:choose>
                </div>
                <button type="button" class="btn btn-outline-primary" onclick="if (validate() === true) this.form.submit()">Update</button>
            </form>
        </div>
    </body>
</html>