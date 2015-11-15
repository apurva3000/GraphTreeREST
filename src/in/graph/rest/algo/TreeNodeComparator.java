package in.graph.rest.algo;

import in.graph.rest.domain.TreeNode;

import java.util.Comparator;

public class TreeNodeComparator implements Comparator<TreeNode> {

	public int compare(TreeNode o1, TreeNode o2) {
		// TODO Auto-generated method stub
		
		if(o1.getVal() > o2.getVal())
			return 1;
		else
			return -1;
	}
	

}
