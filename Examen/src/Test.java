import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Alumno al1 = new Alumno("David", "Lopez", "doctor esquerdo 43", "david@david.es", 27, 1, 10);
		Alumno al2 = new Alumno("Laura", "García", "sin miedo 12", "laurag@gmail.com", 32, 2, 5);
		Alumno al3 = new Alumno("Federico", "García", "Poetas anónimo 32", "elLorquita@gmail.com", 103, 3, 9);

		Alumno[] alumnos = { al1, al2, al3 };
		RandomAccessFile random = null;

		try {
			random = new RandomAccessFile(new File("Examen.dat"), "rw");
			Alumno al = new Alumno();
			Scanner teclado = new Scanner(System.in);

			System.out.println("1.Escribir 2. Leer");
			int opcion = teclado.nextInt();

			teclado.close();
			switch (opcion) {
			case 1:
				al.write(random, alumnos);
				System.out.println("Escrito correctamente");
				break;
			case 2:
				al.read(random);
				break;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
