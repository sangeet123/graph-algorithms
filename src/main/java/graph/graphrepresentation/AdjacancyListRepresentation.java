package graph.graphrepresentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graph.model.BaseGraphWithValidator;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.Node;

public class AdjacancyListRepresentation extends BaseGraphWithValidator implements GraphRepresentation {

	private Map<Node, Set<Node>> adjList;
	private Map<EdgeWrapper, Edge> edgeMapper;

	public AdjacancyListRepresentation(final Graph graph) {
		this.graph = graph;
	}

	@Override()
	public void setGraph(final Graph graph) {
		// decided not to support this operation once adjacancy list object is
		// created as this will need extra effort for validation
		throw new UnsupportedOperationException("Operation not supported");
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

	private Set<Edge> reverseEdge(final Set<Edge> edges) {
		Set<Edge> reversedEdgeSet = new HashSet<>();
		edges.forEach(edge -> {
			Edge reverseEdge = new GraphEdge(edge.getDestination(), edge.getSource());
			reversedEdgeSet.add(reverseEdge);
		});
		return reversedEdgeSet;
	}

	@Override()
	public GraphRepresentation transpose() {
		Graph transposeOfGraph = new Graph();
		Set<Node> nodesForTransposedGraph = new HashSet<>(this.getGraph().getNodes());
		transposeOfGraph.setNodes(nodesForTransposedGraph);
		Set<Edge> edgeSetForTransposedGraph = reverseEdge(this.graph.getEdges());
		transposeOfGraph.setEdges(edgeSetForTransposedGraph);
		return new AdjacancyListRepresentation(transposeOfGraph).createRepresentation();
	}

	@Override()
	public Set<Node> getNeighbors(final Node node) {
		if (!validator.isValidNodeOfGraph(graph, node)) {
			throw new IllegalArgumentException(validator.getErrorMessage());
		}
		return adjList.get(node);
	}

	@Override()
	public GraphRepresentation createRepresentation() {
		createAdjacancyList();
		createEdgeMapper();
		return this;
	}

	@Override()
	public double getDistanceBetweenNodes(final Node source, final Node destination) {
		Edge edge = new GraphEdge(source, destination);
		if (!validator.isValidEdgeOfGraph(graph, edge)) {
			throw new IllegalArgumentException(validator.getErrorMessage());
		}
		EdgeWrapper edgeWrapper = new EdgeWrapper(source, destination);
		Edge edgeInMap = edgeMapper.get(edgeWrapper);
		return edgeInMap == null ? Double.MAX_VALUE : edgeInMap.getEdgeWeight();
	}

	@Override
	public boolean isGraphRepresentationOf(Graph graph) {
		return this.getGraph() != null && this.getGraph().equals(graph);
	}

}
