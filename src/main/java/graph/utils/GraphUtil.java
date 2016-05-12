package graph.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.enums.NodeState;
import graph.model.Graph;
import graph.model.Node;

public final class GraphUtil {

	public GraphUtil() {
		throw new Error();
	}

	public static Map<Node, NodeState> getNodeStateTracker(final List<Node> nodes) {
		if (nodes == null) {
			return null;
		}

		Map<Node, NodeState> nodeStateTracker = new HashMap<Node, NodeState>();
		nodes.forEach(node -> {
			nodeStateTracker.put(node, NodeState.WHITE);
		});
		return nodeStateTracker;
	}

	public static boolean isGraphNullOrEmpty(Graph graph) {
		if (graph == null || graph.getNodes().isEmpty()) {
			return true;
		}
		return false;
	}

}
