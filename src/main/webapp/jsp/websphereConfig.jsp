<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="javax.portlet.*,tutorial.websphere.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                
<portlet:defineObjects/>
<link rel="stylesheet" href='<%= renderResponse.encodeURL(renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.CSS_FOLDER + "estilos.css") %>' type="text/css">

<div id="contenido">
	<h1>Página para el modo CONFIG</h1>
	
	<%@include  file="modos.html" %>
</div>