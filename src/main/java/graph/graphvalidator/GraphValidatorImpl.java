package graph.graphvalidator;

import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.Node;
import graph.shortestpathalgorithm.BellmanFord;
import graph.shortestpathalgorithm.Dijkstra;
import graph.traversalalgorithm.BFSTraversal;
import graph.traversalalgorithm.DFSTraversal;
import graph.utils.GraphUtil;

public class GraphValidatorImpl implements GraphValidator {

	private StringBuilder errorMessage = new StringBuilder();
	private static final String GRAPH_MUST_BE_NON_EMPTY_OR_NULL = "Graph must not be empty or null\n";
	private static final String GRAPH_REPRESENTATION_MUST_NOT_BE_NULL = "Graph Representation should not be null\n";
	private static final String GRAPH_REPRESENTATION_IS_NOT_VALID_REPRESENTATION_OF_GRAPH = "Graph representation is not a valid graph representation of a graph\n";
	private static final String SOURCE_NODE_MUST_NOT_BE_NULL = "Source node should not be null\n";
	private static final String SOURCE_NODE_MUST_BE_MEMBER_OF_GRAPH = "Source node must be member of graph\n";

	private boolean isGraphValid(Graph graph) {
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

	private boolean isSourceNodeValid(Graph graph, Node source) {
		if (GraphUtil.isNullObject(source)) {
			errorMessage.append(SOURCE_NODE_MUST_NOT_BE_NULL);
			return false;
		}

		if (!graph.getNodes().contains(source)) {
			errorMessage.append(SOURCE_NODE_MUST_BE_MEMBER_OF_GRAPH);
			return false;
		}

		return true;

	}

	private boolean isValid(final Graph graph, final GraphRepresentation representation) {
		errorMessage = new StringBuilder();
		boolean isValidGraph = isGraphValid(graph);
		boolean isValidRepresentation = isGraphRepresentationValid(graph, representation);
		return isValidGraph && isValidRepresentation;
	}

	@Override()
	public boolean isValid(final BFSTraversal object) {
		return isValid(object.getGraph(), object.getGraphRepresentation());

	}

	@Override()
	public boolean isValid(final DFSTraversal object) {
		return isValid(object.getGraph(), object.getGraphRepresentation());
	}

	@Override()
	public boolean isValid(final Dijkstra object) {
		return isValid(object.getGraph(), object.getGraphRepresentation())
				&& isSourceNodeValid(object.getGraph(), object.getSource());
	}

	@Override()
	public boolean isValid(final BellmanFord object) {
		return isGraphValid(object.getGraph()) && isSourceNodeValid(object.getGraph(), object.getSource());
	}

	@Override()
	public String getErrorMessage() {
		return errorMessage.toString();
	}

}
