package se233.chapter1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

import static se233.chapter1.HelloApplication.*;

// Imports are omitted
public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            setMainCharacter(GenCharacter.setUpCharacter());
            refreshPane();
        }
    }
    public static void onDragDetected(MouseEvent event, BasedEquipment equipment,
                                      ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());

        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);  // 👈 ตรงนี้
        db.setContent(content);

        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard(); // ดึงข้อมูลที่กำลังถูกลากมา
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);

        // ตรวจสอบ 2 เงื่อนไข
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && // 1. เป็นไอเท็มที่ถูกต้องหรือไม่?
                retrievedEquipment.getClass().getSimpleName().equals(type)) { // 2. เป็นประเภท (เช่น "Weapon") ที่ช่องนี้ต้องการหรือไม่?

            event.acceptTransferModes(TransferMode.MOVE); // ถ้าใช่, "อนุญาต" ให้วางได้ (เมาส์จะเปลี่ยนรูป)
        }
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();

        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharacter character = getMainCharacter();

            // --- ส่วนของ Weapon ---
            if (retrievedEquipment instanceof Weapon) {
                // 1. แปลงร่าง (cast) ไอเทมเป็น Weapon เพื่อดึงข้อมูลเฉพาะทาง
                Weapon weapon = (Weapon) retrievedEquipment;

                // 2. ดึงค่า type ของตัวละครและอาวุธออกมาเก็บในตัวแปรเพื่อเช็ก
                DamageType characterType = character.getType(); // สมมติว่ามีเมธอด .getType() ในคลาสตัวละคร
                DamageType weaponType = weapon.getDamageType(); // สมมติว่ามีเมธอด .getDamageType() ในคลาสอาวุธ

                // 3. เปรียบเทียบ type (และเผื่อกรณีตัวละครเป็น type "All" ที่ใส่ได้ทุกอย่าง)
                if (characterType == weaponType || characterType == DamageType.All) {
                    // สวมใส่สำเร็จ: ทำแค่สิ่งที่เกี่ยวกับการสวมใส่
                    setEquippedWeapon(weapon);
                    character.equipWeapon(weapon);
                    addItemToList(weapon);

                } else {
                    event.setDropCompleted(false);
                    return;
                }

                // --- ส่วนของ Armor ---
            } else if (retrievedEquipment.getClass().getSimpleName().equals("Armor")) {
                Armor armor = (Armor) retrievedEquipment;
                DamageType characterType = character.getType();
                if (characterType != DamageType.All) {
                    // สวมใส่สำเร็จ: ทำแค่สิ่งที่เกี่ยวกับการสวมใส่
                    setEquippedArmor(armor);
                    character.equipArmor(armor);
                    addItemToList(armor);
                } else {
                    event.setDropCompleted(false);
                    return;
                }
            }
            setMainCharacter(character);
            refreshPane();

            // 2. อัปเดตหน้าจอ (UI)
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1); // ลบรูปไอเท็มเก่าออก
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName()); // เปลี่ยนข้อความ Label

            ImageView imgView = new ImageView();
            imgView.setImage(new Image(HelloApplication.class.getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView); // เพิ่มรูปไอเท็มใหม่เข้าไป

            dragCompleted = true;
            System.out.println(dragCompleted);
        }
        event.setDropCompleted(dragCompleted); // 3. แจ้งระบบว่าการวางสำเร็จแล้ว
    }
    public static void onEquipDone(DragEvent event) {
            Dragboard dragboard = event.getDragboard();
            ArrayList<BasedEquipment> allEquipments = getAllEquipments();
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(
                    BasedEquipment.DATA_FORMAT);
            int pos = -1;
            for (int i = 0; i < allEquipments.size(); i++) {
                if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                    pos = i;
                }
            }
            if (pos != -1) {
                allEquipments.removeIf(equipment -> event.isAccepted() && equipment.getName().equals(retrievedEquipment.getName()));
            }
            setAllEquipments(allEquipments);
            refreshPane();
    }
    public static void backintime()
    {
        BasedCharacter character = getMainCharacter();
        if(character != null) {
            character.outArmor();
            character.outWeapon();
        }
        ArrayList<BasedEquipment> allEquipments = GenItemList.setUpItemList();
        setAllEquipments(allEquipments);
        refreshPanenull();
    }
    private static void addItemToList(BasedEquipment item) {
        ArrayList<BasedEquipment> allEquipments = GenItemList.setUpItemList();
        allEquipments.add(item);
        setAllEquipments(allEquipments);
        refreshPane();
    }


}
