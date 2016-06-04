package graph.minimumspanningtreealgorithms;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import unionfind.UnionFind;

public class Kruskal extends BaseGraphRepresentationWithValidation implements SpanningTreeAlgorithm {

	private Set<Edge> minimumSpanningTreeEdges;

	public Kruskal() {
		/*
		 * 
		 */
	}

	public Kruskal(final Graph graph, final GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	@Override()
	public Set<Edge> getSpanningTreeEdges() {

		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}

		UnionFind<Node> unionFind = new UnionFind<>();
		this.graph.getNodes().forEach(node -> unionFind.add(node));
		Comparator<Edge> byEdgeWeight = (e1, e2) -> Double.compare(e1.getEdgeWeight(), e2.getEdgeWeight());
		List<Edge> sortedEdges = graph.getEdges().stream().sorted(byEdgeWeight).collect(Collectors.toList());
		minimumSpanningTreeEdges = new HashSet<>();

		sortedEdges.forEach(edge -> {
			Node source = edge.getSource();
			Node destination = edge.getDestination();

			if (!unionFind.belongsToSameSet(source, destination)) {
				unionFind.union(source, destination);
				minimumSpanningTreeEdges.add(edge);
			}

		});

		return minimumSpanningTreeEdges;
	}

}
