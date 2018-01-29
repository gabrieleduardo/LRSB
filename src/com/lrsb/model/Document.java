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
package com.lrsb.model;

import java.util.ArrayList;

/**
 * RF04,RF18
 *
 * Classe responsável por armazenar as informações processadas do arquivo XML.
 *
 * @author gabriel
 */
public class Document {

    /**
     * Assunto do documento.
     */
    private String subject;
    
    /**
     * Tarefa realizada no documento. As tarefas podem ser de 
     * pós-edição (post-editing) ou tradução (translation).
     */
    private String task;
    
    /**
     * Documento fonte.
     */
    private String sourceText;
    
    /**
     * Idioma do documento fonte.
     */
    private String sourceLanguage;
    
    /**
     * Idioma do documento alvo.
     */
    private String targetLanguage;
    
    /**
     * Lista de segmentos do documento.
     */
    private ArrayList<Segment> segments = new ArrayList<>();

    /**
     * Método get do campo subject.
     * @return Retorna o valor do campo subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Método set do campo subject.
     * @param subject Novo valor para o campo subject.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Método get do campo task.
     * @return Retorna o valor do campo task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Método set do campo task.
     * @param task Novo valor para o campo task.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Método get do campo sourceText.
     * @return Retorna o valor do campo sourceText.
     */
    public String getSourceText() {
        return sourceText;
    }

    /**
     * Método set do campo sourceText.
     * @param sourceText Novo valor para o campo sourceText.
     */
    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    /**
     * Método get do campo sourceLanguage.
     * @return Retorna o valor do campo sourceLanguage.
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    /**
     * Método set do campo sourceLanguage.
     * @param sourceLanguage Novo valor para o campo sourceLanguage.
     */
    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    /**
     * Método get do campo targetLanguage.
     * @return Retorna o valor do campo targetLanguage.
     */
    public String getTargetLanguage() {
        return targetLanguage;
    }

    /**
     * Método set do campo targetLanguage.
     * @param targetLanguage Novo valor para o campo targetLanguage.
     */
    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    /**
     * Método get do campo segments.
     * @return Retorna o valor do campo segments
     */
    public ArrayList<Segment> getSegments() {
        return segments;
    }

    /**
     * Método set do campo segments
     * @param segments Novo valor para o campo segments
     */
    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Cria uma string formatada com os dados do documento para serem salvos no
     * formato CSV.
     *
     * @return String formatada.
     */
    public String documentToCSV() {
        String str = "";
        String aux = subject + "," + task + "," + sourceText + "," + sourceLanguage + "," + targetLanguage + ",";
        
        for (Segment s : segments) {
            str = str + aux + s.segmentToCSV();
        }

        return str;
    }
}
