package practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Modificar {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		File ruta = new File(Comun.getRuta());
		File ruta2 = new File(System.getProperty("user.home") + File.separator + "departamentos2.dat");

		System.out.println("Dime un departamento a modificar: ");
		System.out.print("Disponibles: ");
		Comun.departamentosDisponibles();

		int de = teclado.nextInt();
		boolean existe = false;
		while (!existe) {
			if (Comun.comprobarDepartamento(de)) {
				existe = true;
				ObjectInputStream entrada;
				try {
					entrada = new ObjectInputStream(new FileInputStream(ruta));
					Departamento[] dep = (Departamento[]) entrada.readObject();

					ArrayList<Departamento> c = new ArrayList<Departamento>();
					ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta2));

					teclado.nextLine();

					System.out.println("Dime el nombre del nuevo departamento:");
					String nombre = teclado.nextLine();
					System.out.println("Dime la localidad del nuevo departamento:");
					String localidad = teclado.nextLine();
					for (int i = 0; i < dep.length; i++) {
						if (dep[i].getNumeroDepartamento() == de) {
							dep[i].setNombre(nombre);
							dep[i].setLocalidad(localidad);

						}
						c.add(dep[i]);
					}
					teclado.close();
					Departamento[] nuevo = new Departamento[c.size()];
					for (int i = 0; i < c.size(); i++) {
						nuevo[i] = c.get(i);
					}

					salida.writeObject(nuevo);
					entrada.close();

					salida.close();

					ruta.delete();
					ObjectInputStream entrada2 = new ObjectInputStream(new FileInputStream(ruta2));
					Departamento[] dep3 = (Departamento[]) entrada2.readObject();
					entrada.close();

					ObjectOutputStream salida2 = new ObjectOutputStream(new FileOutputStream(ruta));
					salida2.writeObject(dep3);
					salida2.close();
					ruta2.delete();
					System.out.println("Departamento modificado.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				existe = false;
				System.out.println("El departamento no existe.");
			}
		}
	}

}
