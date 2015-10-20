<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:redirect context="/jp-portal" url="/login">
    <c:param name="service" value="${pageContext.servletContext.contextPath}" />
</c:redirect>
