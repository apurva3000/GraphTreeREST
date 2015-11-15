package in.graph.rest.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.graph.rest.algo.GraphAlgo;
import in.graph.rest.algo.TreeNodeOps;
import in.graph.rest.dao.TreeDao;
import in.graph.rest.domain.TreeNode;

/**** Handles the get operations mapped via controller ****/

@Service
public class GetManager {

	@Autowired
	GraphAlgo ga;
	
	@Autowired
	TreeNodeOps tno;
	
	public TreeNode getTreeNode(String id)
	{
		
		TreeNode tnode = TreeDao.instance.getModel().get(id);
		return tnode;
	}
	
	
	public String runBFS(String id)
	{
		TreeNode root = TreeDao.instance.getModel().get(id);
		String res;
		if(root!=null)
			res = ga.pathSumBfs(root);
		else
			res = "";
		
		return res;
		
	}
	
	
	public String runDijkstra(String id)
	{
		TreeNode root = TreeDao.instance.getModel().get(id);
		String res;
		if(root!=null)
			res = ga.doDijkstra(root);
		else
			res = "";
		
		return res;
		
	}
	
	public TreeNode createNode(TreeNode node)
	{
		if(node == null)
			throw new RuntimeException("The node is incorrect");
		
		Map<String,TreeNode> model = TreeDao.instance.getModel();
		
		
		if(model.containsKey(node.getId()))
				throw new RuntimeException("The node already exists");
		else
		{
			model.put(node.getId(), node);
			tno.addNode(node);
		}
		return node;
		
	}
	
}
