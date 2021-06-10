package gprofiler.internal;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import gprofiler.internal.ui.SettingsPanel;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.work.SynchronousTaskManager;

/**
 * @description Controls the configuration of the gProfiler Menu in the Cytoscape distribution
 */
public class ProfilerPluginAction extends AbstractCyAction {
    private static final String MENU_NAME = "gProfiler";
    private static final String MENU_CATEGORY = "Apps";
    private static final String WINDOW_TITLE = "gProfiler Settings";
    private final CySwingAppAdapter adapter;
    private final SynchronousTaskManager<?> taskManager;
    public ProfilerPluginAction(CySwingAppAdapter adapter,final SynchronousTaskManager<?> taskManager){
        super(MENU_NAME);
        this.adapter = adapter;
        this.taskManager = taskManager;
        setPreferredMenu(MENU_CATEGORY);
    }

    /**
     * @description controls action triggered on selection of gProfiler after opening the menu in Cytoscape
     * @param event triggered when user clicks on gProfiler in the app menu
     */
    public void actionPerformed(ActionEvent event){
        final JFrame window = new JFrame(WINDOW_TITLE);
        SettingsPanel settingsPanel = null;
        try {
            settingsPanel = new SettingsPanel(adapter,taskManager);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.getContentPane().add(settingsPanel);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.pack();

        //main cytoscape window
        final JFrame mainWindow = adapter.getCySwingApplication().getJFrame();
        window.setLocationRelativeTo(mainWindow);
        window.setResizable(false);
        window.setVisible(true);
    }

}
