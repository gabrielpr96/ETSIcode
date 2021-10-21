<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<catalog>
			<xsl:for-each select="catalog/cd">
				<xsl:if test="price &lt; 10">
					<cd>
						<title><xsl:value-of select="title"/></title>
						<artist><xsl:value-of select="artist"/></artist>
						<country><xsl:value-of select="country"/></country>
						<company><xsl:value-of select="company"/></company>
						<price><xsl:value-of select="price"/></price>
						<year><xsl:value-of select="year"/></year>
					</cd>
				</xsl:if>
			</xsl:for-each>
		</catalog>
	</xsl:template>
</xsl:stylesheet>
