package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.GraphNode;
import graph.model.Node;
import graph.util.GraphTestUtil;

public class AdjacancyListRepresentationTest {
	private static final String GRAPH_WITH_NO_EDGE_FILE_NAME = "TestData1";
	private static final String GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME = "TestData2";
	private static final String UNDIRECTED_GRAPH_FILE_NAME = "TestData3";
	private static final String DIRECTED_GRAPH_FILE_NAME = "TestData4";
	private static final String UNDIRECTED_GRAPH_REPRESENTATION_FILE_NAME = "TestData3Result";
	private static final String DIRECTED_GRAPH_REPRESENTATION_FILE_NAME = "TestData4Result";

	private static Graph graphWithNoEdge;
	private static Graph graphWithNoNodeAndNoEdge;
	private static Graph unidirectedGraph;
	private static Graph directedGraph;

	private static GraphRepresentation noEdgeGraphRepresentation;
	private static GraphRepresentation undirectedGraphRepresentation;
	private static GraphRepresentation directedGraphRepresentation;

	private static Map<Node, Map<Node, Double>> expectedRepresentatinResultForUndirectedGraph;
	private static Map<Node, Map<Node, Double>> expectedRepresentatinResultForDirectedGraph;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// Graph with no edges data preparation
		graphWithNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_EDGE_FILE_NAME);
		noEdgeGraphRepresentation = new AdjacancyListRepresentation(graphWithNoEdge).createRepresentation();

		// Graph with no edges and node data preparation
		graphWithNoNodeAndNoEdge = GraphTestUtil.loadGraph(GRAPH_WITH_NO_NODE_AND_NO_EDGE_FILE_NAME);

		// undirected graph data preparation
		unidirectedGraph = GraphTestUtil.loadGraph(UNDIRECTED_GRAPH_FILE_NAME);
		undirectedGraphRepresentation = new AdjacancyListRepresentation(unidirectedGraph).createRepresentation();
		expectedRepresentatinResultForUndirectedGraph = GraphTestUtil
				.loadResult(UNDIRECTED_GRAPH_REPRESENTATION_FILE_NAME);

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		directedGraphRepresentation = new AdjacancyListRepresentation(directedGraph).createRepresentation();
		expectedRepresentatinResultForDirectedGraph = GraphTestUtil.loadResult(DIRECTED_GRAPH_REPRESENTATION_FILE_NAME);
	}

	@Test()
	public void test_adjacancy_representation_of_graph_with_no_edge() throws Exception {
		Set<Node> expectedNeighbors = new HashSet<>();
		graphWithNoEdge.getNodes()
				.forEach(node -> assertEquals(noEdgeGraphRepresentation.getNeighbors(node), expectedNeighbors));
	}

	@Test()
	public void test_adjacancy_representation_of_graph_with_no_node_no_edge() throws Exception {
		assertEquals(graphWithNoNodeAndNoEdge.getNodes().isEmpty(), true);
		assertEquals(graphWithNoNodeAndNoEdge.getEdges().isEmpty(), true);
	}

	@Test()
	public void test_adjacancy_representation_of_undirected_graph() throws Exception {
		unidirectedGraph.getNodes().forEach(node -> assertEquals(undirectedGraphRepresentation.getNeighbors(node),
				expectedRepresentatinResultForUndirectedGraph.get(node).keySet()));
	}

	@Test()
	public void test_adjacancy_representation_of_directed_graph() throws Exception {
		directedGraph.getNodes().forEach(node -> assertEquals(directedGraphRepresentation.getNeighbors(node),
				expectedRepresentatinResultForDirectedGraph.get(node).keySet()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_neighbor_list_for_node_that_does_not_exist_on_graph_expects_exception() {
		Node nodeThatDoesNotExist = new GraphNode("nodethatdoesnotexist");
		directedGraphRepresentation.getNeighbors(nodeThatDoesNotExist);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void try_setting_graph_for_already_created_adjacancy_list_throws_exception() {
		((AdjacancyListRepresentation) directedGraphRepresentation).setGraph(graphWithNoNodeAndNoEdge);
	}

	@Test()
	public void test_edge_weight_for_directed_graph() throws Exception {
		expectedRepresentatinResultForDirectedGraph.forEach((s, n) -> {
			n.forEach((d, w) -> {
				assertEquals(new Double(directedGraphRepresentation.getDistanceBetweenNodes(s, d)), w);
			});
		});
	}

	@Test()
	public void test_edge_weight_for_undirected_graph() throws Exception {
		expectedRepresentatinResultForUndirectedGraph.forEach((s, n) -> {
			n.forEach((d, w) -> {
				assertEquals(new Double(undirectedGraphRepresentation.getDistanceBetweenNodes(s, d)), w);
			});
		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_edge_weight_that_does_not_exist_in_graph() throws Exception {
		Node sourceNodeThatDoesNotExist = new GraphNode("sourceNodethatdoesnotexist");
		Node destinationNodeThatDoesNotExist = new GraphNode("destinationNodethatdoesnotexist");
		undirectedGraphRepresentation.getDistanceBetweenNodes(sourceNodeThatDoesNotExist,
				destinationNodeThatDoesNotExist);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_edge_weight_that_does_not_exist_in_graph_for_non_existant_source_node() throws Exception {
		Node sourceNodeThatDoesNotExist = new GraphNode("sourceNodethatdoesnotexist");
		Node destinationNodeThatDoesNotExist = new GraphNode("1");
		undirectedGraphRepresentation.getDistanceBetweenNodes(sourceNodeThatDoesNotExist,
				destinationNodeThatDoesNotExist);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_edge_weight_that_does_not_exist_in_graph_for_non_existant_destination_node() throws Exception {
		Node sourceNodeThatDoesNotExist = new GraphNode("1");
		Node destinationNodeThatDoesNotExist = new GraphNode("destinationNodethatdoesnotexist");
		undirectedGraphRepresentation.getDistanceBetweenNodes(sourceNodeThatDoesNotExist,
				destinationNodeThatDoesNotExist);
	}

	@Test()
	public void test_edge_weight_that_does_not_exist_for_valid_nodes() throws Exception {
		Node sourceNode = new GraphNode("1");
		Node destinationNode = new GraphNode("8");
		double epsilon = 0.00001;
		assertTrue(Math.abs(Double.MAX_VALUE
				- undirectedGraphRepresentation.getDistanceBetweenNodes(sourceNode, destinationNode)) < epsilon);
	}
}
