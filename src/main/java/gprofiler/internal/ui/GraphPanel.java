package gprofiler.internal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class GraphPanel extends JPanel implements ItemListener {
    private JCheckBox graphButton;
    private boolean selectedOnly;
    private static String graphLabel = "Get gene list from selected network cluster";
    private Properties props;

    public GraphPanel(boolean selectedOnly){
        this.selectedOnly = selectedOnly;
        this.props = new Properties();
        setOpaque(false);
        initialiseJComponents(selectedOnly);
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBag);
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 10;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
    }

    private void initialiseJComponents(boolean selectedOnly){
        graphButton = new JCheckBox(graphLabel);
        graphButton.setBorder(BorderFactory.createEmptyBorder());
        graphButton.setEnabled(this.selectedOnly);
        graphButton.setMnemonic(KeyEvent.VK_N);
        graphButton.setActionCommand(graphLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(graphButton);
        //register a listener for the radio buttons
        graphButton.addItemListener(this);
    }


    /**
     * Boolean method for checking whether box is checked or not.
     *
     * @return boolean checked or not checked.
     */
    public boolean graphButtonChecked() {
        return graphButton.isSelected();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
            // update if the node has been selected or not
            this.selectedOnly = graphButton.isSelected();
    }
}
