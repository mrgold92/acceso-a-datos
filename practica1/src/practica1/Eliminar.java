package practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Eliminar {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		File ruta = new File(Comun.getRuta());
		File ruta2 = new File(System.getProperty("user.home") + File.separator + "departamentos2.dat");

		boolean existe = false;
		while (!existe) {
			if (Comun.comprobarDepartamento(1)) {
				existe = true;
				try {
					ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta));
					Departamento[] dep = (Departamento[]) entrada.readObject();

					ArrayList<Departamento> c = new ArrayList<Departamento>();
					ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta2));

					System.out.println("Dime un departamento a eliminar: ");
					System.out.print("Disponibles: ");
					Comun.departamentosDisponibles();
					int departamento = teclado.nextInt();
					teclado.close();

					for (int i = 0; i < dep.length; i++) {
						if (dep[i].getNumeroDepartamento() != departamento) {

							c.add(dep[i]);

						}
					}

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

					System.out.println("Departamento eliminado.");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("El departamento no existe.");
				existe = false;
			}
		}
	}

}
