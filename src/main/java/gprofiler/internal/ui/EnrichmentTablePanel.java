package gprofiler.internal.ui;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.work.SynchronousTaskManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * It generates an enrichment table. Also allows users to
 */
public class EnrichmentTablePanel extends JPanel implements ActionListener {
    private final CySwingAppAdapter adapter;
    /**
     * Button to run the Profiler by firing API Request
     */
    private final SynchronousTaskManager<?> taskManager;

    public EnrichmentTablePanel(CySwingAppAdapter adapter, final SynchronousTaskManager<?> taskManager) throws IOException, InterruptedException {
        this.adapter = adapter;
        this.taskManager = taskManager;
        initialiseJComponents();

    }

    /**
     * @description Intializes Table of Results, Icon to run gProfiler, Icon to visualize results Icon for quick options, Icon for exports
     */
    private void initialiseJComponents()  {
        /**
         * Top Panel with all the tools
         */

        /**
         * Table Model
         */

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}