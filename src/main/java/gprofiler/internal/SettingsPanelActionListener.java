package gprofiler.internal;

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
import java.util.*;
import java.util.List;

public class SettingsPanelActionListener implements ActionListener {
    private final CySwingAppAdapter adapter;
    private SettingsPanel settingsPanel;
    private CyNetworkView startNetworkVIew;
    private CyNetwork startNetwork;
    private final SynchronousTaskManager<?> taskManager;
    public SettingsPanelActionListener(SettingsPanel settingsPanel, final CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager){
        this.adapter = adapter;
        this.settingsPanel = settingsPanel;
        this.taskManager = taskManager;
    }
    /**
     * @description action that is performed when the "Run gProfiler" button is pressed
     */
    public void actionPerformed(ActionEvent event){
        Component root  = SwingUtilities.getRoot((JButton)event.getSource());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        // import annotations from appropriate sources -> is this step required? How is it executed?
        boolean status = updateRequestBody();
        if(status){

        }
    }
    private boolean updateRequestBody(){
        return true;
    }
    // Need to figure out a way to fix this function
    /**
     * @description get canonical names of the selected nodes in the graph cluster
     */
    public HashSet getSelectedCanonicalNamesFromNetwork(CyNetwork network){
        Set canonicalNameVector = new HashSet();
        Set<Set<String>> mapNames = new HashSet<Set<String>>();
        final List<CyNode> nodes = network.getNodeList();
        for(CyNode node: nodes){
            String canonicalName = network.getDefaultNodeTable().getRow(node.getSUID()).get(CyNetwork.NAME,String.class);
            canonicalNameVector.add(canonicalName);
        }
        return (HashSet) canonicalNameVector;
    }
    /**
     * @description get canoncial names from the text input by user
     */
    public HashSet getAllCanonicalNamesFromNetwork(CyNetwork network){
        Set canonicalNameVector = new HashSet();
        return (HashSet) canonicalNameVector;
    }
    /**
     * @description get canonical names from the entire graph
     */
    public Set getAllConincalNamesFromNetwork(CyNetwork network){
        Set canonicalNameVector = new HashSet();
        return (HashSet) canonicalNameVector;
    }
}
