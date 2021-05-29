package gprofiler.internal;


import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.util.swing.OpenBrowser;
import org.cytoscape.work.SynchronousTaskManager;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class CyActivator extends AbstractCyActivator {
    public CyActivator() {
        super();
    }

    public void start(BundleContext bc) {

        CySwingAppAdapter adapter = getService(bc,CySwingAppAdapter.class);
        OpenBrowser openBrowserService = getService(bc,OpenBrowser.class);
        SynchronousTaskManager<?> taskManager = getService(bc, SynchronousTaskManager.class);
        ProfilerPluginAction gProfilerPluginAction = new ProfilerPluginAction(adapter,taskManager);
        registerService(bc,gProfilerPluginAction, CyAction.class, new Properties());
    }
}
