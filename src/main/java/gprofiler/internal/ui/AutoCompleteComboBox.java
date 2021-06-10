package gprofiler.internal.ui;

import gprofiler.internal.SpeciesData;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Implementation Referred from https://www.tutorialspoint.com/how-can-we-implement-auto-complete-jcombobox-in-java
public class AutoCompleteComboBox extends JComboBox {
    public int caretPosition = 0;
    public JTextField tfield = null;
    public AutoCompleteComboBox(final SpeciesData species[], String defaultSpeciesName) {
        super(species);
        int defaultIndex =0;
        //by default set species to humans
        for(int i=0;i< species.length;i++){
            if(species[i].getDisplay_name().equals(defaultSpeciesName)){
                defaultIndex = i;
            }
        }
        setSelectedIndex(defaultIndex);
        setEditor(new BasicComboBoxEditor());
        setEditable(true);
    }

    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        tfield.setText(getItemAt(index).toString());
        tfield.setSelectionEnd(caretPosition + tfield.getText().length());
        tfield.moveCaretPosition(caretPosition);
    }

    public void setEditor(ComboBoxEditor editor) {
        super.setEditor(editor);
        if (editor.getEditorComponent() instanceof JTextField) {
            tfield = (JTextField) editor.getEditorComponent();
            tfield.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent ke) {
                    char key = ke.getKeyChar();
                    if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key))) return;
                    caretPosition = tfield.getCaretPosition();
                    String text = "";
                    try {
                        text = tfield.getText(0, caretPosition);
                    } catch (javax.swing.text.BadLocationException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < getItemCount(); i++) {
                        String element = (String) getItemAt(i);
                        if (element.startsWith(text)) {
                            setSelectedIndex(i);
                            return;
                        }
                    }
                }
            });

        }
    }
}
