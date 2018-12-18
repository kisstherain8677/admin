package admin_frame;

import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.table.AbstractTableModel;

import admin.DataProcessing;

public class FileModel extends AbstractTableModel {
	
	FileModel()throws Exception{
		super();
	}
	
	 String[] columns = {"id","creator","timestamp","description","filename"};
	 String[][]values = getFileTableValues(columns);
	
	 String[][] getFileTableValues(String[] columnNames) throws SQLException, ClassNotFoundException{
		 int count ;
			
			Enumeration et = DataProcessing.getAllDocs();
			for(count=0;et.hasMoreElements();count++) 
				et.nextElement();
		String[][] tableValues = new String[count][columnNames.length] ;
		
			Enumeration e =DataProcessing.getAllDocs();
			String[] curValue = new String[count];
			//将每一个枚举量取出来，作为元素存入curValue
			int i = 0;
			while(e.hasMoreElements()) {
				curValue[i] = e.nextElement().toString();
				i++;
			}
			//在每一个元素中
			int j, m;
			for( j=0;j<count;j++) {
				for( m=0;m<curValue[j].split(",").length;m++) {
				tableValues[j][m] = curValue[j].split(",")[m];
				}
			}
			
			return tableValues;
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return values.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return values[arg0][arg1];
	}
	
	public String[] getColumns() {
		return this.columns;
	}
	public String[][] getValues(){
		return this.values;
	}

}
