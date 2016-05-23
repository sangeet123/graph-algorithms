package graph.graphvalidator;

import graph.model.Graph;
import graph.model.Node;
import graph.shortestpathalgorithm.BellmanFord;
import graph.shortestpathalgorithm.Dijkstra;
import graph.shortestpathalgorithm.FloydWarshall;
import graph.traversalalgorithm.BFSTraversal;
import graph.traversalalgorithm.DFSTraversal;

public interface GraphValidator {

	public boolean isValid(final BFSTraversal object);

	public boolean isValid(final DFSTraversal object);

	public boolean isValid(final Dijkstra object);

	public boolean isValid(final BellmanFord object);

	public boolean isValid(final FloydWarshall object);

	public boolean isValidNode(final Graph graph, final Node node);

	public String getErrorMessage();

}
