package br.nnpe.cligen.internal;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import br.nnpe.cligen.BarraSetTop;
import br.nnpe.cligen.ExcelAdapter;
import br.nnpe.cligen.MainFrame;
import br.nnpe.cligen.format.BasicFormatterImpl;
import br.nnpe.cligen.table.CachingResultSetTableModel;
import br.nnpe.cligen.table.ResultSetTableModel;

/**
 * @author Sobreira Created on 16 de Janeiro de 2003, 13:33
 */
public class SqlInternalFrame extends javax.swing.JInternalFrame {

    private Statement stmt;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualiza;
    private javax.swing.JButton formata;
    private javax.swing.JScrollPane jScrollPaneResultadoPesquisa;
    private javax.swing.JPanel botoesSQLPanel;
    private javax.swing.JButton consulta;
    private javax.swing.JPanel jPanelConsulta;
    private javax.swing.JScrollPane jScrollPaneConsulta;
    private javax.swing.JTextArea jTextAreaConsulta;
    private javax.swing.JTextArea jTextAreaCampos;
    private javax.swing.JPanel jPanelResultado;
    private javax.swing.JTable jTableResultado;
    private JSplitPane splitPaneSup;
    private String tbName;

    public SqlInternalFrame(ResultSetTableModel amodel, String aTBname, Statement astmt) throws IOException {
        super();
        this.tbName = aTBname;
        initComponents();
        this.setTitle(aTBname);

        if (amodel != null) {
            jTableResultado.setModel(amodel);
            initColumnSizes(jTableResultado);
        }
        stmt = astmt;
        setVisible(true);
        addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (JInternalFrame.IS_MAXIMUM_PROPERTY.equals(evt.getPropertyName())) {
                    spliDivCampos();
                }
            }
        });
        addInternalFrameListener(new InternalFrameListener() {

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                spliDivCampos();
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                spliDivCampos();
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                spliDivCampos();
            }
        });
        pack();
        try {
            setSelected(true);
            lerSql();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void spliDivCampos() {
        int setentaPorcento = (this.getWidth() * 70) / 100;
        if ("".equals(jTextAreaCampos.getText())) {
            splitPaneSup.setDividerLocation(Integer.MAX_VALUE);
        } else if (setentaPorcento != splitPaneSup.getDividerLocation()) {
            splitPaneSup.setDividerLocation(setentaPorcento);
        }
    }

    private void lerSql() throws IOException {
        String consulta = "sql" + File.separator + this.getTitle().split(" ")[0] + ".sql";
        System.out.println(consulta);
        BufferedReader in = new BufferedReader(
                new FileReader(consulta));
        String readLine = in.readLine();
        while (readLine != null) {
            jTextAreaConsulta.append(readLine + "\n");
            readLine = in.readLine();
        }
        System.out.println(jTextAreaConsulta.getText());
    }

    public String getTitle() {
        return super.getTitle() + " " + ConeccoesInternalFrame.NUM_JANELA;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() { // GEN-BEGIN:initComponents
        jPanelResultado = new javax.swing.JPanel();
        jScrollPaneResultadoPesquisa = new javax.swing.JScrollPane();
        jTableResultado = new javax.swing.JTable();
        jPanelConsulta = new javax.swing.JPanel();
        jScrollPaneConsulta = new javax.swing.JScrollPane();
        jTextAreaConsulta = new javax.swing.JTextArea();
        botoesSQLPanel = new javax.swing.JPanel();
        consulta = new javax.swing.JButton();
        atualiza = new javax.swing.JButton();
        formata = new JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        jPanelResultado.setLayout(new GridLayout(1, 1));
        new ExcelAdapter(jTableResultado);
        jTableResultado.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new String[]{}));
        jScrollPaneResultadoPesquisa.setViewportView(jTableResultado);
        jTableResultado.setAutoCreateRowSorter(true);

        jTableResultado.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                initColumnSizes(jTableResultado);
            }
        });

        jPanelConsulta.setLayout(new java.awt.BorderLayout());

        jTextAreaConsulta.setRows(5);
        jTextAreaConsulta.setBackground(MainFrame.bgColor);
        jScrollPaneConsulta.setViewportView(jTextAreaConsulta);

        jTextAreaCampos = new JTextArea(25, 18);
        jTextAreaCampos.setBackground(MainFrame.bgColor);

        splitPaneSup = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jScrollPaneConsulta,
                new JScrollPane(jTextAreaCampos));
        jTextAreaCampos.setFont(new Font("Arial", Font.BOLD, 16));
        jTextAreaConsulta.setFont(new Font("Arial", Font.BOLD, 16));
        splitPaneSup.setContinuousLayout(true);
        splitPaneSup.setOneTouchExpandable(true);
        splitPaneSup.setDividerLocation(Integer.MAX_VALUE);
        jPanelConsulta.add(splitPaneSup, java.awt.BorderLayout.CENTER);
        botoesSQLPanel.setLayout(new java.awt.GridLayout(1, 8));

        consulta.setText("Consulta F5");
        consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executarConsulta();
            }
        });

        botoesSQLPanel.add(consulta);

        atualiza.setText("Atualização");
        atualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizacao();
            }
        });

        botoesSQLPanel.add(atualiza);

        formata.setText("Formata");
        formata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formata();
            }
        });

        botoesSQLPanel.add(formata);

        jPanelConsulta.add(botoesSQLPanel, java.awt.BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jPanelConsulta, jScrollPaneResultadoPesquisa);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
        jPanelResultado.add(splitPane);
        getContentPane().add(jPanelResultado);
        jTextAreaConsulta.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCoode = e.getKeyCode();

                if (keyCoode == KeyEvent.VK_F5) {
                    executarConsulta();
                }
            }
        });
        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e) {
                super.internalFrameClosing(e);
                BarraSetTop.removerJIntenalFrame(SqlInternalFrame.this);
            }
        });
        insereBotoesTemplates();
    } // GEN-END:initComponents

    protected void formata() {
        String text = jTextAreaConsulta.getText();
        BasicFormatterImpl basicFormatterImpl = new BasicFormatterImpl();
        if (text.contains(";")) {
            jTextAreaConsulta.setText("");
            String[] split = text.trim().split(";");
            for (int i = 0; i < split.length; i++) {
                jTextAreaConsulta.append(basicFormatterImpl.format(split[i]) + ";\n");
            }
        } else {
            jTextAreaConsulta.setText(basicFormatterImpl.format(text));
        }
    }

    private void adicionarTemplate(String string) {
        jTextAreaConsulta.append("\n" + string);

    }

    private void initColumnSizes(JTable table) {

        ResultSetTableModel model = (ResultSetTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < model.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width + 50;
            column.setMinWidth(headerWidth);
        }
    }

    private void insereBotoesTemplates() {
        JButton select = new JButton("<Select>");
        select.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                adicionarTemplate("select * from " + tbName + ";");

            }

        });
        JButton insert = new JButton("<Insert>");
        insert.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StringBuffer buffer = new StringBuffer();
                if (jTableResultado.getModel() instanceof ResultSetTableModel) {
                    ResultSetTableModel model = (ResultSetTableModel) jTableResultado.getModel();

                    buffer.append("(");
                    for (int i = 1; i < model.getColumnCount(); i++) {
                        buffer.append(model.getColumnName(i));
                        if (i == model.getColumnCount() - 1) {
                            buffer.append(")");
                        } else {
                            buffer.append(",");
                        }
                    }
                }
                String result = buffer.toString();
                adicionarTemplate("insert into " + tbName + " " + result + " values " + result + ";");

            }

        });
        JButton update = new JButton("<Update>");
        update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StringBuffer buffer = new StringBuffer();
                if (jTableResultado.getModel() instanceof ResultSetTableModel) {
                    ResultSetTableModel model = (ResultSetTableModel) jTableResultado.getModel();
                    int[] selectsCols = jTableResultado.getSelectedColumns();
                    boolean vaiTodas = false;
                    if (selectsCols.length == 0) {
                        vaiTodas = true;
                    }
                    for (int i = 1; i < model.getColumnCount(); i++) {
                        if (!vaiTodas) {
                            boolean continua = true;
                            for (int j = 0; j < selectsCols.length; j++) {
                                if (selectsCols[j] == i) {
                                    continua = false;
                                }
                            }
                            if (continua) {
                                continue;
                            }
                        }
                        buffer.append(model.getColumnName(i));
                        buffer.append(" = ");
                        buffer.append(model.getColumnName(i));
                        if (i != model.getColumnCount() - 1) {
                            buffer.append(",");
                        }
                    }
                }
                adicionarTemplate("update " + tbName + " set " + buffer.toString() + " where ? ;");

            }

        });
        JButton delete = new JButton("<Delete>");
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                adicionarTemplate("delete from " + tbName + " where ? ;");

            }

        });

        JButton campos = new JButton("Campos");
        campos.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StringBuffer buffer = new StringBuffer();
                if (jTableResultado.getModel() instanceof ResultSetTableModel) {
                    ResultSetTableModel amodel = (ResultSetTableModel) jTableResultado.getModel();
                    if (amodel == null) {
                        return;
                    }
                    for (int i = 1; i < amodel.getColumnCount(); i++) {
                        String nomeCampo = amodel.getColumnName(i);
                        String tipoCampo = amodel.getColumnType(i);
                        if (nomeCampo != null) {
                            nomeCampo = nomeCampo.toUpperCase().trim();
                        }
                        if (tipoCampo != null) {
                            tipoCampo = tipoCampo.toLowerCase().trim();
                        }
                        buffer.append(nomeCampo + " - " + tipoCampo + amodel.isNullable(i) + "\n");

                    }
                }
                if ("".equals(buffer.toString())) {
                    JTextArea ta = new JTextArea(3, 15);
                    ta.setText("Execute uma consulta Antes");
                    JOptionPane.showMessageDialog(SqlInternalFrame.this, ta, "Sem Campos",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                jTextAreaCampos.setText(buffer.toString());
                spliDivCampos();
            }

        });

        botoesSQLPanel.add(select);
        botoesSQLPanel.add(insert);
        botoesSQLPanel.add(update);
        botoesSQLPanel.add(delete);
        botoesSQLPanel.add(campos);
        // TODO Auto-generated method stub

    }

    // GEN-LAST:event_jButton3ActionPerformed

    private void atualizacao() { // GEN-FIRST:event_jButton2ActionPerformed

        // Add your handling code here:
        try {
            String query = jTextAreaConsulta.getSelectedText();
            int afetados = 0;
            if ("".equals(query) || (query == null)) {
                throw new Exception("Selecione um DML");
            }
            if (query.indexOf(";") != -1) {
                query = query.replace(";", "");
            }
            afetados = stmt.executeUpdate(query);

            String txt = "Linhas afetadas: " + afetados + " = " + query.trim();
            JOptionPane.showMessageDialog(this, txt, "Atualização Concluida", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JTextArea ta = new JTextArea(3, 15);
            ta.setText(e.toString());
            JOptionPane.showMessageDialog(this, ta, "Erro SQL", JOptionPane.ERROR_MESSAGE);
        }
    } // GEN-LAST:event_jButton2ActionPerformed

    private void executarConsulta() {
        try {
            String query = jTextAreaConsulta.getSelectedText();

            if ("".equals(query) || (query == null)) {
                query = jTextAreaConsulta.getText();
            }

            if (query.contains(";") && query.trim().split(";").length > 1) {
                throw new Exception("Selecione uma consulta");
            }
            if (query.indexOf(";") != -1) {
                query = query.replace(";", "");
            }

            ResultSet rs = stmt.executeQuery(query);
            jTableResultado.setModel(new CachingResultSetTableModel(rs));
            initColumnSizes(jTableResultado);
            try {
                salvarConsulta();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            JTextArea ta = new JTextArea(3, 15);
            ta.setText(e.toString());
            JOptionPane.showMessageDialog(this, ta, "Erro SQL", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarConsulta() throws FileNotFoundException {
        String consulta = "sql" + File.separator + this.getTitle().split(" ")[0] + ".sql";
        System.out.println(consulta);
        PrintStream out = new PrintStream(
                new FileOutputStream(consulta));
        System.out.println(jTextAreaConsulta.getText());
        out.print(jTextAreaConsulta.getText());
        out.close();
    }

    // End of variables declaration//GEN-END:variables
}
