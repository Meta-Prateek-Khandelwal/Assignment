import java.util.*;
import java.util.Map.Entry;

// this is item class for itemid, name, des, price
class Item {
    static private String prefix = "Item_id_";
    static private int itemId = 0;
    String id;

    private String name;
    private String des;
    private double price;

    Item(String id, String name, String des, double price) {
        this.id = prefix + String.valueOf(++itemId);
        this.name = name;
        this.des = des;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public double getPrice() {
        return price;
    }
}

// this class work the functionlity like add To Cart, displayQty, update,
// delete, total bill Ammount
class Cart {
    private final HashMap<String, Integer> itemList = new HashMap<>();
    HashMap<String, Item> allItems = new HashMap<>();

    void addToCart(Item item, int quantity) {
        itemList.put(item.getId(), itemList.getOrDefault(item.getId(), 0) + quantity);
        allItems.put(item.getId(), item);
        System.out.println("Item add to cart successfully.");
    }

    int displayQty(Item item) {
        // first we check this item is present
        if (!itemList.containsKey(item.getId()))
            return 0;
        return itemList.get(item.getId());
    }

    void updateQty(Item item, int q) {
        if (!itemList.containsKey(item.getId())) {
            System.out.println("Please add item first.");
            return;
        }
        itemList.replace(item.getId(), q);
        System.out.println("Item update successfully.");
    }

    void deleteItem(Item item) {
        if (!itemList.containsKey(item.getId())) {
            System.out.println("First add the item.");
            return;
        }

        System.out.print(itemList.remove(item.getId()));
        System.out.println("Item delete cart successfully.");
    }

    Double displayBill() {
        double totalAmount = 0;

        for (Entry<String, Integer> e : itemList.entrySet()) {
            String itemId = e.getKey();
            int quantity = e.getValue();
            Item item = allItems.get(itemId);
            totalAmount += item.getPrice() * quantity;
        }

        return totalAmount;
    }
}

public class Shopping {
    static String id = "";

    // check item is present or not in the store
    static Item checkItem(String n, ArrayList<Item> list) {
        for (Item i : list) {
            if (i.id.equals(n)) return i;
        }

        return null;
    }

    public static void main(String[] args) {
        // cretae arrayList for item add
        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item(id, "milk", "liquid", 20));
        list.add(new Item(id, "cock", "liquid", 25));
        list.add(new Item(id, "apple", "fruit", 10));
        list.add(new Item(id, "Laptop", "Dell XPS 13", 12000));
        list.add(new Item(id, "phone", "Readmi Node 11", 10000));

        // for this is access all field of item

        for (Item l : list) {
            System.out.println("ID: " + l.getId() + ", Name: " + l.getName() + ", Description: " + l.getDes()
                    + ", Price: " + l.getPrice());
        }

        // for the input purpose
        Scanner sc = new Scanner(System.in);
        Cart store = new Cart();

        while (true) {
            System.out.print("Enter the item id : ");
            String n = sc.next();
            Item find = checkItem(n, list);

            if (find == null) break;

            System.out.println("1: Add to cart: ");
            System.out.println("2: Show quantity for added item: ");
            System.out.println("3: Update quantity of added item: ");
            System.out.println("4: Delete Item from cart: ");
            System.out.println("5: Display cart total value (Total bill Amount): ");

            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Enter the quantity: ");
                    int qua = sc.nextInt();
                    store.addToCart(find, qua);
                    break;

                case 2:
                    System.out.println(
                            "item name: " + find.getName() + "  " + "item quantity: " + store.displayQty(find));
                    break;

                case 3:
                    System.out.println("Enter the updated quantity: ");
                    int q = sc.nextInt();
                    store.updateQty(find, q);
                    break;

                case 4:
                    store.deleteItem(find);
                    break;

                case 5:
                    System.out.println("Total Bill Ammount: " + store.displayBill());
                    break;

                default:
                    System.out.println("invalid choice!!!");
                    break;
            }

            System.out.print("Exit so press -1 other anything: ");
            int ch = sc.nextInt();
            if (ch == -1) break;
        }

        System.out.println("Total Bill Ammount: " + store.displayBill());

    }

}