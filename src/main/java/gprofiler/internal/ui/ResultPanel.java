package gprofiler.internal.ui;

import org.cytoscape.app.swing.CySwingAppAdapter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Set;

public class ResultPanel extends JPanel {
    public static final String HEADER_TERM_NAME = "Term Name";
    public static final String HEADER_TERM_ID = "Term ID";
    public static final String HEADER_P_VAL = "p_adj";
    public static final String TERM_SIZE = "Term Size";
    public static final String QUERY_SIZE = "Query Size";
    public static final String EFFECTIVE_DOMAIN_SIZE = "Effective Domain Size";

    private final CyNetworkView originalNetworkView;
    private final CyNetwork originalNetwork;
    private Object[] columnNames;
    private Object[][] data;
    /** HashSet with the names of the selected nodes. */
    private Set selectedCanonicalNameVector;
    private final CySwingAppAdapter adapter;
    private JPanel basePanel;
    private JScrollPane jScrollPane;
    private JTable jTable;
    final static int SELECT_COLUMN = 0;
    final static int GO_TERM_COLUMN = 1;
    final static int DESCRIPTION_COLUMN = 2;

    public int getSelectColumn() {
        return this.SELECT_COLUMN;
    }
    public int getGoTermColumn() {
        return this.GO_TERM_COLUMN;
    }

    public int getDescriptionColumn() {
        return this.DESCRIPTION_COLUMN;
    }

    //data fetched from a json object
    public ResultPanel(Set selectedCanonicalNameVector, CyNetwork currentNetwork, CyNetworkView currentNetworkview, CySwingAppAdapter adapter){
        this.adapter = adapter;
        this.originalNetwork = currentNetwork;
        this.originalNetworkView = currentNetworkview;
        this.selectedCanonicalNameVector = selectedCanonicalNameVector;
        initialiseJComponents();
    }

    private void initialiseJComponents() {
        columnNames = setHeadersForJTable();
        data = makeDataForJTable(columnNames.length);
        basePanel = new javax.swing.JPanel();
        JButton jCloseButton = new JButton("Close tab");
        JPanel jPanelButtons = new JPanel();

        jCloseButton.addActionListener(new CloseResultPanelListener(this));
        jPanelButtons.add(jCloseButton);
        basePanel.add(jPanelButtons,java.awt.BorderLayout.NORTH);
        final ResultTableModel resultTableModel = new ResultTableModel(columnNames,data);
        jTable = new JTable(resultTableModel);
        jTable.setDragEnabled(false);
        jTable.setCellSelectionEnabled(true);
        TableCellRenderer headerRenderer = jTable.getTableHeader().getDefaultRenderer();
        TableColumnModel tableColumnModel = jTable.getColumnModel();
        /**
         * Set values for each of the columns
         */

     /*   jTable.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() < 2) {
                    return;
                }
                int tableColumn = jTable.columnAtPoint(e.getPoint());
                int modelColumn = jTable.convertColumnIndexToModel(tableColumn);
            }
        });*/
        jScrollPane = new javax.swing.JScrollPane();
        basePanel.add(jScrollPane);
        this.add(basePanel,BorderLayout.CENTER);
    }

    /**
     * @description Process the data returned by HTTP request properly
     * @param length
     * @return
     */
    private Object[][] makeDataForJTable(int length) {
        //
        return this.data;
    }

    private Object[] setHeadersForJTable() {
        Object[] header = new Object[7];
        header[0] = " ";
        header[1] = HEADER_TERM_NAME;
        header[2] = HEADER_TERM_ID;
        header[3] = HEADER_P_VAL;
        header[4] = TERM_SIZE;
        header[5] = QUERY_SIZE;
        header[6] = EFFECTIVE_DOMAIN_SIZE;

        return header;
    }

}
