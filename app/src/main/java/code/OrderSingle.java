package code;

public class OrderSingle {
    private static OrderSingle instance = null;
    private Order order;

    private OrderSingle() {
        order = new Order();
    }

    public static OrderSingle getInstance() {
        if (instance == null) {
            instance = new OrderSingle();
        }
        return instance;
    }

    public Order getOrder() {
        return order;
    }

}
