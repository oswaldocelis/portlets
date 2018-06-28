package tutorial.websphere;

import java.io.*;
import java.lang.reflect.Method;
import javax.portlet.*;
import com.google.gson.Gson;
import tutorial.websphere.TutorialPortletSessionBean;

/**
 * A sample portlet based on GenericPortlet
 */
public class TutorialPortlet extends GenericPortlet{

	public static final String JSP_FOLDER = "/jsp/"; 
	public static final String JS_FOLDER = "/script/"; 
	public static final String CSS_FOLDER = "/css/"; 

	public static final String VIEW_JSP = "websphereView.jsp"; 
	public static final String EDIT_JSP = "websphereEdit.jsp"; 
	public static final String HELP_JSP = "websphereHelp.jsp"; 
	public static final String CONFIG_JSP = "websphereConfig.jsp"; 

	public static final String SESSION_BEAN = "websphereSessionBean"; 

	public static final String FIELD_NOMBRE = "setNombre";
	public static final String FIELD_APELLIDO = "setApellido";
	public static final String FIELD_DOCUMENTO = "setDocumento";
	public static final String FIELD_EMAIL = "setEmail";
	public static final String FIELD_EDAD = "setEdad";
	public static final String FORM_SUBMIT = "websphereFormSubmit"; 

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");

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

	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("doDispatch");
		if (!WindowState.MINIMIZED.equals(request.getWindowState())) {
			PortletMode mode = request.getPortletMode();
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				response.setContentType(request.getResponseContentType());
				PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER + CONFIG_JSP);
				rd.include(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("doEdit");
		response.setContentType(request.getResponseContentType());
		TutorialPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean == null) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER + EDIT_JSP);
		rd.include(request, response);
	}

	protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("doHelp");
		response.setContentType(request.getResponseContentType());
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER + HELP_JSP);
		rd.include(request, response);
	}

	@ProcessAction(name = FORM_SUBMIT)
	public void customProcessAction(ActionRequest request, ActionResponse response)
			throws PortletException, java.io.IOException {
		System.out.println("processAction: " + FORM_SUBMIT);
		TutorialPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean != null) {
			request.getParameterMap().forEach((k, v) -> {
				if (request.getParameter(k) != null) {
					try {
						Method method = TutorialPortletSessionBean.class.getMethod(k, String.class);
						method.invoke(sessionBean, request.getParameter(k));
					} catch (Exception e) {
					}
				}
			});			
		}
	}	

	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
		System.out.println("serveResource");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		String data = "{}";
		Gson gson = new Gson();
		TutorialPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean != null) {
			data = gson.toJson(sessionBean).toString();
		}
		try {
			writer = response.getWriter();
			writer.write(data);
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
