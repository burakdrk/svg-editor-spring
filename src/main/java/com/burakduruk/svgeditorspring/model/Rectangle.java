package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class Rectangle implements SVGElement {
    private String x;
    private String y;
    private String width;
    private String height;
    private String units;

    private List<Attribute> otherAttributes;

    public Rectangle(String x, String y, String width, String height, String units) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.units = units;

        this.otherAttributes = new LinkedList<>();
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getUnits() {
        return units;
    }

    @Override
    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public void modify(Attribute newAttribute) {
        switch (newAttribute.getName()) {
            case "x":
                this.x = newAttribute.getValue();
                return;
            case "y":
                this.y = newAttribute.getValue();
                return;
            case "width":
                this.width = newAttribute.getValue();
                return;
            case "height":
                this.height = newAttribute.getValue();
                return;
        }

        for (Attribute attribute : otherAttributes) {
            if (attribute.getName().equals(newAttribute.getName())) {
                attribute.setValue(newAttribute.getValue());
                return;
            }
        }
    }
}
