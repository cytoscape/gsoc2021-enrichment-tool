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
import java.io.IOException;
import java.util.*;
import java.util.List;

public class QuickOptionsPanelActionListener implements ActionListener {
    private QuickOptionsPanel quickOptionsPanel;
    private CyNetworkView startNetworkView;
    private CyNetwork startNetwork;
    ProfilerParameters params;
    boolean isSelected;
    public QuickOptionsPanelActionListener(QuickOptionsPanel quickOptionsPanel){
        this.quickOptionsPanel = quickOptionsPanel;
    }
    /**
     * @description action that is performed when the "Run gProfiler" button is pressed
     */
    public void actionPerformed(ActionEvent event){
        Properties gprofilerProps = quickOptionsPanel.getgProfilerProps();
        Component root  = SwingUtilities.getRoot((JButton)event.getSource());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //save settings
        gprofilerProps.setProperty("species_name",quickOptionsPanel.getSelectedSpeciesName());
        gprofilerProps.setProperty("selected_graph", quickOptionsPanel.getSelectedModeEnabled());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        try {
            quickOptionsPanel.getParams().storeParameterSettings();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(quickOptionsPanel, "Could not save settings: " + ex);
        }
    }

}
