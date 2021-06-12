package gprofiler.internal.ui;

import gprofiler.internal.SettingsPanelActionListener;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.SynchronousTaskManager;

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
    private final CySwingAppAdapter adapter;
    /**
     * Button to run the Profiler by firing API Request
     */
    private JButton runProfilerButton;
    private final SynchronousTaskManager<?> taskManager;
    public JTextArea outputTextBox;
    /**
     * Stores path details of file
     */
    private JTextField filePathTextField;
    public SettingsPanel(CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager) {
        this.adapter = adapter;
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

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weighty = 100;
        gridBag.setConstraints(outputTextBox, gridBagConstraints);
        add(outputTextBox);
        gridBag.setConstraints(runProfilerButton,gridBagConstraints);
        add(runProfilerButton);

    }

    /**
     * textOrGraphPanel for choosing between text or graph based input
     */
    private void initialiseJComponents(){
        // runProfilerButton
        runProfilerButton = new JButton("Run g:Profiler");
        runProfilerButton.setMnemonic(KeyEvent.VK_B);
        runProfilerButton.addActionListener(new SettingsPanelActionListener(this, adapter,taskManager,false));
        outputTextBox = new JTextArea("");
        // add a textbox which will display the results of firing a query

    }
    public JTextArea getOutputTextBox(){
        return outputTextBox;
    }


}