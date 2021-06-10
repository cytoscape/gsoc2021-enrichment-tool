package gprofiler.internal;

import gprofiler.internal.ui.QuickOptionsPanel;
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

public class EnrichmentTableActionListener implements ActionListener  {
    private final CySwingAppAdapter adapter;
    private QuickOptionsPanel quickOptionsPanel;
    private CyNetworkView startNetworkView;
    private CyNetwork startNetwork;
    private final SynchronousTaskManager<?> taskManager;
    ProfilerParameters params;
    boolean isSelected;
    public EnrichmentTableActionListener(QuickOptionsPanel quickOptionsPanel, final CySwingAppAdapter adapter, final SynchronousTaskManager<?> taskManager, boolean isSelected){
        this.adapter = adapter;
        this.quickOptionsPanel = quickOptionsPanel;
        this.taskManager = taskManager;
        this.isSelected = isSelected;
    }
    /**
     * To Be done: Handle situations where customer would like to visualize results
     */
    /**
     * @description action that is performed when the "Run gProfiler" button is pressed
     */
    public void actionPerformed(ActionEvent event){
        Component root  = SwingUtilities.getRoot((JButton)event.getSource());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        // import annotations from appropriate sources -> is this step required? How is it executed?
        startNetworkView = adapter.getCyApplicationManager().getCurrentNetworkView();
        startNetwork = startNetworkView.getModel();
        final CyNetwork network = adapter.getCyApplicationManager().getCurrentNetwork();
        if(this.isSelected) {
            //run the profiler only on selected nodes
            params.setSelectedNodes(getSelectedNodeNamesFromNetwork(network));

        } else{
            // run the profiler for all nodes
            params.setSelectedNodes(getAllNamesFromNetwork(network));

        }
        params.setSelectedNodes((HashSet<String>) getSelectedNamesFromTextInput());
        Set<String> selectedNodes = params.getSelectedNodes();

        StringBuffer query = new StringBuffer();
        Iterator<String> setIterator = selectedNodes.iterator();
        while(setIterator.hasNext()){
            query.append("\"");
            query.append(setIterator.next());
            query.append("\"");
            if(setIterator.hasNext()){
                query.append(",");
            }
        }
        //run the query
        Map<String,String> parameters = generateQuery(query.toString());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private Map<String, String> generateQuery(String query) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("query",query);
        return parameters;
    }

    private Set<String> getSelectedNamesFromTextInput() {
        String textNodes = params.getTextInput();
        String[] nodes = textNodes.split("\\s+");

        Set canonicalNameVector = new HashSet();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[i].length() != 0 && !canonicalNameVector.contains(nodes[i].toUpperCase())) {
                canonicalNameVector.add(nodes[i].toUpperCase());
            }
        }
        return (HashSet) canonicalNameVector;

    }

    private boolean updateRequestBody(){
        return true;
    }
    // Need to figure out a way to fix this function
    /**
     * @description get canonical names of the selected nodes in the graph cluster
     */
    public HashSet<String> getSelectedNodeNamesFromNetwork(CyNetwork network){
        Set<String> canonicalNameVector = new HashSet<String>();
        final java.util.List<CyNode> nodes = network.getNodeList();
        for(CyNode node: nodes){
            if (network.getDefaultNodeTable().getRow(node.getSUID()).get(CyNetwork.SELECTED, Boolean.class) == false)
                continue;
            //get the canonical name of the given node from the attributes object
            String canonicalName = network.getDefaultNodeTable().getRow(node.getSUID()).get(CyNetwork.SELECTED,String.class);
            if (canonicalName != null && canonicalName.length() != 0 && !canonicalNameVector.contains(canonicalName.toUpperCase())) {
                canonicalName = canonicalName.toUpperCase();
                canonicalNameVector.add(canonicalName);
            }
        }
        return (HashSet) canonicalNameVector;
    }
    /**
     * @description get canonical names from the text input by user
     */
    public HashSet getAllNamesFromNetwork(CyNetwork network) {
        Set canonicalNameVector = new HashSet();
        final List<CyNode> nodes = network.getNodeList();

        for(CyNode node: nodes){
            if (network.getDefaultNodeTable().getRow(node.getSUID()).get(CyNetwork.NAME, Boolean.class) == false)
                continue;
            //get the canonical name of the given node from the attributes object
            String canonicalName = network.getDefaultNodeTable().getRow(node.getSUID()).get(CyNetwork.NAME,String.class);
            if (canonicalName != null && canonicalName.length() != 0 && !canonicalNameVector.contains(canonicalName.toUpperCase())) {
                canonicalName = canonicalName.toUpperCase();
                canonicalNameVector.add(canonicalName);
            }
        }
        return (HashSet) canonicalNameVector;
    }
}
