package br.nnpe.cligen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.nnpe.cligen.internal.ConeccoesInternalFrame;
import br.nnpe.cligen.internal.GeradorClassPathInternalFrame;

/**
 * 
 * @author Paulo Sobreira Created on 15 de Janeiro de 2003, 15:07
 * 
 */
public class MainFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 2933902776883270081L;
	public static JDesktopPane jDesktopPane1;
	public static BarraSetTop barraSetTop;
	public static final Color bgColor = new Color(235, 235, 235);

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JSeparator jSeparator1;

	// End of variables declaration//GEN-END:variables
	private UIManager.LookAndFeelInfo[] looks;

	/** Creates new form mainFrame */
	public MainFrame() {
		looks = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < looks.length; i++) {
			System.out.println(looks[i].getName());
		}
		jDesktopPane1 = new JDesktopPane();
		barraSetTop = new BarraSetTop();

		initComponents();
	}

	public JDesktopPane getJDesktopPane() {
		return jDesktopPane1;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() { // GEN-BEGIN:initComponents
		buttonGroup2 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
		jSeparator1 = new javax.swing.JSeparator();
		jMenu3 = new javax.swing.JMenu();
		jMenu4 = new javax.swing.JMenu();
		jMenuItem2 = new javax.swing.JMenuItem();

		setTitle("CliGenBD Cliente Genérico de Banco de Dados");
		setBackground(new java.awt.Color(0, 0, 0));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jPanel1.setLayout(new java.awt.BorderLayout());

		jPanel1.setPreferredSize(new java.awt.Dimension(640, 480));
		jPanel1.add(jDesktopPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
		getContentPane().add(BarraSetTop.pane, java.awt.BorderLayout.SOUTH);

		jMenu1.setText("Principal");
		jMenuItem1.setText("Conectar");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});

		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Feito por Paulo Sobreira \n"
						+ "sowbreira@gmail.com.br \n"
						+ "https://sowbreira-26fe1.firebaseapp.com/ \n"
						+ "Janeiro de 2003";
				JOptionPane.showMessageDialog(MainFrame.this, msg, "Sobre",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		jMenu1.add(jMenuItem1);
		jMenu1.add(sobre);
		jMenuBar1.add(jMenu1);

		jMenu2.setText("Janelas");
		jMenu2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenu2ActionPerformed(evt);
			}
		});

		jCheckBoxMenuItem1.setText("OutLine");
		jCheckBoxMenuItem1
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(
							java.awt.event.ActionEvent evt) {
						jCheckBoxMenuItem1ActionPerformed(evt);
					}
				});

		jMenu2.add(jCheckBoxMenuItem1);

		jMenu2.add(jSeparator1);

		jMenu3.setText("LookAndFeel");

		for (int i = 0; i < looks.length; i++) {
			javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
			jRadioButtonMenuItem.setText(looks[i].getName());
			buttonGroup2.add(jRadioButtonMenuItem);
			final int index = i;
			jRadioButtonMenuItem
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(
								java.awt.event.ActionEvent evt) {
							mudaLookAndFell(evt, looks[index].getClassName());
						}
					});
			jMenu3.add(jRadioButtonMenuItem);
		}
		jMenu2.add(jMenu3);

		jMenuBar1.add(jMenu2);

		jMenu4.setText("Util");
		jMenuItem2.setText("Gerador de Classpath");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});

		jMenu4.add(jMenuItem2);

		jMenuBar1.add(jMenu4);

		setJMenuBar(jMenuBar1);
		jDesktopPane1.setBackground(bgColor);
		pack();
	} // GEN-END:initComponents

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jMenuItem2ActionPerformed

		GeradorClassPathInternalFrame geradorClassPathInternalFrame = new GeradorClassPathInternalFrame();
		jDesktopPane1.add(geradorClassPathInternalFrame);
		BarraSetTop.adicionarJIntenalFrame(geradorClassPathInternalFrame);
		geradorClassPathInternalFrame.show();
	} // GEN-LAST:event_jMenuItem2ActionPerformed

	private void mudaLookAndFell(java.awt.event.ActionEvent evt,
			String className) { // GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
		// Add your handling code here:
		String original = UIManager.getLookAndFeel().getClass().getName();
		if (original == null) {
			original = "javax.swing.plaf.metal.MetalLookAndFeel";
		}
		try {
			UIManager.setLookAndFeel(className);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			JTextArea ta = new JTextArea(3, 15);
			ta.setText(e.toString());
			JOptionPane.showMessageDialog(this, ta, e.getMessage(),
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			try {
				UIManager.setLookAndFeel(original);
				SwingUtilities.updateComponentTreeUI(this);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	} // GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

	private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jMenu2ActionPerformed

		// Add your handling code here:
	} // GEN-LAST:event_jMenu2ActionPerformed

	private void jCheckBoxMenuItem1ActionPerformed(
			java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
		// Add your handling code here:
		jDesktopPane1.putClientProperty("JDesktopPane.dragMode",
				jCheckBoxMenuItem1.isSelected() ? "outline" : null);
	} // GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jMenuItem1ActionPerformed

		// Add your handling code here:
		ConeccoesInternalFrame coneccoesInternalFrame = new ConeccoesInternalFrame();
		jDesktopPane1.add(coneccoesInternalFrame);
		coneccoesInternalFrame.show();
		BarraSetTop.adicionarJIntenalFrame(coneccoesInternalFrame);
	} // GEN-LAST:event_jMenuItem1ActionPerformed

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) { // GEN-FIRST:event_exitForm
		System.exit(0);
	} // GEN-LAST:event_exitForm

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		mf.setExtendedState(MAXIMIZED_BOTH);
	}
}