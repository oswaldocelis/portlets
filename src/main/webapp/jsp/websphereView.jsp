<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1"
   import="java.util.*,javax.portlet.*,tutorial.websphere.*"
%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<portlet:defineObjects />
<%
	TutorialPortletSessionBean sessionBean = (tutorial.websphere.TutorialPortletSessionBean) renderRequest
			.getPortletSession().getAttribute(tutorial.websphere.TutorialPortlet.SESSION_BEAN);
	PortletURL actionURL = renderResponse.createActionURL();
	actionURL.setParameter("javax.portlet.action", TutorialPortlet.FORM_SUBMIT);
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
   var actionURL = '<portlet:actionURL/>';
   var resourcerURL = '<portlet:resourceURL/>';
</script>

<div id="contenido">
   <h1>Página para el modo VIEW</h1>

   <h3>Uso de processAction() con anotación</h3>
   <form action="<%=actionURL%>" method="POST id="formURL"">
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_NOMBRE%>" name="">Nombre</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_NOMBRE%>" type="text"
            value="<%=sessionBean.getNombre()%>"
         />
      </div>
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_APELLIDO%>" name="">Apellido</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_APELLIDO%>" type="text"
            value="<%=sessionBean.getApellido()%>"
         />
      </div>
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_DOCUMENTO%>" name="">Documento</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_DOCUMENTO%>" type="text"
            value="<%=sessionBean.getDocumento()%>"
         />
      </div>
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_EDAD%>" name="">Edad</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_EDAD%>" type="text"
            value="<%=sessionBean.getEdad()%>"
         />
      </div>
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_EMAIL%>" name="">Email</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_EMAIL%>" type="text" value="<%=sessionBean.getEmail()%>" />
      </div>
      <input type="submit" class="btn btn-primary" name="<%=TutorialPortlet.FORM_SUBMIT%>" />
   </form>

   <%@include file="modos.html"%>

</div>