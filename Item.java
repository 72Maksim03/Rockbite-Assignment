import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable {

    private String name;
    private Rarity rarity;
    private int upgrade;

    public Item(String itemName, Rarity rar, int upgCount){
        name = itemName;
        rarity = rar;
        upgrade = upgCount;
    }

    public Item(String itemName, Rarity rar){this(itemName, rar, 0);}

    public String getName(){return name;}
    public Rarity getRarity(){return rarity;}
    public int getUpgrade(){return upgrade;}
    
    public String upgrade(){
        if (upgrade < 2){
            if (getRarity() == Rarity.LEGENDARY) return "Item is already at maximum rarity";
            upgrade++;
            return "Item upgraded";
        }
        else{
            if (rarity == Rarity.COMMON){
                rarity = Rarity.GREAT;
                upgrade = 0;
                return "Item upgraded to Great";
            } else if(rarity == Rarity.GREAT){
                rarity = Rarity.RARE;
                upgrade = 0;
                return "Item upgraded to Rare";
            } else if(rarity == Rarity.RARE){
                rarity = Rarity.EPIC;
                upgrade = 0;
                return "Item upgraded to Epic";
            } else if(rarity == Rarity.EPIC){
                rarity = Rarity.LEGENDARY;
                upgrade = 0;
                return "Item upgraded to Legendary";
            } else{
                return "Item is already at maximum rarity";
            }
        }
    }

    public String toString(){
        String upgStr = (upgrade == 0) ? "" : (" " + upgrade);

        return rarity.toString() + upgStr + " " + name;
    }

    public int compareTo(Item other) {
        int rarityComparison = this.rarity.compareTo(other.rarity);
        if (rarityComparison != 0) {
            return rarityComparison;
        }
        return Integer.compare(this.getUpgrade(), other.getUpgrade());
    }
}
