/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.model;

/**
 *
 * @author gabriel
 */
public class Segment {

    private Integer microUnitId;
    private Integer linearRep;
    private Integer pause;
    private Integer start;
    private Integer end;
    private Integer durationM;
    private Integer durationT;
    private Integer fixCountS;
    private Integer fixDurationS;
    private Integer fixCountT;
    private Integer fixDurationT;
    private Double meanDurationT;
    private Integer fixCountST;
    private Integer fixDurationST;
    private Integer fixCountSPause;
    private Integer fixDurationSPause;
    private Double meanDurationSPause;
    private Integer fixCountTPause;
    private Integer fixDurationTPause;
    private Double meanDurationTPause;
    private Integer fixCountSTPause;
    private Integer fixDurationSTPause;
    private Double meanDurationSTPause;
    private Integer visits;
    private Integer visitsPause;
    private Integer saccade;
    private Integer sacaddeAngle;
    private Integer saccadeSum;
    private Integer saccadeMean;
    private Integer saccadePause;
    private Integer saccadeSumPause;
    private Double saccadeMeanPause;
    private Integer ins;
    private Integer del;

    /**
     * @return the microUnitId
     */
    public Integer getMicroUnitId() {
        return microUnitId;
    }

    /**
     * @param microUnitId the microUnitId to set
     */
    public void setMicroUnitId(Integer microUnitId) {
        this.microUnitId = microUnitId;
    }

    /**
     * @return the linearRep
     */
    public Integer getLinearRep() {
        return linearRep;
    }

    /**
     * @param linearRep the linearRep to set
     */
    public void setLinearRep(Integer linearRep) {
        this.linearRep = linearRep;
    }

    /**
     * @return the pause
     */
    public Integer getPause() {
        return pause;
    }

    /**
     * @param pause the pause to set
     */
    public void setPause(Integer pause) {
        this.pause = pause;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Integer end) {
        this.end = end;
    }

    /**
     * @return the durationM
     */
    public Integer getDurationM() {
        return durationM;
    }

    /**
     * @param durationM the durationM to set
     */
    public void setDurationM(Integer durationM) {
        this.durationM = durationM;
    }

    /**
     * @return the durationT
     */
    public Integer getDurationT() {
        return durationT;
    }

    /**
     * @param durationT the durationT to set
     */
    public void setDurationT(Integer durationT) {
        this.durationT = durationT;
    }

    /**
     * @return the fixCountS
     */
    public Integer getFixCountS() {
        return fixCountS;
    }

    /**
     * @param fixCountS the fixCountS to set
     */
    public void setFixCountS(Integer fixCountS) {
        this.fixCountS = fixCountS;
    }

    /**
     * @return the fixDurationS
     */
    public Integer getFixDurationS() {
        return fixDurationS;
    }

    /**
     * @param fixDurationS the fixDurationS to set
     */
    public void setFixDurationS(Integer fixDurationS) {
        this.fixDurationS = fixDurationS;
    }

    /**
     * @return the fixCountT
     */
    public Integer getFixCountT() {
        return fixCountT;
    }

    /**
     * @param fixCountT the fixCountT to set
     */
    public void setFixCountT(Integer fixCountT) {
        this.fixCountT = fixCountT;
    }

    /**
     * @return the fixDurationT
     */
    public Integer getFixDurationT() {
        return fixDurationT;
    }

    /**
     * @param fixDurationT the fixDurationT to set
     */
    public void setFixDurationT(Integer fixDurationT) {
        this.fixDurationT = fixDurationT;
    }

    /**
     * @return the meanDurationT
     */
    public Double getMeanDurationT() {
        return meanDurationT;
    }

    /**
     * @param meanDurationT the meanDurationT to set
     */
    public void setMeanDurationT(Double meanDurationT) {
        this.meanDurationT = meanDurationT;
    }

    /**
     * @return the fixCountST
     */
    public Integer getFixCountST() {
        return fixCountST;
    }

    /**
     * @param fixCountST the fixCountST to set
     */
    public void setFixCountST(Integer fixCountST) {
        this.fixCountST = fixCountST;
    }

    /**
     * @return the fixDurationST
     */
    public Integer getFixDurationST() {
        return fixDurationST;
    }

    /**
     * @param fixDurationST the fixDurationST to set
     */
    public void setFixDurationST(Integer fixDurationST) {
        this.fixDurationST = fixDurationST;
    }

    /**
     * @return the fixCountSPause
     */
    public Integer getFixCountSPause() {
        return fixCountSPause;
    }

    /**
     * @param fixCountSPause the fixCountSPause to set
     */
    public void setFixCountSPause(Integer fixCountSPause) {
        this.fixCountSPause = fixCountSPause;
    }

    /**
     * @return the fixDurationSPause
     */
    public Integer getFixDurationSPause() {
        return fixDurationSPause;
    }

    /**
     * @param fixDurationSPause the fixDurationSPause to set
     */
    public void setFixDurationSPause(Integer fixDurationSPause) {
        this.fixDurationSPause = fixDurationSPause;
    }

    /**
     * @return the meanDurationSPause
     */
    public Double getMeanDurationSPause() {
        return meanDurationSPause;
    }

    /**
     * @param meanDurationSPause the meanDurationSPause to set
     */
    public void setMeanDurationSPause(Double meanDurationSPause) {
        this.meanDurationSPause = meanDurationSPause;
    }

    /**
     * @return the fixCountTPause
     */
    public Integer getFixCountTPause() {
        return fixCountTPause;
    }

    /**
     * @param fixCountTPause the fixCountTPause to set
     */
    public void setFixCountTPause(Integer fixCountTPause) {
        this.fixCountTPause = fixCountTPause;
    }

    /**
     * @return the fixDurationTPause
     */
    public Integer getFixDurationTPause() {
        return fixDurationTPause;
    }

    /**
     * @param fixDurationTPause the fixDurationTPause to set
     */
    public void setFixDurationTPause(Integer fixDurationTPause) {
        this.fixDurationTPause = fixDurationTPause;
    }

    /**
     * @return the meanDurationTPause
     */
    public Double getMeanDurationTPause() {
        return meanDurationTPause;
    }

    /**
     * @param meanDurationTPause the meanDurationTPause to set
     */
    public void setMeanDurationTPause(Double meanDurationTPause) {
        this.meanDurationTPause = meanDurationTPause;
    }

    /**
     * @return the fixCountSTPause
     */
    public Integer getFixCountSTPause() {
        return fixCountSTPause;
    }

    /**
     * @param fixCountSTPause the fixCountSTPause to set
     */
    public void setFixCountSTPause(Integer fixCountSTPause) {
        this.fixCountSTPause = fixCountSTPause;
    }

    /**
     * @return the fixDurationSTPause
     */
    public Integer getFixDurationSTPause() {
        return fixDurationSTPause;
    }

    /**
     * @param fixDurationSTPause the fixDurationSTPause to set
     */
    public void setFixDurationSTPause(Integer fixDurationSTPause) {
        this.fixDurationSTPause = fixDurationSTPause;
    }

    /**
     * @return the meanDurationSTPause
     */
    public Double getMeanDurationSTPause() {
        return meanDurationSTPause;
    }

    /**
     * @param meanDurationSTPause the meanDurationSTPause to set
     */
    public void setMeanDurationSTPause(Double meanDurationSTPause) {
        this.meanDurationSTPause = meanDurationSTPause;
    }

    /**
     * @return the visits
     */
    public Integer getVisits() {
        return visits;
    }

    /**
     * @param visits the visits to set
     */
    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    /**
     * @return the visitsPause
     */
    public Integer getVisitsPause() {
        return visitsPause;
    }

    /**
     * @param visitsPause the visitsPause to set
     */
    public void setVisitsPause(Integer visitsPause) {
        this.visitsPause = visitsPause;
    }

    /**
     * @return the saccade
     */
    public Integer getSaccade() {
        return saccade;
    }

    /**
     * @param saccade the saccade to set
     */
    public void setSaccade(Integer saccade) {
        this.saccade = saccade;
    }

    /**
     * @return the sacaddeAngle
     */
    public Integer getSacaddeAngle() {
        return sacaddeAngle;
    }

    /**
     * @param sacaddeAngle the sacaddeAngle to set
     */
    public void setSacaddeAngle(Integer sacaddeAngle) {
        this.sacaddeAngle = sacaddeAngle;
    }

    /**
     * @return the saccadeSum
     */
    public Integer getSaccadeSum() {
        return saccadeSum;
    }

    /**
     * @param saccadeSum the saccadeSum to set
     */
    public void setSaccadeSum(Integer saccadeSum) {
        this.saccadeSum = saccadeSum;
    }

    /**
     * @return the saccadeMean
     */
    public Integer getSaccadeMean() {
        return saccadeMean;
    }

    /**
     * @param saccadeMean the saccadeMean to set
     */
    public void setSaccadeMean(Integer saccadeMean) {
        this.saccadeMean = saccadeMean;
    }

    /**
     * @return the saccadePause
     */
    public Integer getSaccadePause() {
        return saccadePause;
    }

    /**
     * @param saccadePause the saccadePause to set
     */
    public void setSaccadePause(Integer saccadePause) {
        this.saccadePause = saccadePause;
    }

    /**
     * @return the saccadeSumPause
     */
    public Integer getSaccadeSumPause() {
        return saccadeSumPause;
    }

    /**
     * @param saccadeSumPause the saccadeSumPause to set
     */
    public void setSaccadeSumPause(Integer saccadeSumPause) {
        this.saccadeSumPause = saccadeSumPause;
    }

    /**
     * @return the saccadeMeanPause
     */
    public Double getSaccadeMeanPause() {
        return saccadeMeanPause;
    }

    /**
     * @param saccadeMeanPause the saccadeMeanPause to set
     */
    public void setSaccadeMeanPause(Double saccadeMeanPause) {
        this.saccadeMeanPause = saccadeMeanPause;
    }

    /**
     * @return the ins
     */
    public Integer getIns() {
        return ins;
    }

    /**
     * @param ins the ins to set
     */
    public void setIns(Integer ins) {
        this.ins = ins;
    }

    /**
     * @return the del
     */
    public Integer getDel() {
        return del;
    }

    /**
     * @param del the del to set
     */
    public void setDel(Integer del) {
        this.del = del;
    }
}