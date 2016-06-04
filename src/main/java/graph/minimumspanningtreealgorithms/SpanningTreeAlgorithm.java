package graph.minimumspanningtreealgorithms;

import java.util.Set;

import graph.model.Edge;
import graph.model.Node;

public interface SpanningTreeAlgorithm {

	default Set<Edge> getSpanningTreeEdges(Node startNode) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	default Set<Edge> getSpanningTreeEdges() {
		throw new UnsupportedOperationException("Operation not supported");
	}

}
