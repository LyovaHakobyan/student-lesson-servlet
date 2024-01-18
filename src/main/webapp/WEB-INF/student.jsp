<%@ page import="com.example.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
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
            justify-content: center;
            flex-direction: column;
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

        th {
            background-color: #4CAF50;
            color: white;
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
    List<Lesson> lessons = (List<Lesson>) session.getAttribute("allLessons");
    List<Student> students = (List<Student>) session.getAttribute("allStudents");
%>
<div class="left">
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Age</th>
            <th>Image</th>
            <th>Lesson Name</th>
        </tr>
        <%
            if (students != null) {
                for (Student student : students) {
        %>
        <tr>
            <td><%=student.getId()%>
            </td>
            <td><%=student.getName()%>
            </td>
            <td><%=student.getSurname()%>
            </td>
            <td><%=student.getEmail()%>
            </td>
            <td><%=student.getAge()%>
            </td>
            <td><img src="downloadImg?imgName=<%=student.getImgName()%>" alt="No Img" style="width: 60px;">
            </td>
                <%if(student.getLesson() != null) {
                %>
            <td><%=student.getLesson().getName()%>
            </td>
                <%} else { %>
            <td>No Lesson
            </td>
                <% } %>
            <td><a href="deleteStudent?id=<%=student.getId()%>">
                <button>Delete</button>
            </a>
            </td>
                <% }}
    %>
    </table>
</div>

<div class="right">
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
    <form action="addStudent" method="post" autocomplete="off" enctype="multipart/form-data">
        <label for="name">Name
            <input type="text" name="name" id="name">
        </label>
        <label for="surname">Surname
            <input type="text" name="surname" id="surname">
        </label>
        <label for="email">Email
            <input type="email" name="email" id="email">
        </label>
        <label for="age">Age
            <input type="number" name="age" id="age" min="0">
        </label>
        <label for="img">Image
            <input type="file" name="img" id="img">
        </label>
        <label for="lesson">Lesson
            <select name="lessonId" id="lesson">
                <% if (lessons != null) {
                    for (Lesson lesson : lessons) {
                %>
                <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </label>
        <input type="submit" value="Send" class="sbm">
    </form>
    <p><a href="home" class="ps"><i>Back To Home</i></a></p>
</div>

</body>
</html>
