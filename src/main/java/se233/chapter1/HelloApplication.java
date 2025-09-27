package se233.chapter1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {
        private static Scene mainScene;
        private static BasedCharacter mainCharacter = null;
        private static ArrayList<BasedEquipment> allEquipments = null;
        private static Weapon equippedWeapon = null;
        private static Armor equippedArmor = null;
        private static CharacterPane characterPane = null;
        private static EquipPane equipPane = null;
        private static InventoryPane inventoryPane = null;
    public static Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
    public  static ArrayList<BasedEquipment> getAllEquipments() {
        return allEquipments;
    }
    public static void setEquippedWeapon(Weapon weapon) {
        HelloApplication.equippedWeapon = weapon;
    }
    public  static  Armor getEquippedArmor() {
        return equippedArmor;
    }
    public  static  void  setAllEquipments(ArrayList<BasedEquipment> allEquipments) {
        HelloApplication.allEquipments = allEquipments;
    }
    public  static  void setEquippedArmor(Armor armor) {
        HelloApplication.equippedArmor = armor;
    }
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Chapter1");
            primaryStage.setResizable(false);
            primaryStage.show();
            mainCharacter = GenCharacter.setUpCharacter();
            allEquipments = GenItemList.setUpItemList();
            Pane mainPane = getMainPane();
            mainScene = new Scene(mainPane);
            primaryStage.setScene(mainScene);
        }

        public Pane getMainPane() {
            BorderPane mainPane = new BorderPane();
            characterPane = new CharacterPane();
            equipPane = new EquipPane();
            inventoryPane = new InventoryPane();
            refreshPane();
            mainPane.setCenter(characterPane);
            mainPane.setLeft(equipPane);
            mainPane.setBottom(inventoryPane);
            return mainPane;
        }

        public static void refreshPane() {
            characterPane.drawPane(mainCharacter);
            equipPane.drawPane(equippedWeapon, equippedArmor);
            inventoryPane.drawPane(allEquipments);
        }
    public static void refreshPanenull() {
        characterPane.drawPane(mainCharacter);
        equippedWeapon = null;
        equippedArmor = null;
        equipPane.drawPane(equippedWeapon, equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }

        public static BasedCharacter getMainCharacter() {
            return mainCharacter;
        }



        public static void setMainCharacter(BasedCharacter mainCharacter) {
            HelloApplication.mainCharacter = mainCharacter;
        }

        public static void main(String[] args) {
            launch(args);
        }

}