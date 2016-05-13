package graph.graphvalidator;

import graph.shortestpathalgorithm.BellmanFord;
import graph.shortestpathalgorithm.Dijkstra;
import graph.traversalalgorithm.BFSTraversal;
import graph.traversalalgorithm.DFSTraversal;

public interface GraphValidator {

	public boolean isValid(final BFSTraversal object);

	public boolean isValid(final DFSTraversal object);

	public boolean isValid(final Dijkstra object);

	public boolean isValid(final BellmanFord object);

	public String getErrorMessage();

}
