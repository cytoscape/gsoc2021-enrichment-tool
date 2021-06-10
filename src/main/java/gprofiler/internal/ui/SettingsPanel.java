package gprofiler.internal.ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import gprofiler.internal.HTTPRequests.HTTPRequests;
import gprofiler.internal.SettingsPanelActionListener;
import gprofiler.internal.SpeciesData;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.SynchronousTaskManager;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @description Asks users to add the basic Settings for the project: We will assume as many parameters to be default as poosible with minimal room for adjusting preferences
 * This panel allows users to enter data that would then be used along with decisions on running as ordered or multiquery
 */
public class SettingsPanel extends JPanel {
    /**
     * the height of the panel
     */
    private static final int DIM_HEIGHT = 800;
    /**
     * the width of the panel
     */
    private static final int DIM_WIDTH = 550;
    /**
     * Panel for text or graph input
     */
    private GraphPanel graphPanel; // need to figure out how to handle it

    private JLabel speciesLabel;
    /**
     * User enters species name to this text field
     */
    private JComboBox<String>  speciesNameField;
    private final CySwingAppAdapter adapter;
    /**
     * Button to run the Profiler by firing API Request
     */
    private JButton runProfilerButton;
    private final SynchronousTaskManager<?> taskManager;

    private Properties props;
    //private JLabel filePathLabel;
    /**
     * Stores path details of file
     */
    private JTextField filePathTextField;
    public SettingsPanel(CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager) throws IOException, InterruptedException {
        this.adapter = adapter;
        this.props = new Properties();
        initialiseJComponents();
        this.taskManager = taskManager;
        setPreferredSize(new Dimension(DIM_WIDTH, DIM_HEIGHT));
        setOpaque(false);
        // create border.
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "gProfiler settings", 0, 0,
                new Font("gProfiler settings", Font.BOLD, 16), Color.black));


        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel dummyPanel = new JPanel();
        dummyPanel.setOpaque(false);
        dummyPanel.setPreferredSize(new Dimension(50, 20));
        gridBagConstraints.gridx = 0;
        gridBag.setConstraints(dummyPanel, gridBagConstraints);
        add(dummyPanel);
        
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 1;

        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 100;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBag.setConstraints(speciesLabel, gridBagConstraints);
        add(speciesLabel);

        gridBag.setConstraints(speciesNameField, gridBagConstraints);
        add(speciesNameField);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 100;
        gridBag.setConstraints(graphPanel, gridBagConstraints);
        add(graphPanel);

        gridBag.setConstraints(runProfilerButton,gridBagConstraints);
        add(runProfilerButton);

    }

    /**
     * textOrGraphPanel for choosing between text or graph based input
     */
    private void initialiseJComponents() throws IOException, InterruptedException {
        final boolean isSelected = Boolean.parseBoolean(this.props.getProperty("selected_graph"));

        // TextOrGraphPanel: should be set by default to text input
        graphPanel = new GraphPanel(isSelected);

        // JLabels
        speciesLabel = new JLabel("Species: ");
        HTTPRequests request = new HTTPRequests();
        HttpResponse<String> response  = request.makeGetRequests("organisms_list");
        // stores the species mapping
        String responseBody = response.body();
        Gson gson = new Gson();
        SpeciesData[] speciesData = gson.fromJson(responseBody,SpeciesData[].class);
        this.speciesNameField = new AutoCompleteComboBox(speciesData);
        this.speciesNameField.setVisible(true);
        // make the combo box
        // runProfilerButton
        runProfilerButton = new JButton("Run g:Profiler");
        runProfilerButton.setMnemonic(KeyEvent.VK_B);
        runProfilerButton.addActionListener(new SettingsPanelActionListener(this, adapter,taskManager,isSelected));
    }


}