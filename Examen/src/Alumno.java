import java.io.IOException;
import java.io.RandomAccessFile;

public class Alumno {

	private String nombre;
	private String apellido;
	private String direccon;
	private String email;
	private int edad;
	private long id_matricula;
	private float nota_media;

	public Alumno() {

	}

	public Alumno(String nombre, String apellido, String direccon, String email, int edad, long id_matricula,
			float nota_media) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccon = direccon;
		this.email = email;
		this.edad = edad;
		this.id_matricula = id_matricula;
		this.nota_media = nota_media;
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

	public String getDireccon() {
		return direccon;
	}

	public void setDireccon(String direccon) {
		this.direccon = direccon;
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

	public long getId_matricula() {
		return id_matricula;
	}

	public void setId_matricula(long id_matricula) {
		this.id_matricula = id_matricula;
	}

	public float getNota_media() {
		return nota_media;
	}

	public void setNota_media(float nota_media) {
		this.nota_media = nota_media;
	}

	public void write(RandomAccessFile random, Alumno[] alumnos) throws IOException {
		random.seek(0);

		for (int i = 0; i < alumnos.length; i++) {

			StringBuffer nombre = new StringBuffer();
			nombre.append(alumnos[i].getNombre());
			nombre.setLength(10);
			random.writeChars(nombre.toString());

			StringBuffer apellido = new StringBuffer();
			apellido.append(alumnos[i].getApellido());
			apellido.setLength(10);
			random.writeChars(apellido.toString());

			StringBuffer direccion = new StringBuffer();
			direccion.append(alumnos[i].getDireccon());
			direccion.setLength(10);
			random.writeChars(direccion.toString());

			StringBuffer email = new StringBuffer();
			email.append(alumnos[i].getEmail());
			email.setLength(10);
			random.writeChars(email.toString());

			random.writeInt(alumnos[i].getEdad());
			random.writeLong(alumnos[i].getId_matricula());
			random.writeFloat(alumnos[i].getNota_media());
		}
	}

	public void read(RandomAccessFile random) throws IOException {

		random.seek(0 * getTamano(random));

		while ((random.getFilePointer()) < random.length()) {

			char[] nombre = new char[10];
			char[] apellido = new char[10];
			char[] direccion = new char[10];
			char[] email = new char[10];
			for (int j = 0; j < 10; j++) {
				nombre[j] = random.readChar();
			}

			for (int j = 0; j < 10; j++) {
				apellido[j] = random.readChar();
			}

			for (int j = 0; j < 10; j++) {
				direccion[j] = random.readChar();
			}

			for (int j = 0; j < 10; j++) {
				email[j] = random.readChar();
			}

			System.out.print("Nombre: ");
			System.out.println(nombre);
			System.out.print("Apellido: ");
			System.out.println(apellido);
			System.out.print("Direccion: ");
			System.out.println(direccion);
			System.out.print("Email: ");
			System.out.println(email);

			this.nombre = nombre.toString();
			this.apellido = apellido.toString();
			this.direccon = direccion.toString();
			this.email = email.toString();

			this.edad = random.readInt();

			this.id_matricula = random.readLong();
			this.nota_media = random.readFloat();

			System.out.println("Edad: " + this.edad);
			System.out.println("id matrícula: " + this.id_matricula);
			System.out.println("Nota media: " + this.nota_media);
			System.out.println();
			System.out.println();

		}
	}

	public int getTamano(RandomAccessFile random) {

		// sabemos que es 96 porque cuando leemos el fichero,
		// leemos el primer objeto, y se posiciona el puntero en el byte 96, así que,
		// sabemos que cada objeto ocupada 96 bytes.
		// por lo tanto el primero 96, el segundo 192, el tercero 288...
		return 96;
	}

}
