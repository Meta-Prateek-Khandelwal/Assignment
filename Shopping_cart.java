import java.util.*;

// this is item class for itemid, name, des, price
class Item{
    // int itemId;
    // String name;
    // String des;
    // int price;

    private int itemId;
    private String name;
    private String des;
    private int price;

    Item(int itemId, String name, String des, int price){
        this.itemId = itemId;
        this.name = name;
        this.des = des;
        this.price = price;
    }
    public int getId() { return itemId;}
    public void setId(int itemId){
        this.itemId = itemId;
    }

    public String getName() { return name;}
    public void setName(String name){
        this.name = name;
    }

    public String getDes() { return des;}
    public void setDes(String des){
        this.des = des;
    }

    public int getPrice() { return price;}
    public void setPrice(int price){
        this.price = price;
    }
}

// this class work the functionlity like add To Cart, displayQty, update, delete, total bill Ammount
class Shopping {
    HashMap<Item, Integer> itemList = new HashMap<>();

    void addToCart(Item item, int q){
        itemList.put(item , q);
        System.out.println("Item add to cart successfully.");
    }

    int displayQty(Item item){
        //first we check this item is present
        System.out.println(item.getId()+" : "+item.getName()+" : "+item.getDes()+" : "+item.getPrice());
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
            // System.out.println(e.getKey().getId());
        }

        return totalAmount;
    }
}

class Shopping_cart{
    static int id = 0;

    static Item checkItem(int n, ArrayList<Item> list){
        for(Item i: list){
            if(i.getId() == n) return i;
        }

        return null;
    }

    public static void main(String[] args){
        // cretae arrayList for item add
        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item(++id,"milk", "liquid", 20));
        list.add(new Item(++id,"cock", "liquid", 25));
        list.add(new Item(++id,"apple", "fruit", 10));

        System.out.println("-----------------Cart Menu------------");
        System.out.println();
    
        for(Item l: list){ 
            System.out.println("item-id: "+l.getId());
            System.out.println("item name: "+l.getName());
            System.out.println("item des: "+l.getDes());
            System.out.println("item price: "+l.getPrice());
            System.out.println();
        }

        // /for the input purpose
        Scanner sc = new Scanner(System.in);
        Shopping shop = new Shopping();

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
                    shop.addToCart(find, qua);
                    break;

                case 2: 
                    System.out.println("item name: "+find.getName()+"  "+"item quantity: "+shop.displayQty(find));
                    break;

                case 3: 
                    System.out.println("Enter the updated quantity: ");
                    int q = sc.nextInt();
                    shop.updateQty(find, q);
                    break;

                case 4: 
                    shop.delete(find);
                    break;

                case 5: 
                    System.out.println("Total Bill Ammount: "+shop.displayBill());
                    break;

                default: 
                    System.out.println("invalid choice!!!");
                    break;
            }

            System.out.print("Exit so press -1 other anything: ");
            int ch = sc.nextInt();
            if(ch == -1) break;
        }
        
        
        System.out.println("Total Bill Ammount: "+shop.displayBill());

    }

}