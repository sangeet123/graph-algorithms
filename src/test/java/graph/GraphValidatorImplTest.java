package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.graphvalidator.GraphValidator;
import graph.graphvalidator.GraphValidatorImpl;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.GraphNode;
import graph.model.Node;
import graph.util.GraphTestUtil;

public class GraphValidatorImplTest {

	private static final String DIRECTED_GRAPH_FILE_NAME = "TestData4";
	private static Graph directedGraph;
	private static GraphRepresentation directedGraphRepresentation;
	private static GraphValidator graphValidator = new GraphValidatorImpl();

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		directedGraphRepresentation = new AdjacancyListRepresentation(directedGraph).createRepresentation();
	}

	@Test()
	public void test_null_is_not_a_valid_graph() throws Exception {
		assertFalse(graphValidator.isValidGraph(null));
		assertEquals(graphValidator.getErrorMessage(), "Graph must not be empty or null\n");
	}

	@Test()
	public void test_valid_graph() throws Exception {
		assertTrue(graphValidator.isValidGraph(directedGraph));
	}

	@Test()
	public void test_valid_graph_node_pair() throws Exception {
		assertTrue(graphValidator.isValidNodeOfGraph(directedGraph, new GraphNode("1")));
	}

	@Test()
	public void test_invalid_graph_node_pair_where_graph_is_not_valid() throws Exception {
		assertFalse(graphValidator.isValidNodeOfGraph(null, new GraphNode("1")));
		assertEquals(graphValidator.getErrorMessage(), "Graph must not be empty or null\n");
	}

	@Test()
	public void test_invalid_graph_node_pair_where_graph_is_valid_but_node_is_not_which_is_null() throws Exception {
		assertFalse(graphValidator.isValidNodeOfGraph(directedGraph, null));
		assertEquals(graphValidator.getErrorMessage(), "Node should not be null\n");
	}

	@Test()
	public void test_invalid_graph_node_pair_where_graph_is_valid_but_node_is_not_memeber_of_graph() throws Exception {
		assertFalse(graphValidator.isValidNodeOfGraph(directedGraph, new GraphNode("notamemeberofgraph")));
		assertEquals(graphValidator.getErrorMessage(), "Node must be member of graph\n");
	}

	@Test()
	public void test_valid_graph_and_graph_representation_pair() throws Exception {
		assertTrue(graphValidator.isValidGraphRepresentation(directedGraph, directedGraphRepresentation));
	}

	@Test()
	public void test_invalid_graph_which_is_empty_and_valid_graph_representation() throws Exception {
		assertFalse(graphValidator.isValidGraphRepresentation(new Graph(), directedGraphRepresentation));
		assertEquals(graphValidator.getErrorMessage(),
				"Graph must not be empty or null\nGraph representation is not a valid graph representation of a graph\n");
	}

	@Test()
	public void test_valid_graph_which_and_in_valid_graph_representation() throws Exception {
		assertFalse(graphValidator.isValidGraphRepresentation(new Graph(), null));
		assertEquals(graphValidator.getErrorMessage(),
				"Graph must not be empty or null\nGraph Representation should not be null\n");
	}

	@Test()
	public void test_valid_graph_edge_pair() throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("2");
		assertTrue(graphValidator.isValidEdgeOfGraph(directedGraph, new GraphEdge(source, destination)));
	}

	@Test()
	public void test_invalid_graph_edge_pair_where_graph_is_not_valid() throws Exception {
		Node source = new GraphNode("1");
		Node destination = new GraphNode("2");
		assertFalse(graphValidator.isValidEdgeOfGraph(null, new GraphEdge(source, destination)));
		assertEquals(graphValidator.getErrorMessage(), "Graph must not be empty or null\n");
	}

	@Test()
	public void test_invalid_graph_edge_pair_where_graph_is_valid_but_edge_is_not_which_is_null() throws Exception {
		assertFalse(graphValidator.isValidEdgeOfGraph(directedGraph, null));
		assertEquals(graphValidator.getErrorMessage(), "Edge should not be null\n");
	}

	@Test()
	public void test_invalid_graph_edge_pair_where_graph_is_valid_but_edge_is_not_memeber_of_graph() throws Exception {
		Node source = null;
		Node destination = new GraphNode("notanode2");
		assertFalse(graphValidator.isValidEdgeOfGraph(directedGraph, new GraphEdge(source, destination)));
		assertEquals(graphValidator.getErrorMessage(), "Edge must be member of graph\n");
	}

}
