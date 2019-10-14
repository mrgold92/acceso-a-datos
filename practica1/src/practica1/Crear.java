package practica1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Crear {

	public static void main(String[] args) {
		Departamento dep0 = new Departamento(1, "Marketing", "Madrid");
		Departamento dep1 = new Departamento(2, "Recursos humanos", "Barcelona");
		Departamento dep2 = new Departamento(3, "Jurídico", "Madrid");
		Departamento dep3 = new Departamento(4, "Compras", "Valencia");
		Departamento dep4 = new Departamento(5, "Logística", "Almería");

		Departamento[] departamentos = { dep0, dep1, dep2, dep3, dep4 };

		File ruta = new File(Comun.getRuta());
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta));

			salida.writeObject(departamentos);

			salida.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
