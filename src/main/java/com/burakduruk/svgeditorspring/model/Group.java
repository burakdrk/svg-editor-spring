package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class Group implements SVGElement {
    private List<SVGElement> elements;
    private List<Attribute> otherAttributes;

    public Group() {
        this.elements = new LinkedList<>();
        this.otherAttributes = new LinkedList<>();
    }

    public List<SVGElement> getElements() {
        return elements;
    }

    public void addElement(SVGElement element) {
        elements.add(element);
    }

    @Override
    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public SVGElementType getType() {
        return SVGElementType.GROUP;
    }

    @Override
    public void modify(Attribute newAttribute) {
        for (Attribute attribute : otherAttributes) {
            if (attribute.getName().equals(newAttribute.getName())) {
                attribute.setValue(newAttribute.getValue());
                return;
            }
        }

        // If the attribute does not exist, add it
        otherAttributes.add(newAttribute);
    }
}
