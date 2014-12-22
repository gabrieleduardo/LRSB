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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the st
     */
    public String getSt() {
        return st;
    }

    /**
     * @param st the st to set
     */
    public void setSt(String st) {
        this.st = st;
    }

    /**
     * @return the stLanguage
     */
    public String getStLanguage() {
        return stLanguage;
    }

    /**
     * @param stLanguage the stLanguage to set
     */
    public void setStLanguage(String stLanguage) {
        this.stLanguage = stLanguage;
    }

    /**
     * @return the ttLanguage
     */
    public String getTtLanguage() {
        return ttLanguage;
    }

    /**
     * @param ttLanguage the ttLanguage to set
     */
    public void setTtLanguage(String ttLanguage) {
        this.ttLanguage = ttLanguage;
    }

    /**
     * @return the finalTime
     */
    public Integer getFinalTime() {
        return finalTime;
    }

    /**
     * @param finalTime the finalTime to set
     */
    public void setFinalTime(Integer finalTime) {
        this.finalTime = finalTime;
    }

    /**
     * @return the fixList
     */
    public ArrayList<Fix> getFixList() {
        return fixList;
    }

    /**
     * @param fixList the fixList to set
     */
    public void setFixList(ArrayList<Fix> fixList) {
        this.fixList = fixList;
    }

    /**
     * @return the actionList
     */
    public ArrayList<Action> getActionList() {
        return actionList;
    }

    /**
     * @param actionList the actionList to set
     */
    public void setActionList(ArrayList<Action> actionList) {
        this.actionList = actionList;
    }
}
