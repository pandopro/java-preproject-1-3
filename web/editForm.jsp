<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pando
  Date: 28.05.2020
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="/edit" method="post">
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Edit form # <b><%= request.getParameter("id") %>
            </b>!</h2></caption>
            <tr>
                <th>ID</th>
                <th>
                    <input type="hidden" name="id" value="<%=request.getParameter("id")%>" readonly="true"/>
                </th>

            </tr>
            <th>Name</th>
            <th>

                <input type="text" name="name"/>
            </th>
            </tr>
            <tr>
                <th>Email</th>
                <th>
                    <input type="text" name="email"/>
                </th>
            </tr>
            <tr>
                <th>Country</th>
                <th>
                    <input type="text" name="country"/>
                </th>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
