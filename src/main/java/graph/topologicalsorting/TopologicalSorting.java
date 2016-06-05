package graph.topologicalsorting;

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

public class TopologicalSorting extends BaseGraphRepresentationWithValidation {
	List<Node> topologicalSortingOrder;

	public TopologicalSorting() {
		/*
		 * 
		 */
	}

	public TopologicalSorting(Graph graph, GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	private void getTopologicalOrder(Node node, Map<Node, NodeState> nodeStateTracker) {
		nodeStateTracker.put(node, NodeState.GREY);
		Set<Node> neighbors = graphRepresentation.getNeighbors(node);
		neighbors.forEach(neighbor -> {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				getTopologicalOrder(neighbor, nodeStateTracker);
			}
		});
		topologicalSortingOrder.add(0, node);
		nodeStateTracker.put(node, NodeState.BLACK);
	}

	public List<Node> getTopologicalOrder() {
		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		Set<Node> nodes = graph.getNodes();
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(nodes);
		topologicalSortingOrder = new LinkedList<>();
		nodes.forEach(node -> {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				getTopologicalOrder(node, nodeStateTracker);
			}
		});

		return topologicalSortingOrder;
	}

}
