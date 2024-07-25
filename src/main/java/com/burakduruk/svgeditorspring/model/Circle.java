package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class Circle implements SVGElement {
    private String cx;
    private String cy;
    private String r;
    private String units;

    private List<Attribute> otherAttributes;

    public Circle(String cx, String cy, String r, String units) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
        this.units = units;

        this.otherAttributes = new LinkedList<>();
    }

    public String getCx() {
        return cx;
    }

    public String getCy() {
        return cy;
    }

    public String getR() {
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
    public void modify(Attribute newAttribute) {
        switch (newAttribute.getName()) {
            case "cx":
                this.cx = newAttribute.getValue();
                return;
            case "cy":
                this.cy = newAttribute.getValue();
                return;
            case "r":
                this.r = newAttribute.getValue();
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
