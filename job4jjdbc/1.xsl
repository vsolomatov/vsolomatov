<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
      <xsl:text>&#13;</xsl:text>
      <xsl:text disable-output-escaping = 'yes'>&lt;entries&gt;&#13;</xsl:text>
      <xsl:for-each select="entries/entry">
		<xsl:text disable-output-escaping = 'yes'>   &lt;entry field="</xsl:text>
		<xsl:value-of select="field" />
		<xsl:text disable-output-escaping = 'yes'>"/&gt;&#13;</xsl:text>
      </xsl:for-each>
      <xsl:text disable-output-escaping = 'yes'>&lt;/entries&gt;&#13;</xsl:text>
</xsl:template>
</xsl:stylesheet> 
