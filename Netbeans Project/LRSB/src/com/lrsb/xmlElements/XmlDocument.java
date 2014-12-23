/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.xmlElements;

import java.util.ArrayList;

/**
 *
 * @author gabriel
 */
public class XmlDocument {

    private String subject;
    private String task;
    private String st;
    private String stLanguage;
    private String ttLanguage;
    private Integer finalTime;
    private ArrayList<Fix> fixList = new ArrayList<>();
    private ArrayList<Action> actionList = new ArrayList<>();

    /**
     * Método get da variável subject
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Método set da variável subject
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Método get da variável task
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Método set da variável task
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Método get da variável st
     * @return the st
     */
    public String getSt() {
        return st;
    }

    /**
     * Método set da variável st
     * @param st the st to set
     */
    public void setSt(String st) {
        this.st = st;
    }

    /**
     * Método get da variável STlanguage
     * @return the stLanguage
     */
    public String getStLanguage() {
        return stLanguage;
    }

    /**
     * Método set da variável STlanguage
     * @param stLanguage the stLanguage to set
     */
    public void setStLanguage(String stLanguage) {
        this.stLanguage = stLanguage;
    }

    /**
     * Método get da variável TTlanguage
     * @return the ttLanguage
     */
    public String getTtLanguage() {
        return ttLanguage;
    }

    /**
     * Método set da variável TTlanguage
     * @param ttLanguage the ttLanguage to set
     */
    public void setTtLanguage(String ttLanguage) {
        this.ttLanguage = ttLanguage;
    }

    /**
     * Método get da variável finalTime
     * @return the finalTime
     */
    public Integer getFinalTime() {
        return finalTime;
    }

    /**
     * Método set da variável finalTime
     * @param finalTime the finalTime to set
     */
    public void setFinalTime(Integer finalTime) {
        this.finalTime = finalTime;
    }

    /**
     * Método get da lista fixList
     * @return the fixList
     */
    public ArrayList<Fix> getFixList() {
        return fixList;
    }

    /**
     * Método set da lista fixList
     * @param fixList the fixList to set
     */
    private void setFixList(ArrayList<Fix> fixList) {
        this.fixList = fixList;
    }

    /**
     * Método get da lista actionList
     * @return the actionList
     */
    public ArrayList<Action> getActionList() {
        return actionList;
    }

    /**
     * Método set da variável actionList
     * @param actionList the actionList to set
     */
    private void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }
}
