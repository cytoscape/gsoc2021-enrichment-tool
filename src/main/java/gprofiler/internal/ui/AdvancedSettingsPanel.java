package gprofiler.internal.ui;

import org.cytoscape.app.swing.CySwingAppAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @description Panel for advanced AdvancedSettings for a user
 */
public class AdvancedSettingsPanel extends JPanel {
    /**
     * @description height of the panel
     */
    private static final int DIM_HEIGHT = 800;
    /**
     * @description width of the panel
     */
    private static final int DIM_WIDTH = 550;

    /**
     * @description button that allows you to save your AdvancedSettings
     */
    private JButton saveAdvancedSettingsButton;
//    /**
//     * Panel for overrepresentation/underrepresentation and visualization.
//     */
//    private OverUnderVizPanel overUnderVizPanel;
    private JLabel nameLabel;
    private JButton runProfilerButton;
    /**
     * Allows users to save their data
     */
    private SaveResultsPanel dataPanel;
    /**
     * text field for naming test cluster
     */
    private JTextField nameField;

    String clustername_label = "Cluster name:";
    String test_label = "Select a statistical test:";
    String correction_label = "Select a multiple testing correction:";
    String sig_label = "Choose a significance level:";
    String category_label = "Select the categories to be visualized:";
    String ref_label = "Select reference set:";
    String annotation_label = "Select organism/annotation:";
    String ontology_label = "Select ontology file:";
    String namespace_label = "Select namespace:";
    String ec_label = "Discard the following evidence codes:";
    private final CySwingAppAdapter adapter;


    public AdvancedSettingsPanel(final String gProfilerDirectory, CySwingAppAdapter adapter) {
        super();
        this.adapter = adapter;
        initialiseJComponents();
        setPreferredSize(new Dimension(DIM_WIDTH, DIM_HEIGHT));
        setOpaque(false);
        // create border.
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "gProfiler Advanced Settings", 0, 0,
                new Font("gProfiler AdvancedSettings", Font.BOLD, 16), Color.black));
        // Layout with GridBagLayout.
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBag);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(saveAdvancedSettingsButton, gridBagConstraints);
        add(saveAdvancedSettingsButton);
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 100;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBag.setConstraints(nameLabel, gridBagConstraints);
        add(nameLabel);

        gridBag.setConstraints(nameField, gridBagConstraints);
        add(nameField);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 1;
//        gridBag.setConstraints(dataPanel,gridBagConstraints);
//        add(dataPanel);
        gridBag.setConstraints(runProfilerButton,gridBagConstraints);
        add(runProfilerButton);
        //keep adding components to the panel as per the level of customization we intend to provide
    }

    public JButton getSaveAdvancedSettingsButton() {
        return saveAdvancedSettingsButton;
    }

    public void setSaveAdvancedSettingsButton(JButton saveAdvancedSettingsButton) {
        this.saveAdvancedSettingsButton = saveAdvancedSettingsButton;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JButton getRunProfilerButton() {
        return runProfilerButton;
    }

    public void setRunProfilerButton(JButton runProfilerButton) {
        this.runProfilerButton = runProfilerButton;
    }
    /**
     * Get the dataPanel.
     *
     * @return AdvancedSettingsSavePanel dataPanel.
     */
    public SaveResultsPanel getDataPanel() {
        return dataPanel;
    }

    private void initialiseJComponents() {
        saveAdvancedSettingsButton = new JButton("Save AdvancedSettings as default");
        saveAdvancedSettingsButton.setMnemonic(KeyEvent.VK_S);
        //saveAdvancedSettingsButton.addActionListener(new SaveAdvancedSettingsButtonActionListener(this));

        // JLabels
        nameLabel = new JLabel(clustername_label);
        runProfilerButton = new JButton("Run g:Profiler");
        runProfilerButton.setMnemonic(KeyEvent.VK_B);
        //runProfilerButton.addActionListener(new AdvancedSettingsPanelActionListener(params, this, adapter, syncTaskManager));

    }
}
