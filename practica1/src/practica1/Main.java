package practica1;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable{

	
	private static final long serialVersionUID = 6130619196076580904L;

	public static void main(String[] args){

		Departamento[] departamentos = new Departamento[5];

		Operaciones op = new Operaciones();

		Scanner teclado = new Scanner(System.in);
		File ruta = new File(System.getProperty("user.home") + File.separator + "configuracion.dat");

		if (!ruta.exists()) {
			op.crearLeerYmostrarArhivo(departamentos);
		} else {
			System.out.println("�Quieres modificar alg�n departamento? 1.S� 2.No");
			int opc = teclado.nextInt();
			String nombre = "";
			String localidad = "";
			int departamento = 0;
			teclado.nextLine();
			if (opc == 1) {
				System.out.println("Dime el n�mero de departamento");
				departamento = teclado.nextInt();
				boolean c = op.comprobarDepartamento(departamento);
				System.out.println(departamento);
				if (!c) {
					System.out.println("El departamento no existe");
				} else {
					teclado.nextLine();
					System.out.println("Dime el nuevo nombre del departamento");
					nombre = teclado.next();
					System.out.println("Dime la localidad del departamento");
					localidad = teclado.next();
					op.modificarArchivo(departamento, nombre, localidad);
					op.mostrarArchivo();

				}

			} else {
				System.out.println("�Quieres eliminar alg�n departamento? 1.S� 2.No");
				int op2 = teclado.nextInt();
				if (op2 == 1) {
					System.out.println("Dime el n�mero de departamento");
					departamento = teclado.nextInt();
					boolean c2 = true;

					if (!c2) {
						System.out.println("El departamento no existe");
					} else {
						op.eliminarDepartamento(departamento);
						op.mostrarArchivo();
						
					}

				}
				System.out.println("Hasta la pr�xima");
			}
		}
		
		

	}

}
