import java.util.HashMap;
import java.util.ArrayList;

public class Table {
    private HashMap<String, String> orders;
    public Table() {
        orders = new HashMap<String, String>();
    }

    public HashMap<String, String> getOrders() {
        return orders;
    }
}