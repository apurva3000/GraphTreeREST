package in.graph.rest.controller;

import in.graph.rest.dao.TreeDao;
import in.graph.rest.domain.TreeNode;
import in.graph.rest.service.GetManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/node")
public class GraphController {

	@Autowired
	GetManager gm;
	
	@RequestMapping(value = "{id}.json", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	TreeNode getTreeNodeJson(@PathVariable("id") String id)
	{
		TreeNode tnode = gm.getTreeNode(id);
		return tnode;
	}
	
	
	@RequestMapping(value = "{id}.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody
	TreeNode getTreeNodeXml(@PathVariable("id") String id)
	{
		TreeNode tnode = gm.getTreeNode(id);
		return tnode;
	}
	
	
	@RequestMapping(value = "bfs/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String getBfsNodes(@PathVariable String id)
	{
		
		String res= gm.runBFS(id);
			return res;
		
	}
	
	
	/****Dijkstra's algorithm for Cheapest path****/
	@RequestMapping(value = "dijkstra/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String getDijkstraNodes(@PathVariable String id)
	{
		
		String res= gm.runDijkstra(id);
			return res;
		
	}
	
	
	/***** The POST method for creating a new TreeNode in the tree *****/
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody TreeNode createTreeNode(@RequestBody TreeNode tn) 
	{
    
		tn = gm.createNode(tn);
		
		return tn;
		
    }
	

}