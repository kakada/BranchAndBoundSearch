package edu.rupp.mite.weekend.BranchAndBoundSearch.route;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.rupp.mite.weekend.BranchAndBoundSearch.config.Setting;
import edu.rupp.mite.weekend.BranchAndBoundSearch.file.FileReader;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.Node;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.RowCollection;

public class RoutingTable {
	
	private List<RowCollection> rows = new ArrayList<RowCollection>();
	
	public static RoutingTable fromFile(File file) {
		RoutingTable table = new RoutingTable();
		FileReader fileReader = new FileReader(file);
		String matrix_data = fileReader.getContent();
		
		String[] matrix_rows = matrix_data.split(Setting.get(Setting.NEW_LINE));
		for(Integer rowIndex = 0; rowIndex < matrix_rows.length; rowIndex++) {
			// store row
			RowCollection row = new RowCollection(rowIndex);
			
			String[] distance_costs = matrix_rows[rowIndex].split(Setting.get(Setting.NODE_SEPARATOR));
			for(Integer columnIndex = 0; columnIndex < distance_costs.length; columnIndex++) {
				Node node = new Node(columnIndex, Integer.valueOf(distance_costs[columnIndex]));
				row.addNode(node);
			}
			
			table.getRows().add(row);
			
		}
		
		return table;
	}
	
	public Node getNodeByIndex(int rowIndex, int columnIndex) {
		return rows.get(rowIndex).getNodeByIndex(columnIndex);
	}
	
	public List<RowCollection> getRows() {
		return rows;
	}
	
}
