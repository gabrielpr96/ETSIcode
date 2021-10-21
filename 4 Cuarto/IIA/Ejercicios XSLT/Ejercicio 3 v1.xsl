<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<sql>
			INSERT INTO CDS (<xsl:value-of select="catalog/cd[1]/title"/>, <xsl:value-of select="catalog/cd[1]/price"/>)
		</sql>
	</xsl:template>
</xsl:stylesheet>
