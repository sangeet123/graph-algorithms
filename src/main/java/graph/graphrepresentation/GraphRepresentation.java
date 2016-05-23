package graph.graphrepresentation;

import java.util.Set;

import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public interface GraphRepresentation {

	public Set<Node> getNeighbors(final Node node);

	public GraphRepresentation createRepresentation();

	default double getDistanceBetweenNodes(final Node source, final Node destination) {
		throw new UnsupportedOperationException(GraphUtil.GET_DISTANCE_BETWEEN_NODES_OPERATION_NOT_SUPPORTED);
	}

	public boolean isGraphRepresentationOf(final Graph graph);
}
