package graph.traversalalgorithm;

import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class DFSTraversal implements TraversalAlgorithm {

	private static void traverse(Map<Node, Map<Node, Double>> adjList, Node node,
			Map<Node, NodeState> nodeStateTracker) {
		System.out.println(node);
		nodeStateTracker.put(node, NodeState.GREY);
		Map<Node, Double> neighbors = adjList.get(node);
		neighbors.forEach((neighbor, weight) -> {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				traverse(adjList, neighbor, nodeStateTracker);
			}
		});
		nodeStateTracker.put(node, NodeState.BLACK);
	}

	@Override()
	public void traverse(Graph graph) {
		Map<Node, Map<Node, Double>> adjList = GraphUtil.createAdjacancyList(graph);
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(graph.getNodes());
		List<Node> nodes = graph.getNodes();

		nodes.forEach(node -> {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				traverse(adjList, node, nodeStateTracker);
			}
		});

	}

}
