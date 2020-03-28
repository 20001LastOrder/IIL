package ca.mcgill.comp421.ill.model;

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
	
}
