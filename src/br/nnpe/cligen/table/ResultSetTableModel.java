package br.nnpe.cligen.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * @author Sobreira Created on 16 de Janeiro de 2003, 13:13
 */
public abstract class ResultSetTableModel extends AbstractTableModel {
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	protected boolean soHum;

	public ResultSetTableModel(ResultSet aResultSet) {
		rs = aResultSet;

		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getColumnName(int c) {
		if (c == 0) {
			return "Row";
		}
		try {
			return rsmd.getColumnName(c);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return "";
		}
	}

	public int getColumnCount() {
		try {
			return rsmd.getColumnCount() + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}

	public String getColumnType(int i) {
		try {

			return rsmd.getColumnTypeName(i);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return e.getMessage();

		}

	}

	public String isNullable(int i) {
		try {

			return (rsmd.isNullable(i) == ResultSetMetaData.columnNullable ? " - nulls"
					: "");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
			return e.getMessage();
		}

	}

	protected ResultSet getResultSet() {
		return rs;
	}
}
