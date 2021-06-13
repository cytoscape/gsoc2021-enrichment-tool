package gprofiler.internal.ui;

import gprofiler.internal.RequestEngine.HTTPRequestEngine;
import gprofiler.internal.ProfilerParameters;
import gprofiler.internal.QuickOptionsPanelActionListener;
import gprofiler.internal.RequestEngine.HTTPRequestEngine;
import gprofiler.internal.SpeciesData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Properties;

/**
 * @description Asks users to add the basic Settings for the project: We will assume as many parameters to be default as poosible with minimal room for adjusting preferences
 * This panel allows users to enter data that would then be used along with decisions on running as ordered or multiquery
 */
public class QuickOptionsPanel extends JPanel {

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

    /**
     * Save quick option settings
     */
    private JButton saveSettingsButton;

    private boolean isSelected;

    private String profilerDirectory;
    private ProfilerParameters params;
    private Properties gProfilerProps;

    public QuickOptionsPanel(final String profilerDirectory) {
        this.profilerDirectory = profilerDirectory;
        try{
            params = new ProfilerParameters(this.profilerDirectory);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error opening the properties file." + "\n"
                    + "Please make sure that there is gprofiler_gui.properties file" + "\n"
                    + "in the gprofiler.jar or in your cytoscape plugins directory.");
        }
        this.gProfilerProps = params.getProfilerProps();

        //initialize JComponent
        initialiseJComponents();
        setPreferredSize(new Dimension(DIM_WIDTH, DIM_HEIGHT));
        setOpaque(false);
        // create border.
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Quick Options", 0, 0,
                new Font("Quick Options", Font.BOLD, 16), Color.black));


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

        gridBag.setConstraints(saveSettingsButton,gridBagConstraints);
        add(saveSettingsButton);

    }

    /**
     * textOrGraphPanel for choosing between text or graph based input
     */
    private void initialiseJComponents() {
         isSelected = Boolean.parseBoolean(this.gProfilerProps.getProperty("selected_graph"));

        // TextOrGraphPanel: should be set by default to text input
        graphPanel = new GraphPanel(isSelected);

        // JLabels
        speciesLabel = new JLabel("Species: ");
        HTTPRequestEngine request = new HTTPRequestEngine();
        HttpResponse<String> response  = request.makePostRequest("organisms_list");
        // stores the species mapping
        String responseBody = response.body();
        Gson gson = new Gson();
        SpeciesData[] speciesData = gson.fromJson(responseBody,SpeciesData[].class);
        String defaultSpeciesName = this.gProfilerProps.getProperty("species_name");
        this.speciesNameField = new AutoCompleteComboBox(speciesData,defaultSpeciesName);
        this.speciesNameField.setVisible(true);
        this.speciesNameField.setSelectedItem(defaultSpeciesName);
        // make the combo box
        // saveSettingsButton
        saveSettingsButton = new JButton("Save Settings");
        saveSettingsButton.setMnemonic(KeyEvent.VK_B);
        saveSettingsButton.addActionListener(new QuickOptionsPanelActionListener(this));
    }

    public String getSelectedModeEnabled(){
        return String.valueOf(this.isSelected);
    }

    public String getSelectedSpeciesName(){
        return this.speciesNameField.getSelectedItem().toString();
    }

    public Properties getgProfilerProps(){
        return gProfilerProps;
    }

    public ProfilerParameters getParams() {
        return params;
    }
}