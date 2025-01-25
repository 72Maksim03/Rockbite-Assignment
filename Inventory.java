import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Inventory implements Serializable {
    private ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public Inventory(ArrayList<Item> list){
        items = new ArrayList<>(list);
    }

    public ArrayList<Item> getItems(){return items;}

    public void upgrade(int itToUpgrade, int addItem1, int addItem2) throws IllegalArgumentException{
        if ((itToUpgrade < 0 || itToUpgrade > items.size() - 1) ||
        (addItem1 < 0 || addItem1 > items.size() - 1) ||
        (addItem2 < 0 || addItem2 > items.size() - 1)){
            throw new IllegalArgumentException("Invalid index.");
        }

        if (itToUpgrade == addItem1 || itToUpgrade == addItem2 || addItem1 == addItem2)
            throw new IllegalArgumentException("Illegal index. Same items can not be merged");

        Item baseItem = items.get(itToUpgrade);
        Item additionalItem1 = items.get(addItem1);
        Item additionalItem2 = items.get(addItem2);
        Item[] tempIts = {baseItem, additionalItem1, additionalItem2};

        switch (baseItem.getRarity()){
            case COMMON:
            case GREAT:
            case RARE:
                if (!areSameType(tempIts))
                    throw new IllegalArgumentException("Items to upgrade must be of the same type.");
                if (!areSameRarity(tempIts))
                    throw new IllegalArgumentException("Items to upgrade must be of the same rarity.");
                performUpgrade(baseItem, addItem1, addItem2);
                break;
            case EPIC:
                if ((baseItem.getUpgrade() == 0 || baseItem.getUpgrade() == 1) ||
                        additionalItem1.getUpgrade() == 2 && additionalItem2.getUpgrade() == 2){
                    performUpgrade(baseItem, addItem1, addItem2);
                } else{
                    throw new IllegalArgumentException("To upgrade EPIC 2 item to LEGENDARY other 2 items should also be EPIC 2");
                }
                break;
            case LEGENDARY:
                throw new IllegalArgumentException("Legendary items cannot be upgraded");
        }
        Collections.sort(items, Collections.reverseOrder());
    }

    public void add(String name, String rar){
        try{
            Rarity rarity = Rarity.valueOf(rar.toUpperCase());
            Item item = new Item(name, rarity);
            items.add(item);
            System.out.println(item + " is added");
            Collections.sort(items, Collections.reverseOrder());
        } catch (IllegalArgumentException e){
            System.out.println("Invalid rarity. Please use COMMON, GREAT, RARE, EPIC, or LEGENDARY.");
        }
    }

    public void addRandom(String name){
        Rarity rarity = null;
        int randomNum = (int)(Math.random() * 100);
        if (randomNum < 2) rarity = Rarity.LEGENDARY;
        else if (randomNum < 10) rarity = Rarity.EPIC;
        else if (randomNum < 25) rarity = Rarity.RARE;
        else if (randomNum < 50) rarity = Rarity.GREAT;
        else rarity = Rarity.COMMON;
        add(name, rarity.toString());
    }

    public void drop(int index) throws IllegalArgumentException{
        if (index < 0 || index >= items.size()) throw new IllegalArgumentException("Invalid index.");
        Item temp = items.get(index);
        items.remove(index);
        System.out.println(temp + " is dropped");
    }

    public String toString(){
        if (items.isEmpty()) return "Empty";

        String inventory = "";
        for (int i = 0; i < items.size(); i++) inventory += (i + 1) + ". " + items.get(i).toString() + "\n";
        return inventory;
    }
    
    private boolean areSameRarity(Item[] its){
        Rarity rar = its[0].getRarity();

        for (Item it : its)
            if (it.getRarity() != rar) return false;
        return true;
    }

    private boolean areSameType(Item[] its){
        String type = its[0].getName();

        for (Item it : its)
            if (!it.getName().equalsIgnoreCase(type)) return false;

        return true;
    }

    private void performUpgrade(Item baseItem, int addItem1, int addItem2) {
        String updateMessage = baseItem.upgrade();
        items.remove(addItem2);
        items.remove(addItem1);
        System.out.println(updateMessage);
    }
}
