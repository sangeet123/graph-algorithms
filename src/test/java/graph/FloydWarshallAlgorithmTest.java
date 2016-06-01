package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.model.Graph;
import graph.model.GraphNode;
import graph.model.Node;
import graph.shortestpathalgorithm.FloydWarshall;
import graph.shortestpathalgorithm.ShortestPathAlgorithm;
import graph.util.GraphTestUtil;

public class FloydWarshallAlgorithmTest {

	private static final String GRAPH_WITH_NO_EDGE_FILE_NAME = "TestGraphDataWithNoEdge";
	private static final String GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME = "TestGraphDataWithNoNodeAndEdge";
	private static final String UNDIRECTED_GRAPH_FILE_NAME = "UnDirectedGraphTestData";
	private static final String DIRECTED_GRAPH_FILE_NAME = "DirectedGraphTestData";
	
	private static Graph graphWithNoEdge;
	private static Graph graphWithNoNodeAndNoEdge;
	private static Graph unidirectedGraph;
	private static Graph directedGraph;

	private static ShortestPathAlgorithm floydWarshallNoEdgeGraph;
	private static ShortestPathAlgorithm floydWarshallForGraphWithNoNodeAndNoEdge;
	private static ShortestPathAlgorithm floydWarshallForDirectedGraph;
	private static ShortestPathAlgorithm floydWarshallForUnDirectedGraph;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// Graph with no edges data preparation
		graphWithNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_EDGE_FILE_NAME);
		floydWarshallNoEdgeGraph = new FloydWarshall(graphWithNoEdge);
		floydWarshallNoEdgeGraph.findShortestPath();

		// Graph with no edges and node data preparation
		graphWithNoNodeAndNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME);
		floydWarshallForGraphWithNoNodeAndNoEdge = new FloydWarshall(graphWithNoNodeAndNoEdge);
		floydWarshallForGraphWithNoNodeAndNoEdge.findShortestPath();

		// undirected graph data preparation
		unidirectedGraph = GraphTestUtil.loadGraph(UNDIRECTED_GRAPH_FILE_NAME);
		floydWarshallForUnDirectedGraph = new FloydWarshall(unidirectedGraph);
		floydWarshallForUnDirectedGraph.findShortestPath();

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		floydWarshallForDirectedGraph = new FloydWarshall(directedGraph);
		floydWarshallForDirectedGraph.findShortestPath();
	}

	// Test of graph with no node and no edge starts here
	@Test(expected = IllegalArgumentException.class)
	public void test_result_of_shortest_path_for_graph_with_no_nodes_no_edges() throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("3");
		((FloydWarshall) floydWarshallForGraphWithNoNodeAndNoEdge).getPath(source, destination);
	}

	// Test of graph with no edges starts here
	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_2()
			throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("2");
		assertTrue(((FloydWarshall) floydWarshallNoEdgeGraph).getPath(source, destination).isEmpty());
	}

	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_3()
			throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("3");
		assertTrue(((FloydWarshall) floydWarshallNoEdgeGraph).getPath(source, destination).isEmpty());
	}

	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_4()
			throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("4");
		assertTrue(((FloydWarshall) floydWarshallNoEdgeGraph).getPath(source, destination).isEmpty());
	}

	// directed graph tests start here
	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_10()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "4", "10" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("10");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_2()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "2" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("2");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_6()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "2", "6" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("6");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_5()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("5");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_3()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "3" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("3");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_4()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "4" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("4");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_10_and_destination_node_10()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		Node source = new GraphNode("10");
		Node destination = new GraphNode("10");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_10_and_destination_node_1()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		Node source = new GraphNode("10");
		Node destination = new GraphNode("1");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForDirectedGraph).getPath(source, destination));
	}

	// undirected graph test starts here
	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_5()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7", "4", "5" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("5");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_6()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "6" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("6");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_7()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("7");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_2()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("2");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_3()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "3" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("3");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_4()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7", "4" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("4");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_8()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "3", "8" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("1");
		Node destination = new GraphNode("8");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_8_and_destination_node_1()
			throws Exception {
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "8", "3", "2", "1" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		Node source = new GraphNode("8");
		Node destination = new GraphNode("1");
		assertEquals(expectedPath, ((FloydWarshall) floydWarshallForUnDirectedGraph).getPath(source, destination));
	}

}