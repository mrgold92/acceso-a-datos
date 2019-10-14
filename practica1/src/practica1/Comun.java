package practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;

public abstract class Comun {

	public static String getRuta() {
		Properties pro = new Properties();
		String url = "";
		try {
			pro.load(new FileInputStream(System.getProperty("user.home") + File.separator + "configuracion.props"));
			url = pro.getProperty("nombrearchivo");
		} catch (IOException e) {

			e.printStackTrace();
		}

		return System.getProperty("user.home") + File.separator + url;
	}

	public static boolean comprobarDepartamento(int departamento) {
		File ruta = new File(getRuta());
		boolean e = false;

		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(ruta));
			Departamento[] dep = (Departamento[]) entrada.readObject();
			entrada.close();

			for (int i = 0; i < dep.length; i++) {
				if (dep[i].getNumeroDepartamento() == departamento) {
					e = true;
				}

			}
		} catch (IOException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}

		return e;
	}

	public static void departamentosDisponibles() {

		ObjectInputStream entrada;
		try {
			entrada = new ObjectInputStream(new FileInputStream(Comun.getRuta()));
			Departamento[] dep = (Departamento[]) entrada.readObject();
			entrada.close();

			for (int i = 0; i < dep.length; i++) {
				System.out.print(dep[i].getNumeroDepartamento() + " ");

			}
			System.out.println();
		} catch (IOException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		}
	}

}
