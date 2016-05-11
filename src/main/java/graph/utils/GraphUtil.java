package graph.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.Node;

public final class GraphUtil {

	public GraphUtil() {
		throw new Error();
	}

	public static Map<Node, Map<Node, Double>> createAdjacancyList(Graph graph) {

		List<Edge> edges = graph.getEdges();
		Map<Node, Map<Node, Double>> adjList = new HashMap<Node, Map<Node, Double>>();

		edges.forEach(edge -> {
			Node source = edge.getSource();
			Node destination = edge.getDestination();

			Map<Node, Double> link = adjList.get(source);

			if (link == null) {
				link = new HashMap<Node, Double>();
				adjList.put(source, link);
			}

			double weight = ((GraphEdge) edge).getWeight();
			link.put(destination, weight);
		});
		return null;
	}

	public static Map<Node, NodeState> getNodeStateTracker(List<Node> nodes) {
		Map<Node, NodeState> nodeStateTracker = new HashMap<Node, NodeState>();
		nodes.forEach(node -> {
			nodeStateTracker.put(node, NodeState.WHITE);
		});
		return nodeStateTracker;
	}

}
