package order;

import strategy.STRATEGY;

// Clase que representa una orden de compra
public class Order {
    // Variable para almacenar el costo total de la orden
    private int totalCost = 0;
    // Variable que indica si la orden está cerrada o no
    private boolean isClosed = false;

    // Método para procesar la orden utilizando una estrategia de pago
    public void processOrder(STRATEGY strategy) {
        // Invoca el método para recopilar detalles de pago de la estrategia
        strategy.collectPaymentDetails();
        // Aquí podríamos recopilar y almacenar datos de pago de la estrategia.
    }

    // Método para establecer el costo total de la orden
    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    // Método para obtener el costo total de la orden
    public int getTotalCost() {
        return totalCost;
    }

    // Método para verificar si la orden está cerrada
    public boolean isClosed() {
        return isClosed;
    }

    // Método para marcar la orden como cerrada
    public void setClosed() {
        isClosed = true;
    }
}
