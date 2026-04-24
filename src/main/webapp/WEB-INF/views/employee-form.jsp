<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Thêm mới nhân viên</title>
    <style>.error { color: red; }</style>
</head>
<body>
<h2>Thêm mới nhân viên</h2>


<form:form action="add-employee" modelAttribute="employee" method="post">

    <div>
        <label>Tên nhân viên:</label><br/>
        <form:input path="name" />
            <%-- 3. ĐÂY LÀ CÁCH JSP HIỂN THỊ LỖI (Thay cho th:errors) --%>
        <form:errors path="name" cssClass="error" />
    </div>

    <br/>

    <div>
        <label>Tuổi:</label><br/>
        <form:input path="age" type="number" />
        <form:errors path="age" cssClass="error" />
    </div>

    <br/>

    <div>
        <label>Email:</label><br/>
        <form:input path="email" />
        <form:errors path="email" cssClass="error" />
    </div>

    <br/>
    <button type="submit">Lưu nhân viên</button>
</form:form>
</body>
</html>