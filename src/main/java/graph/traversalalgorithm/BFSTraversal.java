package graph.traversalalgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class BFSTraversal extends BaseGraphRepresentationWithValidation implements TraversalAlgorithm {

	public BFSTraversal() {
		/*
		 * 
		 */
	}

	public BFSTraversal(Graph graph, GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	@Override()
	public void traverse() {
		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		List<Node> queue = new LinkedList<>();
		if (graph.getNodes().isEmpty()) {
			return;
		}
		Node first = graph.getNodes().iterator().next();
		queue.add(first);
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(graph.getNodes());
		nodeStateTracker.put(first, NodeState.GREY);

		while (!queue.isEmpty()) {
			Node frontInQueue = queue.remove(0);
			System.out.println(frontInQueue);
			Set<Node> neighbors = graphRepresentation.getNeighbors(frontInQueue);
			neighbors.forEach(node -> {
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
