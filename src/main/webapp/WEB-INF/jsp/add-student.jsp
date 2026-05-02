<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
    <style>
        :root {
            --bg: #f4f7fb;
            --panel: #ffffff;
            --text: #1f2937;
            --muted: #667085;
            --line: #d9e1ec;
            --primary: #2563eb;
            --primary-dark: #1d4ed8;
            --danger: #b42318;
            --field: #f8fafc;
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            color: var(--text);
            background: var(--bg);
        }

        .page {
            width: min(760px, calc(100% - 32px));
            margin: 32px auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            gap: 16px;
            align-items: center;
            margin-bottom: 20px;
        }

        h1 {
            margin: 0;
            font-size: 2rem;
            letter-spacing: 0;
        }

        .subtitle {
            color: var(--muted);
            margin: 8px 0 0;
        }

        .panel {
            background: var(--panel);
            border: 1px solid var(--line);
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 12px 32px rgba(31, 41, 55, 0.08);
        }

        .form-grid {
            display: grid;
            gap: 18px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 700;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            min-height: 44px;
            border: 1px solid var(--line);
            border-radius: 8px;
            background: var(--field);
            padding: 0 12px;
            color: var(--text);
            font-size: 1rem;
        }

        input:focus {
            outline: 3px solid #cfe0ff;
            border-color: var(--primary);
            background: #ffffff;
        }

        .course-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 10px;
        }

        .course-option {
            display: flex;
            gap: 10px;
            align-items: flex-start;
            min-height: 46px;
            padding: 12px;
            border: 1px solid var(--line);
            border-radius: 8px;
            background: #ffffff;
        }

        .course-option input {
            margin-top: 2px;
        }

        .error {
            display: block;
            margin-top: 6px;
            color: var(--danger);
            font-size: 0.9rem;
            font-weight: 700;
        }

        .alert-error {
            padding: 12px 14px;
            border-radius: 8px;
            margin-bottom: 16px;
            color: var(--danger);
            background: #fff1f0;
            border: 1px solid #ffc9c3;
            font-weight: 700;
        }

        .actions {
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
            margin-top: 6px;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 42px;
            padding: 0 16px;
            border-radius: 8px;
            border: 1px solid transparent;
            color: #ffffff;
            background: var(--primary);
            text-decoration: none;
            font-weight: 700;
            cursor: pointer;
            font-size: 1rem;
        }

        .btn:hover {
            background: var(--primary-dark);
        }

        .btn-secondary {
            color: var(--primary);
            background: #ffffff;
            border-color: #bfd0ff;
        }

        .btn-secondary:hover {
            background: #eef4ff;
        }
    </style>
</head>
<body>
<main class="page">
    <div class="header">
        <div>
            <h1>Add Student</h1>
            <p class="subtitle">Create a new student and assign courses.</p>
        </div>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">Back</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert-error">${errorMessage}</div>
    </c:if>

    <section class="panel">
        <form:form method="post"
                   action="${pageContext.request.contextPath}/students"
                   modelAttribute="studentForm"
                   cssClass="form-grid">
            <div>
                <label for="name">Name</label>
                <form:input path="name" id="name" type="text" autocomplete="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>

            <div>
                <label for="email">Email</label>
                <form:input path="email" id="email" type="email" autocomplete="email"/>
                <form:errors path="email" cssClass="error"/>
            </div>

            <div>
                <label>Courses</label>
                <div class="course-grid">
                    <c:forEach var="course" items="${courses}">
                        <label class="course-option" for="course-${course.id}">
                            <form:checkbox path="courseIds" id="course-${course.id}" value="${course.id}"/>
                            <span>${course.title}</span>
                        </label>
                    </c:forEach>
                </div>
            </div>

            <div class="actions">
                <button class="btn" type="submit">Save Student</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">Cancel</a>
            </div>
        </form:form>
    </section>
</main>
</body>
</html>
