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
	private Node source;

	public Dijkstra() {
		/*
		 * can set the graph, source and graphRepresentation later on
		 */
	}

	public Dijkstra(Graph graph, Node source, GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.source = source;
		this.graphRepresentation = graphRepresentation;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	@Override()
	public void findShortestPath() {
		if (!validator.isValid(this)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		Set<Node> nodes = graph.getNodes();
		resultMap = new HashMap<>();
		nodes.forEach(node -> resultMap.put(node, new Result(node)));
		int size = nodes.size();
		Queue<Result> priorityQueue = new PriorityQueue<>(size,
				(resultThis, resultThat) -> resultThis.getPriority().compareTo(resultThat.getPriority()));
		resultMap.get(source).setParentNode(null);
		resultMap.get(source).setPriority(Double.MIN_VALUE);

		while (!priorityQueue.isEmpty()) {
			final Result result = priorityQueue.poll();
			final Node resultNode = result.getSource();
			Set<Node> neighbors = graphRepresentation.getNeighbors(resultNode);
			neighbors.forEach(neighbor -> {
				Result resultForNeighbor = resultMap.get(neighbor);
				double weight = graphRepresentation.getDistanceBetweenNodes(resultNode, resultForNeighbor.getSource());
				if (resultForNeighbor.getPriority() > weight) {
					resultForNeighbor.setPriority(weight);
					resultForNeighbor.setParentNode(resultNode);
				}
			});
		}
	}

	public List<Node> getShortestPathTo(Node destination) {
		if (!resultMap.containsKey(destination)) {
			throw new IllegalArgumentException(destination + " is not a valid node in graph");
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
