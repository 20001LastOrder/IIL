package ill.gui;

import java.util.List;

import ca.mcgill.comp421.ill.model.ResultTable;

public final class GUIUtil {
	public static String[][] to2DArray(ResultTable table){
		List<List<String>> listed = table.toList();
		if(listed.size() == 0) {
			String[][] message = new String[1][1];
			message[0][0] = "No record matches found!";
			return message;
		}
		
		// Do not include header
		String[][] result = new String [listed.get(0).size() - 1][listed.size()];
		for(int i = 1; i < listed.get(0).size(); i++) {
			for(int j = 0; j < listed.size(); j++) {
				result[i - 1][j] = listed.get(j).get(i);
			}
		}
		return result;
	}
}
