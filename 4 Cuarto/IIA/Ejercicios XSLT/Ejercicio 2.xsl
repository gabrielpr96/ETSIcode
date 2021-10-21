<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<catalogo>
			<xsl:for-each select="catalog/cd[price &lt; 10]">
				<cd>
					<titulo><xsl:value-of select="title"/></titulo>
					<artista><xsl:value-of select="artist"/></artista>
					<precio><xsl:value-of select="price"/></precio>
				</cd>
			</xsl:for-each>
		</catalogo>
	</xsl:template>
</xsl:stylesheet>
