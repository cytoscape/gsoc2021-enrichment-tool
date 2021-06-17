package gprofiler.internal;

import gprofiler.internal.tasks.FetchDataTask;
import gprofiler.internal.ui.SettingsPanel;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.FinishStatus;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.SynchronousTaskManager;
import org.cytoscape.work.TaskObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanelActionListener implements ActionListener {
    private SettingsPanel settingsPanel;
    boolean isSelected;
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsPanelActionListener.class);
    private final CySwingAppAdapter adapter;

    private final SynchronousTaskManager<?> taskManager;
    public SettingsPanelActionListener(SettingsPanel settingsPanel, boolean isSelected, CySwingAppAdapter adapter, final SynchronousTaskManager<?> taskManager){
        this.settingsPanel = settingsPanel;
        this.isSelected = isSelected;
        this.taskManager = taskManager;
        this.adapter = adapter;
    }
    /**
     * @description action that is performed when the "Run gProfiler" button is pressed
     */
    public void actionPerformed(ActionEvent event){
        Component root  = SwingUtilities.getRoot((JButton)event.getSource());
        root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final FetchDataTask fetchResultsTask = new FetchDataTask(settingsPanel,adapter);
        taskManager.execute(new GenericTaskFactory(fetchResultsTask).createTaskIterator(),  new TaskObserver() {
            @Override
            public void taskFinished(ObservableTask observableTask) {
                // ignored
                JOptionPane.showMessageDialog(settingsPanel, observableTask.toString(),"Success", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void allFinished(FinishStatus finishStatus) {
                if (finishStatus.getType() == FinishStatus.Type.FAILED && finishStatus.getException() != null) {
                    JOptionPane.showMessageDialog(settingsPanel, finishStatus.getException().getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        adapter.getCySwingApplication().getJFrame().toFront();
        if(fetchResultsTask.isTaskCompleted())
        {
            settingsPanel.getOutputTextBox().setText("Code ran successfully");
            root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
