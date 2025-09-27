package se233.chapter1;

import javafx.scene.input.DataFormat;

import java.io.Serializable;

// Imports are omitted
public class BasedEquipment implements Serializable {
    public static final DataFormat DATA_FORMAT = new DataFormat("BasedEquipment");

    protected String name;
    protected String imgpath;

    public String getName() {
        return name;
    }

    public String getImagepath() {
        return imgpath;
    }

    public void setImagepath(String imgpath) {
        this.imgpath = imgpath;
    }
}
