package gprofiler.internal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseResultPanelListener implements ActionListener {
    ResultPanel resultPanel;
    public CloseResultPanelListener(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }
    public void actionPerformed(ActionEvent e){
        // remove the result panel
    }
}
