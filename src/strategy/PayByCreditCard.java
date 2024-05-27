package strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Clase que implementa la interfaz STRATEGY para procesar pagos con tarjeta de crédito
public class PayByCreditCard implements STRATEGY {
    // Objeto BufferedReader para leer la entrada del usuario desde la consola
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    // Objeto CreditCard para almacenar los datos de la tarjeta de crédito
    private CreditCard card;

    /**
     * Recolecta los datos de la tarjeta de crédito.
     */
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Ingrese el número de tarjeta: ");
            String number = READER.readLine();
            System.out.print("Ingrese la fecha de vencimiento de la tarjeta 'mm/yy': ");
            String date = READER.readLine();
            System.out.print("Introduce el código CVV: ");
            String cvv = READER.readLine();
            // Crea una nueva instancia de CreditCard con los datos proporcionados
            card = new CreditCard(number, date, cvv);

            // Aquí se podría validar el número de la tarjeta de crédito...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Después de la validación de la tarjeta, podemos cobrar el monto al cliente.
     */
    @Override
    public boolean pay(int paymentAmount) {
        // Verifica si la tarjeta está presente y realiza el pago
        if (cardIsPresent()) {
            System.out.println("Pagar " + paymentAmount + " usando tarjeta de crédito.");
            // Descuenta el monto del pago del saldo de la tarjeta
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    // Método privado para verificar si la tarjeta está presente
    private boolean cardIsPresent() {
        return card != null;
    }
}
