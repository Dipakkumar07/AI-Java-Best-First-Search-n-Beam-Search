package ie.gmit.sw;
import java.util.*;
public class BeamSearch {
	LinkedList<Node> queue = new LinkedList<Node>();
	HeuristicNodeComparator sorter = new HeuristicNodeComparator();
	int beamWidth= 3;
	public void search(Node node){
		queue.addFirst(node);
		int totalDistance = 0;
		while(!queue.isEmpty()){
			queue.removeFirst();
			System.out.print("Visiting " + node.getNodeName() + "\t");
			if (node.isGoalNode()){
				System.out.println("Reached goal node " + node.getNodeName() + " after " + totalDistance + " miles.");
				System.exit(0);
			}else{
				Node[] children = node.children();	
				Collections.sort(Arrays.asList(children), sorter);
				
				int bound = 0;
				if (children.length < beamWidth){
					bound = children.length;
				}else{
					bound = beamWidth;
				}
				for (int i = 0; i < bound; i++) {
					if (!children[i].isVisited()){
						queue.addLast(children[i]);
					}
				}
				System.out.println(queue);
				totalDistance = totalDistance + node.getDistance(queue.getFirst());
				node = queue.getFirst();
				node.setVisited(true);
			}
		}
	}
	
	public static void main(String[] args) {
		IrelandMap ire = new IrelandMap();
		Node start = ire.getStartNode();
		start.setVisited(true);
		BeamSearch bf = new BeamSearch();
		bf.search(start);
	}
}
