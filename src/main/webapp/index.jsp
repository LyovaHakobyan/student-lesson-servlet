<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lesson-Student</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .title {
            height: 100px;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .title h1 {
            font-size: 70px;
        }

        .center {
            margin-top: 10%;
            height: 180px;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .center button {
            height: 100%;
            width: 200px;
            color: black;
            font-size: 20px;
            background-color: greenyellow;
            transition: 0.3s;
        }

        .center button:hover {
            transform: scale(1.05);
            background-color: green;
            cursor: pointer;
        }

        .center a {
            height: 50%;
        }

        .empty {
            height: 100%;
            width: 10%;
        }
    </style>
</head>
<body>

<div class="title"><h1>Lesson - Student</h1></div>
<div class="center">
    <a href="login">
        <button>LOGIN</button>
    </a>
    <div class="empty"></div>
    <a href="register">
        <button class="for">REGISTER</button>
    </a>
</div>
</body>
</html>