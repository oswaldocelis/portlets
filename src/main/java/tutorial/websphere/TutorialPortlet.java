package tutorial.websphere;

import java.io.*;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import javax.portlet.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tutorial.websphere.TutorialPortletSessionBean;

/**
 * A sample portlet based on GenericPortlet
 */
public class TutorialPortlet extends GenericPortlet {

	public static final String JSP_FOLDER = "/jsp/";
	public static final String JS_FOLDER = "/script/";
	public static final String CSS_FOLDER = "/css/";

	public static final String VIEW_JSP = "websphereView.jsp";

	public static final String SESSION_BEAN = "websphereSessionBean";

	public static final String FIELD_NOMBRE = "setNombre";
	public static final String FIELD_APELLIDO = "setApellido";
	public static final String FIELD_DOCUMENTO = "setDocumento";
	public static final String FIELD_EMAIL = "setEmail";
	public static final String FIELD_EDAD = "setEdad";
	public static final String FORM_SUBMIT = "websphereFormSubmit";

	public void init() throws PortletException {
		super.init();
		System.out.println("init");
	}

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("doView");
		response.setContentType(request.getResponseContentType());
		TutorialPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean == null) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER + VIEW_JSP);
		rd.include(request, response);
	}

	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
		System.out.println("serveResource");
		int modo = Integer.parseInt(request.getParameter("modo"));
		PrintWriter writer;
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String resultado = "";
		if (modo == 1){
			TutorialPortletSessionBean bean = (TutorialPortletSessionBean) request.getPortletSession().getAttribute(SESSION_BEAN);
			if (bean == null){
				bean = new TutorialPortletSessionBean();
			}
			resultado = gson.toJson(bean);
		}else if (modo == 2){
			String datos = new BufferedReader(new InputStreamReader(request.getPortletInputStream())).lines().collect(Collectors.joining("\n"));
			TutorialPortletSessionBean bean = gson.fromJson(datos, TutorialPortletSessionBean.class);
			JsonObject json = new JsonObject();
			json.addProperty("mensaje", "Bean actualizado satisfactoriamente");
			resultado = json.toString();
			request.getPortletSession().setAttribute(SESSION_BEAN, bean);
		}														
		try {			
			writer = response.getWriter();
			writer.write(resultado);
			writer.close();
		} catch (Exception e) {
		}
		
	}

	public void destroy() {
		System.out.println("destroy");
	}

	private static TutorialPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if (session == null) {
			return null;
		}
		TutorialPortletSessionBean sessionBean = (TutorialPortletSessionBean) session.getAttribute(SESSION_BEAN);
		if (sessionBean == null) {
			sessionBean = new TutorialPortletSessionBean();
			session.setAttribute(SESSION_BEAN, sessionBean);
		}
		return sessionBean;
	}

}
