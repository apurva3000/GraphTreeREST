package in.graph.rest.dao;

import in.graph.rest.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;



public enum TreeDao {
  instance;
  
  private Map<String, TreeNode> contentProvider = new HashMap<String, TreeNode>();
  private TreeNode root;
  
  private TreeDao() {
    
	  
	  
	  	
	  	TreeNode n5 = new TreeNode();
	  	n5.setId("A");
	  	n5.setVal(5);
	  	contentProvider.put("A", n5);
		
	  	root = n5;
	  	
	  	TreeNode n4 = new TreeNode();
	  	n4.setId("B");
	  	n4.setVal(4);
	  	contentProvider.put("B", n4);
	  	
	  	
	  	
	  	TreeNode n8 = new TreeNode();
	  	n8.setId("C");
	  	n8.setVal(8);
	  	contentProvider.put("C", n8);
	  	
	  	TreeNode n11 = new TreeNode();
	  	n11.setId("D");
	  	n11.setVal(11);
	  	contentProvider.put("D", n11);
	  	
	  	TreeNode n7 = new TreeNode();
	  	n7.setId("E");
	  	n7.setVal(7);
	  	contentProvider.put("E", n7);
	  	
	  	TreeNode n2 = new TreeNode();
	  	n2.setId("F");
	  	n2.setVal(2);
	  	contentProvider.put("F", n2);
	  	
	  	TreeNode n13 = new TreeNode();
	  	n13.setId("G");
	  	n13.setVal(13);
	  	contentProvider.put("G", n13);
	  	
	  	TreeNode n4_1 = new TreeNode();
	  	n4_1.setId("H");
	  	n4_1.setVal(4);
	  	contentProvider.put("H", n4_1);
		
	  	TreeNode n1 = new TreeNode();
	  	n1.setId("I");
	  	n1.setVal(1);
	  	contentProvider.put("I", n1);
	  	
	  	TreeNode n3 = new TreeNode();
	  	n3.setId("J");
	  	n3.setVal(3);
	  	contentProvider.put("J", n3);
	  	
	  	TreeNode n10 = new TreeNode();
	  	n10.setId("K");
	  	n10.setVal(10);
	  	contentProvider.put("K", n10);
		
	  	
	  	TreeNode n3_1 = new TreeNode();
	  	n3_1.setId("L");
	  	n3_1.setVal(3);
	  	contentProvider.put("L", n3_1);
		
		n11.setChildren(n13,n8);
		
		
		//n4_1.setChildren(new TreeNode("01",0),n1);
		
		n4.setChildren(new TreeNode("02",0),n4_1);
		
		n8.setChildren(n10, n7);
		n2.setChildren(n3_1, n1);
		n3.setChildren(n4, n2);
		n5.setChildren(n11, n3);
		
		
    
  }
  public Map<String, TreeNode> getModel(){
    return contentProvider;
  }
  
  public TreeNode getRoot(){
	    return root;
	  }
} 


