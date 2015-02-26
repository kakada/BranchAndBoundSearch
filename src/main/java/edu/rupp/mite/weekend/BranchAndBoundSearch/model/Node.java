package edu.rupp.mite.weekend.BranchAndBoundSearch.model;

import java.util.List;

import edu.rupp.mite.weekend.BranchAndBoundSearch.route.Router;

public class Node {
	
	private int index = -1;
	private int cost = 0;
	
	public Node(int index) {
		this(index, 0);
	}
	
	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(index + 1);
		output.append("->");
		output.append(getIndex() + 1);
		output.append("=");
		output.append(getCost());
		output.append("\n");
		
		return output.toString();
	}
	
	public boolean equals(Node node) {
		return index == node.getIndex();
	}
	
	/**
	 * Get neighbors node from the current node
	 * @param node
	 * @return all neighbors from the current node
	 */
	public List<Node> getSuccessors() {
		return Router.getInstance().getSuccessorsOfNode(this);
	}
	
	/**
	 * Get cost of node from the specific node
	 * @param node
	 * @return cost of current node from the specific node
	 */
	public int getCostFrom(Node node) {
		return node.getCost() + getCost();
	}
	
	/**
	 * Does cost of current node is less than the specific node?
	 * @param node
	 * @return 
	 * 	True: if current node's cost is less than the specific node,
	 * 	False: if current node's cost is greater than or equals to specific node;
	 */
	public boolean isCostLessThan(Node node) {
		return getCost() < node.getCost();
	}
	
}
