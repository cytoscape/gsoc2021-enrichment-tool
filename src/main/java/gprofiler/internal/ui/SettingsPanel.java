package gprofiler.internal.ui;

import gprofiler.internal.SettingsPanelActionListener;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.SynchronousTaskManager;

import java.util.Properties;

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
    private TextOrGraphPanel textOrGraphPanel; // need to figure out how to handle it

    private JLabel organismLabel;
    /**
     * User enters organism name to this text field
     */
    private JTextField organismNameField;
    private final CySwingAppAdapter adapter;
    private JButton runProfilerButton;
    private final SynchronousTaskManager<?> taskManager;

    private Properties props;
    //private JLabel filePathLabel;
    /**
     * Stores path details of file
     */
    private JTextField filePathTextField;
    public SettingsPanel(CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager) {
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

        gridBag.setConstraints(organismLabel, gridBagConstraints);
        add(organismLabel);

        gridBag.setConstraints(organismNameField, gridBagConstraints);
        add(organismNameField);

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 100;
        gridBag.setConstraints(textOrGraphPanel, gridBagConstraints);
        add(textOrGraphPanel);

        gridBag.setConstraints(runProfilerButton,gridBagConstraints);
        add(runProfilerButton);

    }

    /**
     * textOrGraphPanel for choosing between text or graph based input
     */
    private void initialiseJComponents() {
        final boolean graph = Boolean.parseBoolean(this.props.getProperty("graph_def", Boolean.toString(true)));

        // TextOrGraphPanel: should be set by default to text input
        textOrGraphPanel = new TextOrGraphPanel(graph, this.props.getProperty("text_def", ""));

        // JLabels
        organismLabel = new JLabel("Organism Name: ");
        organismNameField = new JTextField("");

        // runProfilerButton
        runProfilerButton = new JButton("Run g:Profiler");
        runProfilerButton.setMnemonic(KeyEvent.VK_B);
        runProfilerButton.addActionListener(new SettingsPanelActionListener(this, adapter,taskManager));
    }


}