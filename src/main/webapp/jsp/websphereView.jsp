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
<link rel="stylesheet" href='<%=renderResponse.encodeURL(renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.CSS_FOLDER + "estilos.css")%>' type="text/css">
<link rel="stylesheet" href='<%=renderResponse.encodeURL(renderRequest.getContextPath()	+ tutorial.websphere.TutorialPortlet.CSS_FOLDER + "bootstrap.min.css")%>' type="text/css">

<script src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.JS_FOLDER + "jquery-3.3.1.min.js")%>'></script>
<script src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.JS_FOLDER + "bootstrap.min.js")%>'></script>
<script src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.JS_FOLDER + "websphere.js")%>'></script>

<script>
   var resourceURL = '<portlet:resourceURL/>';
</script>

<div id="contenido">
   <h1>Página para el modo VIEW</h1>

   <form action="<portlet:resourceURL/>" method="POST" id="formResource">
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
         <input class="form-control" name="<%=TutorialPortlet.FIELD_EDAD%>" type="number"
            value="<%=sessionBean.getEdad()%>"
         />
      </div>
      <div class="form-group">
         <label for="<%=TutorialPortlet.FIELD_EMAIL%>" name="">Email</label>
         <input class="form-control" name="<%=TutorialPortlet.FIELD_EMAIL%>" type="email"
            value="<%=sessionBean.getEmail()%>"
         />
      </div>
      <input type="submit" class="btn btn-primary" value="Datos ficticios" />
   </form>
   <div class="card">
      <div class="card-body">
         <form id="formResourceFile" enctype="multipart/form-data" method="post">
            <input type="file" name="file" accept="text/plain" />
            <input type="submit" class="btn btn-primary" value="Subir perfil" />
         </form>
         <a class="btn btn-secondary" href="<portlet:resourceURL/>?modo=2" target="_blank">Descargar perfil ficticio</a>
      </div>
   </div>

   <%@include file="modos.html"%>

</div>