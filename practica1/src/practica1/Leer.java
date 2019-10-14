package practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Leer {

	public static void main(String[] args) {
		File ruta = new File(Comun.getRuta());
		try {
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta));
			Departamento[] dep = (Departamento[]) entrada.readObject();
			entrada.close();

			System.out.println("\nNÚMERO DE DEPARTAMENTOS ACTUALES:" + dep.length + "\n");

			for (Departamento departamento : dep) {
				System.out.println("Nº departamento: " + departamento.getNumeroDepartamento());
				System.out.println("Nombre departamento: " + departamento.getNombre());
				System.out.println("Localidad departamento: " + departamento.getLocalidad());
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

}
