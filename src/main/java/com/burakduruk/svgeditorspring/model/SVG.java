package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class SVG implements SVGElement {
    private String namespace;
    private String title;
    private String description;

    private List<SVGElement> elements;
    private List<Attribute> otherAttributes;

    public SVG(String namespace, String title, String description) {
        this.namespace = namespace;
        this.title = title;
        this.description = description;

        this.elements = new LinkedList<>();
        this.otherAttributes = new LinkedList<>();
    }

    public String getDescription() {
        return description;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getTitle() {
        return title;
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
