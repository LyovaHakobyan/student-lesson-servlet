<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        h1, h2 {
            text-align: center;
        }

        h2 {
            margin-top: 7%;
        }

        a {
            color: green;
        }

        form {
            margin-top: 5%;
            text-align: center;
        }

        label {
            font-size: 30px;
            display: block;
            margin-top: 3%;
            margin-bottom: 1%;
        }

        input {
            width: 64%;
            padding: 8px;
            margin-bottom: 12px;
            box-sizing: border-box;
            text-align: center;
            font-size: 20px;
        }

        button {
            background-color: #30f34c;
            margin-top: 3%;
            border: none;
            width: 24%;
            height: 5%;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background-color: green;
            transform: scale(1.1);
        }

    </style>
</head>
<body>

<h1>Login to Your Account</h1>

<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
<p style="color:red; text-align: center; font-size: 20px"><%=msg%>
</p>
<%
    }
    session.removeAttribute("msg");
%>

<form action="login" method="post">
    <label for="email">Mail:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <button type="submit">Login</button>
</form>
<h2>Not registered? <a href="register">Register here</a></h2>
</body>
</html>
