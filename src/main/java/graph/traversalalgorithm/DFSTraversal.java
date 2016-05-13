package graph.traversalalgorithm;

import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Node;
import graph.utils.GraphUtil;

public class DFSTraversal extends BaseGraphRepresentationWithValidation implements TraversalAlgorithm {
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
		if (validator.isValid(this)) {
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
