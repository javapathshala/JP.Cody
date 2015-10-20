<%@ page contentType="text/html" errorPage="/WEB-INF/branding/jsp/error.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/WEB-INF/branding/jspf/head.jspf" />
        <link rel="stylesheet" href="app/common/css/validationEngine.jquery.css" type="text/css" media="all" />
        <link rel="stylesheet" href="app/common/css/login.css" type="text/css" media="all" />
        <script src="app/common/scripts/jquery.validationEngine-en.js"></script>
        <script src="app/common/scripts/jquery.validationEngine.js"></script>
        <script src="app/common/scripts/login.js"></script>
    </head>
    <body>
        <div class="container">    
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-xs-8 col-xs-offset-2">                    
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h1>${pageContext.servletContext.servletContextName}</h1>
                    </div>
                    <div class="panel-body" >
                        <div class="col-xs-5">
                            <div>
                                <img src="branding/common/images/jp_logo_symbol.png" alt="Java Pathshala logo"/>
                            </div>
                            <div id="clientLogo">
                                <img src="branding/themes/${sessionScope['branding']}/images/logo.png" />
                            </div>
                        </div>
                        <div class="col-xs-7">
                            <form id="login_form" action="j_security_check" method="post">
                                <div class="form-group">
                                    <input id="j_username" name="j_username" type="text" class="form-control" placeholder="Enter your username"/>
                                </div>
                                <div class="form-group">
                                    <input id="j_password" name="j_password" type="password" class="form-control" placeholder="Enter your password"/>
                                </div>
                                <div class="form-group">
                                    <button id="login_button" type="submit" class="btn btn-warning">Login</button>
                                </div>
                            </form>
                            <div class="form-group">
                                <a href="mailto:${initParam['SUPPORT-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - Trouble Logging In"><span class="fa fa-envelope-o"></span> Need help?</a>
                            </div>
                            <div class="form-group">
                                <a href="mailto:${initParam['USER-ACCESS-EMAIL']}?subject=${pageContext.servletContext.servletContextName} - User Access Request&body=Service Desk,%0A%0APlease open a request for Voice Development to create a CMO Development Services portal profile for me.%0A%0AWorkstation Username: %0ARelated Client: %0ARequired Portal Modules (e.g. Reports, Control Points, etc): %0AJustification: %0A%0A%0A"><span class="fa fa-envelope-o"></span> Request an account</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
