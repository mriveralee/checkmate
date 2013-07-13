import java.util.HashMap;
import java.util.ArrayList;

public class Diner {
    private String venmoId;
    private HashMap<String, String> orders;
    private HashMap<String, Integer> quantity;

    public Diner(String venmoId) {
        this.venmoId = venmoId;
        orders = new HashMap<String, String>();
    }

    public void addItem(String item, String cost) {
        if (orders.containsKey(item)) {
           quantity.put(item, quantity.get(item) + 1);
        }
        else {
            orders.put(item, cost);
            quantity.put(item, 1);
        }
    }

    public void decrementItem(String item) {
        if (!orders.containsKey(item)) {
            return;
        }
        quantity.put(quantity.get(item) - 1);
        if (quantity.get(item) == 0) {
            quantity.remove(item);
            orders.remove(item); 
        }
    }

    public double getDebt() {
        double debt = 0.0;
        for (String item : orders.keySet()) {
            debt += Double.parseDouble(orders.get(item)) * quantity.get(item);
        }
        return debt;
    }

    public String getVenmoId() {
        return venmoId;
    }
}