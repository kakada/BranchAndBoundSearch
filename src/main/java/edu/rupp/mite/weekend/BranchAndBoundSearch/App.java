package edu.rupp.mite.weekend.BranchAndBoundSearch;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Scanner;

import edu.rupp.mite.weekend.BranchAndBoundSearch.model.Node;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.RowCollection;
import edu.rupp.mite.weekend.BranchAndBoundSearch.route.Router;
import edu.rupp.mite.weekend.BranchAndBoundSearch.route.RoutingTable;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		
		// load matrices data
		File file = new File(bundle.getString("file"));
		RoutingTable routingTable = RoutingTable.fromFile(file);
		
		Router router = Router.getInstance();
		router.storeRoutingTable(routingTable);
		
		for(RowCollection nodeCollection : router.getRoutingTable().getRows()) {
			for(Node node : nodeCollection.getNodes()) {
				System.out.println(node.toString());
			}
		}
		
		// set start node from keyboard input
		int startNodeIndex = -1;
		do {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Available node: 1,2,3,4,5");
			System.out.println("please select start node:");
			startNodeIndex = keyboard.nextInt();
		} while(startNodeIndex < 0 || startNodeIndex > 5);
		
		Node startNode = routingTable.getNodeByIndex(startNodeIndex - 1, startNodeIndex - 1);
		
		// set goal node from keyboard input
		int goalNodeIndex = -1;
		do {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Available node: 1,2,3,4,5");
			System.out.println("please select goal node:");
			goalNodeIndex = keyboard.nextInt();
		} while(goalNodeIndex < 0 || goalNodeIndex > 5);
		
		Node goalNode = routingTable.getNodeByIndex(goalNodeIndex - 1, goalNodeIndex - 1);
		
		int routesCost = router.getCostOf(startNode, goalNode);
		
		System.out.println("From node " + startNodeIndex + " to node " + goalNodeIndex + ": " + routesCost);
		
	}
}
