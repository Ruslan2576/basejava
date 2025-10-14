<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:if test="${type != 'EXPERIENCE' && type != 'EDUCATION'}">
                <dl>
                    <dt>${type.title}</dt>
                    <dd>
                        <c:set var="section" value="${resume.getSection(type)}" />
                        <c:choose>
                            <c:when test="${type == 'PERSONAL' || type == 'OBJECTIVE'}">
                                <input type="text" name="${type.name()}" size="50"
                                       value="${fn:replace(fn:replace(section.toString(), '[', ''), ']', '')}">
                            </c:when>
                            <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                                <c:set var="sectionText" value="${fn:replace(fn:replace(section.toString(), '[', ''), ']', '')}" />
                                <textarea name="${type.name()}" rows="5" cols="50">${fn:replace(sectionText, ', ', '')}</textarea>
                            </c:when>
                        </c:choose>
                    </dd>
                </dl>
            </c:if>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>