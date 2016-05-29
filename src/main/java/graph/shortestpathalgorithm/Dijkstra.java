package graph.shortestpathalgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Graph;
import graph.model.Node;
import graph.model.Result;

public class Dijkstra extends BaseGraphRepresentationWithValidation implements ShortestPathAlgorithm {
	private Map<Node, Result> resultMap;

	public Dijkstra() {
		/*
		 * can set the graph, source and graphRepresentation later on
		 */
	}

	public Dijkstra(final Graph graph, final GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	@Override()
	public void findShortestPath(Node source) {
		if (!validator.isValidNodeOfGraph(graph, source)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		Set<Node> nodes = graph.getNodes();
		int size = nodes.size();
		Queue<Result> priorityQueue = new PriorityQueue<>(size,
				(resultThis, resultThat) -> resultThis.getPriority().compareTo(resultThat.getPriority()));
		resultMap = new HashMap<>();
		nodes.forEach(node -> {
			Result result = new Result(node, Double.MAX_VALUE);
			resultMap.put(node, result);
			priorityQueue.add(result);
		});

		resultMap.get(source).setParentNode(null);
		resultMap.get(source).setPriority(0);

		while (!priorityQueue.isEmpty()) {
			final Result result = priorityQueue.poll();
			final Node resultNode = result.getSource();
			Set<Node> neighbors = graphRepresentation.getNeighbors(resultNode);
			neighbors.forEach(neighbor -> {
				Result resultForNeighbor = resultMap.get(neighbor);
				double weight = result.getPriority()
						+ graphRepresentation.getDistanceBetweenNodes(resultNode, resultForNeighbor.getSource());
				if (resultForNeighbor.getPriority() > weight) {
					resultForNeighbor.setPriority(weight);
					resultForNeighbor.setParentNode(resultNode);
				}
			});
		}
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

		if (resultMap.get(destination).getParentNode() == null) {
			return path;
		}

		Node nodeToadd = destination;
		while (nodeToadd != null) {
			path.add(0, nodeToadd);
			nodeToadd = resultMap.get(nodeToadd).getParentNode();
		}
		return path;
	}

}
