package graph.graphvalidator;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;

public interface GraphValidator {

	public boolean isValidNodeOfGraph(final Graph graph, final Node node);

	public boolean isValidGraphRepresentation(final Graph graph, final GraphRepresentation graphRepresentation);

	public boolean isValidGraph(final Graph graph);

	public boolean isValidEdgeOfGraph(final Graph graph, final Edge edge);

	public String getErrorMessage();

}
