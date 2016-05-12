package graph.traversalalgorithm;

import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class DFSTraversal implements TraversalAlgorithm {
	private GraphRepresentation graphRepresentation;
	private Map<Node, NodeState> nodeStateTracker;

	private void traverse(Node node) {
		System.out.println(node);
		nodeStateTracker.put(node, NodeState.GREY);
		List<Node> neighbors = graphRepresentation.getNeighbors(node);
		neighbors.forEach(neighbor -> {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				traverse(neighbor);
			}
		});
		nodeStateTracker.put(node, NodeState.BLACK);
	}

	@Override()
	public void traverse(Graph graph) {
		if (GraphUtil.isGraphNullOrEmpty(graph)) {
			return;
		}
		graphRepresentation = new AdjacancyListRepresentation(graph);
		nodeStateTracker = GraphUtil.getNodeStateTracker(graph.getNodes());
		List<Node> nodes = graph.getNodes();
		nodes.forEach(node -> {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				traverse(node);
			}
		});

	}

}
