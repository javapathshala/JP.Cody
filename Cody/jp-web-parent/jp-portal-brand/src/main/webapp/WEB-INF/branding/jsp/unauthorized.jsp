<%@ page contentType="text/html" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/WEB-INF/branding/jspf/head.jspf" />
    </head>
    <body>
        <div class="container">    
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-xs-8 col-xs-offset-2">                    
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h1>Contact Center Services Portal</h1>
                    </div>
                    <div class="panel-body" >
                        <div class="col-xs-1">
                            <h1><span class="fa fa-exclamation-circle"></span></h1>
                        </div>
                        <div class="col-xs-11">
                            <div style="padding-top: 8px;">
                                <h3>Invalid username and/or password.</h3>
                            </div>
                            <div>
                                <a id="errorpage_backlink" href="javascript:void(0);" onclick="history.back();" title="Back to previous page."><h2><span class="fa fa-arrow-left"></span></h2></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
