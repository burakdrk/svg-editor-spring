package com.burakduruk.svgeditorspring.service;

import com.burakduruk.svgeditorspring.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@Service
public class SVGParser {
    private DocumentBuilderFactory factory = null;
    private Validator validator = null;

    public SVGParser() throws SAXException, IOException {
        var schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        File xsdFile = new ClassPathResource("xsd/svg.xsd").getFile();
        var schema = schemaFactory.newSchema(xsdFile);

        this.validator = schema.newValidator();

        this.factory = DocumentBuilderFactory.newInstance();
        this.factory.setNamespaceAware(true);
        this.factory.setSchema(schema);
    }

    public SVG parseSVG(File svgFile) {
        var svg = new SVG();

        try {
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            Document document = builder.parse(svgFile);

            validator.validate(new DOMSource(document));

            System.out.println("SVG is valid against the XSD.");

            populate(svg, document.getDocumentElement(), 0);
        } catch (SAXException e) {
            System.out.println("SVG is not valid against the XSD.");
            System.out.println("Reason: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return svg;
    }

    private void populate(SVG svg, Node node, int depth) {
        // Print node name and value
        printNode(node, depth);

        switch (node.getNodeName()) {
            case "svg":
                svg.setNamespace(node.getNamespaceURI());
                parseAttributes(svg, node);
                break;
            case "title":
                svg.setTitle(node.getTextContent().trim());
                break;
            case "desc":
                svg.setDescription(node.getTextContent().trim());
                break;
            case "path":
                Path path = new Path();
                parseAttributes(path, node);
                svg.addElement(path);
                break;
            case "rect":
                Rectangle rect = new Rectangle();
                parseAttributes(rect, node);
                svg.addElement(rect);
                break;
            case "circle":
                Circle circ = new Circle();
                parseAttributes(circ, node);
                svg.addElement(circ);
                break;
            default:
                break;
        }

        // Traverse child nodes
        NodeList nodeList = node.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                populate(svg, childNode, depth + 1);
            }
        }
    }

    private void parseAttributes(SVGElement element, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            element.modify(new Attribute(attribute.getNodeName(), attribute.getNodeValue()));
        }
    }

    private void printNode(Node node, int depth) {
        // Print node name and value
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node name: " + node.getNodeName() + ", Node value: " + node.getTextContent().trim());
    }
}
