package com.burakduruk.svgeditorspring.controller;

import com.burakduruk.svgeditorspring.model.SVG;
import com.burakduruk.svgeditorspring.service.SVGParser;
import com.burakduruk.svgeditorspring.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class SVGController {
    private final StorageService storageService;
    private final SVGParser svgParser;

    public SVGController(StorageService storageService, SVGParser svgParser) {
        this.storageService = storageService;
        this.svgParser = svgParser;
    }

    @GetMapping("/get_svg")
    public SVG getSVG(@RequestParam String filename) {
        File svgFile = null;
        try {
            svgFile = storageService.loadAsResource(filename).getFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        var svg = svgParser.parseSVG(svgFile);

        return svg;
    }
}
