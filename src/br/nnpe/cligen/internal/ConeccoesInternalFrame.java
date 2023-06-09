package br.nnpe.cligen.internal;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import br.nnpe.cligen.BarraSetTop;
import br.nnpe.cligen.MainFrame;
import br.nnpe.cligen.table.CachingResultSetTableModel;

/**
 * 
 * @author Sobreira Created on 15 de Janeiro de 2003, 15:12
 */
public class ConeccoesInternalFrame extends javax.swing.JInternalFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	public static int NUM_JANELA;
	private JComboBox<String> dbName;
	private javax.swing.JButton conectar;
	private javax.swing.JButton abrirTabela;
	private javax.swing.JButton executarConsulta;
	private JComboBox<String> comboListaTabelas;
	private JTextField pesquisaTabelas;
	private List<String> listatabelas;
	private javax.swing.JPanel jPanelPricipal;
	private javax.swing.JPanel jPanelComponentes;
	private javax.swing.JPanel jPanelLabels;
	private javax.swing.JPanel jPanelTabelasServidor;
	private javax.swing.JPanel jPanelBotoes;
	private javax.swing.JPanel jPanelEsquerda;
	private javax.swing.JPanel jPanelInferior;
	private javax.swing.JPasswordField password;
	private javax.swing.JTextField driver;
	private javax.swing.JTextField url;
	private javax.swing.JTextField user;
	private JLabel servidor, versao;

	// End of variables declaration//GEN-END:variables
	private ResultSet rs;
	private Statement stmt;

	public ConeccoesInternalFrame() {
		NUM_JANELA++;
		initComponents();

		try {
			dbName.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					preenchedadosBase(arg0.getItem().toString());

				}

			});
			SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
			Document doc = builder.build(new File("databases.xml"));
			Element root = doc.getRootElement();
			List dbs = root.getChildren("db");
			for (Iterator iterator = dbs.iterator(); iterator.hasNext();) {
				Element db = (Element) iterator.next();
				if (db.getChild("name") != null) {
					dbName.addItem(db.getChild("name").getText().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Erro de SAXParser",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	private void preenchedadosBase(String dbName) {
		try {
			if (dbName == null) {
				return;
			}
			SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
			Document doc = builder.build(new File("databases.xml"));
			Element root = doc.getRootElement();
			List dbs = root.getChildren("db");
			for (Iterator iterator = dbs.iterator(); iterator.hasNext();) {
				Element db = (Element) iterator.next();
				if (db.getChild("name") != null && dbName.equals(db.getChild("name").getText().trim())) {
					driver.setText(db.getChild("dbdriver").getText().trim());
					url.setText(db.getChild("dburl").getText().trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Erro de SAXParser",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	public String getTitle() {
		return super.getTitle() + " " + NUM_JANELA;
	}

	private void initComponents() { // GEN-BEGIN:initComponents
		jPanelPricipal = new javax.swing.JPanel();
		jPanelEsquerda = new javax.swing.JPanel();
		jPanelLabels = new javax.swing.JPanel();
		jPanelComponentes = new javax.swing.JPanel();
		dbName = new JComboBox<String>();
		comboListaTabelas = new JComboBox<String>();
		pesquisaTabelas = new JTextField();
		listatabelas = new ArrayList<String>();
		driver = new javax.swing.JTextField();
		url = new javax.swing.JTextField();
		user = new javax.swing.JTextField();
		password = new javax.swing.JPasswordField();
		jPanelInferior = new javax.swing.JPanel();
		jPanelBotoes = new javax.swing.JPanel();
		conectar = new javax.swing.JButton();
		abrirTabela = new javax.swing.JButton();
		executarConsulta = new javax.swing.JButton();
		jPanelTabelasServidor = new javax.swing.JPanel();
		servidor = new JLabel();
		versao = new JLabel();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("ConDB");
		setMinimumSize(new java.awt.Dimension(600, 300));
		setPreferredSize(new java.awt.Dimension(600, 300));
		jPanelPricipal.setLayout(new java.awt.BorderLayout(10, 10));

		jPanelPricipal.setBorder(new javax.swing.border.TitledBorder("Conectar ao Banco de Dados"));
		jPanelPricipal.setPreferredSize(new java.awt.Dimension(380, 270));
		jPanelEsquerda.setLayout(new java.awt.BorderLayout(10, 10));

		jPanelBotoes.setLayout(new GridLayout(1, 3));

		jPanelLabels.setLayout(new java.awt.GridLayout(5, 0, 5, 5));

		jPanelLabels.add(new JLabel("Nome"));

		jPanelLabels.add(new JLabel("Driver"));

		jPanelLabels.add(new JLabel("Url"));

		jPanelLabels.add(new JLabel("User"));

		jPanelLabels.add(new JLabel("Password"));

		jPanelEsquerda.add(jPanelLabels, java.awt.BorderLayout.WEST);

		jPanelComponentes.setLayout(new java.awt.GridLayout(5, 0, 5, 5));

		jPanelComponentes.add(dbName);

		driver.setEditable(true);
		jPanelComponentes.add(driver);

		url.setEditable(true);
		jPanelComponentes.add(url);

		jPanelComponentes.add(user);

		jPanelComponentes.add(password);

		jPanelEsquerda.add(jPanelComponentes, java.awt.BorderLayout.CENTER);

		jPanelPricipal.add(jPanelEsquerda, java.awt.BorderLayout.CENTER);

		jPanelInferior.setLayout(new java.awt.BorderLayout());

		conectar.setText("Conectar");
		conectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				conectarAction(evt);
			}
		});

		jPanelBotoes.add(conectar);

		abrirTabela.setText("Abrir Tabela");
		abrirTabela.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				abrirTabelaAction(evt);
			}
		});

		jPanelBotoes.add(abrirTabela);

		executarConsulta.setText("Executar SQL");
		executarConsulta.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				executarSqlAction(evt);
			}
		});

		jPanelBotoes.add(executarConsulta);

		jPanelInferior.add(jPanelBotoes, java.awt.BorderLayout.NORTH);

		jPanelTabelasServidor.setLayout(new java.awt.GridLayout(4, 1));

		jPanelTabelasServidor.add(pesquisaTabelas);

		jPanelTabelasServidor.add(comboListaTabelas);

		jPanelTabelasServidor.add(servidor);

		jPanelTabelasServidor.add(versao);

		jPanelInferior.add(jPanelTabelasServidor, java.awt.BorderLayout.CENTER);

		jPanelPricipal.add(jPanelInferior, java.awt.BorderLayout.SOUTH);

		getContentPane().add(jPanelPricipal, java.awt.BorderLayout.CENTER);
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				super.internalFrameClosing(e);
				BarraSetTop.removerJIntenalFrame(ConeccoesInternalFrame.this);
			}
		});
		pack();
	} // GEN-END:initComponents

	private void executarSqlAction(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jButton3ActionPerformed

		// Add your handling code here:
		try {
			if (rs != null) {
				rs.close();
			}
			String tableName = (String) comboListaTabelas.getSelectedItem();
			SqlInternalFrame pesquisaInternalFrame;
			try {
				NUM_JANELA++;
				pesquisaInternalFrame = new SqlInternalFrame(null, "Sql ", stmt);
			} catch (Exception e) {
				NUM_JANELA--;
				throw e;
			}

			MainFrame.jDesktopPane1.add(pesquisaInternalFrame);
			pesquisaInternalFrame.show();
			BarraSetTop.adicionarJIntenalFrame(pesquisaInternalFrame);
		} catch (Exception e) {
			JTextArea ta = new JTextArea(3, 15);
			ta.setText(e.toString());
			JOptionPane.showMessageDialog(this, ta, "Erro SQL", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	} // GEN-LAST:event_jButton3ActionPerformed

	private void abrirTabelaAction(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jButton2ActionPerformed

		// Add your handling code here:
		try {
			String tableName = (String) comboListaTabelas.getSelectedItem();

			if (rs != null) {
				rs.close();
			}

			String query = "SELECT * FROM " + tableName;
			rs = stmt.executeQuery(query);

			SqlInternalFrame pesquisaInternalFrame;

			try {
				NUM_JANELA++;
				pesquisaInternalFrame = new SqlInternalFrame(new CachingResultSetTableModel(rs, true), tableName, stmt);
			} catch (Exception e) {
				NUM_JANELA--;
				throw e;
			}

			MainFrame.jDesktopPane1.add(pesquisaInternalFrame);
			pesquisaInternalFrame.show();
			BarraSetTop.adicionarJIntenalFrame(pesquisaInternalFrame);
			pesquisaInternalFrame.setMaximum(true);

		} catch (Exception e) {
			JTextArea ta = new JTextArea(3, 15);
			ta.setText(e.toString());
			JOptionPane.showMessageDialog(this, ta, "Erro SQL", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	} // GEN-LAST:event_jButton2ActionPerformed

	private void conectarAction(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jButton1ActionPerformed

		// Add your handling code here:
		try {
			Class.forName((String) driver.getText());

			Connection con = DriverManager.getConnection((String) url.getText(), user.getText(),
					new String(password.getPassword()));

			stmt = con.createStatement();

			DatabaseMetaData md = con.getMetaData();
			ResultSet mrs = md.getTables(null, null, null, new String[] { "TABLE" });

			while (mrs.next()) {
				String nomeTabela = mrs.getString(3);
				listatabelas.add(nomeTabela);
				comboListaTabelas.addItem(nomeTabela);
			}

			mrs.close();
			servidor.setText("Servidor: " + md.getDatabaseProductName());
			versao.setText("Versão: " + md.getDatabaseProductVersion());
			pesquisaTabelas.addKeyListener(new KeyAdapter() {

				@Override
				public void keyReleased(KeyEvent arg0) {
					comboListaTabelas.removeAllItems();
					if ("".equals(pesquisaTabelas.getText())) {
						for (Iterator iterator = listatabelas.iterator(); iterator.hasNext();) {
							String nomeTabela = (String) iterator.next();
							comboListaTabelas.addItem(nomeTabela);
						}
					} else {
						for (Iterator iterator = listatabelas.iterator(); iterator.hasNext();) {
							String nomeTabela = (String) iterator.next();
							if (nomeTabela.toLowerCase().contains(pesquisaTabelas.getText().toLowerCase())) {
								comboListaTabelas.addItem(nomeTabela);
							}
						}
					}
				}
			});
			conectar.setEnabled(false);
		} catch (Exception e) {
			JTextArea ta = new JTextArea(3, 15);
			ta.setText(e.toString());
			JOptionPane.showMessageDialog(this, ta, "Erro SQL", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	} // GEN-LAST:event_jButton1ActionPerformed
}
