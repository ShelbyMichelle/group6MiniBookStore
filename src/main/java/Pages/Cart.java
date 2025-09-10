package Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart<T>{
    private List<T> items;
    private Map<Long, Integer> quantities;

    public Cart() {
        this.items = new ArrayList<>();
        this.quantities = new HashMap<>();
    }

    public void addItem(T item, Long id, Integer quantity) {
        items.add(item);
        quantities.put(id, quantities.getOrDefault(id, 0) + quantity);
    }

    public void removeItem(T item, Long id) {
        items.remove(item);
        quantities.remove(id);
    }

    public List<T> getItems() {
        return new ArrayList<>(items);
    }

    public Map<Long, Integer> getQuantities() {
        return new HashMap<>(quantities);
    }

    public void clear() {
        items.clear();
        quantities.clear();
    }

    public int getItemCount() {
        return items.size();
    }
}
