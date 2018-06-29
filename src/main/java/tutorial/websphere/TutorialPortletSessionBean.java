package tutorial.websphere;

import java.io.Serializable;

public class TutorialPortletSessionBean implements Serializable {

	private static final long serialVersionUID = -8553202539180124756L;

	private String nombre;
	private String apellido;
	private String documento;
	private String email;
	private int edad;

	public TutorialPortletSessionBean() {
		super();
		nombre = "";
		apellido = "";
		documento = "";
		email = "";
		edad = 0;
	}

	public TutorialPortletSessionBean(String nombre, String apellido, String documento, String email, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.email = email;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
