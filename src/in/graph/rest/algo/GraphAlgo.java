package in.graph.rest.algo;

import in.graph.rest.dao.TreeDao;
import in.graph.rest.domain.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;


@Component
public class GraphAlgo {

	
	
	/**** Print the different paths with their total summation ******
	This is the classic case of a breadth first search *****/
	
	public String pathSumBfs(TreeNode root) //Uses a queue to process the elements
	{
		
		String res = "";
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		
		
		queue.add(root);
		
		//LinkedList<Integer> sumsqueue = new LinkedList<Integer>(); 
		LinkedList<PathNSum> sumspathqueue = new LinkedList<PathNSum>();
		//sumsqueue.add(0); //Autoboxing
		PathNSum pns = new PathNSum(0,"");
		sumspathqueue.add(pns);
		
		
		while(!queue.isEmpty())
		{
			TreeNode el  = queue.poll();
			PathNSum ps = sumspathqueue.poll(); 
			
			int sum = ps.getSum();
			sum += el.getVal();
			
			TreeNode left = null;
			TreeNode right = null;
			
			if(el.getChildren().size()==2)
			{
				left = el.getChildren().get(1);
				if(left.getVal() ==0)
					left = null;
			}
				
			
			if(el.getChildren().size()>1)
			{
				right = el.getChildren().get(0);
				if(right.getVal() == 0)
					right = null;
			}
				
			
			
				
			
				
			ps = new PathNSum(sum, ps.getSb());
			ps.setSb(el.getId());
			
			if(left !=null)
			{
				queue.add(left);
				sumspathqueue.add(ps);
			}
			if(right!=null)
			{	
				queue.add(right);
				sumspathqueue.add(ps);
			}
			
			if(left == null && right == null) //Once we reach the leaf output the path and the total sum leading upto it
			{
				//System.out.print("Path: " + ps.getSb()); //This prints all the paths it finds
				//System.out.println("Total Sum: " + ps.getSum()); 
				res = ps.getSb()  + String.valueOf(ps.getSum());  //But we need to stop as we find the first path which is actually the shortest path
				return res;
			}
			
					
					
					
					//sumsqueue.add(sum); //Helps in keeping track of node by node sum for different paths effectively
			
			
			
		}
		
		return res;
	}

	
	
	/**** Perform Dijstra's search to find the cheapest path *******/	
	
	//NOTE: Since we are only doing it for Trees at the moment, there's no concept of cycles and hence
	// We don't require colors for the nodes as , we don't have the possiblity of getting stuck in an infinite loop due to cycles.
	public String doDijkstra(TreeNode root)
	{
		
		PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>(1000,new TreeNodeComparator());
		pq.add(root);
		root.setDistance(0); //Set the distance of the root as zero
		int mindist = 9999;
		TreeNode minNode = null;
		
		
		while(!pq.isEmpty())
		{
			TreeNode u = pq.poll();
			
			
			if(!u.isLeaf()) //Check if it is not a leaf node
			{
				Iterator<TreeNode> tnit = u.getChildren().iterator();
				//System.out.println(u.getChildren().get(0).getVal());
				while(tnit.hasNext()) //Look for the adjacent neighbors of it
				{
					TreeNode v = tnit.next();
				
					if(v.getVal() != 0) //Check if it is not a empty node (which we use to denote no child)
					{
						pq.add(v); //Send that to the priority queue
						relax(u,v); //The most important function in Dijkstra's algorithm
					}
				}
			
					
			}
			else //if it is a leaf node, check the final distance from source and print the path
			{
				
				if(u.getDistance()<mindist)
				{
					mindist = u.getDistance();
					minNode = u;
					
				}
				
			}
		}
		
		
		//System.out.print("Distance ");
		//System.out.println(mindist);
		
		
		StringBuilder sb = new StringBuilder(minNode.getId());
		sb.append(",");
		
		TreeNode parents = minNode.getParent();
		
		
		
		while(parents!=null)
		{
			sb.append(parents.getId());
			sb.append(",");
			
			//System.out.println(parents.getId());
			parents = parents.getParent();
			
		}
		
		sb.append(String.valueOf(mindist));
		//System.out.println(sb.toString());
		
		
		//Cleanup
		
		for(TreeNode cleanup : TreeDao.instance.getModel().values())
		{
		
			cleanup.setParent(null);
			cleanup.setDistance(9999);
		}
		
		
		return sb.toString();
			
	}
	
	
	public void relax(TreeNode u, TreeNode v)
	{
	
		if(v.getDistance() > u.getDistance() + v.getVal()) //The val is our weight for the so called between u and v
		{
			v.setDistance(u.getDistance() + v.getVal());
			v.setParent(u);
		}
		
	}
	
	
	
	
	public static void main(String[] args)
	{
		TreeNode tnode = TreeDao.instance.getModel().get("A");
		GraphAlgo ga = new GraphAlgo();
		String res = ga.doDijkstra(tnode);
		System.out.println(res);
		
		String s = ga.pathSumBfs(tnode);
		System.out.println(s);
		
	}
	
	
	
	
	
	
	
	
	
	
	/***** Helper class for BFS *****/

	class PathNSum
	{
		StringBuilder sb = new StringBuilder("");
		int sum;
		
		public PathNSum(int sum, String base)
		{
			this.sum = sum;
			sb.append(base);
		}
		
		public int getSum() {
			return sum;
		}
		public void setSum(int sum) {
			this.sum = sum;
		}
		
		public void setSb(String inp)
		{
			
			sb.append(inp);
			sb.append(",");
		}
		
		public String getSb()
		{
			
			return sb.toString();
		}
		
		
		
		
		
		
		
		
	}
	
}
