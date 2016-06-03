package graph.graphrepresentation;

import java.util.Set;

import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public interface GraphRepresentation {

	Set<Node> getNeighbors(final Node node);

	GraphRepresentation createRepresentation();

	default double getDistanceBetweenNodes(final Node source, final Node destination) {
		throw new UnsupportedOperationException(GraphUtil.GET_DISTANCE_BETWEEN_NODES_OPERATION_NOT_SUPPORTED);
	}

	boolean isGraphRepresentationOf(final Graph graph);
}
