<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xsi:schemaLocation='http://hcu234.dit.cb:6080/arcgis/services/public/GPB_ESP/MapServer/WFSServer
                http://geo.brest-metropole.fr/ArcGIS/services/public/GPB_ESP/MapServer/WFSServer?request=DescribeFeatureType%26version=2.0.0%26typename=DECHETS_PROPRETE_-_Points_d_apport_volontaire__Pays_de_Brest_ 
                http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/gml/3.2 http://schemas.opengis.net/gml/3.2.1/gml.xsd'
    xmlns:public_GPB_ESP='http://hcu234.dit.cb:6080/arcgis/services/public/GPB_ESP/MapServer/WFSServer'
    xmlns:gml='http://www.opengis.net/gml/3.2'
    xmlns:wfs='http://www.opengis.net/wfs/2.0'
    xmlns:xlink='http://www.w3.org/1999/xlink'
    xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  
  
  <xsl:template match="/">
    <dataBase>
      <brest>


        <dmr>
          <xsl:apply-templates select="wfs:FeatureCollection/gml:member" mode="dmr"/>
        </dmr>


        <recyclable>
          <xsl:apply-templates select="wfs:FeatureCollection/gml:member" mode="recyclable"/>
        </recyclable>


        <verre>
            <xsl:apply-templates select="wfs:FeatureCollection/gml:member" mode="dmr"/>
        </verre>


      </brest>
    </dataBase>
  </xsl:template>

  <xsl:template match="*/gml:member" mode="dmr">
    
    <xsl:if test="*/public_GPB_ESP:OM &gt; 0">

      <xsl:call-template name="extraire_coordonnees"/>

    </xsl:if>
  </xsl:template>

  <xsl:template match="*/gml:member" mode="recyclable">
    <xsl:if test="*/public_GPB_ESP:RECYCLABLE &gt; 0">

      <xsl:call-template name="extraire_coordonnees"/>

    </xsl:if>
  </xsl:template>

  <xsl:template match="*/gml:member" mode="verre">
    <xsl:if test="*/public_GPB_ESP:VERRE &gt; 0">

      <xsl:call-template name="extraire_coordonnees"/>
      
    </xsl:if>
  </xsl:template>

  
  <xsl:template name="extraire_coordonnees">
      <pdt>

    <latitude>
      <xsl:value-of select="substring-before (*/public_GPB_ESP:SHAPE/gml:Point/gml:coordinates,',')" />
    </latitude>
    <longitude>
      <xsl:value-of select="substring-after (*/public_GPB_ESP:SHAPE/gml:Point/gml:coordinates,',')" />
    </longitude>

      </pdt>
  </xsl:template>



</xsl:stylesheet>
