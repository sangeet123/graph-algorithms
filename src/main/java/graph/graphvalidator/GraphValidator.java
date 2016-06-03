package graph.graphvalidator;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;

public interface GraphValidator {

	boolean isValidNodeOfGraph(final Graph graph, final Node node);

	boolean isValidGraphRepresentation(final Graph graph, final GraphRepresentation graphRepresentation);

	boolean isValidGraph(final Graph graph);

	boolean isValidEdgeOfGraph(final Graph graph, final Edge edge);

	String getErrorMessage();

}
