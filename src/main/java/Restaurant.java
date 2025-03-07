
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<String> selectedItemNames = new ArrayList<String>(){{
        add("Lemon Tea");
        add("Green Tea");
    }};


    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        int compareOpenTime = this.getCurrentTime().compareTo(this.openingTime);
        int compareCloseTime = this.getCurrentTime().compareTo(this.closingTime);
        if(compareOpenTime >= 0  && compareCloseTime <= 0) {
            return true;
        }
        else return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }
    public int getTotalPrice(List<String> selectedItemNames){
        int sum = 0;
        for ( String itemName: selectedItemNames) {
            for(Item item:getMenu()){
                if(itemName.equals(item.getName()))
                    sum += item.getPrice();
            }
        }
        return sum;
    }

    public String getName() {
        return name;
    }

}
