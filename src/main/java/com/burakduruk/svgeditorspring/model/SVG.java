package com.burakduruk.svgeditorspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.List;

public class SVG implements SVGElement {
    private String namespace;
    private String title;

    @JsonProperty("desc")
    private String description;

    private final List<SVGElement> elements;
    private final List<Attribute> otherAttributes;

    public SVG() {
        this.elements = new LinkedList<>();
        this.otherAttributes = new LinkedList<>();
        this.description = "";
        this.namespace = "";
        this.title = "";
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SVGElement> getElements() {
        return elements;
    }

    public void addElement(SVGElement element) {
        elements.add(element);
    }

    public int getNumElements(SVGElementType type) {
        return (int) elements.stream().filter(e -> e.getType() == type).count();
    }

    public int getNumElements() {
        return elements.size();
    }

    @Override
    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public SVGElementType getType() {
        return SVGElementType.SVG_IMG;
    }

    @Override
    public void modify(Attribute newAttribute) {
        if (newAttribute.getName().equals("xmlns")) {
            return;
        }

        for (Attribute attribute : otherAttributes) {
            if (attribute.getName().equals(newAttribute.getName())) {
                attribute.setValue(newAttribute.getValue());
                return;
            }
        }

        // If the attribute does not exist, add it
        otherAttributes.add(newAttribute);
    }

    @Override
    public String toString() {
        return "SVG{" +
                "namespace='" + namespace + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", elements=" + elements +
                ", otherAttributes=" + otherAttributes +
                '}';
    }
}
