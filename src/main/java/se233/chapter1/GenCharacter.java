package se233.chapter1;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand = new Random();
        int type = rand.nextInt(3) + 1; // สุ่มเลข 1 หรือ 2
        int basedDef = rand.nextInt(50) + 1; // สุ่มเลข 1-50
        int basedRes = rand.nextInt(50) + 1; // สุ่มเลข 1-50

        if (type == 1) {
            character = new MagicalCharacter("MagicChar1", "assets/wizard.png", basedDef, basedRes);
        }else if(type == 2) {character = new Battlemage("Battlemage", "assets/yuri.png", basedDef, basedRes);
        }else {
            character = new PhysicalCharacter("PhysicalChar1", "assets/knight.png", basedRes, basedRes);
        }
        return character;
    }
}
