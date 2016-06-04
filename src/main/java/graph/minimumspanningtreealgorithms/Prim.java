package graph.minimumspanningtreealgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.Node;
import graph.model.Result;

public class Prim extends BaseGraphRepresentationWithValidation implements SpanningTreeAlgorithm {

	private Map<Node, Result> resultMap;
	private Set<Edge> minimumSpanningTreeEdges;
	private double episilon = 0.00000001;

	public Prim() {
		/*
		 * 
		 */
	}

	public Prim(final Graph graph, final GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	private void removeIncreaseAndAddToPriorityQueue(Queue<Result> priorityQueue, Node node, Node parentNode,
			double priority) {
		Result sourceResult = resultMap.get(node);
		priorityQueue.remove(sourceResult);
		sourceResult.setParentNode(parentNode);
		sourceResult.setPriority(priority);
		priorityQueue.add(sourceResult);
	}

	@Override()
	public Set<Edge> getSpanningTreeEdges(Node startNode) {

		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}

		if (!validator.isValidNodeOfGraph(graph, startNode)) {
			throw new IllegalArgumentException(validator.getErrorMessage());
		}

		Set<Node> nodes = graph.getNodes();
		minimumSpanningTreeEdges = new HashSet<>();

		if (nodes.isEmpty()) {
			return minimumSpanningTreeEdges;
		}

		Node nodeToStartFrom = startNode;
		int size = nodes.size();
		Queue<Result> priorityQueue = new PriorityQueue<>(size,
				(resultThis, resultThat) -> resultThis.getPriority().compareTo(resultThat.getPriority()));
		resultMap = new HashMap<>();

		nodes.forEach(node -> {
			Result result = new Result(node, Double.MAX_VALUE);
			resultMap.put(node, result);
			priorityQueue.add(result);
		});
		removeIncreaseAndAddToPriorityQueue(priorityQueue, nodeToStartFrom, null, 0);
		while (!priorityQueue.isEmpty()) {
			final Result result = priorityQueue.poll();
			final Node resultNode = result.getSource();
			minimumSpanningTreeEdges.add(new GraphEdge(result.getParentNode(), resultNode));
			Set<Node> neighbors = graphRepresentation.getNeighbors(resultNode);
			neighbors.forEach(neighbor -> {
				Result resultForNeighbor = resultMap.get(neighbor);
				double weight = graphRepresentation.getDistanceBetweenNodes(resultNode, neighbor);
				if ((resultForNeighbor.getPriority() - weight) > episilon) {
					removeIncreaseAndAddToPriorityQueue(priorityQueue, neighbor, resultNode, weight);
				}
			});
		}

		return minimumSpanningTreeEdges;

	}
}
