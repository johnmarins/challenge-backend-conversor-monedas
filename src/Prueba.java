import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                String entrada;

                System.out.print("Ingresa una palabra (máximo 3 letras): ");
                while (true) {
                    entrada = scanner.nextLine();
                    if (entrada.matches("[a-zA-Z]{1,3}")) {
                        break; // La entrada es válida
                    } else {
                        System.out.print("La palabra debe tener entre 1 y 3 letras. Inténtalo nuevamente: ");
                    }
                }

                System.out.println("Palabra ingresada: " + entrada);
            }
}
