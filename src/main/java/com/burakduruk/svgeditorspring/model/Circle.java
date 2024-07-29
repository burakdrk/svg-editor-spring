package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class Circle implements SVGElement {
    private float cx;
    private float cy;
    private float r;
    private final String units;

    private final List<Attribute> otherAttributes;

    public Circle() {
        this.cx = 0;
        this.cy = 0;
        this.r = 0;
        this.units = "";

        this.otherAttributes = new LinkedList<>();
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getR() {
        return r;
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
        return SVGElementType.CIRC;
    }

    @Override
    public void modify(Attribute newAttribute) {
        switch (newAttribute.getName()) {
            case "cx":
                this.cx = Float.parseFloat(newAttribute.getValue());
                return;
            case "cy":
                this.cy = Float.parseFloat(newAttribute.getValue());
                return;
            case "r":
                this.r = Float.parseFloat(newAttribute.getValue());
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
