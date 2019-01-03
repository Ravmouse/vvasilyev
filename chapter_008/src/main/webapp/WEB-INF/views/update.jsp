<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Update the user</title>
    </head>
    <body>
        <h2 align="center" style="color: darkred">Update the user</h2>
        <table align="center">
            <form action="${pageContext.servletContext.contextPath}/list?action=update&id=${user.id}" method="post">
                <tr>
                    <td>Name: </td>
                    <td><input name="name" value="${user.name}" size="30"></td>
                </tr>
                <tr>
                    <td>Login: </td>
                    <td><input name="login" value="${user.login}" size="30"></td>
                </tr>
                <tr>
                    <td>E-mail: </td>
                    <td><input name="email" value="${user.email}" size="30"></td>
                </tr>
                <tr>
                    <td>Create date: </td>
                    <td><input name="createDate" value="${user.createDate}" disabled size="30"></td>
                </tr>
                <tr>
                    <td>Comments: </td>
                    <td><input name="comments" value="${user.comments}" size="30"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input name="password" value="${user.password}" size="30"></td>
                </tr>
                <tr>
                    <%--Строка роль есть как у администратора, так и у юзера--%>
                    <td>Role: </td>
                    <td>
                        <%--Выбрать--%>
                        <c:choose>
                            <%--Если роль юзера не равна User (Administrator, Anonymous, Moderator)--%>
                            <c:when test="${loginRole.name != 'User'}">
                                <select name="role">
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
                                <select name="role" disabled>
                                    <option selected value="${user.role.name}">${user.role.name}</option>
                                </select>
                                <%--Элемент нужен только для того, чтобы присвоить ему имя и значение неактивного select--%>
                                <input type="hidden" name="role" value="${user.role.name}"/>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center"><input type="submit" value="Update"></td>
                </tr>
            </form>
        </table>
    </body>
</html>