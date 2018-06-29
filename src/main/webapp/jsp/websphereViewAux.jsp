<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1"
   import="java.util.*,javax.portlet.*,tutorial.websphere.*"
%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<%
	TutorialPortletSessionBean sessionBean = (tutorial.websphere.TutorialPortletSessionBean) renderRequest
			.getPortletSession().getAttribute(tutorial.websphere.TutorialPortlet.SESSION_BEAN);
%>
<link rel="stylesheet"
   href='<%=renderResponse.encodeURL(
					renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.CSS_FOLDER + "estilos.css")%>'
   type="text/css"
>
<link rel="stylesheet"
   href='<%=renderResponse.encodeURL(renderRequest.getContextPath()
					+ tutorial.websphere.TutorialPortlet.CSS_FOLDER + "bootstrap.min.css")%>'
   type="text/css"
>

<script
   src='<%=renderResponse.encodeURL(renderRequest.getContextPath()
					+ tutorial.websphere.TutorialPortlet.JS_FOLDER + "jquery-3.3.1.min.js")%>'
></script>
<script
   src='<%=renderResponse.encodeURL(renderRequest.getContextPath()
					+ tutorial.websphere.TutorialPortlet.JS_FOLDER + "bootstrap.min.js")%>'
></script>

<script>
   var resourceURL = '<portlet:resourceURL/>';
</script>

<div id="contenido">
   <h1>P�gina para el modo VIEW</h1>

   <div class="form-group">
      <label for="<%=TutorialPortlet.FIELD_NOMBRE%>" name="">Nombre</label>
      <input class="form-control" name="<%=TutorialPortlet.FIELD_NOMBRE%>" type="text"
         value="<%=sessionBean.getNombre()%>" disabled
      />
   </div>
   <div class="form-group">
      <label for="<%=TutorialPortlet.FIELD_APELLIDO%>" name="">Apellido</label>
      <input class="form-control" name="<%=TutorialPortlet.FIELD_APELLIDO%>" type="text"
         value="<%=sessionBean.getApellido()%>" disabled
      />
   </div>
   <div class="form-group">
      <label for="<%=TutorialPortlet.FIELD_DOCUMENTO%>" name="">Documento</label>
      <input class="form-control" name="<%=TutorialPortlet.FIELD_DOCUMENTO%>" type="text"
         value="<%=sessionBean.getDocumento()%>" disabled
      />
   </div>
   <div class="form-group">
      <label for="<%=TutorialPortlet.FIELD_EDAD%>" name="">Edad</label>
      <input class="form-control" name="<%=TutorialPortlet.FIELD_EDAD%>" type="text" value="<%=sessionBean.getEdad()%>"
         disabled
      />
   </div>
   <div class="form-group">
      <label for="<%=TutorialPortlet.FIELD_EMAIL%>" name="">Email</label>
      <input class="form-control" name="<%=TutorialPortlet.FIELD_EMAIL%>" type="text"
         value="<%=sessionBean.getEmail()%>" disabled
      />
   </div>
</div>