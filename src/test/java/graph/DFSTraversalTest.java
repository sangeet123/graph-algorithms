package graph;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.traversalalgorithm.DFSTraversal;
import graph.traversalalgorithm.TraversalAlgorithm;
import graph.util.GraphTestUtil;

/*
 * This is just a simple test for DFSTraversal algorithm. The test
 * does not assert anything except for weather the DFSTraversal class is 
 * displaying appropriate results or not. The result of BFSTraversal depends
 */
public class DFSTraversalTest {

	private static final String GRAPH_WITH_NO_EDGE_FILE_NAME = "TestGraphDataWithNoEdge";
	private static final String GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME = "TestGraphDataWithNoNodeAndEdge";
	private static final String UNDIRECTED_GRAPH_FILE_NAME = "UnDirectedGraphTestData";
	private static final String DIRECTED_GRAPH_FILE_NAME = "DirectedGraphTestData";
	
	private static Graph graphWithNoEdge;
	private static Graph graphWithNoNodeAndNoEdge;
	private static Graph unidirectedGraph;
	private static Graph directedGraph;

	private static GraphRepresentation noEdgeGraphRepresentation;
	private static GraphRepresentation noNodeAndNoEdgeGraphRepresentation;
	private static GraphRepresentation undirectedGraphRepresentation;
	private static GraphRepresentation directedGraphRepresentation;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// Graph with no edges data preparation
		graphWithNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_EDGE_FILE_NAME);
		noEdgeGraphRepresentation = new AdjacancyListRepresentation(graphWithNoEdge).createRepresentation();

		// Graph with no edges and node data preparation
		graphWithNoNodeAndNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME);
		noNodeAndNoEdgeGraphRepresentation = new AdjacancyListRepresentation(graphWithNoNodeAndNoEdge)
				.createRepresentation();

		// undirected graph data preparation
		unidirectedGraph = GraphTestUtil.loadGraph(UNDIRECTED_GRAPH_FILE_NAME);
		undirectedGraphRepresentation = new AdjacancyListRepresentation(unidirectedGraph).createRepresentation();

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		directedGraphRepresentation = new AdjacancyListRepresentation(directedGraph).createRepresentation();
	}

	@After()
	public void summary() {
		System.out.println("---------------------------------------------------------------");
	}

	@Test()
	public void bfs_traversal_test_for_graph_with_no_edge() throws Exception {
		TraversalAlgorithm traversal = new DFSTraversal(graphWithNoEdge, noEdgeGraphRepresentation);
		traversal.traverse();
	}

	@Test()
	public void bfs_traversal_test_for_graph_with_no_node_and_no_edge() throws Exception {
		TraversalAlgorithm traversal = new DFSTraversal(graphWithNoNodeAndNoEdge, noNodeAndNoEdgeGraphRepresentation);
		traversal.traverse();
	}

	@Test()
	public void bfs_traversal_test_for_undirected_graph() throws Exception {
		TraversalAlgorithm traversal = new DFSTraversal(unidirectedGraph, undirectedGraphRepresentation);
		traversal.traverse();
	}

	@Test()
	public void bfs_traversal_test_for_directed_graph() throws Exception {
		TraversalAlgorithm traversal = new DFSTraversal(directedGraph, directedGraphRepresentation);
		traversal.traverse();
	}

	@Test(expected = IllegalStateException.class)
	public void bfs_traversal_test_for_graph_with_invalid_representation() throws Exception {
		TraversalAlgorithm traversal = new DFSTraversal(directedGraph, undirectedGraphRepresentation);
		traversal.traverse();

	}

}
