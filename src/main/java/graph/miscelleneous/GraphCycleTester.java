package graph.miscelleneous;

import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;
import unionfind.UnionFind;

public class GraphCycleTester {

	private static boolean traverse(Node node, Map<Node, NodeState> nodeStateTracker,
			GraphRepresentation graphRepresentation) {
		nodeStateTracker.put(node, NodeState.GREY);
		Set<Node> neighbors = graphRepresentation.getNeighbors(node);

		for (Node neighbor : neighbors) {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				return traverse(neighbor, nodeStateTracker, graphRepresentation);
			} else if (nodeStateTracker.get(neighbor) == NodeState.GREY) {
				return true;
			}
		}
		nodeStateTracker.put(node, NodeState.BLACK);
		return false;
	}

	public static boolean hasGraphCycle(Graph graph) {
		Set<Node> nodes = graph.getNodes();
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(nodes);
		GraphRepresentation adjRepresentation = new AdjacancyListRepresentation(graph).createRepresentation();

		for (Node node : nodes) {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				if (traverse(node, nodeStateTracker, adjRepresentation)) {
					return true;
				}
			}
		}

		return false;
	}

	/*
	 * This function does not need both the edge of the graph as it is implied
	 * that if there is edge from a to b there is also edge from b to a
	 */
	public static boolean hasUndirectedGraphCycle(Graph graph) {
		UnionFind<Node> uf = new UnionFind<>();

		graph.getNodes().forEach(node -> uf.add(node));

		for (Edge edge : graph.getEdges()) {
			if (uf.belongsToSameSet(edge.getSource(), edge.getDestination())) {
				return true;
			} else {
				uf.union(edge.getSource(), edge.getDestination());
			}
		}
		return false;
	}

}
