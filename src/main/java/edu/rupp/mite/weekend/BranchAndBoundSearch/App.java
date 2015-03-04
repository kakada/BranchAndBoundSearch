package edu.rupp.mite.weekend.BranchAndBoundSearch;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Scanner;

import edu.rupp.mite.weekend.BranchAndBoundSearch.config.Setting;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.Node;
import edu.rupp.mite.weekend.BranchAndBoundSearch.route.Router;
import edu.rupp.mite.weekend.BranchAndBoundSearch.route.RoutingTable;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// store setting from bundle
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		Setting.loadFromBundle(bundle);
		
		// load matrices data
		File file = new File(Setting.get(Setting.FILE));
		RoutingTable routingTable = RoutingTable.fromFile(file);
		
		Router router = Router.getInstance();
		router.storeRoutingTable(routingTable);
		
		// set start node from keyboard input
		String continuous = "";
		while(!continuous.toLowerCase().equals("no")) {
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
			
			System.out.println("Cost from node " + startNodeIndex + " to node " + goalNodeIndex + " is : " + routesCost);
			
			// quit
			Scanner keyboard = new Scanner(System.in);
			System.out.println();
			System.out.println("Do you want to continue(Yes/No)?");
			continuous = keyboard.nextLine();
		}
	}
}
