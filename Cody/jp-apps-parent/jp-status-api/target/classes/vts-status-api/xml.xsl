<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml" version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-8" indent="yes" media-type="text/xml" method="xml" version="1.0" />
    <xsl:template match="/">
        <xsl:copy-of select="*" />
    </xsl:template>
</xsl:stylesheet>
