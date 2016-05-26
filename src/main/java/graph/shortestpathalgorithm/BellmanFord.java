package graph.shortestpathalgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.model.BaseGraphWithValidator;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import graph.model.Result;

public class BellmanFord extends BaseGraphWithValidator implements ShortestPathAlgorithm {
	private Map<Node, Result> resultMap;

	public BellmanFord() {
		/*
		 * source and graph can be set later on
		 */
	}

	public BellmanFord(final Graph graph) {
		this.graph = graph;
	}

	@Override()
	public void findShortestPath(Node source) {
		if (validator.isValidNodeOfGraph(this.getGraph(), source)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		Set<Node> nodes = graph.getNodes();
		resultMap = new HashMap<>();
		nodes.forEach(node -> resultMap.put(node, new Result(node, Double.MAX_VALUE)));
		resultMap.get(source).setParentNode(null);
		resultMap.get(source).setPriority(0);

		Set<Edge> edges = graph.getEdges();

		nodes.forEach(node -> edges.forEach(edge -> {
			Result sourceResult = resultMap.get(edge.getSource());
			Result destinationResult = resultMap.get(edge.getDestination());
			if (sourceResult.getPriority() + edge.getEdgeWeight() < destinationResult.getPriority()) {
				destinationResult.setPriority(sourceResult.getPriority() + edge.getEdgeWeight());
				destinationResult.setParentNode(source);
			}

		}));
	}

	@Override()
	public void findShortestPath() {
		throw new UnsupportedOperationException("Operation not supported for Dijkstra algorithm");
	}

	public List<Node> getShortestPathTo(final Node destination) {
		if (!validator.isValidNodeOfGraph(graph, destination)) {
			throw new IllegalArgumentException(validator.getErrorMessage());
		}
		List<Node> path = new LinkedList<>();
		Node nodeToadd = destination;
		while (nodeToadd != null) {
			path.add(0, nodeToadd);
			nodeToadd = resultMap.get(nodeToadd).getParentNode();
		}
		return path;
	}

}
