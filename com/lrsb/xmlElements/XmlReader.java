/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.xmlElements;

import com.lrsb.model.StringTreatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * TODO: Arrumar verificação de existência para os itens
 *
 * @author gabriel
 */
public class XmlReader {

    /**
     * XPath expression to get the Languages
     */
    private static final String expressionLanguages = "//Languages";

    /**
     * XPath expression to get the Source Text
     */
    private static final String expressionSourceText = "//SourceTextUTF8";

    /**
     * XPath expression to get the subject
     */
    private static final String expressionSubject = "//Subject";

    /**
     * XPath expression to gets all the Key fields
     */
    private static final String expressionKey = "//Key";

    /**
     * XPath expression to gets all the Mouse fields
     */
    private static final String expressionMouse = "//Mouse";

    /**
     * XPath expression to gets all the fixation fields
     */
    private static final String expressionfixation = "//Fix[@Win='1' or @Win='2']";

    /**
     * XPath expression to gets the stop time
     */
    private static final String expressionFinalTime = "//System[@Value='STOP']";

    /**
     * RF04,RF05,RF06,RF07
     *
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public static XmlDocument parseDocument(String filename) throws FileNotFoundException, IOException, Exception {
        try {
            XmlDocument xmlDocument = new XmlDocument();
            File arq = new File(filename);
            FileReader fr = new FileReader(arq);
            BufferedReader br = new BufferedReader(fr);
            String line;

            xmlDocument.setSubject(StringTreatment.getXMLName(filename)); // Trocar pelo nome correto.

            //Parses each file line
            while (br.ready()) {
                line = br.readLine();


                if (line.contains("<Fix")) {
                    xmlDocument.getFixList().add(parseFixation(line));
                } else if (line.contains("<Key Time")) {
                    xmlDocument.getActionList().add(parseKey(line));
                } else if (line.contains("<Mouse")) {
                    xmlDocument.getActionList().add(parseMouse(line));
                } else if (line.contains("<System") && line.contains("STOP")) {
                    xmlDocument.setFinalTime(parseFinalTime(line));
                } else if (line.contains("<Subject")) {
                    xmlDocument.setSubject(parseSubject(line));
                } else if (line.contains("<SourceTextUTF8>")) {
                    //Some sources texts use more than one line
                    if (!line.contains("</SourceTextUTF8>")) {
                        while (br.ready()) {
                            line = line + br.readLine();
                            if (line.contains("</SourceTextUTF8>")) {
                                break;
                            }
                        }
                    }

                    xmlDocument.setSt(parseSourceText(line));

                } else if (line.contains("<Languages")) {
                    line = StringTreatment.replaceLanguage(line);
                    String[] rv = parseElement(line);
                    xmlDocument.setStLanguage(rv[0]);
                    xmlDocument.setTtLanguage(rv[1]);
                    xmlDocument.setTask(rv[2]);
                }
            }

            return xmlDocument;

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File [" + filename + "] was not found or can not be read.");
        } catch (IOException ex) {
            throw new IOException("I/O Error");
        } catch (Exception ex) {
            throw new Exception("File parsing error");
        }
    }

    // Gets all the fixation fields
    private static Fix parseFixation(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        Node node = (Node) xPath.compile(expressionfixation).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer tt = Integer.parseInt(node.getAttributes().getNamedItem("TT").getNodeValue());
        Integer win = Integer.parseInt(node.getAttributes().getNamedItem("Win").getNodeValue());
        //Integer cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());

        if (node.getAttributes().getNamedItem("Text") != null) { //Then it's a TextFixation
            String text = node.getAttributes().getNamedItem("Text").getNodeValue();
            Integer block = Integer.parseInt(node.getAttributes().getNamedItem("Block").getNodeValue());
            //Integer x = Integer.parseInt(node.getAttributes().getNamedItem("X").getNodeValue());
            //Integer y = Integer.parseInt(node.getAttributes().getNamedItem("Y").getNodeValue());
            Integer dur = Integer.parseInt(node.getAttributes().getNamedItem("Dur").getNodeValue());
            return new TextFix(time, tt, win, 0, block, text, 0, 0, dur);
        } else {
            return new Fix(time, tt, win, 0);
        }
    }

    // Gets all the Key fields
    private static Key parseKey(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(expressionKey).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        String value = node.getAttributes().getNamedItem("Value").getNodeValue();
        String type = node.getAttributes().getNamedItem("Type").getNodeValue();
        //Integer Width = Integer.parseInt(node.getAttributes().getNamedItem("Width").getNodeValue());
        //Integer Height = Integer.parseInt(node.getAttributes().getNamedItem("Height").getNodeValue());

        return new Key(time, cursor, value, "Key", type, 0, 0);
    }

    // Gets all the Mouse fields
    private static Action parseMouse(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(expressionMouse).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        String value = node.getAttributes().getNamedItem("Value").getNodeValue();

        return new Action(time, cursor, value, "Mouse");
    }

    // Gets all the stop time
    private static Integer parseFinalTime(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList nodeList = (NodeList) xPath.compile(expressionFinalTime).evaluate(xmlDocument, XPathConstants.NODESET);
        Integer returnValue = Integer.parseInt(nodeList.item(0).getAttributes().getNamedItem("Time").getNodeValue());
        return returnValue;
    }

    /**
     * RF04,RF05 Get subject
     *
     * @param st
     * @return
     * @throws Exception
     */
    private static String parseSubject(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList nodeList = (NodeList) xPath.compile(expressionSubject).evaluate(xmlDocument, XPathConstants.NODESET);
        String returnValue = nodeList.item(0).getTextContent();
        return returnValue;
    }

    /**
     * RF04,RF07 Get SourceText
     *
     * @param st
     * @return
     * @throws Exception
     */
    private static String parseSourceText(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList nodeList = (NodeList) xPath.compile(expressionSourceText).evaluate(xmlDocument, XPathConstants.NODESET);
        String returnValue = nodeList.item(0).getTextContent();
        return StringTreatment.getFirstTwo(returnValue);
    }

    /**
     * RF04,RF06 Get task
     *
     * @param st
     * @return
     * @throws Exception
     */
    private static String[] parseElement(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();
        String returnValue[] = new String[3];

        NodeList nodeList = (NodeList) xPath.compile(expressionLanguages).evaluate(xmlDocument, XPathConstants.NODESET);
        returnValue[0] = nodeList.item(0).getAttributes().getNamedItem("source").getNodeValue();
        returnValue[1] = nodeList.item(0).getAttributes().getNamedItem("target").getNodeValue();
        returnValue[2] = nodeList.item(0).getAttributes().getNamedItem("task").getNodeValue();
        return returnValue;
    }

    /*
     * Creates a Document from a String
     * As seen in: http://stackoverflow.com/questions/562160/in-java-how-do-i-parse-xml-as-a-string-instead-of-a-file
     */
    private static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

}
