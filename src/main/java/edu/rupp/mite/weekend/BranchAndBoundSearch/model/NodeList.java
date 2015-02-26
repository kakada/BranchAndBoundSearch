package edu.rupp.mite.weekend.BranchAndBoundSearch.model;

import java.util.ArrayList;
import java.util.List;

public class NodeList {
	
	private List<Node> nodes = new ArrayList<Node>();
	
	public List<Node> getAll() {
		return nodes;
	}
	
	public Node get(int index) {
		return nodes.get(index);
	}
	
	public int getIndexOf(Node node) {
		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).getIndex() == node.getIndex()) {
				return i;
			}
		}
		return -1;
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
	
	public void remove(Node node) {
		remove(node.getIndex());
	}
	
	public void remove(int index) {
		nodes.remove(index);
	}
	
	public boolean isExists(Node node) {
		return getIndexOf(node) != -1;
	}
	
	public boolean isEmpty() {
		return nodes.isEmpty();
	}
	
	public int getSize() {
		return nodes.size();
	}

}
