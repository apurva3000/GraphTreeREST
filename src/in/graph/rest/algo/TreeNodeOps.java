package in.graph.rest.algo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import in.graph.rest.dao.TreeDao;
import in.graph.rest.domain.TreeNode;



@Component
public class TreeNodeOps {

	
	
	public void addNode(TreeNode newnode)
	{
		
		TreeNode root = TreeDao.instance.getRoot();
		TreeDao.instance.getModel().put(newnode.getId(), newnode);
		traverseTree(root, newnode);
		
		
		
	}
	
	
	public void traverseTree(TreeNode node,TreeNode newnode)
	{
		TreeNode child;
		
		if(newnode.getVal() > node.getVal())
		{
			if(node.getChildren().size() > 0)
			{
				child = node.getChildren().get(0); //Right
				if(child!=null && child.getVal() != 00)
					traverseTree(child,newnode);
				else
				{
					node.setRight(newnode);
				}
					
			}
			else //It is a leaf node
			{
				node.getChildren().add(newnode);
				
			}
			
			
		}
		else
		{
			if(node.getChildren().size() > 0)
			{
				child = node.getChildren().get(1); //Left
				
				if(child!=null && child.getVal() != 0)
					traverseTree(child,newnode); //Explore Further
				else
				{
					node.setLeft(newnode); //Set left
				}
					
			}
			else //Leaf Node
			{
				node.getChildren().add(null);
				node.getChildren().add(newnode); 
			}
		}
		
		
	}
	
	
	
	public static void main(String[] args)
	{
		
		TreeNodeOps tno = new TreeNodeOps();
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		TreeNode n4 = TreeDao.instance.getModel().get("B");
		System.out.println(n4.getChildren().get(1).getId());
		
		TreeNode tn = new TreeNode();
		tn.setId("N");
		tn.setVal(3);
		
		tno.addNode(tn);
		
		
		
		System.out.println(n4.getChildren().get(0).getId());
		System.out.println(n4.getChildren().get(1).getId());
		
		
	}
}
