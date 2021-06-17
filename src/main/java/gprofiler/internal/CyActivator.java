package gprofiler.internal;


import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.work.SynchronousTaskManager;
import org.osgi.framework.BundleContext;

import java.util.Properties;

public class CyActivator extends AbstractCyActivator {
    public CyActivator() {
        super();
    }

    /**
     *
     * @description add a simple panel with a single box that is clicked by user to display the results of the gProfiler API request
     */
    public void start(BundleContext bc) {

        CySwingAppAdapter adapter = getService(bc,CySwingAppAdapter.class);
        //register service
        SynchronousTaskManager<?> taskManager = getService(bc, SynchronousTaskManager.class);
        ProfilerPluginAction gProfilerPluginAction = new ProfilerPluginAction(adapter,taskManager);
        registerService(bc,gProfilerPluginAction, CyAction.class, new Properties());
    }
}
