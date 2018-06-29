package tutorial.websphere;

import java.io.*;
import javax.portlet.*;

import com.google.gson.Gson;

/**
 * A sample portlet based on GenericPortlet
 */
public class TutorialPortletAux extends GenericPortlet implements EventPortlet {

	public static final String JSP_FOLDER = "/jsp/";
	public static final String JS_FOLDER = "/script/";
	public static final String CSS_FOLDER = "/css/";

	public static final String VIEW_JSP = "websphereViewAux.jsp";

	public static final String SESSION_BEAN = "websphereSessionBean";

	public static final String FORM_SUBMIT = "websphereFormSubmit";

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");

	public void init() throws PortletException {
		super.init();
		System.out.println("initAux");
	}

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		System.out.println("doViewAux");
		response.setContentType(request.getResponseContentType());
		TutorialPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean == null) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}
		String beanParam = request.getParameter("bean-param");		
		System.out.println(beanParam);
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(JSP_FOLDER + VIEW_JSP);
		rd.include(request, response);
	}

	public void processEvent(EventRequest request, EventResponse response) throws PortletException, IOException {
		System.out.println("processEventAux");
		Event event = request.getEvent();
		System.out.println("Event Name " + event.getName());
		String eventValue = (String) event.getValue();
		Gson gson = new Gson();		
		request.getPortletSession().setAttribute(SESSION_BEAN, gson.fromJson(eventValue, TutorialPortletSessionBean.class));
		System.out.println("Event Value " + eventValue);
	}

	public void destroy() {
		System.out.println("destroyAux");
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
