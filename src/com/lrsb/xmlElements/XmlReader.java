/**
 * LRSB - Linear Representation Spreedsheat Builder - Pauses Analysis of XML files generated by Translog II software.
 * For Translog II details, see http://bridge.cbs.dk/platform/?q=Translog-II.
 *
 * Developed with a grant from the Federal University of Uberlândia, Brazil (Project 2014PBG000883, Supervisor:
 * Prof. Dr. Igor A. Lourenço da Silva)
 *
 * Copyright (C) 2015 Gabriel Ed. da Silva
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses/.
 */
package com.lrsb.xmlElements;

import com.lrsb.log.LogControl;
import com.lrsb.model.StringTreatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * Requisitos Funcionais: RF04,RF05,RF06,RF07.
 *
 * Classe responsável pela leitura do arquivo xml.
 *
 * @author gabriel
 */
public class XmlReader {

    /**
     * Expressão xpath responsável pela leitura do campo Languages.
     */
    private static final String LANGUAGES = "//Languages";

    /**
     * Expressão xpath responsável pela leitura do campo SourceTextUTF8 Campo
     * referente ao texto fonte.
     */
    private static final String SOURCE_TEXT = "//SourceTextUTF8";

    /**
     * Expressão xpath responsável pela leitura do campo Subject Pode não
     * existir em alguns documentos.
     */
    private static final String SUBJECT = "//Subject";

    /**
     * Expressão xpath responsável pela leitura do campo Key.
     */
    private static final String KEY = "//Key";

    /**
     * Expressão xpath responsável pela leitura do campo mouse.
     */
    private static final String MOUSE = "//Mouse";

    /**
     * Expressão xpath responsável pela leitura das janelas válidas. Win = 1
     * representa o texto fonte. Win = 2 represneta o texto alvo.
     */
    private static final String FIXATION = "//Fix[@Win='1' or @Win='2']";

    /**
     * Expressão xpath responsável pela leitura do tempo final da atividade.
     */
    private static final String FINAL_TIME = "//System[@Value='STOP']";
    
    /**
     * Encode padrão do sistema.
     */
    private static final String ENCODE = System.getProperty("file.encoding");

    /**
     * Requisitos Funcionais: RF04,RF05,RF06,RF07.
     *
     * Método responsável pela leitura do arquivo .xml É o único método público
     * da classe.
     *
     * @param filename - Caminho para o documento a ser processado.
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
            
            LogControl.log("Iniciando abertura do arquivo: ["+filename+"].");
            
            File file = new File(filename);
               
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODE));

            String textLine;

            /**
             * Valida o RF05 iniciando o valor do subject com o nome do
             * documento Caso exista um valor de subject no documento, o mesmo
             * irá sobrepor esse valor posteriormente.
             */
            xmlDocument.setSubject(StringTreatment.getXMLName(filename));

            /**
             * Faz a leitura de todas as linhas do documento, a leitura
             * sequêncial garante um desempenho superior a busca dos nodes no
             * xpath.
             */
                  
            LogControl.log("Iniciando leitura do arquivo: ["+filename+"].");
            
            while (bufferedReader.ready()) {
                textLine = bufferedReader.readLine();

                if (textLine.contains("<Fix")) {
                    xmlDocument.getFixationList().add(parseFixation(textLine));
                } else if (textLine.contains("<Key Time")) {
                    xmlDocument.getActionList().add(parseKey(textLine));
                } else if (textLine.contains("<Mouse")) {
                    xmlDocument.getActionList().add(parseMouse(textLine));
                } else if (textLine.contains("<System") && textLine.contains("STOP")) {
                    xmlDocument.setStopTime(parseFinalTime(textLine));
                } else if (textLine.contains("<Subject")) {
                    xmlDocument.setSubject(parseSubject(textLine));
                } else if (textLine.contains("<SourceTextUTF8>")) {
                    /**
                     * Alguns arquivos possuem o texto fonte em mais de uma
                     * linha por isso é necessário o tratamento abaixo.
                     */
                    if (!textLine.contains("</SourceTextUTF8>")) {
                        while (bufferedReader.ready()) {
                            textLine = textLine + bufferedReader.readLine();
                            if (textLine.contains("</SourceTextUTF8>")) {
                                break;
                            }
                        }
                    }

                    xmlDocument.setSourceText(parseSourceText(textLine));

                } else if (textLine.contains("<Languages")) {
                    
                    textLine = StringTreatment.replaceLanguage(textLine);
                    String[] translationLanguages = parseElement(textLine);
                    xmlDocument.setSourceLanguage(translationLanguages[0]);
                    xmlDocument.setTargetLanguage(translationLanguages[1]);

                    /**
                     * RF 06 – Caso o campo task esteja vazio a aplicação deve
                     * colocar o valor NA.
                     */
                    if (translationLanguages[2] != null) {
                        xmlDocument.setTask(translationLanguages[2]);
                    } else {
                        xmlDocument.setTask("NA");
                    }
                }
            }
            
            LogControl.log("Leitura do arquivo ["+filename+"] finalizada");

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
     * Tratamento de Erros: Por não ter um padrão para os itens de uma fixação é
     * necessário algumas verificação para não chamar o método getNodeValue() de
     * um atributo inexiste.
     *
     * @param fixationText - String contendo uma fixação a ser processada.
     * @return Objeto da classe Fix representando o node lido.
     * @throws Exception
     */
    private static Fix parseFixation(String fixationText) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(fixationText);
        XPath xPath = XPathFactory.newInstance().newXPath();

        Node node = (Node) xPath.compile(FIXATION).evaluate(xmlDocument, XPathConstants.NODE);

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

            String itemText = node.getAttributes().getNamedItem("Text").getNodeValue();

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

            return new TextFix(time, tt, win, cursor, block, itemText, x, y, dur);
        } else {
            return new Fix(time, tt, win, cursor);
        }
    }

    /**
     * Realiza o parse das ações de tecla por xpath.
     *
     * Tratamento de Erros: Por não ter um padrão para os itens de uma fixação é
     * necessário algumas verificação para não chamar o método getNodeValue() de
     * um atributo inexiste.
     *
     * @param text - String com um ação de tecla a ser processada.
     * @return Objeto da classe Key representando o node lido.
     * @throws Exception
     */
    private static Key parseKey(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(KEY).evaluate(xmlDocument, XPathConstants.NODE);

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
     * Realiza o parse de uma ação de mouse por xpath.
     *
     * @param text - String contendo uma ação de mouse a ser processada.
     * @return Objeto da classe Action representando o nó lido.
     * @throws Exception
     */
    private static Action parseMouse(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(MOUSE).evaluate(xmlDocument, XPathConstants.NODE);

        Integer time = Integer.parseInt(node.getAttributes().getNamedItem("Time").getNodeValue());
        Integer cursor = Integer.parseInt(node.getAttributes().getNamedItem("Cursor").getNodeValue());
        String value = node.getAttributes().getNamedItem("Value").getNodeValue();

        return new Action(time, cursor, value, "Mouse");
    }

    /**
     * Realiza o parse do tempo final da tarefa por xpath.
     *
     * @param text String com o tempo final a ser processada.
     * @return Tempo final da tarefa.
     * @throws Exception
     */
    private static Integer parseFinalTime(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(FINAL_TIME).evaluate(xmlDocument, XPathConstants.NODESET);
        return Integer.parseInt(nodeList.item(0).getAttributes().getNamedItem("Time").getNodeValue());
    }

    /**
     * Requisitos funcionáis: RF04,RF05.
     *
     * Realiza o parse do subject por xpath.
     *
     * @param text String com o texto do subject a ser processado.
     * @return O subject do documento.
     * @throws Exception
     */
    private static String parseSubject(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(SUBJECT).evaluate(xmlDocument, XPathConstants.NODESET);
        return nodeList.item(0).getTextContent();
    }

    /**
     * Requisitos Funcionais: RF04,RF07
     *
     * Realiza o parse do texto fonte por xpath.
     *
     * @param text String com o texto do source a ser processado.
     * @return As duas primeiras palavras do texto fonte, conforme requisito.
     * @throws Exception
     */
    private static String parseSourceText(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(SOURCE_TEXT).evaluate(xmlDocument, XPathConstants.NODESET);
        return StringTreatment.getFirstTwoWords(nodeList.item(0).getTextContent());
    }

    /**
     * Requisitos Funcionais: RF04,RF06.
     *
     * Realiza o parse do campo Languages por xpath.
     *
     * @param text String com o texto a ser processado.
     * @return Um Array de String com a linguagem do texto fonte, linguagem do
     * texto alvo e a tarefa na respectiva ordem.
     * @throws Exception
     */
    private static String[] parseElement(String text) throws Exception {
        org.w3c.dom.Document xmlDocument = loadXMLFromString(text);
        XPath xPath = XPathFactory.newInstance().newXPath();
        String[] translationLanguages = new String[3];

        NodeList nodeList = (NodeList) xPath.compile(LANGUAGES).evaluate(xmlDocument, XPathConstants.NODESET);
        translationLanguages[0] = nodeList.item(0).getAttributes().getNamedItem("source").getNodeValue();
        translationLanguages[1] = nodeList.item(0).getAttributes().getNamedItem("target").getNodeValue();
        translationLanguages[2] = nodeList.item(0).getAttributes().getNamedItem("task").getNodeValue();
        return translationLanguages;
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
        InputSource inputSource = new InputSource(new StringReader(xml));
        return builder.parse(inputSource);
    }
}
