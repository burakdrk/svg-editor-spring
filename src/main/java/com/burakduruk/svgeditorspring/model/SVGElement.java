package com.burakduruk.svgeditorspring.model;

import java.util.List;

public interface SVGElement {
    List<Attribute> getOtherAttributes();

    void modify(Attribute newAttribute);

    SVGElementType getType();
}
