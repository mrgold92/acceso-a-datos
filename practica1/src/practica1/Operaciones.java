package practica1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Operaciones {

	public void crearLeerYmostrarArhivo(Departamento[] departamentos) {

		int[] numeroDepartamentos = { 1, 2, 3, 4, 5, 6 };
		String[] nombres = { "Marketing", "Recursos humanos", "Ventas", "Compras", "Jur�dico" };
		String[] localidades = { "Madrid", "Barcelona", "Valencia", "M�laga", "Lugo" };

		int numeroDepartamento = 0;
		String nombre = "";
		String localidad = "";
		ObjectOutputStream salida = null;
		ObjectInputStream enter = null;

		int c = 1;

		for (int i = 0; i < departamentos.length; i++) {

			numeroDepartamento = numeroDepartamentos[c - 1];
			nombre = nombres[i];
			localidad = localidades[i];

			departamentos[i] = new Departamento(numeroDepartamento, nombre, localidad);
			c++;
		}

		try {
			salida = new ObjectOutputStream(
					new FileOutputStream(System.getProperty("user.home") + File.separator + "configuracion.dat"));

			salida.writeObject(departamentos);
			salida.close();

			enter = new ObjectInputStream(
					new FileInputStream(System.getProperty("user.home") + File.separator + "configuracion.dat"));

			Departamento[] departament = (Departamento[]) enter.readObject();
			enter.close();

			for (Departamento departamento : departament) {
				System.out.println(departamento.getNumeroDepartamento());
				System.out.println(departamento.getNombre());
				System.out.println(departamento.getLocalidad());
			}

		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getNombreArchivo() {
		Properties properties = new Properties();

		String nombre = null;
		try {
			properties.load(
					new FileInputStream(System.getProperty("user.home") + File.separator + "configuracion.dat"));
			nombre = properties.getProperty("nombrearchivo");
		} catch (IOException e) {

			e.printStackTrace();
		}

		return nombre;
	}

	public void modificarArchivo(int numeroDepartamento, String nombre, String localidad) {
		ObjectInputStream enter = null;
		ObjectOutputStream salida = null;
		File ruta = new File(System.getProperty("user.home") + File.separator + "configuracion.dat");
		File ruta2 = new File(System.getProperty("user.home") + File.separator + "configuracion2.dat");

		try {
			enter = new ObjectInputStream(new FileInputStream(ruta));

			Departamento[] depa = (Departamento[]) enter.readObject();
			Departamento[] depa2 = new Departamento[depa.length];

			enter.close();

			for (int i = 0; i < depa.length; i++) {

				if (depa[i].getNumeroDepartamento() == numeroDepartamento) {
					depa2[i] = depa[i];
					depa2[i].setNombre(nombre);
					depa2[i].setLocalidad(localidad);

				} else {
					depa2[i] = depa[i];
				}
				salida = new ObjectOutputStream(new FileOutputStream(ruta));

				for (int j = 0; j < depa2.length; j++) {
					salida.writeObject(depa2);
				}
				// ruta2.delete();
				salida.close();

			}

		} catch (EOFException e) {
			System.out.println("ex");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostrarArchivo() {

		File ruta = new File(System.getProperty("user.home") + File.separator + "configuracion.dat");

		try {

			FileInputStream fin3 = new FileInputStream(ruta);
			ObjectInputStream objectInputStream = new ObjectInputStream(fin3);
			Departamento[] test3 = (Departamento[]) objectInputStream.readObject();

			for (int i = 0; i < test3.length; i++) {
				System.out.println(test3[i].getNumeroDepartamento());
				System.out.println(test3[i].getNombre());
				System.out.println(test3[i].getLocalidad());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean comprobarDepartamento(int dep) {

		ObjectInputStream enter = null;
		File ruta = new File(System.getProperty("user.home") + File.separator + "configuracion.dat");
		boolean c = true;
		int encontrado = 0;
		try {
			enter = new ObjectInputStream(new FileInputStream(ruta));

			Departamento[] depa = (Departamento[]) enter.readObject();
			enter.close();

			for (Departamento departamento : depa) {

				if (departamento.getNumeroDepartamento() != dep) {

					c = false;

				} else {
					c = true;
					encontrado++;
				}

			}

			if (encontrado > 0) {
				c = true;
			}

		} catch (EOFException e) {
			System.out.println("ex");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

	public void eliminarDepartamento(int numeroDepartamento) {

		File ruta = new File(System.getProperty("user.home") + File.separator + "configuracion.dat");
		File ruta2 = new File(System.getProperty("user.home") + File.separator + "configuracion2.dat");

		try {

			FileInputStream fin = new FileInputStream(ruta);
			Departamento[] test2 = (Departamento[]) new ObjectInputStream(fin).readObject();

			FileOutputStream fout = new FileOutputStream(ruta2);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			ArrayList<Departamento> depa = new ArrayList<Departamento>();

			for (int i = 0; i < test2.length; i++) {
				if (test2[i].getNumeroDepartamento() != numeroDepartamento) {
					out.writeObject(test2[i]);
				}

			}

			out.close();
			fout.close();

			ruta.delete();

			FileInputStream fin1 = new FileInputStream(ruta2);
			ObjectInputStream objectInputStream = new ObjectInputStream(fin1);
			Departamento[] test3 = (Departamento[]) objectInputStream.readObject();
			fin1.close();
			objectInputStream.close();

			FileOutputStream fout3 = new FileOutputStream(ruta);
			ObjectOutputStream out3 = new ObjectOutputStream(fout);

			for (int i = 0; i < test3.length; i++) {

				out3.writeObject(test3[i]);

			}

			fout3.close();
			out3.close();

		} catch (EOFException e) {

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
