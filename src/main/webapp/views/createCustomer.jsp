<%--
  Created by IntelliJ IDEA.
  User: johntoan98gmail.com
  Date: 29/07/2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Table Create Customer</h2>
    <form action="/customer?action=create" method="post">
    <table class="table">
        <tbody>
        <tr>
            <td><input placeholder="nhập id" name="id"></td>
            <td><input placeholder="nhập tên" name="name"></td>
            <td><input placeholder="nhập email" name="email"></td>
            <td><input placeholder="nhập địa chỉ" name="address"></td>
        </tr>

        </tbody>
    </table>
        <button type="submit" class="btn btn-success">Create Customer</button>

    </form>
</div>

</body>
</html>
