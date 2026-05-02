<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Students</title>
    <style>
        :root {
            --bg: #f4f7fb;
            --panel: #ffffff;
            --text: #1f2937;
            --muted: #667085;
            --line: #d9e1ec;
            --primary: #2563eb;
            --primary-dark: #1d4ed8;
            --success: #0f766e;
            --danger: #b42318;
            --chip: #eef6ff;
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
            width: min(1180px, calc(100% - 32px));
            margin: 32px auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            gap: 16px;
            align-items: center;
            margin-bottom: 20px;
        }

        h1, h2 {
            margin: 0;
            letter-spacing: 0;
        }

        h1 {
            font-size: 2rem;
        }

        h2 {
            font-size: 1.25rem;
            margin-bottom: 14px;
        }

        .subtitle {
            color: var(--muted);
            margin: 8px 0 0;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 40px;
            padding: 0 14px;
            border-radius: 8px;
            border: 1px solid transparent;
            color: #ffffff;
            background: var(--primary);
            text-decoration: none;
            font-weight: 700;
            white-space: nowrap;
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

        .alert {
            padding: 12px 14px;
            border-radius: 8px;
            margin-bottom: 16px;
            font-weight: 700;
        }

        .alert-success {
            color: var(--success);
            background: #ecfdf3;
            border: 1px solid #b6f0d1;
        }

        .alert-error {
            color: var(--danger);
            background: #fff1f0;
            border: 1px solid #ffc9c3;
        }

        .panel {
            background: var(--panel);
            border: 1px solid var(--line);
            border-radius: 8px;
            overflow: hidden;
            margin-bottom: 24px;
            box-shadow: 0 12px 32px rgba(31, 41, 55, 0.08);
        }

        .panel-header {
            padding: 18px 20px;
            border-bottom: 1px solid var(--line);
        }

        .table-wrap {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 760px;
        }

        th, td {
            padding: 14px 16px;
            text-align: left;
            border-bottom: 1px solid var(--line);
            vertical-align: top;
        }

        th {
            color: #344054;
            background: #f8fafc;
            font-size: 0.85rem;
            text-transform: uppercase;
        }

        tr:last-child td {
            border-bottom: 0;
        }

        .chips {
            display: flex;
            flex-wrap: wrap;
            gap: 8px;
        }

        .chip {
            display: inline-flex;
            align-items: center;
            min-height: 28px;
            padding: 0 10px;
            border-radius: 999px;
            color: #174ea6;
            background: var(--chip);
            border: 1px solid #cce3ff;
            font-size: 0.86rem;
            font-weight: 700;
        }

        .empty {
            padding: 24px;
            color: var(--muted);
        }

        @media (max-width: 700px) {
            .page {
                width: min(100% - 20px, 1180px);
                margin: 20px auto;
            }

            .header {
                align-items: flex-start;
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
<main class="page">
    <div class="header">
        <div>
            <h1>Student Management</h1>
            <p class="subtitle">Create, view, and update students with course enrollments.</p>
        </div>
        <a class="btn" href="${pageContext.request.contextPath}/students/new">Add Student</a>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-error">${errorMessage}</div>
    </c:if>

    <section class="panel">
        <div class="panel-header">
            <h2>Students</h2>
        </div>

        <c:choose>
            <c:when test="${empty students}">
                <div class="empty">No students found.</div>
            </c:when>
            <c:otherwise>
                <div class="table-wrap">
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Courses</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.id}</td>
                                <td>${student.name}</td>
                                <td>${student.email}</td>
                                <td>
                                    <div class="chips">
                                        <c:forEach var="course" items="${student.courses}">
                                            <span class="chip">${course.title}</span>
                                        </c:forEach>
                                    </div>
                                </td>
                                <td>
                                    <a class="btn btn-secondary"
                                       href="${pageContext.request.contextPath}/students/edit/${student.id}">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

    <section class="panel">
        <div class="panel-header">
            <h2>Student and Course Join Query</h2>
            <p class="subtitle">Rows returned by the JPQL inner join query.</p>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Course Title</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${studentCoursePairs}">
                    <tr>
                        <td>${row.studentName}</td>
                        <td>${row.courseTitle}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
</body>
</html>
