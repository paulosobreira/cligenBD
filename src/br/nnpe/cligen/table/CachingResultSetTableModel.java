package br.nnpe.cligen.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * @author Sobreira Created on 16 de Janeiro de 2003, 13:18
 */
public class CachingResultSetTableModel extends ResultSetTableModel {
	private static final long serialVersionUID = 2733364870122110178L;
	private ArrayList cache;
	private DecimalFormat format = new DecimalFormat("0000");

	public CachingResultSetTableModel(ResultSet aResultSet) {
		this(aResultSet, false);
	}

	public CachingResultSetTableModel(ResultSet aResultSet, boolean sohum) {
		super(aResultSet);

		try {
			cache = new ArrayList();

			int cols = getColumnCount();
			ResultSet rs = getResultSet();

			/*
			 * place all data in an array list of Object[] arrays We don't use
			 * an Object[][] because we don't know how many rows are in the
			 * result set
			 */
			int cont = 0;
			while (rs.next()) {
				Object[] row = new Object[cols];

				for (int j = 0; j < row.length - 1; j++)
					row[j] = rs.getObject(j + 1);

				if (cont > 8195) {
					break;
				}

				cache.add(row);
				cont++;
				if (sohum) {
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Erro de SQL", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Object getValueAt(int r, int c) {
		if (c == 0) {
			return format.format(r + 1);
		}

		if (r < cache.size()) {
			return ((Object[]) cache.get(r))[c - 1];
		} else {
			return null;
		}
	}

	public int getRowCount() {
		return cache.size();
	}
}
