package br.nnpe.cligen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.nnpe.cligen.internal.ConeccoesInternalFrame;

public class BarraSetTop {
	private static Map jInternais = new HashMap();
	private static JPanel panel = new JPanel();
	public static JScrollPane pane = new JScrollPane();

	public BarraSetTop() {
		pane.setViewportView(panel);
	}

	public static void adicionarJIntenalFrame(final JInternalFrame internalFrame) {
		JButton button = new JButton(internalFrame.getTitle());
		jInternais.put(internalFrame, button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					internalFrame.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		try {
			internalFrame.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		atualizarBarra();
	}

	public static void removerJIntenalFrame(JInternalFrame internalFrame) {
		jInternais.remove(internalFrame);
		ConeccoesInternalFrame.NUM_JANELA--;
		atualizarBarra();
	}

	private static void atualizarBarra() {
		panel.removeAll();

		for (Iterator iter = jInternais.keySet().iterator(); iter.hasNext();) {
			JInternalFrame element = (JInternalFrame) iter.next();
			JButton button = (JButton) jInternais.get(element);
			panel.add(button);
		}
		panel.revalidate();
		panel.repaint();
	}
}
