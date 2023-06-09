package br.nnpe.cligen.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * 
 * @author Sobreira Created on 21 de Janeiro de 2003, 14:09
 * 
 */
public class ScrollingResultSetTableModel extends ResultSetTableModel {
	private static final long serialVersionUID = 6937430623236803281L;

	public ScrollingResultSetTableModel(ResultSet aResultSet) {

		this(aResultSet, false);
	}

	public ScrollingResultSetTableModel(ResultSet aResultSet, boolean sohum) {
		super(aResultSet);
		this.soHum = true;
	}

	public Object getValueAt(int r, int c) {
		if (c == 0) {
			return new Integer(r + 1);
		}
		try {
			ResultSet rs = getResultSet();
			rs.absolute(r + 1);

			return rs.getObject(c);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	public int getRowCount() {
		ResultSet rs = getResultSet();
		try {
			if (soHum) {
				return 1;
			}
			rs.last();

			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
}
