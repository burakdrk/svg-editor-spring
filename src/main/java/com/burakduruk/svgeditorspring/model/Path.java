package com.burakduruk.svgeditorspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class Path implements SVGElement {
    @JsonProperty("d")
    private String data;
    
    private final List<Attribute> otherAttributes;

    public Path() {
        this.otherAttributes = new LinkedList<>();
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

    @Override
    public SVGElementType getType() {
        return SVGElementType.PATH;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<path ");
        sb.append("d=\"").append(data).append("\" ");
        for (Attribute attribute : otherAttributes) {
            sb.append(attribute.toString()).append(" ");
        }
        sb.append("/>");
        return sb.toString();
    }
}
