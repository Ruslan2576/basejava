<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="image/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            ${contactEntry.key.toHtml(contactEntry.value)}<br/>
        </c:forEach>
    </p>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <c:if test="${sectionEntry.key == 'PERSONAL' || sectionEntry.key == 'OBJECTIVE' || sectionEntry.key == 'ACHIEVEMENT' || sectionEntry.key == 'QUALIFICATIONS'}">
            <h3>${sectionEntry.key.title}</h3>
            <div>
                    ${sectionEntry.key.toHtml(sectionEntry.value)}
            </div>
        </c:if>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>