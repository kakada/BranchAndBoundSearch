package edu.rupp.mite.weekend.BranchAndBoundSearch.model;

import java.util.ArrayList;
import java.util.List;

public class RowCollection {
	
	private int index = -1;
	private List<Node> nodes = null;
	
	public RowCollection(int index) {
		this(index, new ArrayList<Node>());
	}
	
	public RowCollection(int index, List<Node> nodes) {
		this.index = index;
		this.nodes = nodes;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public Node getNodeByIndex(int columnIndex) {
		return nodes.get(columnIndex);
	}
	
}
