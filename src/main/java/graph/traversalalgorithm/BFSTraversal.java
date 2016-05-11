package graph.traversalalgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class BFSTraversal implements TraversalAlgorithm {

	@Override()
	public void traverse(Graph graph) {
		List<Node> queue = new LinkedList<Node>();
		Node first = graph.getNodes().get(0);
		queue.add(first);
		Map<Node, Map<Node, Double>> adjList = GraphUtil.createAdjacancyList(graph);
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(graph.getNodes());
		nodeStateTracker.put(first, NodeState.GREY);

		while (queue.size() != 0) {
			Node frontInQueue = queue.remove(0);
			System.out.println(frontInQueue);
			Map<Node, Double> neighbors = adjList.get(frontInQueue);
			neighbors.forEach((node, weight) -> {
				NodeState nodeState = nodeStateTracker.get(node);
				if (nodeState == NodeState.WHITE) {
					queue.add(node);
					nodeStateTracker.put(node, NodeState.GREY);
				}
			});
			nodeStateTracker.put(frontInQueue, NodeState.BLACK);
		}
	}

}
