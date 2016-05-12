package graph.traversalalgorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class BFSTraversal implements TraversalAlgorithm {

	@Override()
	public void traverse(Graph graph) {
		if (GraphUtil.isGraphNullOrEmpty(graph)) {
			return;
		}
		List<Node> queue = new LinkedList<>();
		Node first = graph.getNodes().get(0);
		queue.add(first);
		GraphRepresentation graphRepresentation = new AdjacancyListRepresentation(graph);
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(graph.getNodes());
		nodeStateTracker.put(first, NodeState.GREY);

		while (!queue.isEmpty()) {
			Node frontInQueue = queue.remove(0);
			System.out.println(frontInQueue);
			List<Node> neighbors = graphRepresentation.getNeighbors(frontInQueue);
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
