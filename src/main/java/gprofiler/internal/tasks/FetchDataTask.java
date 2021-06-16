package gprofiler.internal.tasks;

import gprofiler.internal.SettingsPanelActionListener;
import gprofiler.internal.ui.SettingsPanel;
import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public final class FetchDataTask implements Task {
   // private final CyNetwork network;
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsPanelActionListener.class);
    SettingsPanel settingsPanel;
    private boolean cancelled = false;
    private TaskMonitor taskMonitor = null;
    private boolean taskCompleted;
    private final CySwingAppAdapter adapter;

    public FetchDataTask(SettingsPanel settingsPanel, final CySwingAppAdapter adapter) {
        if (adapter == null)
            throw new NullPointerException("Plugin Adapter is null.");
        this.adapter = adapter;
        this.settingsPanel = settingsPanel;
        //this.network = network;
    }

    @Override
    public void run(TaskMonitor tMonitor) throws Exception {
        taskMonitor.setProgress(1.0);
        taskCompleted = true;
        return;
    }

    private Map<String, String> generateQuery(String query) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.put("organism","hsapiens");
        parameters.put("query",query);
        return parameters;
    }
    public void cancel() {
        this.cancelled = true;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }
}
