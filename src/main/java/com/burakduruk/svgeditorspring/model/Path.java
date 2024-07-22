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

    public List<Attribute> getOtherAttributes() {
        return otherAttributes;
    }
}
