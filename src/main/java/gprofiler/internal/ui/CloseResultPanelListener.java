package gprofiler.internal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseResultPanelListener implements ActionListener {
    ResultPanel resultPanel;
    EnrichmentTableModel enrichmentTableModel;
    public CloseResultPanelListener(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public CloseResultPanelListener(EnrichmentTableModel enrichmentTableModel) {
        this.enrichmentTableModel = enrichmentTableModel;
    }

    public void actionPerformed(ActionEvent e){
        // remove the result panel
    }
}
