package ca.mcgill.comp421.ill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.vandermeer.asciitable.AsciiTable;

/**
 * 
 * @author Percy
 * Generic class to store the result of a query
 *
 */
public class ResultTable {
	HashMap<String, List<Object>> table;
	List<String> keyType;
	
	public ResultTable(List<String> keyType) {
		table = new HashMap<String, List<Object>>();
		this.keyType = keyType;
		
		for(String s : keyType) {
			table.put(s, new ArrayList<Object>());
		}
	}
	
	public void loadResult(ResultSet result) throws SQLException {
		while(result.next()) {
			for(String s : keyType) {
				table.get(s).add(result.getObject(s));
			}
		}
	}

	public List<String> getKeyType()
	{
		return this.keyType;
	}

	public int getSize() {
		Iterator<String> i = table.keySet().iterator();
		String key = i.next();
		if(key != null) {
			return table.get(key).size();
		}else {
			return 0;
		}
	}
	
	public String toString() {
		AsciiTable at = new AsciiTable();
		List<String> keys = new ArrayList<String>(table.keySet());
		// add the keys as the header
		at.addRule();
		at.addRow(keys);
		
		int n = getSize();
		
		for(int i = 0; i < n; i++) {
			at.addRule();
			final int index = i;
			List<String> record = keys.stream().map(key -> {
				Object o = table.get(key).get(index);
				return o == null? "":o.toString();
			}).collect(Collectors.toList());
			at.addRow(record);
			at.addRule();
		}
		return at.render();
	}
	
	public List<List<String>> toList(){
		List<List<String>> list = new ArrayList<List<String>>();
		
		for(String key : table.keySet()) {
			List<String> column = new ArrayList<String>();
			column.add(key);
			column.addAll(table.get(key).stream().map(a -> a!=null? a.toString() : "").collect(Collectors.toList()));
			list.add(column);
		}
		
		return list;
	}
	
	public boolean toCsv(String filename) {
		boolean success = false;
		try {
			PrintWriter writer = new PrintWriter(new File(filename));
			List<List<String>> formatedTable = toList();
			StringBuilder output = new StringBuilder();
			
			if(formatedTable.size() > 0) {
				for(int i = 0; i < formatedTable.get(0).size(); i++) {
					for(int j = 0; j < formatedTable.size() - 1; j++) {
						output.append(formatedTable.get(j).get(i) + ",");
					}
					
					if(formatedTable.size() > 0) {
						output.append(formatedTable.get(formatedTable.size() - 1).get(i));
						output.append("\n");

					}		
				}
				writer.write(output.toString());
				writer.close();
				success = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}
	
}
