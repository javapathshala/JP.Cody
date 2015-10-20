<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml" exclude-result-prefixes="jp" version="1.0"
                xmlns:jp="http://jp.com/status/response"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" encoding="UTF-8" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" indent="yes" media-type="text/html" method="html" />
    <xsl:template name="colorCoding">
        <xsl:param name="statusCode" />
        <xsl:param name="text" />
        <div class="status_block">
            <div class="status_color status_{$statusCode}"></div>
            <div class="status_text">                
                <xsl:value-of disable-output-escaping="yes" select="$text" />
            </div>
            <div class="clear_floats"></div>
        </div>
    </xsl:template>
    <xsl:template match="jp:application">
        <html>
            <head>
                <title>
                    <xsl:value-of select="@name" /> Status
                </title>
                <style>
                    body {font-family:'Arial'; font-size:10pt; font-weight:normal;}
                    .aspect_container {float:left;}
                    .aspect_message {float:left; margin:3px 0; line-height:18px; width:275px;}
                    .aspect_status {float:left; width:280px;}
                    .clear_floats {clear:both;}
                    .component_container {border-bottom:1px solid #808080; border-top:1px solid #808080; margin:8px 0;}
                    .component_status {float:left; width:280px;}
                    .status_block {margin:3px 0;}
                    .status_color {border:1px solid silver; float:left; height:16px; margin-right:5px; width:20px;}
                    .status_ERROR {background-color:orangeRed;}
                    .status_FATAL {background-color:darkRed;}
                    .status_OK {background-color:green;}
                    .status_WARN {background-color:yellow;}
                    .status_ {background-color:blue;}
                    .status_text {float:left; line-height:18px; width:250px; word-wrap:break-word;}
                    #application_container {margin:0 auto; width:850px;}
                    #application_heading {background-color:#F8951F; color:white; font-size:16pt; font-weight:bold; height:24px; padding:5px 0; margin:0 auto 5px; text-align:center; width:850px;}
                    #aspect_message_heading {width:275px;}
                    #aspect_status_heading {width: 280px;}
                    #component_heading {background-color:#808080; color:white; font-size:12pt; font-weight:bold; height:24px; padding-top:5px;}
                    #component_heading div {float:left; margin:0 2px;}
                    #component_status_heading {width: 280px;}
                </style>
                <meta http-equiv="refresh" content="60"></meta>
            </head>
            <body>
                <div id="application_heading">
                    <xsl:value-of select="@name" /> Status
                </div>
                <div id="application_container">
                    <div id="component_heading">
                        <div id="component_status_heading">Component Name</div>
                        <div id="aspect_status_heading">Status</div>
                        <div id="aspect_message_heading">Messages</div>
                        <div class="clear_floats"></div>
                    </div>
                    <xsl:for-each select="component">
                        <xsl:sort select="@name" />
                        <div class="component_container">
                            <div class="component_status">
                                <xsl:call-template name="colorCoding">
                                    <xsl:with-param name="statusCode" select="@status" />
                                    <xsl:with-param name="text" select="@name" />
                                </xsl:call-template>
                            </div>
                            <div class="aspect_container">
                                <xsl:for-each select="aspect">
                                    <xsl:sort select="@name" />
                                    <div class="aspect_status">
                                        <xsl:call-template name="colorCoding">
                                            <xsl:with-param name="statusCode" select="@status" />
                                            <xsl:with-param name="text" select="@name" />
                                        </xsl:call-template>
                                    </div>
                                    <div class="aspect_message">
                                        <xsl:choose>
                                            <xsl:when test="not(string(message))">
                                                <xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="message" />
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </div>
                                    <div class="clear_floats"></div>
                                </xsl:for-each>
                            </div>
                            <div class="clear_floats"></div>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
