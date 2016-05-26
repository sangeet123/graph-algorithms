package graph.traversalalgorithm;

import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class DFSTraversal extends BaseGraphRepresentationWithValidation implements TraversalAlgorithm {

	public DFSTraversal() {
		/*
		 * 
		 */
	}

	public DFSTraversal(Graph graph, GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	private void traverse(Node node, Map<Node, NodeState> nodeStateTracker) {
		System.out.println(node);
		nodeStateTracker.put(node, NodeState.GREY);
		Set<Node> neighbors = graphRepresentation.getNeighbors(node);
		neighbors.forEach(neighbor -> {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				traverse(neighbor, nodeStateTracker);
			}
		});
		nodeStateTracker.put(node, NodeState.BLACK);
	}

	@Override()
	public void traverse() {
		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		Set<Node> nodes = graph.getNodes();
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(nodes);
		nodes.forEach(node -> {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				traverse(node, nodeStateTracker);
			}
		});

	}

}
