package gprofiler.internal;

import gprofiler.internal.RequestEngine.HTTPRequestEngine;
import gprofiler.internal.ui.SettingsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.HttpResponse;
import java.util.*;

public class SettingsPanelActionListener implements ActionListener {
    private SettingsPanel settingsPanel;
    boolean isSelected;
    public SettingsPanelActionListener(SettingsPanel settingsPanel,  boolean isSelected){
        this.settingsPanel = settingsPanel;
        this.isSelected = isSelected;
    }
    /**
     * @description action that is performed when the "Run gProfiler" button is pressed
     */
    public void actionPerformed(ActionEvent event){
        Component root  = SwingUtilities.getRoot((JButton)event.getSource());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Set<String> selectedNodes = new HashSet<>(){{
            add("CASQ2");
            add("CASQ1");
            add("GSTO1");
            add("DMD");
            add("GSTM2");
        }};

        selectedNodes.add("Homo sapiens");
        StringBuffer query = new StringBuffer("");

        Iterator<String> setIterator = selectedNodes.iterator();
        query.append("\"");
        while(setIterator.hasNext()){
            query.append(setIterator.next());
            query.append(" ");
        }
        query.append("\"");
        //System.out.println(query);
        //run the query
        Map<String,String> parameters = generateQuery(query.toString());
        HTTPRequestEngine requestEngine = new HTTPRequestEngine();
        HttpResponse<String> response = requestEngine.makePostRequest("gost/profile/",parameters);
        StringBuffer responseBuffer = new StringBuffer("");
        if(response!=null)
            responseBuffer.append(response.body());
        //SpeciesData[] speciesData = gson.fromJson(responseBody,SpeciesData[].class);

        //response.body();
        //fire an api request with all the parameters
        //set value in a text box to show the json output
        settingsPanel.getOutputTextBox().setText(responseBuffer.toString());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private Map<String, String> generateQuery(String query) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("organism","hsapiens");
        parameters.put("query",query);
        return parameters;
    }

}
