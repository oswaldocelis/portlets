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
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.2/angular.min.js"></script>
<script
   src='<%=renderResponse.encodeURL(
					renderRequest.getContextPath() + tutorial.websphere.TutorialPortlet.JS_FOLDER + "websphere.js")%>'
></script>


<div id="contenido" ng-app="app" ng-controller="BeanController" data-ng-init="inicializar('<portlet:resourceURL/>')">
   <form ng-submit="enviar('<portlet:resourceURL/>')">
      <div class="form-group">
         <label for="nombre">Nombre</label>
         <input class="form-control" name="nombre" ng-model="bean.nombre" required />
      </div>
      <div class="form-group">
         <label for="apellido">Apellido</label>
         <input class="form-control" name="apellido" ng-model="bean.apellido" required />
      </div>
      <div class="form-group">
         <label for="documento">Documento</label>
         <input class="form-control" name="documento" ng-model="bean.documento" required />
      </div>
      <div class="form-group">
         <label for="email">Email</label>
         <input class="form-control" type="email" name="email" ng-model="bean.email" required />
      </div>
      <div class="form-group">
         <label for="edad">Edad</label>
         <input class="form-control" type="number" name="edad" ng-model="bean.edad" required />
      </div>
      <input type="submit" class="btn btn-primary" value="Enviar formulario" />

   </form>
   <div class="alert alert-success" role="alert" ng-show="mensaje != ''">{{mensaje}}</div>
</div>

