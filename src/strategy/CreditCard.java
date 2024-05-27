package strategy;

// Clase que representa una tarjeta de crédito
public class CreditCard {
    // Variables para almacenar el monto, número, fecha de caducidad y CVV de la tarjeta
    private int amount;
    private String number;
    private String date;
    private String cvv;

    // Constructor para inicializar la tarjeta con el número, fecha y CVV proporcionados
    CreditCard(String number, String date, String cvv) {
        // Establece un monto inicial para la tarjeta
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    // Método para establecer el monto de la tarjeta
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // Método para obtener el monto de la tarjeta
    public int getAmount() {
        return amount;
    }
}
