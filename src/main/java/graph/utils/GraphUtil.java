package graph.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.model.Graph;
import graph.model.Node;

public final class GraphUtil {

	public static final String GET_DISTANCE_BETWEEN_NODES_OPERATION_NOT_SUPPORTED = "get distance between two nodes operation is not supported";

	private GraphUtil() throws InstantiationException {
		throw new InstantiationException();
	}

	public static Map<Node, NodeState> getNodeStateTracker(final Set<Node> nodes) {
		if (nodes == null) {
			return null;
		}

		Map<Node, NodeState> nodeStateTracker = new HashMap<>();
		nodes.forEach(node -> nodeStateTracker.put(node, NodeState.WHITE));
		return nodeStateTracker;
	}

	public static <T> boolean isNullObject(T obj) {
		return obj == null;
	}

	public static boolean isGraphNullOrEmpty(Graph graph) {
		if (isNullObject(graph) || graph.getNodes().isEmpty()) {
			return true;
		}
		return false;
	}
}
