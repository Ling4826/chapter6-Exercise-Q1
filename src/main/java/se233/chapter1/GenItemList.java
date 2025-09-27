package se233.chapter1;

import java.util.ArrayList;

// Imports are omitted
public class GenItemList {
    public static ArrayList<BasedEquipment> setUpItemList() {
        ArrayList<BasedEquipment> itemLists = new ArrayList<BasedEquipment>(5);
        itemLists.add(new Weapon("Sword",10,DamageType.physical,"assets/sword.png"));
        itemLists.add(new Weapon("Gun",20,DamageType.physical,"assets/gun.png"));
        itemLists.add(new Weapon("Staff",30,DamageType.magical,"assets/staff.png"));
        itemLists.add(new Armor("Shirt",0,50,"assets/shirt.png"));
        itemLists.add(new Armor("Armor",50,0,"assets/armor.png"));
        itemLists.add(new Weapon("Gattling_Tank",100,DamageType.physical,"assets/Gattling_Tank.jpg"));
        itemLists.add(new Weapon("yclnicon",999,DamageType.magical,"assets/yclnicon.jpg"));
        itemLists.add(new Armor("MCV",200,100,"assets/MCV.jpg"));
        return itemLists;
    }

}
