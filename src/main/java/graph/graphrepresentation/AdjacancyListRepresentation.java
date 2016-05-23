package graph.graphrepresentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;

public class AdjacancyListRepresentation implements GraphRepresentation {

	private final Graph graph;

	private Map<Node, Set<Node>> adjList;
	private Map<EdgeWrapper, Edge> edgeMapper;

	public AdjacancyListRepresentation(final Graph graph) {
		this.graph = graph;
	}

	@Override()
	public final Graph getGraph() {
		return this.graph;
	}

	private class EdgeWrapper {

		public final Node source;

		public final Node destination;

		public EdgeWrapper(Node source, Node destination) {
			this.source = source;
			this.destination = destination;
		}

		@Override()
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((destination == null) ? 0 : destination.hashCode());
			result = prime * result + ((source == null) ? 0 : source.hashCode());
			return result;
		}

		@Override()
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EdgeWrapper other = (EdgeWrapper) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (destination == null) {
				if (other.destination != null)
					return false;
			} else if (!destination.equals(other.destination))
				return false;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
				return false;
			return true;
		}

		private AdjacancyListRepresentation getOuterType() {
			return AdjacancyListRepresentation.this;
		}

	}

	private void createAdjacancyList() {
		Set<Node> nodes = graph.getNodes();
		adjList = new HashMap<>();
		nodes.forEach(node -> adjList.put(node, new HashSet<Node>()));
		Set<Edge> edges = graph.getEdges();
		edges.forEach(edge -> {
			Node source = edge.getSource();
			Set<Node> neighbors = adjList.get(source);
			neighbors.add(edge.getDestination());
		});

	}

	private void createEdgeMapper() {
		Set<Edge> edges = graph.getEdges();
		edgeMapper = new HashMap<>();
		edges.forEach(edge -> {
			Node source = edge.getSource();
			Node destination = edge.getDestination();
			EdgeWrapper edgeWrapper = new EdgeWrapper(source, destination);
			edgeMapper.put(edgeWrapper, edge);
		});
	}

	@Override()
	public Set<Node> getNeighbors(final Node node) {
		Set<Node> neighbors = adjList.get(node);
		if (neighbors == null) {
			throw new IllegalArgumentException("No such node exists:" + node);
		}
		return neighbors;
	}

	@Override()
	public GraphRepresentation createRepresentation() {
		createAdjacancyList();
		createEdgeMapper();
		return this;
	}

	@Override()
	public double getDistanceBetweenNodes(final Node source, final Node destination) {
		EdgeWrapper edgeWrapper = new EdgeWrapper(source, destination);
		Edge edge = edgeMapper.get(edgeWrapper);
		if (edge == null) {
			StringBuilder exceptionMessage = new StringBuilder();
			if (!adjList.containsKey(source)) {
				exceptionMessage.append("Graph does not have " + source + " node\n");
			}
			if (!adjList.containsKey(destination)) {
				exceptionMessage.append("Graph does not have " + destination + " node\n");
			}
			throw new IllegalArgumentException(exceptionMessage.toString());
		}
		return edge.getEdgeWeight();
	}
}
