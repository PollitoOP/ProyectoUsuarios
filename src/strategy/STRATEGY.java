package strategy;

// Interfaz que define el contrato para las estrategias de pago
public interface STRATEGY {
    // Método para realizar el pago con un monto determinado
    boolean pay(int paymentAmount);
    
    // Método para recopilar los detalles del pago
    void collectPaymentDetails();
}
