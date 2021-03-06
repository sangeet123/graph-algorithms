package graph.graphvalidator;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class GraphValidatorImpl implements GraphValidator {

	private StringBuilder errorMessage = new StringBuilder();
	private static final String GRAPH_MUST_BE_NON_EMPTY_OR_NULL = "Graph must not be empty or null\n";
	private static final String GRAPH_REPRESENTATION_MUST_NOT_BE_NULL = "Graph Representation should not be null\n";
	private static final String GRAPH_REPRESENTATION_IS_NOT_VALID_REPRESENTATION_OF_GRAPH = "Graph representation is not a valid graph representation of a graph\n";
	private static final String NODE_MUST_NOT_BE_NULL = "Node should not be null\n";
	private static final String NODE_MUST_BE_MEMBER_OF_GRAPH = "Node must be member of graph\n";
	private static final String EDGE_MUST_NOT_BE_NULL = "Edge should not be null\n";

	private boolean isGraphValid(final Graph graph) {
		if (GraphUtil.isGraphNullOrEmpty(graph)) {
			errorMessage.append(GRAPH_MUST_BE_NON_EMPTY_OR_NULL);
			return false;
		}

		return true;
	}

	private boolean isGraphRepresentationValid(final Graph graph, final GraphRepresentation representation) {

		if (GraphUtil.isNullObject(representation)) {
			errorMessage.append(GRAPH_REPRESENTATION_MUST_NOT_BE_NULL);
			return false;
		}

		if (!representation.isGraphRepresentationOf(graph)) {
			errorMessage.append(GRAPH_REPRESENTATION_IS_NOT_VALID_REPRESENTATION_OF_GRAPH);
			return false;
		}

		return true;
	}

	private boolean isGraphNodeValid(final Graph graph, final Node node) {
		if (GraphUtil.isNullObject(node)) {
			errorMessage.append(NODE_MUST_NOT_BE_NULL);
			return false;
		}

		if (!graph.getNodes().contains(node)) {
			errorMessage.append(node + " " + NODE_MUST_BE_MEMBER_OF_GRAPH);
			return false;
		}

		return true;

	}

	/*
	 * validity of graph edge is measured if both nodes are present in graph
	 */

	private boolean isGraphEdgeValid(final Graph graph, final Edge edge) {
		if (GraphUtil.isNullObject(edge)) {
			errorMessage.append(EDGE_MUST_NOT_BE_NULL);
			return false;
		}

		boolean isSourceNodeValid = isGraphNodeValid(graph, edge.getSource());
		boolean isDestinationNodeValid = isGraphNodeValid(graph, edge.getDestination());

		if (!(isSourceNodeValid && isDestinationNodeValid)) {
			return false;
		}

		return true;
	}

	@Override()
	public boolean isValidGraphRepresentation(final Graph graph, final GraphRepresentation representation) {
		errorMessage = new StringBuilder();
		boolean isValidGraph = isGraphValid(graph);
		boolean isValidRepresentation = isGraphRepresentationValid(graph, representation);
		return isValidGraph && isValidRepresentation;
	}

	@Override()
	public boolean isValidNodeOfGraph(final Graph graph, final Node node) {
		errorMessage = new StringBuilder();
		return isGraphValid(graph) && isGraphNodeValid(graph, node);
	}

	@Override()
	public boolean isValidGraph(final Graph graph) {
		errorMessage = new StringBuilder();
		return isGraphValid(graph);
	}

	@Override()
	public boolean isValidEdgeOfGraph(final Graph graph, final Edge edge) {
		errorMessage = new StringBuilder();
		return isGraphValid(graph) && isGraphEdgeValid(graph, edge);
	}

	@Override()
	public String getErrorMessage() {
		return errorMessage.toString();
	}

}
