package se233.chapter1;

//Imports are omitted
public class Weapon extends BasedEquipment {
    private int power;
    private DamageType damageType;

    public Weapon(String name, int power, DamageType damageType, String imgpath) {
        this.name = name;
        this.imgpath = imgpath;
        this.power = power;
        this.damageType = damageType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType weaponType) {
        this.damageType = weaponType;
    }

    // The original script cuts off here.
    // It's likely overriding the toString() method to display weapon info.
    // For example:
    @Override
    public String toString() {
        return name + " (Power: " + power + ", Type: " + damageType + ")";
    }
}