package com.burakduruk.svgeditorspring.model;

import java.util.LinkedList;
import java.util.List;

public class SVG {
    private String namespace;
    private String title;
    private String description;

    private List<SVGElement> elements;

    public SVG(String namespace, String title, String description) {
        this.namespace = namespace;
        this.title = title;
        this.description = description;

        this.elements = new LinkedList<>();
    }

    public String getDescription() {
        return description;
    }
}
