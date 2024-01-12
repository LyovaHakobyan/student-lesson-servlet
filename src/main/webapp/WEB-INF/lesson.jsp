<%@ page import="com.example.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
    <style>
        body {
            display: flex;
            justify-content: center;
        }

        .left {
            width: 50%;
        }

        .right {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 50%;
        }

        table {
            width: 80%;
            margin: 50px auto;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }

        td {
            padding: 15px;
            text-align: left;
            border: 1px solid black;
        }

        th {
            padding: 15px;
            text-align: left;
        }

        tbody {
            background-color: #c1f3c3;
        }
        table button {
            background-color: #016501;
            color: white;
        }
        table button:hover {
            transform: scale(1.1);
            cursor: pointer;
        }
        form {
            margin: 0 auto;
            width: 70%;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
        }

        .sbm {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        .sbm:hover {
            background-color: green;
            transform: scale(1.1);
        }
        .ps {
            color: black;
            font-size: 30px;
            margin-left: 10%;
        }
    </style>
</head>
<body>

<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("allLessons");
%>
<div class="left">
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Duration</th>
            <th>Lecturer Name</th>
            <th>Price</th>
        </tr>
        <%
            if (lessons != null) {
                for (Lesson lesson : lessons) {
        %>
        <tr>
            <td><%=lesson.getId()%>
            </td>
            <td><%=lesson.getName()%>
            </td>
            <td><%=lesson.getDuration()%>
            </td>
            <td><%=lesson.getLecturerName()%>
            </td>
            <td><%=lesson.getPrice()%>
            </td>
            <td><a href="deleteLesson?id=<%=lesson.getId()%>">
                <button>Delete</button>
            </a>
            </td>
                <% }}
    %>
    </table>
</div>

<div class="right">
    <form action="addLesson" method="post" autocomplete="off">
        <label for="name">Name
            <input type="text" name="name" id="name">
        </label>
        <label for="duration">Duration
            <input type="number" name="duration" placeholder="Duration In Minutes" id="duration" min="0">
        </label>
        <label for="lecName">Lecturer Name
            <input type="text" name="lecturerName" id="lecName">
        </label>
        <label for="price">Price
            <input type="number" name="price" id="price" min="0">
        </label>
        <input type="submit" value="Send" class="sbm">
    </form>
    <p><a href="index" class="ps"><i>Back To Main Menu</i></a></p>
</div>

</body>
</html>
