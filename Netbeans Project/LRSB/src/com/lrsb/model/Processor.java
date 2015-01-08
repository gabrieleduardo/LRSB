package com.lrsb.model;

import com.lrsb.xmlElements.Action;
import com.lrsb.xmlElements.Fix;
import com.lrsb.xmlElements.Key;
import com.lrsb.xmlElements.TextFix;
import com.lrsb.xmlElements.XmlEvent;
import com.lrsb.xmlElements.XmlDocument;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author gabriel
 */
public class Processor {

    public static Document doRender(XmlDocument xDoc) {
        Document doc = new Document();
        doc.setSt(xDoc.getSt());
        doc.setStLanguage(xDoc.getStLanguage());
        doc.setSubject(xDoc.getSubject());
        doc.setTask(xDoc.getTask());
        doc.setTtLanguage(xDoc.getTtLanguage());
        getMicroUnits(xDoc, doc);
        return doc;
    }

    private static void getMicroUnits(XmlDocument xDoc, Document doc) {
        // Polimorfismo para conseguir todos os Eventos em ordem
        ArrayList<XmlEvent> eList = new ArrayList<>();
        eList.addAll(xDoc.getFixList());
        eList.addAll(xDoc.getActionList());
        sortEventList(eList);
        // Dados da configuração
        Integer pauseA = 2400;
        Integer pauseB = Integer.MAX_VALUE;
        // Variaveis
        Integer dif;
        Integer microUnitId = 1;
        Integer tf = eList.get(0).getTime();
        Integer ti = tf;
        Integer fixCountS = 0;
        Integer fixCountT = 0;
        Integer ins = 0;
        Integer del = 0;
        Integer fixDurationS = 0;
        Integer fixDurationT = 0;
        String linearRep = "";
        Segment seg = new Segment();

        for (int i = 0; i < eList.size() - 1; i++) {
            seg.setMicroUnitId(microUnitId);
            seg.setStart(ti);
            XmlEvent e = eList.get(i);

            dif = eList.get(i + 1).getTime() - e.getTime();

            if (e.getClass() == Fix.class) {
                Fix fix = (Fix) e;
                if(fix.getWin() == 1){
                    fixCountS++;
                }else if(fix.getWin() ==2){
                    fixCountT++;
                }
            } else if (e.getClass() == TextFix.class) {
                TextFix textFix = (TextFix) e;
                if(textFix.getWin() == 1){
                    fixDurationS += textFix.getDur();
                    fixCountS++;
                }else if(textFix.getWin() ==2){
                    fixDurationT += textFix.getDur();
                    fixCountT++;
                }
                //linearRep = linearRep + textFix.getText();
            } else if (e.getClass() == Action.class) {
                Action action = (Action) e;
                linearRep = linearRep + action.getValue();
            } else if (e.getClass() == Key.class) {
                Key key = (Key) e;
                if ("delete".equalsIgnoreCase(key.getType())) {
                    del++;
                } else if ("insert".equalsIgnoreCase(key.getType())) {
                    ins++;
                }

                linearRep = linearRep + key.getValue();
            }
            
            if (dif >= pauseA && dif <= pauseB) {
                // Adiciona os valores ao segmento
                seg.setEnd(eList.get(i + 1).getTime()-1);
                seg.setDurationM(tf - ti);
                //seg.setDurationT();
                seg.setLinearRep(linearRep);
                seg.setFixCountS(fixCountS);
                seg.setFixCountT(fixCountT);
                seg.setFixDurationS(fixDurationS);
                seg.setFixDurationT(fixDurationT);
                seg.setMeanDurationT(fixDurationT.doubleValue() / fixCountT);
                seg.setMeanDurationS(fixDurationS.doubleValue() / fixCountS);
                seg.setFixCountST(fixCountT + fixCountS);
                seg.setFixDurationST(fixDurationS + fixDurationT);
                seg.setMeanDurationST(seg.getFixDurationST().doubleValue() / seg.getFixCountST());
                //seg.setFixCountSPause();
                //seg.setFixDurationSPause();
                //seg.setMeanDurationSPause();
                //seg.setFixCountTPause();
                //seg.setFixDurationTPause();
                //seg.setMeanDurationTPause();
                //seg.setFixDurationSTPause();
                //seg.setFixCountSTPause();
                //seg.setMeanDurationSTPause();
                //seg.setVisits();
                //seg.setVisitsPause();
                //seg.setSaccade();
                //seg.setSacaddeAngle();
                //seg.setSaccadeSum();
                //seg.setSaccadeMean();
                //seg.setSaccadePause();
                //seg.setSaccadeAnglePause();
                //seg.setSaccadeSumPause();
                //seg.setSaccadeMeanPause();
                seg.setIns(ins);
                seg.setDel(del);
                // Adiciona o segmento ao Documento
                doc.getSegments().add(seg);
                //Atualiza as Variaveis
                tf = eList.get(i + 1).getTime();
                ti = tf;
                fixCountS = 0;
                fixCountT = 0;
                ins = 0;
                del = 0;
                fixDurationS = 0;
                fixDurationT = 0;
                linearRep = "";
                microUnitId++;
                seg = new Segment();
            }
        }
    }

    private static void sortEventList(ArrayList<XmlEvent> eList) {
        Collections.sort(eList, new Comparator<XmlEvent>() {
            @Override
            public int compare(XmlEvent e1, XmlEvent e2) {
                return e1.getTime().compareTo(e2.getTime());
            }
        });
    }

    private static void resetValues(Integer ti, Integer tf, Integer fixCountS, Integer fixCountT, Integer ins, Integer del,
            Integer fixDurationS, Integer fixDurationT, String linearRep, Segment seg) {
        ti = tf;
        fixCountS = 0;
        fixCountT = 0;
        ins = 0;
        del = 0;
        fixDurationS = 0;
        fixDurationT = 0;
        linearRep = "";
        seg = new Segment();
    }
}
