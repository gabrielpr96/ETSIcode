<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/catalog/cd[1]">
		<sql>
			INSERT INTO CDS (<xsl:value-of select="title"/>, <xsl:value-of select="price"/>)
		</sql>
	</xsl:template>
</xsl:stylesheet>
