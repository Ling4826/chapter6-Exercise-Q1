package se233.chapter1;
// Imports are omitted
public class Battlemage extends BasedCharacter {

    public Battlemage(String name, String imgpath, int basedDef, int basedRes) {
        this.name = name;
        this.type = DamageType.All;
        this.imgpath = imgpath;
        this.fullHp = 40;
        this.basedPow = 40;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}
