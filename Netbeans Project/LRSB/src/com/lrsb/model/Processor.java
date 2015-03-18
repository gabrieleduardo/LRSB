package com.lrsb.model;

import static com.lrsb.model.StringTreatment.format2f;
import com.lrsb.spreadsheet.SaveToCSV;
import com.lrsb.xmlElements.Action;
import com.lrsb.xmlElements.Fix;
import com.lrsb.xmlElements.Key;
import com.lrsb.xmlElements.TextFix;
import com.lrsb.xmlElements.XmlEvent;
import com.lrsb.xmlElements.XmlDocument;
import com.lrsb.xmlElements.XmlReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class Processor {

    /**
     * RF08,RF09
     * 
     * @param sourcePath Texto Fonte
     * @param targetPath Texto Alvo
     * @param pauseBegin Inicio do intervalo para validação da pausa
     * @param pauseEnd Fim do intervalo para validação da pausa
     */
    public static void process(String sourcePath, String targetPath, String pauseBegin, String pauseEnd) {
        File dir = new File(sourcePath);
        String[] filename = dir.list();

        if (filename == null) {
            return;
        }

        // Dados da configuração
        Integer pauseA;
        Integer pauseB;

        /**
         * RF 08 – O pesquisados deve poder indicar o valor de pausa. 
         * O default é de 2400 ms.
         * 
         * RF 09 – O pesquisador deve poder determinar o valor mínimo e 
         * máximo de uma pausa.
         * 
         * Nota: Caso o valor máximo não seja informado, será considerado
         * o valor máximo do tipo de dados inteiro.
         */
        
        
        if (pauseBegin.matches("^(?!^0)\\d{1,9}$")) {
            pauseA = Integer.parseInt(pauseBegin);
            if(pauseA < 0){
                pauseA = 2400;
            }
        } else {
            pauseA = 2400;
        }

        if (pauseEnd.matches("^(?!^0)\\d{1,9}$")) {
            pauseB = Integer.parseInt(pauseEnd);
            
            if(pauseB < 0){
                pauseB = Integer.MAX_VALUE;
            }
        } else {
            pauseB = Integer.MAX_VALUE;;
        }

        for (String st : filename) {
            if (st.endsWith(".xml")) {
                try {
                    System.out.println("Processando arquivo: " + st);
                    XmlDocument xdoc = XmlReader.parseDocument(sourcePath + File.separator + st);
                    Document doc = Processor.doRender(xdoc, pauseA, pauseB);
                    SaveToCSV.save(doc, targetPath + File.separator + removeXml(st) + ".csv");
                } catch (Exception ex) {
                    Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Processa uma instância XmlDocument e retorna uma instância Document
     *
     * @param xDoc XmlDocument
     * @return
     */
    public static Document doRender(XmlDocument xDoc, Integer pauseA, Integer pauseB) {
        Document doc = new Document();
        doc.setSt(xDoc.getSt());
        doc.setStLanguage(xDoc.getStLanguage());
        doc.setSubject(xDoc.getSubject());
        doc.setTask(xDoc.getTask());
        doc.setTtLanguage(xDoc.getTtLanguage());
        getMicroUnits(xDoc, doc, pauseA, pauseB);
        return doc;
    }

    private static void getMicroUnits(XmlDocument xDoc, Document doc, Integer pauseA, Integer pauseB) {
        // Polimorfismo para conseguir todos os Eventos em ordem
        ArrayList<XmlEvent> eList = new ArrayList<>();
        eList.addAll(xDoc.getFixList());
        eList.addAll(xDoc.getActionList());
        sortEventList(eList);
        // Variaveis
        Integer dif = Integer.MIN_VALUE;
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
        Integer pauseAnt = 0;
        Integer visits = 0;
        Integer winAnt = 0;
        Integer xAnt = null;
        Integer yAnt = null;
        Double distancia;
        String saccade = "";
        Double saccadeSum = 0.0;
        Integer saccadeAc = 0;
        Integer pastActionTime = Integer.MAX_VALUE;
        Integer currentActionTime = 0;
        Integer fixDurationSPause = 0;
        Integer fixCountSPause = 0;
        Integer fixDurationTPause = 0;
        Integer fixCountTPause = 0;
        String saccadeAngle = "";
        String saccadePause = "";
        String saccadeAnglePause = "";
        Double saccadeSumPause = 0.0;
        Integer visitsPause = 0;
        Integer saccadeAcPause = 0;

        for (int i = 0; i < eList.size() - 1; i++) {
            seg.setMicroUnitId(microUnitId);
            seg.setStart(ti);
            XmlEvent e = eList.get(i);
            dif = Integer.MIN_VALUE;

            
            if (e.getClass() == Key.class) {
                currentActionTime = e.getTime();
                dif = currentActionTime - pastActionTime;
                pastActionTime = currentActionTime;
                currentActionTime = 0;
            }

            /**
             * Verifica o tipo de evento e atualiza as variaveis correspondentes
             */
            if (e.getClass() == Key.class) {
                Key key = (Key) e;
                if ("delete".equalsIgnoreCase(key.getType())) {
                    del++;
                } else if ("insert".equalsIgnoreCase(key.getType())) {
                    ins++;
                }

                linearRep = linearRep + key.getValue();
            } else if (e.getClass() == Fix.class) {
                Fix fix = (Fix) e;
                if (fix.getWin() == 1) {
                    fixCountS++;
                    fixCountSPause++;
                } else if (fix.getWin() == 2) {
                    fixCountT++;
                    fixCountTPause++;
                }
                // Nota: Não considera visitas inválidas.              
                if (fix.getWin() != winAnt && (fix.getWin() == 1 || fix.getWin() == 2)) {
                    visits++;
                    visitsPause++;
                    winAnt = fix.getWin();
                }
            } else if (e.getClass() == TextFix.class) {
                TextFix textFix = (TextFix) e;

                if (textFix.getWin() == 1) {
                    fixDurationS += textFix.getDur();
                    fixCountS++;
                    fixDurationSPause += textFix.getDur();
                    fixCountSPause++;
                } else if (textFix.getWin() == 2) {
                    fixDurationT += textFix.getDur();
                    fixCountT++;
                    fixDurationTPause += textFix.getDur();
                    fixCountTPause++;
                }

                //Verifica se houve uma saccade na microunidade
                if (xAnt != null && yAnt != null && textFix.getX() != null && textFix.getY() != null) {
                    distancia = distancia(xAnt, textFix.getX(), yAnt, textFix.getY());
                    saccadeSum += distancia;
                    saccadeSumPause += distancia;
                    saccadeAc++;
                    saccadeAcPause++;

                    if (saccade.isEmpty()) {
                        saccade = saccade + format2f(distancia);
                    } else {
                        saccade = saccade + "+" + format2f(distancia);
                    }

                    if (saccadePause.isEmpty()) {
                        saccadePause = saccadePause + format2f(distancia);
                    } else {
                        saccadePause = saccadePause + "+" + format2f(distancia);
                    }

                    if (saccadeAngle.isEmpty()) {
                        saccadeAngle = saccadeAngle + format2f(Vector.getAngle(xAnt, yAnt, textFix.getX(), textFix.getY()));
                    } else {
                        saccadeAngle = saccadeAngle + "+" + format2f(Vector.getAngle(xAnt, yAnt, textFix.getX(), textFix.getY()));
                    }

                    if (saccadeAnglePause.isEmpty()) {
                        saccadeAnglePause = saccadeAnglePause + format2f(Vector.getAngle(xAnt, yAnt, textFix.getX(), textFix.getY()));
                    } else {
                        saccadeAnglePause = saccadeAnglePause + "+" + format2f(Vector.getAngle(xAnt, yAnt, textFix.getX(), textFix.getY()));
                    }
                }

                xAnt = textFix.getX();
                yAnt = textFix.getY();
            } else if (e.getClass() == Action.class) {
                Action action = (Action) e;
                linearRep = linearRep + action.toString();
            }

            /**
             * Verifica se o valor está dentro do intervalo de pausas
             * pré-determinado.
             */
            if (dif >= pauseA && dif <= pauseB) {
                //Atualiza o tempo final
                tf = eList.get(i + 1).getTime() - 1;

                // Adiciona os valores ao segmento
                seg.setPause(pauseAnt);
                seg.setEnd(tf);
                seg.setDurationM(tf - ti);
                seg.setDurationT(0);
                seg.setLinearRep(linearRep);
                seg.setFixCountS(fixCountS);
                seg.setFixCountT(fixCountT);
                seg.setFixDurationS(fixDurationS);
                seg.setFixDurationT(fixDurationT);

                if (fixCountT > 0) {
                    seg.setMeanDurationT(fixDurationT.doubleValue() / fixCountT);
                } else {
                    seg.setMeanDurationT(0.0);
                }

                if (fixCountS > 0) {
                    seg.setMeanDurationS(fixDurationS.doubleValue() / fixCountS);
                } else {
                    seg.setMeanDurationS(0.0);
                }

                seg.setFixCountST(fixCountT + fixCountS);
                seg.setFixDurationST(fixDurationS + fixDurationT);

                if (fixCountT + fixCountS > 0) {
                    seg.setMeanDurationST(seg.getFixDurationST().doubleValue() / (fixCountT + fixCountS));
                } else {
                    seg.setMeanDurationST(0.0);
                }

                seg.setFixCountSPause(fixCountSPause);
                seg.setFixDurationSPause(fixDurationSPause);

                if (fixCountSPause > 0) {
                    seg.setMeanDurationSPause((double) fixDurationSPause / fixCountSPause);
                } else {
                    seg.setMeanDurationSPause(0.0);
                }

                seg.setFixCountTPause(fixCountTPause);
                seg.setFixDurationTPause(fixDurationTPause);

                if (fixCountTPause > 0) {
                    seg.setMeanDurationTPause((double) fixDurationTPause / fixCountTPause);
                } else {
                    seg.setMeanDurationTPause(0.0);
                }

                seg.setFixDurationSTPause(fixDurationSPause + fixDurationTPause);
                seg.setFixCountSTPause(fixCountSPause + fixCountTPause);

                if (fixDurationSPause + fixDurationTPause > 0) {
                    seg.setMeanDurationSTPause((double) seg.getFixDurationSTPause() / seg.getFixCountST());
                } else {
                    seg.setMeanDurationSTPause(0.0);
                }
                seg.setVisits(visits);
                seg.setVisitsPause(visitsPause);
                seg.setSaccade(saccade);
                seg.setSacaddeAngle(saccadeAngle);

                if (saccadeAc > 0) {
                    seg.setSaccadeMean(saccadeSum / saccadeAc);
                    seg.setSaccadeSum(saccadeSum);
                } else {
                    seg.setSaccadeMean(0.0);
                    seg.setSaccadeSum(0.0);
                }
                seg.setSaccadePause(saccadePause);
                seg.setSaccadeAnglePause(saccadeAnglePause);
                seg.setSaccadeSumPause(saccadeSumPause);

                if (saccadeAcPause > 0) {
                    seg.setSaccadeMeanPause(saccadeSumPause / saccadeAcPause);
                    seg.setSaccadeSumPause(saccadeSumPause);
                } else {
                    seg.setSaccadeMeanPause(0.0);
                }
                seg.setIns(ins);
                seg.setDel(del);

                // Adiciona o segmento ao Documento
                doc.getSegments().add(seg);

                //Atualiza as Variaveis
                pauseAnt = dif;
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
                visits = 0;
                saccade = "";
                saccadeAngle = "";
                saccadeSum = 0.0;
                fixCountSPause = 0;
                fixCountTPause = 0;
                fixDurationSPause = 0;
                fixDurationTPause = 0;
                saccadePause = "";
                saccadeAnglePause = "";
                saccadeSumPause = 0.0;
                visitsPause = 0;
                saccadeAcPause = 0;
            }

            //if (e.getClass() == Action.class || e.getClass() == Key.class) {
            if (e.getClass() == Key.class) {
                fixCountSPause = 0;
                fixCountTPause = 0;
                fixDurationSPause = 0;
                fixDurationTPause = 0;
                saccadePause = "";
                saccadeAnglePause = "";
                saccadeSumPause = 0.0;
                visitsPause = 0;
                saccadeAcPause = 0;
            }
        }
    }

    /**
     * Ordena os eventos por ordem cronológica
     *
     * @param eList Lista de Eventos
     */
    private static void sortEventList(ArrayList<XmlEvent> eList) {
        Collections.sort(eList, new Comparator<XmlEvent>() {
            @Override
            public int compare(XmlEvent e1, XmlEvent e2) {
                return e1.getTime().compareTo(e2.getTime());
            }
        });
    }

    /**
     * Função auxiliar que reseta os valores das váriaveis passadas.
     */
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

    /**
     * Calcula a distância
     */
    private static Double distancia(Integer x1, Integer y1, Integer x2, Integer y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Remove a extensão XML de uma String
     *
     * @param nome Nome do Arquivo fonte
     * @return Nome sem extensão xml
     */
    public static String removeXml(String nome) {
        return nome.replaceAll(".xml", "");
    }
}
