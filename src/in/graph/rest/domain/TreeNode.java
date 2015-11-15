package in.graph.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlRootElement 
public class TreeNode {

	String id;
	
	int val;
	
	int distance = 9999;
	TreeNode parent = null;
	boolean leaf = true;
	
	
	
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	List<TreeNode> children; 
	
	
	public TreeNode() // Default constructor which creates a leaf node and contains empty nodes as children
	{
		double d = Math.random();
		children = new ArrayList<TreeNode>();
		children.add(new TreeNode(String.valueOf(d),0));
		children.add(new TreeNode(String.valueOf(d+1),0));
		
	}
	
	public TreeNode(String id, int val) //Only for creating empty nodes
	{
		this.id = id;
		this.val = val;
		children = new ArrayList<TreeNode>(); //To avoid problems
		leaf = false; //It is not a leaf, it is just plain empty (D3 problems)
	}
	
	@XmlElement
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(TreeNode right, TreeNode left) {
		
		children.set(0,right);
		children.set(1,left);
		leaf = false;
		
	}

	
	public void setRight(TreeNode right) {
		
		children.set(0, right);
		leaf = false;
		
	}
	
	public void setLeft(TreeNode left) {
		
		children.set(1, left);
		leaf = false;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVal()
	{
		return val;
	}
	
	public void setVal(int val)
	{
		this.val = val;
	}

	
	
	
	
	
	
	
}
