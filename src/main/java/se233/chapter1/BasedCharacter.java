package se233.chapter1;

public class BasedCharacter {

    protected String name, imgpath;
    protected DamageType type  ;
    protected Integer fullHp, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor armor;
    public void equipWeapon( Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }
    public void outWeapon() {
        if(weapon != null)
            this.power = this.power - weapon.getPower();
        
        weapon = null;
    }
    public void equipArmor( Armor armor) {
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance= this.basedRes + armor.getResistance();
    }
    public void outArmor(){
    if(armor != null)
    {
        this.defense = this.defense - armor.getDefense();
        this.resistance = this.resistance - armor.getResistance();
        armor = null;
    }
    }
    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public String getImagepath() {
        return imgpath;
    }

    public Integer getFullHp() {
        return fullHp;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getDefense() {
        return defense;
    }
    public  Integer getResistance() {
        return resistance;
    }

    public DamageType getType() {
        return type;
    }
}
