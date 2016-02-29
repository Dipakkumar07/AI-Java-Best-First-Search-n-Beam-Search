package ie.gmit.sw;
import java.util.*;
public class BestFirstSearch {
	LinkedList<Node> queue = new LinkedList<Node>();
	List<Node> closed = new ArrayList<Node>();
	HeuristicNodeComparator sorter = new HeuristicNodeComparator();
	public void search(Node node){
		queue.addFirst(node);
		while(!queue.isEmpty()){
			queue.removeFirst();
			closed.add(node);
			if (node.isGoalNode()){
				path(node);
				System.exit(0);
			}else{
				Node[] children = node.children();								
				for (int i = 0; i < children.length; i++) {
					if (!children[i].isVisited()){
						queue.addFirst(children[i]);
						children[i].setParent(node);
					}
				}
				Collections.sort(queue, sorter);
				node = queue.getFirst();
				node.setVisited(true);
			}
		}
	}
	
	private void path(Node node){
		List<Node> path = new ArrayList<Node>();
		while(node.getParent() != null){
			path.add(node);
			node = node.getParent();
		}
		path.add(node);
		Collections.reverse(path);
		System.out.println("Path: " + path + ". Distance of " + distance(path));
	}
	
	private int distance(List<Node> path){
		int distance = 0;
		for (int i = 0; i < path.size(); i++) {
			if (i + i <= path.size()) distance += path.get(i).getDistance(path.get(i + 1));
		}
		return distance;
	}
	
	public static void main(String[] args) {
		IrelandMap ire = new IrelandMap();
		Node start = ire.getStartNode();
		start.setVisited(true);
		BestFirstSearch bf = new BestFirstSearch();
		bf.search(start);
	}
}