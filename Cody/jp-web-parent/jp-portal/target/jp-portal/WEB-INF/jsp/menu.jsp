<%@ page contentType="text/html" errorPage="/WEB-INF/branding/jsp/error.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/WEB-INF/branding/jspf/head.jspf" />
    </head>
    <body>
        <div class="container border">
            <jsp:include page="/WEB-INF/branding/jspf/page-banner.jspf" />
        </div>
        <div class="container border">
            <div class="row">
                <div class="col-xs-4 sidebar">
                    <jsp:include page="/app/servlet/list/applications" />
                </div>
                <div class="col-xs-8 main">
                    <div class="panel panel-primary">
                        <div class="panel-heading clearfix stacked">
                            <div class="pull-left">
                                <h4>Welcome to the ${pageContext.servletContext.servletContextName}</h4>
                            </div>
                        </div>
                        <div class="panel-body">
                            <p>
                                This portal provides access to CMO tools and reports.
                            </p>
                            <p>
                                Listed to the left are all the applications and reports you currently have access to. To request access to another application or report, please
                                <a class="undecorated_link" href="mailto:${initParam['USER-ACCESS-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - User Access Request&body=Service Desk,%0A%0APlease open a request for Voice Development to add the following access to my CMO Development Services portal profile.%0A%0AWorkstation Username: <%=request.getRemoteUser()%>%0ARelated Client: %0ARequired Portal Modules (e.g. Reports, Control Points, etc): %0AJustification: %0A%0A%0A">
                                    send an email
                                </a>
                                to have the Support Center open a request.
                            </p>
                            <p>
                                If you have any questions or suggestions, please
                                <a class="undecorated_link" href="mailto:${initParam['SUPPORT-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - Question or Suggestion">
                                    send an email
                                </a>
                                to the CMO Development Services team.
                            </p>
                            <p>
                                <a class="undecorated_link" href="mailto:${initParam['USER-ACCESS-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - User Access Request&body=Service Desk,%0A%0APlease open a request for Voice Development to add the following access to my CMO Development Services portal profile.%0A%0AWorkstation Username: <%=request.getRemoteUser()%>%0ARelated Client: %0ARequired Portal Modules (e.g. Reports, Control Points, etc): %0AJustification: %0A%0A%0A">
                                    <span class="fa fa-envelope-o"></span> Email Support Center access request
                                </a>
                            </p>
                            <p>
                                <a class="undecorated_link" href="mailto:${initParam['SUPPORT-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - Question or Suggestion">
                                    <span class="fa fa-envelope-o"></span> Email CMO Development Services
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
