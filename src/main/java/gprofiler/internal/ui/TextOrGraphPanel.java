package gprofiler.internal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TextOrGraphPanel extends JPanel implements ActionListener {
    private JRadioButton graphButton;
    private JRadioButton textButton;
    private JPanel radioPanel;
    private JScrollPane scrollPane;
    private JTextArea textInputArea;
    private static String graphLabel = "Get cluster from network";
    private static String textLabel = "Take genes as input query";
    public TextOrGraphPanel(boolean graph, String text){
        super();
        setOpaque(false);
        initialiseJComponents(text,graph);
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBag);
        gridBagConstraints.weighty = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
        gridBag.setConstraints(radioPanel,gridBagConstraints);
        add(radioPanel);
        gridBagConstraints.weighty = 10;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(scrollPane,gridBagConstraints);
        add(scrollPane);
    }

    private void initialiseJComponents(String text,boolean graph){
        graphButton = new JRadioButton(graphLabel);
        graphButton.setBorder(BorderFactory.createEmptyBorder());
        graphButton.setMnemonic(KeyEvent.VK_N);
        graphButton.setActionCommand(graphLabel);

        textButton = new JRadioButton(textLabel);
        textButton.setBorder(BorderFactory.createEmptyBorder());
        textButton.setMnemonic(KeyEvent.VK_T);
        textButton.setActionCommand(textLabel);

        // both the buttons should be in a panel
        radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radioPanel.add(graphButton);
        radioPanel.add(Box.createHorizontalStrut(15));
        radioPanel.add(textButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(graphButton);
        buttonGroup.add(textButton);
        //register a listener for the radio buttons
        graphButton.addActionListener(this);
        textButton.addActionListener(this);

        textInputArea = new JTextArea(text,1000,500);
        scrollPane = new JScrollPane(textInputArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        if(graph){
            textInputArea.setEditable(false);
            textInputArea.setEnabled(false);
            scrollPane.setEnabled(false);
        }
    }

    /**
     * @description getter for input text
     * @return input text
     */
    public String getInputText() {
        return textInputArea.getText();
    }


    /**
     * Boolean method for checking whether box is checked or not.
     *
     * @return boolean checked or not checked.
     */
    public boolean graphButtonChecked() {
        return graphButton.isSelected();
    }

    /**
     * @description action performed when radioButton is clicked
     */
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(textLabel)){
            textInputArea.setEnabled(true);
            textInputArea.setEditable(true);
            scrollPane.setEnabled(true);
        } else {
            textInputArea.setEnabled(false);
            textInputArea.setEditable(false);
            scrollPane.setEnabled(false);
        }
    }

}
