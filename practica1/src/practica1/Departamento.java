package practica1;

import java.io.Serializable;

/**
 * 
 * @author David S. 2�DAM
 *
 */
public class Departamento implements Serializable {


	private static final long serialVersionUID = 1L;
	private int numeroDepartamento;
	private String nombre;
	private String localidad;

	public Departamento(int numeroDepartamento, String nombre, String localidad) {
		this.numeroDepartamento = numeroDepartamento;
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public int getNumeroDepartamento() {
		return numeroDepartamento;
	}

	public void setNumeroDepartamento(int numeroDepartamento) {
		this.numeroDepartamento = numeroDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

}
