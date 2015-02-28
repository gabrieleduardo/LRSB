/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lrsb.spreadsheet;

import com.lrsb.model.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class SaveToCSV {

    private static final String header = "sep=,\n"
            + "Subject,Task,ST,STlanguage,TTlanguage,"
            + "MicroUnitId,LinearRep,Pause,PauseType,Start,End,"
            + "DurationM,DurationT,FixCountS,FixDurationS,MeanDurationS,FixCountT,"
            + "FixDurationT,MeanDurationT,FixCountST,FixDurationST,MeanDurationST,"
            + "FixCountSPause,FixDurationSPause,MeanDurationSPause,FixCountTPause,"
            + "FixDurationTPause,MeanDurationTPause,FixCountSTPause,FixDurationSTPause,"
            + "MeanDurationSTPause,Visits,VisitsPause,Saccade,SacaddeAngle,SaccadeSum,"
            + "SaccadeMean,SaccadePause,SaccadeAnglePause,SaccadeSumPause,SaccadeMeanPause,"
            + "ins,del\n";

    public static void save(Document doc, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.append(header);
            fw.append(doc.documentToCSV());
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveToCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
