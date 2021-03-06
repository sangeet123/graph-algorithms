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
import graph.shortestpathalgorithm.BellmanFord;
import graph.shortestpathalgorithm.ShortestPathAlgorithm;
import graph.util.GraphTestUtil;

public class BellmanFordAlgorithmTest {

	private static final String GRAPH_WITH_NO_EDGE_FILE_NAME = "TestGraphDataWithNoEdge";
	private static final String GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME = "TestGraphDataWithNoNodeAndEdge";
	private static final String UNDIRECTED_GRAPH_FILE_NAME = "UnDirectedGraphTestData";
	private static final String DIRECTED_GRAPH_FILE_NAME = "DirectedGraphTestData";
	
	private static Graph graphWithNoEdge;
	private static Graph graphWithNoNodeAndNoEdge;
	private static Graph unidirectedGraph;
	private static Graph directedGraph;

	private static ShortestPathAlgorithm bellmanFordNoEdgeGraph;
	private static ShortestPathAlgorithm bellmanFordForGraphWithNoNodeAndNoEdge;
	private static ShortestPathAlgorithm bellmanFordForDirectedGraph;
	private static ShortestPathAlgorithm bellmanFordForUnDirectedGraph;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// Graph with no edges data preparation
		graphWithNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_EDGE_FILE_NAME);
		bellmanFordNoEdgeGraph = new BellmanFord(graphWithNoEdge);

		// Graph with no edges and node data preparation
		graphWithNoNodeAndNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME);
		bellmanFordForGraphWithNoNodeAndNoEdge = new BellmanFord(graphWithNoNodeAndNoEdge);

		// undirected graph data preparation
		unidirectedGraph = GraphTestUtil.loadGraph(UNDIRECTED_GRAPH_FILE_NAME);
		bellmanFordForUnDirectedGraph = new BellmanFord(unidirectedGraph);

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		bellmanFordForDirectedGraph = new BellmanFord(directedGraph);
	}

	// Test of graph with no node and no edge starts here
	@Test(expected = IllegalStateException.class)
	public void test_result_of_shortest_path_for_graph_with_no_nodes_no_edges() throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForGraphWithNoNodeAndNoEdge.findShortestPath(source);
	}

	// Test of graph with no edges starts here
	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_2()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordNoEdgeGraph.findShortestPath(source);
		assertTrue(((BellmanFord) bellmanFordNoEdgeGraph).getShortestPathTo(new GraphNode("2")).isEmpty());
	}

	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_3()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordNoEdgeGraph.findShortestPath(source);
		assertTrue(((BellmanFord) bellmanFordNoEdgeGraph).getShortestPathTo(new GraphNode("3")).isEmpty());
	}

	@Test()
	public void test_result_of_shortest_path_for_graph_with_no_edges_with_source_1_and_destination_4()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordNoEdgeGraph.findShortestPath(source);
		assertTrue(((BellmanFord) bellmanFordNoEdgeGraph).getShortestPathTo(new GraphNode("4")).isEmpty());
	}

	// directed graph tests start here
	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_10()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "4", "10" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("10")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_2()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "2" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("2")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_6()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "2", "6" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("6")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_5()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("5")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_3()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "3" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("3")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_1_and_destination_node_4()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "5", "4" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("4")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_10_and_destination_node_10()
			throws Exception {
		Node source = new GraphNode("10");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("10")));
	}

	@Test()
	public void test_result_of_shortest_path_for_directed_graph_with_source_node_10_and_destination_node_1()
			throws Exception {
		Node source = new GraphNode("10");
		bellmanFordForDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForDirectedGraph).getShortestPathTo(new GraphNode("1")));
	}

	// undirected graph test starts here
	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_5()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7", "4", "5" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("5")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_6()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "6" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("6")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_7()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("7")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_2()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("2")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_3()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "3" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("3")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_4()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "7", "4" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("4")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_1_and_destination_node_8()
			throws Exception {
		Node source = new GraphNode("1");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "1", "2", "3", "8" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("8")));
	}

	@Test()
	public void test_result_of_shortest_path_for_un_directed_graph_with_source_node_8_and_destination_node_1()
			throws Exception {
		Node source = new GraphNode("8");
		bellmanFordForUnDirectedGraph.findShortestPath(source);
		List<Node> expectedPath = new LinkedList<>();
		String[] nodeNames = { "8", "3", "2", "1" };
		for (String node : nodeNames) {
			expectedPath.add(new GraphNode(node));
		}
		assertEquals(expectedPath, ((BellmanFord) bellmanFordForUnDirectedGraph).getShortestPathTo(new GraphNode("1")));
	}

}
