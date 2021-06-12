package gprofiler.internal;

import com.fasterxml.jackson.databind.MappingIterator;
import gprofiler.internal.HTTPRequests.HTTPRequests;
import gprofiler.internal.ui.SettingsPanel;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.SynchronousTaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;

public class SettingsPanelActionListener implements ActionListener {
    private final CySwingAppAdapter adapter;
    private SettingsPanel settingsPanel;
    private CyNetworkView startNetworkView;
    private CyNetwork startNetwork;
    private final SynchronousTaskManager<?> taskManager;
    boolean isSelected;
    public SettingsPanelActionListener(SettingsPanel settingsPanel, final CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager, boolean isSelected){
        this.adapter = adapter;
        this.settingsPanel = settingsPanel;
        this.taskManager = taskManager;
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
        HTTPRequests requestEngine = new HTTPRequests();
        HttpResponse<String> response = null;
        try {
            response = requestEngine.makePostRequest("gost/profile/",parameters);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String responseBody="";
        if(response!=null)
            responseBody= response.body();
        //SpeciesData[] speciesData = gson.fromJson(responseBody,SpeciesData[].class);

        //response.body();
        //fire an api request with all the parameters
        //set value in a text box to show the json output
        StringBuffer output = new StringBuffer();
        settingsPanel.getOutputTextBox().setText(responseBody);
        root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private Map<String, String> generateQuery(String query) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("organism","hsapiens");
        parameters.put("query",query);
        return parameters;
    }

}
