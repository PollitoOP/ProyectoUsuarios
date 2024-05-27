package strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Estrategia concreta. Implementa el método de pago PayPal.
 */
public class PayByPayPal implements STRATEGY {
    // Base de datos simulada que almacena las credenciales de PayPal
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    // Objeto BufferedReader para leer la entrada del usuario desde la consola
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    // Variables para almacenar el correo electrónico, contraseña y estado de inicio de sesión
    private String email;
    private String password;
    private boolean signedIn;

    // Inicialización estática para definir las credenciales de PayPal
    static {
        DATA_BASE.put("franklin1234", "franklin@gmail.com");
        DATA_BASE.put("tendencias", "tendencias@gmail.com");
    }

    /**
     * Recolecta los datos del cliente.
     */
    @Override
    public void collectPaymentDetails() {
        try {
            // Solicita al usuario que ingrese su correo electrónico y contraseña hasta que se inicie sesión correctamente
            while (!signedIn) {
                System.out.print("Ingrese el correo electrónico del usuario: ");
                email = READER.readLine();
                System.out.print("Ingrese la contraseña: ");
                password = READER.readLine();
                // Verifica las credenciales ingresadas
                if (verify()) {
                    System.out.println("La verificación de datos ha sido exitosa.");
                } else {
                    System.out.println("¡Correo o contraseña inválidos!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método privado para verificar las credenciales del usuario
    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /**
     * Guarda los datos del cliente para futuros intentos de compra.
     */
    @Override
    public boolean pay(int paymentAmount) {
        // Realiza el pago si el usuario ha iniciado sesión correctamente
        if (signedIn) {
            System.out.println("Pagar " + paymentAmount + " usando PAYPAL.");
            return true;
        } else {
            return false;
        }
    }

    // Método privado para establecer el estado de inicio de sesión
    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}
