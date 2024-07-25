package com.burakduruk.svgeditorspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SVGController {

    @GetMapping("/svg")
    public String getSVG() {
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"100\" height=\"100\">\n" +
                "  <rect width=\"100\" height=\"100\" style=\"fill:rgb(0,0,255);stroke-width:1;stroke:rgb(0,0,0)\" />\n" +
                "</svg>";
    }
}
