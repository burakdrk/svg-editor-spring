package com.burakduruk.svgeditorspring.model;

import java.util.List;

public class Path implements SVGElement {
    private String data;
    private List<Attribute> otherAttributes;

    public Path(String data, List<Attribute> otherAttributes) {
        this.data = data;
        this.otherAttributes = otherAttributes;
    }

    public String getData() {
        return data;
    }

    @Override
    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public void modify(Attribute newAttribute) {
        if (newAttribute.getName().equals("d")) {
            this.data = newAttribute.getValue();
            return;
        }

        for (Attribute attribute : otherAttributes) {
            if (attribute.getName().equals(newAttribute.getName())) {
                attribute.setValue(newAttribute.getValue());
                return;
            }
        }

        otherAttributes.add(newAttribute);
    }
}
