package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.minimumspanningtreealgorithms.Prim;
import graph.model.Edge;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.GraphNode;
import graph.model.Node;
import graph.util.GraphTestUtil;

public class PrimTest {

	private static final String GRAPH_WITH_NO_EDGE_FILE_NAME = "TestGraphDataWithNoEdge";
	private static final String GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME = "TestGraphDataWithNoNodeAndEdge";
	private static final String UNDIRECTED_GRAPH_FILE_NAME = "UnDirectedGraphTestData";
	private static final String DIRECTED_GRAPH_FILE_NAME = "DirectedGraphTestData";
	private static final double epsilon = 0.0000001;

	private static Graph graphWithNoEdge;
	private static Graph graphWithNoNodeAndNoEdge;
	private static Graph unidirectedGraph;
	private static Graph directedGraph;

	private static GraphRepresentation noEdgeGraphRepresentation;
	private static GraphRepresentation noNodeAndNoEdgeGraphRepresentation;
	private static GraphRepresentation undirectedGraphRepresentation;
	private static GraphRepresentation directedGraphRepresentation;

	private static Prim primNoEdgeGraph;
	private static Prim primForGraphWithNoNodeAndNoEdge;
	private static Prim primForDirectedGraph;
	private static Prim primForUnDirectedGraph;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// Graph with no edges data preparation
		graphWithNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_EDGE_FILE_NAME);
		noEdgeGraphRepresentation = new AdjacancyListRepresentation(graphWithNoEdge).createRepresentation();
		primNoEdgeGraph = new Prim(graphWithNoEdge, noEdgeGraphRepresentation);

		// Graph with no edges and node data preparation
		graphWithNoNodeAndNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME);
		noNodeAndNoEdgeGraphRepresentation = new AdjacancyListRepresentation(graphWithNoNodeAndNoEdge)
				.createRepresentation();
		primForGraphWithNoNodeAndNoEdge = new Prim(graphWithNoNodeAndNoEdge, noNodeAndNoEdgeGraphRepresentation);

		// undirected graph data preparation
		unidirectedGraph = GraphTestUtil.loadGraph(UNDIRECTED_GRAPH_FILE_NAME);
		undirectedGraphRepresentation = new AdjacancyListRepresentation(unidirectedGraph).createRepresentation();
		primForUnDirectedGraph = new Prim(unidirectedGraph, undirectedGraphRepresentation);

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		directedGraphRepresentation = new AdjacancyListRepresentation(directedGraph).createRepresentation();
		primForDirectedGraph = new Prim(directedGraph, directedGraphRepresentation);
	}

	// Test of graph with no node and no edge starts here
	@Test()
	public void test_result_of_minimum_spanning_tree_for_graph_with_no_nodes_no_edges() throws Exception {
		Set<Edge> spanningTreeEdges = primForGraphWithNoNodeAndNoEdge.getSpanningTreeEdges();
		assertTrue(spanningTreeEdges.isEmpty());

	}

	// Test of graph with no edges starts here
	@Test()
	public void test_result_of_minimum_spanning_tree_for_graph_with_no_edges() throws Exception {
		Set<Edge> spanningTreeEdges = primNoEdgeGraph.getSpanningTreeEdges();
		Set<Edge> expectedMinimumSpanningTree = new HashSet<>(Arrays.asList(
				new GraphEdge[] { new GraphEdge(null, new GraphNode("1")), new GraphEdge(null, new GraphNode("2")),
						new GraphEdge(null, new GraphNode("3")), new GraphEdge(null, new GraphNode("4")) }));
		assertEquals(expectedMinimumSpanningTree, spanningTreeEdges);
	}

	// directed graph test
	@Test()
	public void test_result_of_minimum_spanning_tree_for_directed_graph() throws Exception {
		Set<Edge> spanningTreeEdges = primForDirectedGraph.getSpanningTreeEdges();
		Node graphNode1 = new GraphNode("1");
		Node graphNode2 = new GraphNode("2");
		Node graphNode3 = new GraphNode("3");
		Node graphNode4 = new GraphNode("4");
		Node graphNode5 = new GraphNode("5");
		Node graphNode6 = new GraphNode("6");
		Node graphNode10 = new GraphNode("10");

		Set<Edge> expectedMinimumSpanningTree = new HashSet<>(
				Arrays.asList(new GraphEdge[] { new GraphEdge(null, graphNode1), new GraphEdge(graphNode1, graphNode5),
						new GraphEdge(graphNode5, graphNode2), new GraphEdge(graphNode2, graphNode3),
						new GraphEdge(graphNode2, graphNode6), new GraphEdge(graphNode6, graphNode4),
						new GraphEdge(graphNode4, graphNode10) }));
		Set<Edge> edgesFromGraph = directedGraph.getEdges();

		double totalEdgeWeight = 0;
		double expectedEdgeWeight = 0;

		for (Edge edge : edgesFromGraph) {
			if (expectedMinimumSpanningTree.contains(edge)) {
				expectedEdgeWeight += ((GraphEdge) edge).getEdgeWeight();
			}
			if (spanningTreeEdges.contains(edge)) {
				totalEdgeWeight += ((GraphEdge) edge).getEdgeWeight();
			}
		}

		assertTrue(Math.abs(expectedEdgeWeight - totalEdgeWeight) < epsilon);
	}

	// undirected graph test
	@Test()
	public void test_result_of_minimum_spanning_tree_for_un_directed_graph() throws Exception {
		Set<Edge> spanningTreeEdges = primForUnDirectedGraph.getSpanningTreeEdges();
		Set<Edge> edgesFromGraph = unidirectedGraph.getEdges();

		Node graphNode1 = new GraphNode("1");
		Node graphNode2 = new GraphNode("2");
		Node graphNode3 = new GraphNode("3");
		Node graphNode4 = new GraphNode("4");
		Node graphNode5 = new GraphNode("5");
		Node graphNode6 = new GraphNode("6");
		Node graphNode7 = new GraphNode("7");
		Node graphNode8 = new GraphNode("8");

		Set<Edge> expectedMinimumSpanningTree = new HashSet<>(
				Arrays.asList(new GraphEdge[] { new GraphEdge(null, graphNode1), new GraphEdge(graphNode1, graphNode2),
						new GraphEdge(graphNode2, graphNode6), new GraphEdge(graphNode2, graphNode7),
						new GraphEdge(graphNode6, graphNode3), new GraphEdge(graphNode3, graphNode8),
						new GraphEdge(graphNode7, graphNode4), new GraphEdge(graphNode4, graphNode5) }));

		double totalEdgeWeight = 0;
		double expectedEdgeWeight = 0;

		for (Edge edge : edgesFromGraph) {
			if (expectedMinimumSpanningTree.contains(edge)) {
				expectedEdgeWeight += ((GraphEdge) edge).getEdgeWeight();
			}
			if (spanningTreeEdges.contains(edge)) {
				totalEdgeWeight += ((GraphEdge) edge).getEdgeWeight();
			}
		}
		assertTrue(Math.abs(expectedEdgeWeight - totalEdgeWeight) < epsilon);

	}

}
