package com.burakduruk.svgeditorspring.controller;

import com.burakduruk.svgeditorspring.exception.StorageFileNotFoundException;
import com.burakduruk.svgeditorspring.model.GetFilesResponse;
import com.burakduruk.svgeditorspring.model.SVGElementType;
import com.burakduruk.svgeditorspring.service.SVGParser;
import com.burakduruk.svgeditorspring.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileUploadController {
    private final StorageService storageService;
    private final SVGParser svgParser;

    @Autowired
    public FileUploadController(StorageService storageService, SVGParser svgParser) {
        this.storageService = storageService;
        this.svgParser = svgParser;
    }

    @GetMapping("/get_files")
    public List<GetFilesResponse> listUploadedFiles() throws IOException {
        var files = this.storageService.loadAll();
        var returnList = new ArrayList<GetFilesResponse>();

        files.toList().forEach(e -> {
            File svgFile = null;
            try {
                svgFile = storageService.loadAsResource(e.getFileName().toString()).getFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            var svg = svgParser.parseSVG(svgFile);

            returnList.add(new GetFilesResponse(
                    e.getFileName().toString(),
                    svg.getNumElements(SVGElementType.CIRC),
                    svg.getNumElements(SVGElementType.RECT),
                    svg.getNumElements(SVGElementType.GROUP),
                    svg.getNumElements(SVGElementType.PATH),
                    (int) (e.toFile().length() / 1024)
            ));
        });

        return returnList;
    }

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/svg+xml")
                .body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/hello";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}

