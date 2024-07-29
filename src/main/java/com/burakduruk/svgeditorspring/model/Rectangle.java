package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class Rectangle implements SVGElement {
    private float x;
    private float y;
    private float width;
    private float height;
    private final String units;

    private final List<Attribute> otherAttributes;

    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.units = "";

        this.otherAttributes = new LinkedList<>();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
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
    public SVGElementType getType() {
        return SVGElementType.RECT;
    }

    @Override
    public void modify(Attribute newAttribute) {
        switch (newAttribute.getName()) {
            case "x":
                this.x = Float.parseFloat(newAttribute.getValue());
                return;
            case "y":
                this.y = Float.parseFloat(newAttribute.getValue());
                return;
            case "width":
                this.width = Float.parseFloat(newAttribute.getValue());
                return;
            case "height":
                this.height = Float.parseFloat(newAttribute.getValue());
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
