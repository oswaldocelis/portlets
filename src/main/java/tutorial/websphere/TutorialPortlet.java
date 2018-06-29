package tutorial.websphere;

import java.io.*;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import javax.portlet.*;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.MultipartStream;

import com.google.gson.Gson;
import tutorial.websphere.TutorialPortletSessionBean;

/**
 * A sample portlet based on GenericPortlet
 */
public class TutorialPortlet extends GenericPortlet {

	public static final String JSP_FOLDER = "/jsp/";
	public static final String JS_FOLDER = "/script/";
	public static final String CSS_FOLDER = "/css/";

	public static final String VIEW_JSP = "websphereView.jsp";
	public static final String EDIT_JSP = "websphereEdit.jsp";
	public static final String HELP_JSP = "websphereHelp.jsp";
	public static final String CONFIG_JSP = "websphereConfig.jsp";

	public static final String SESSION_BEAN = "websphereSessionBean";

	public static final String FIELD_NOMBRE = "nombre";
	public static final String FIELD_APELLIDO = "apellido";
	public static final String FIELD_DOCUMENTO = "documento";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_EDAD = "edad";
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

	public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
		System.out.println("serveResource");
		PrintWriter writer;
		String modo = request.getParameter("modo");
		System.out.println("modo: " + modo);
		String data = "{}";
		Gson gson = new Gson();
		TutorialPortletSessionBean bean = new TutorialPortletSessionBean();
		if ("1".equals(modo)) {			
			response.setCharacterEncoding("UTF-8");
			bean.setNombre("Camilo");
			bean.setApellido("Beltrán");
			bean.setEdad("" + ((int) (Math.random() * 10) + 18));
			bean.setEmail("camilo.beltran@colombia.com");
			bean.setDocumento("123456789");
		} else if ("2".equals(modo)) {
			response.setContentType("text/plain");
			response.addProperty("Content-Disposition", "attachment; filename=profile.txt");
			bean.setNombre("Camilo");
			bean.setApellido("Beltrán");
			bean.setEdad("" + ((int) (Math.random() * 10) + 18));
			bean.setEmail("camilo.beltran@colombia.com");
			bean.setDocumento("123456789");
			data = gson.toJson(bean).toString();
			OutputStream out = response.getPortletOutputStream();
			InputStream in = new ByteArrayInputStream(data.getBytes());
			byte[] buffer = new byte[4096];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			in.close();
			out.close();
			return;
		} else {
			MultipartStream multipartStream = new MultipartStream(request.getPortletInputStream(),
					extractBoundary(request).getBytes(), 1024, null);
			boolean nextPart = multipartStream.skipPreamble();
			while (nextPart) {
				String header = multipartStream.readHeaders();
				if (header.contains("filename")) {
					// if input is file
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					multipartStream.readBodyData(output);
					output.flush();
					output.close();
					bean = gson.fromJson(output.toString("utf-8"), TutorialPortletSessionBean.class);
				}
				nextPart = multipartStream.readBoundary();
			}
		}
		data = gson.toJson(bean).toString();
		try {
			writer = response.getWriter();
			writer.write(data);
			writer.close();
		} catch (Exception e) {
		}
		request.getPortletSession().setAttribute(SESSION_BEAN, bean);
	}

	private String extractBoundary(ResourceRequest request) {
		String boundaryHeader = "boundary=";
		int i = request.getContentType().indexOf(boundaryHeader) + boundaryHeader.length();
		return request.getContentType().substring(i);
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
