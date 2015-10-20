<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://www.w3.org/1999/xhtml" version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output encoding="UTF-8" media-type="text/plain" method="text" />
    <xsl:template match="/">
        <xsl:value-of select="count(//component[@status='FATAL'])" />|<xsl:value-of select="count(//component[@status='ERROR'])" />|<xsl:value-of select="count(//component[@status='WARN'])" />|<xsl:value-of select="count(//component[@status='OK'])" />
    </xsl:template>
</xsl:stylesheet>
