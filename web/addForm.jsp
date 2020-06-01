<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>create User</title>
</head>
<body>
<c:if test="${user == null}">
<form action="/new" method="post">
    </c:if>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Create user</h2></caption>
            <tr>
            </tr>
            <th>Name</th>
            <th>

                <input type="text" name="name" size="45" />
            </th>
            </tr>
            <tr>
                <th>Email</th>
                <th>

                    <input type="text" name="email" size="45"  />
                </th>
            </tr>
            <tr>
                <th>Country</th>
                <th>

                    <input type="text" name="country" size="45" />
                </th>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
