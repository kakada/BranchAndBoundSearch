package edu.rupp.mite.weekend.BranchAndBoundSearch.route;

import java.util.ArrayList;
import java.util.List;

import edu.rupp.mite.weekend.BranchAndBoundSearch.model.Node;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.NodeList;
import edu.rupp.mite.weekend.BranchAndBoundSearch.model.RowCollection;

public class Router {
	private final String OPENED = "OPENED";
	private final String CLOSED = "CLOSED";
	
	private static Router router = null;
	private RoutingTable routingTable = new RoutingTable();
	
	/**
	 * Get the instance object of router
	 * @return the instance object of router
	 */
	public static Router getInstance() {
		if(router == null) {
			router = new Router();
		}
		return router;
	}
	
	/**
	 * Store the routing table to each nodes
	 * @param routingTable
	 */
	public void storeRoutingTable(RoutingTable routingTable) {
		this.routingTable = routingTable;
	}
	
	/**
	 * Get the routing table to routes to each nodes
	 * @return
	 */
	public RoutingTable getRoutingTable() {
		return routingTable;
	}
	
	/**
	 * Get neighbor nodes from the specific node
	 * @param node
	 * @return all neighbor node from the specific node
	 */
	public List<Node> getSuccessorsOfNode(Node node) {
		List<Node> nodes = new ArrayList<Node>();
		for(RowCollection nodeCollection : routingTable.getRows()) {
			if(nodeCollection.getIndex() == node.getIndex()) {
				for(Node currentNode : nodeCollection.getNodes()) {
					if(currentNode.getCost() != 0) {
						nodes.add(currentNode);
					}
				}
			}
		}
		return nodes;
	}
	
	/**
	 * Calculate the cost from start node to goal node
	 * @param startNode is the beginning of node
	 * @param goalNode is the target node
	 * @return the cost that spend from start node to goal node
	 */
	public int getCostOf(Node startNode, Node goalNode) {
		if (startNode == null || goalNode == null) return 0;
		
		NodeList openedNodes = new NodeList();
		NodeList closedNodes = new NodeList();
		
		// initial start node
		openedNodes.add(startNode);
		int routesCost = 9999; // 9999 mean unlimited
		
		while(!openedNodes.isEmpty()) {
			
			Node selectedNode = openedNodes.get(0);
			openedNodes.remove(openedNodes.getIndexOf(selectedNode));
			closedNodes.add(selectedNode);
			
			// selected node is a goal node
			if(selectedNode.equals(goalNode)) {
				if(selectedNode.getCost() < routesCost) {
					routesCost = selectedNode.getCost();
				}
			} else {
				if (selectedNode.getCost() < routesCost) {
					// find successor of selected node
					List<Node> successorNodes = selectedNode.getSuccessors();
					
					for (Node successorNode : successorNodes) {
						// calculate cost of successor node
						successorNode.setCost(successorNode.getCostFrom(selectedNode));
						
						boolean isExisting = false;
						String existingLabel = "";
						Node queuedOrVisitedNode = null; 
						
						if(openedNodes.isExists(successorNode)) {
							isExisting = true;
							existingLabel = OPENED;
							queuedOrVisitedNode = openedNodes.get(openedNodes.getIndexOf(successorNode));
						}
						
						if(closedNodes.isExists(successorNode)) {
							isExisting = true;
							existingLabel = CLOSED;
							queuedOrVisitedNode = closedNodes.get(closedNodes.getIndexOf(successorNode));
						}
						
						if(isExisting) {
							if(successorNode.isCostLessThan(queuedOrVisitedNode)) {
								if(existingLabel.equals(OPENED)) {
									openedNodes.remove(openedNodes.getIndexOf(queuedOrVisitedNode));
								} else {
									closedNodes.remove(closedNodes.getIndexOf(queuedOrVisitedNode));
								}
								openedNodes.add(successorNode);
							}
						} else{
							openedNodes.add(successorNode);
						}
					}
				}
			}
		}
		
		return routesCost;
		
	}
	
}
