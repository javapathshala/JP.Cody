<%@ page contentType="text/html" errorPage="/WEB-INF/jsp/branding/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="applicationList" scope="request" type="java.util.List<com.jp.portal.listener.PortalApplication>" />
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4>Available Applications</h4>
    </div>
    <div class="panel-body">
        <ul class="nav nav-list">
            <c:choose>
                <c:when test="${empty applicationList}">
                    <li>No Applications Available</li>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${applicationList}" var="app" varStatus="status">
                        <li>
                            <a href="${app.contextPath}" title="${app.description}">
                                <span class="fa fa-chevron-right"></span>
                                ${app.displayName}
                            </a>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>