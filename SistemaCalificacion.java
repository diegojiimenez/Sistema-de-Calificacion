import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Materia {
    private String nombre;
    private double nota;

    public Materia(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNota() {
        return nota;
    }
}

class Estudiante {
    private String nombre;
    private Map<String, Materia> materias;

    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.materias = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, Materia> getMaterias() {
        return materias;
    }

    public void agregarMateria(String nombreMateria, double nota) {
        Materia materia = new Materia(nombreMateria, nota);
        materias.put(nombreMateria, materia);
    }
}

class Maestro {
    private String nombre;

    public Maestro(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void ingresarNotas(Estudiante estudiante, String materia, double nota) {
        estudiante.agregarMateria(materia, nota);
    }
}

public class SistemaCalificacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Sistema de Calificación");

        System.out.print("¿Eres alumno o maestro? (alumno/maestro): ");
        String tipoUsuario = scanner.nextLine();

        if (tipoUsuario.equalsIgnoreCase("maestro")) {
            System.out.print("Ingrese el nombre del maestro: ");
            String nombreMaestro = scanner.nextLine();
            Maestro maestro = new Maestro(nombreMaestro);

            ingresarNotasComoMaestro(scanner, maestro);
        } else if (tipoUsuario.equalsIgnoreCase("alumno")) {
            System.out.print("Ingrese el nombre del alumno: ");
            String nombreAlumno = scanner.nextLine();
            Estudiante alumno = new Estudiante(nombreAlumno);

            verNotasComoAlumno(alumno);
        } else {
            System.out.println("Tipo de usuario no reconocido. Saliendo...");
        }

        scanner.close();
    }

    private static void ingresarNotasComoMaestro(Scanner scanner, Maestro maestro) {
        System.out.print("Ingrese el número de materias: ");
        int numMaterias = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número de materias.

        for (int i = 0; i < numMaterias; i++) {
            System.out.print("Ingrese el nombre de la materia: ");
            String materia = scanner.nextLine();

            System.out.print("Ingrese la nota para " + materia + ": ");
            double nota = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea después de la nota.

            // Aquí se puede realizar alguna lógica de validación antes de ingresar la nota.
            

            System.out.print("Ingrese el nombre del alumno: ");
            String nombreAlumno = scanner.nextLine();

            Estudiante alumno = new Estudiante(nombreAlumno);
            maestro.ingresarNotas(alumno, materia, nota);

            System.out.println("Nota ingresada exitosamente para " + alumno.getNombre() + " en " + materia);
        }
    }

    private static void verNotasComoAlumno(Estudiante alumno) {
        Map<String, Materia> materias = alumno.getMaterias();

        if (materias.isEmpty()) {
            System.out.println("Aún no hay notas disponibles para este alumno.");
        } else {
            System.out.println("Estas son tus notas como alumno:");

            for (Map.Entry<String, Materia> entry : materias.entrySet()) {
                String materia = entry.getKey();
                Materia infoMateria = entry.getValue();

                System.out.println("Materia: " + materia + ", Nota: " + infoMateria.getNota());
            }
        }
    }
}
