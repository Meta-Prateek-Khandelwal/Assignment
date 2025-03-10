import java.util.*;

// this is item class for itemid, name, des, price
class Item{
    
    static private int itemId = 0;
    private int id;
    private String name;
    private String des;
    private double price;

    Item(int id,String name, String des, double price){
        this.id = ++itemId;
        this.name = name;
        this.des = des;
        this.price = price;
    }
    
    public int getId() {return id;}

    public String getName() { return name;} 

    public String getDes() { return des;}

    public double getPrice() { return price;}
}

// this class work the functionlity like add To Cart, displayQty, update, delete, total bill Ammount
class Cart {
    HashMap<Item, Integer> itemList = new HashMap<>();

    void addToCart(Item item, int q){
        itemList.put(item , q);
        System.out.println("Item add to cart successfully.");
    }

    int displayQty(Item item){
        //first we check this item is present
        if(!itemList.containsKey(item)) return 0;
        return itemList.get(item);
    }

    void updateQty(Item item, int q){
        if(!itemList.containsKey(item)) {
            System.out.println("Please add item first.");
            return;
        }
        itemList.replace(item, q);
        System.out.println("Item update successfully.");
    }

    void delete(Item item){
        if(!itemList.containsKey(item)){
            System.out.println("First add the item.");
            return;
        }
        
        System.out.print(itemList.remove(item));
        System.out.println("Item delete cart successfully.");
    }

    Double displayBill(){
        double totalAmount = 0;

        for(Map.Entry<Item, Integer> e: itemList.entrySet()){
            totalAmount += e.getKey().getPrice() * e.getValue();
        }

        return totalAmount;
    }
}

class Shopping{
    static int id = 0;
    static String prefix = "Item_id_";

    // check item is present or not in the store
    static Item checkItem(int n, ArrayList<Item> list){
        for(Item i: list){
            if(i.getId() == n) return i;
        }

        return null;
    }

    public static void main(String[] args){
        // cretae arrayList for item add
        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item(id,"milk", "liquid", 20));
        list.add(new Item(id,"cock", "liquid", 25));
        list.add(new Item(id,"apple", "fruit", 10));

        
        for(Item l: list){ 
            System.out.println("ID: "+prefix+l.getId()+", Name: "+l.getName()+", Description: "+l.getDes()+", Price: "+l.getPrice());
        }

        // /for the input purpose
        Scanner sc = new Scanner(System.in);
        Cart store = new Cart();

        System.out.print("Enter the item id : ");
        int n = sc.nextInt();
        Item find = checkItem(n, list);


        while(true){
            if(find == null) break;

            System.out.println("1: Add to cart: ");
            System.out.println("2: Show quantity for added item: ");
            System.out.println("3: Update quantity of added item: ");
            System.out.println("4: Delete Item from cart: ");
            System.out.println("5: Display cart total value (Total bill Amount): ");

            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();

            switch(choice){

                case 1: 
                    System.out.println("Enter the quantity: ");
                    int qua = sc.nextInt();
                    store.addToCart(find, qua);
                    break;

                case 2: 
                    System.out.println("item name: "+find.getName()+"  "+"item quantity: "+store.displayQty(find));
                    break;

                case 3: 
                    System.out.println("Enter the updated quantity: ");
                    int q = sc.nextInt();
                    store.updateQty(find, q);
                    break;

                case 4: 
                    store.delete(find);
                    break;

                case 5: 
                    System.out.println("Total Bill Ammount: "+store.displayBill());
                    break;

                default: 
                    System.out.println("invalid choice!!!");
                    break;
            }

            System.out.print("Exit so press -1 other anything: ");
            int ch = sc.nextInt();
            if(ch == -1) break;
        }
        
        
        System.out.println("Total Bill Ammount: "+store.displayBill());

    }

}