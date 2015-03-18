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
 * Requisitos Funcionais: RF04,RF05,RF06,RF07
 * 
 * Classe responsável pela leitura do arquivo xml
 * @author gabriel
 */
public class XmlReader {

    /**
     * Exprassão xpath responsável pela leitura do campo Languages
     */
    private static final String expressionLanguages = "//Languages";

    /**
     * Exprassão xpath responsável pela leitura do campo SourceTextUTF8
     * Campo referente ao texto fonte.
     */
    private static final String expressionSourceText = "//SourceTextUTF8";

    /**
     * Exprassão xpath responsável pela leitura do campo Subject
     * Pode não existir em alguns documentos.
     */
    private static final String expressionSubject = "//Subject";

    /**
     * Exprassão xpath responsável pela leitura do campo Key
     */
    private static final String expressionKey = "//Key";

    /**
     * Exprassão xpath responsável pela leitura do campo mouse
     */
    private static final String expressionMouse = "//Mouse";

    /**
     * Exprassão xpath responsável pela leitura das janelas válidas.
     * Win = 1 representa o texto fonte.
     * Win = 2 represneta o texto alvo. 
     */
    private static final String expressionfixation = "//Fix[@Win='1' or @Win='2']";

    /**
     * Exprassão xpath responsável pela leitura do tempo final da atividade
     */
    private static final String expressionFinalTime = "//System[@Value='STOP']";

    /**
     * Requisitos Funcionais: RF04,RF05,RF06,RF07
     * 
     * Método responsável pela leitura do arquivo .xml
     * É o único método público da classe.
     *
     * @param filename - Caminho para o documento a ser processado
     * @return Um objeto da classe XmlDocument populado com os dados processados
     * no documento lido.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public static XmlDocument parseDocument(String filename) throws FileNotFoundException, IOException, Exception {
        try {
            XmlDocument xmlDocument = new XmlDocument();
            File arq = new File(filename);
            FileReader fr = new FileReader(arq); //Problema aqui
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            
            /**
             * Valida o RF05 iniciando o valor do subject com o nome do documento
             * Caso exista um valor de subject no documento, o mesmo irá sobrepor
             * esse valor posteriormente.
             */
            xmlDocument.setSubject(StringTreatment.getXMLName(filename)); 

            /**
             * Faz a leitura de todas as linahs do documento, a leitura sequêncial
             * garante um desempenho superior a busca dos nodes no xpath.
             */
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
                    /**
                     * Alguns arquivos possuem o texto fonte em mais de uma linha
                     * por isso é necessário o tratamento abaixo.
                     */
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
                    
                    /**
                     * RF 06 – Caso o campo task esteja vazio a aplicação deve colocar o valor NA.
                     */
                    if(rv[2] != null){
                        xmlDocument.setTask(rv[2]);
                    }else{
                        xmlDocument.setTask("NA");
                    }
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

    /**
     * Realiza o parse das fixações por xpath. 
     * 
     * Tratamento de Erros: Por não ter um padrão para os itens de uma fixação
     * é necessário algumas verificação para não chamar o método getNodeValue()
     * de um atributo inexiste.
     * 
     * @param st - String conténdo uma fixação
     * @return Objeto da classe Fix representando o node lido.
     * @throws Exception 
     */
    private static Fix parseFixation(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        Node node = (Node) xPath.compile(expressionfixation).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer tt = Integer.parseInt(node.getAttributes().getNamedItem("TT").getNodeValue());
        Integer win = Integer.parseInt(node.getAttributes().getNamedItem("Win").getNodeValue());
        Integer cursor = null;

        if (node.getAttributes().getNamedItem("Cursor") != null) {
            cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        }

        if (node.getAttributes().getNamedItem("Text") != null) { //Then it's a TextFixation
            Integer x = null;
            Integer y = null;
            Integer block = null;
            Integer dur = null;

            String text = node.getAttributes().getNamedItem("Text").getNodeValue();

            if (node.getAttributes().getNamedItem("Block") != null) {
                block = Integer.parseInt(node.getAttributes().getNamedItem("Block").getNodeValue());
            }
            if (node.getAttributes().getNamedItem("X") != null) {
                x = Integer.parseInt(node.getAttributes().getNamedItem("X").getNodeValue());
            }

            if (node.getAttributes().getNamedItem("Y") != null) {
                y = Integer.parseInt(node.getAttributes().getNamedItem("Y").getNodeValue());
            }

            if (node.getAttributes().getNamedItem("Dur") != null) {
                dur = Integer.parseInt(node.getAttributes().getNamedItem("Dur").getNodeValue());
            }

            return new TextFix(time, tt, win, cursor, block, text, x, y, dur);
        } else {
            return new Fix(time, tt, win, cursor);
        }
    }

    /**
     * Realiza o parse das ações de tecla por xpath. 
     * 
     * Tratamento de Erros: Por não ter um padrão para os itens de uma fixação
     * é necessário algumas verificação para não chamar o método getNodeValue()
     * de um atributo inexiste.
     * @param st - String com um ação de tecla.
     * @return Objeto da classe Key representando o node lido.
     * @throws Exception 
     */
    private static Key parseKey(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(expressionKey).evaluate(xmlDocument, XPathConstants.NODE);

        Integer cursor = null;
        Integer width = null;
        Integer height = null;

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        String value = node.getAttributes().getNamedItem("Value").getNodeValue();
        String type = node.getAttributes().getNamedItem("Type").getNodeValue();

        if (node.getAttributes().getNamedItem("Cursor") != null) {
            cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        }

        if (node.getAttributes().getNamedItem("Width") != null) {
            width = Integer.parseInt(node.getAttributes().getNamedItem("Width").getNodeValue());
        }
        if (node.getAttributes().getNamedItem("Height") != null) {
            height = Integer.parseInt(node.getAttributes().getNamedItem("Height").getNodeValue());
        }
        return new Key(time, cursor, value, "Key", type, width, height);
    }

    /**
     *  Realiza o parse de uma ação de mouse por xpath. 
     * 
     * @param st - String contendo uma ação de mouse
     * @return Object da classe Action representando o nó lido.
     * @throws Exception 
     */
    private static Action parseMouse(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(expressionMouse).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        String value = node.getAttributes().getNamedItem("Value").getNodeValue();

        return new Action(time, cursor, value, "Mouse");
    }

    /**
     * Realiza o parse do tempo final da tarefa por xpath.
     * 
     * @param st - String conténdo o tempo final da tarefa
     * @return Tempo final da tarefa..
     * @throws Exception 
     */
    private static Integer parseFinalTime(String st) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(st);
        XPath xPath = XPathFactory.newInstance().newXPath();

        NodeList nodeList = (NodeList) xPath.compile(expressionFinalTime).evaluate(xmlDocument, XPathConstants.NODESET);
        Integer returnValue = Integer.parseInt(nodeList.item(0).getAttributes().getNamedItem("Time").getNodeValue());
        return returnValue;
    }

    /**
     * Requisitos funcionáis: RF04,RF05
     * 
     * Realiza o parse do subject por xpath.
     *  
     * @param st - String com o subject
     * @return O subject do documento.
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
     * Requisitos Funcionais: RF04,RF07
     * 
     * Realiza o parse do texto fonte por xpath.
     * 
     * @param st - String com o texto fonte.
     * @return As duas primeiras palavras do texto fonte, conforme requisito.
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
     * Requisitos Funcionais: RF04,RF06
     * 
     * Realiza o parse do campo Languages por xpath.
     * 
     * @param st
     * @return Um Array de String com a linguagem do texto fonte, linguagem do
     * texto alvo e a tarefa na respectiva ordem.
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

    /**
     * Cria um documento de uma String. É utilizado para poder usar xpath em
     * apenas um campo, melhorando a performance da aplicação.
     * 
     * A técnica foi retirada do seguinte tópico do site StackOverflow:
     * http://stackoverflow.com/questions/562160/in-java-how-do-i-parse-xml-as-a-string-instead-of-a-file
     */
    private static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

}
