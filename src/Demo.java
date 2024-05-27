import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import order.Order;
import strategy.PayByCreditCard;
import strategy.PayByPayPal;
import strategy.STRATEGY;

public class Demo {
    // Mapa que asocia el ID del producto con su precio
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    // Objeto BufferedReader para leer la entrada del usuario desde la consola
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Objeto Order para representar la orden de compra
    private static Order order = new Order();
    // Objeto STRATEGY para gestionar la estrategia de pago
    private static STRATEGY strategy;

    // Inicialización estática para definir los precios de los productos
    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        // Bucle principal que permite al usuario realizar pedidos hasta que se cierre la orden
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            // Bucle para seleccionar productos y establecer cantidades
            do {
                System.out.print("Por favor seleccione un producto:" + "\n" +
                        "1 - Tarjeta madre" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Cantidad: ");
                int count = Integer.parseInt(reader.readLine());
                // Establece el costo total de la orden basado en el precio del producto y la cantidad seleccionada
                order.setTotalCost(cost * count);
                System.out.print("¿Quieres seguir seleccionando productos? S/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("S"));

            // Si no se ha seleccionado una estrategia de pago, solicita al usuario que la elija
            if (strategy == null) {
                System.out.println("Por favor seleccione un método de pago:" + "\n" +
                        "1 - PalPay" + "\n" +
                        "2 - Tarjeta de crédito");
                String paymentMethod = reader.readLine();

                // Crea la estrategia de pago según la opción del usuario
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            // Procesa la orden con la estrategia de pago seleccionada
            order.processOrder(strategy);

            // Pregunta al usuario si desea pagar o continuar comprando
            System.out.print("Pagar " + order.getTotalCost() + " unidades o ¿Continuar comprando? P/C: ");
            String proceed = reader.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                // Realiza el pago utilizando la estrategia de pago seleccionada
                if (strategy.pay(order.getTotalCost())) {
                    System.out.println("El pago ha sido exitoso.");
                } else {
                    System.out.println("¡ERROR! Por favor, revisa tus datos.");
                }
                // Marca la orden como cerrada
                order.setClosed();
            }
        }
    }
}
