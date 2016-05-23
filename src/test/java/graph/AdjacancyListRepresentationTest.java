package graph;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
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
	private static GraphRepresentation noNodeAndNoEdgeGraphRepresentation;
	private static GraphRepresentation undirectedGraphRepresentation;
	private static GraphRepresentation directedGraphRepresentation;

	private static Map<Node, Set<Node>> expectedRepresentatinResultForUndirectedGraph;
	private static Map<Node, Set<Node>> expectedRepresentatinResultForDirectedGraph;

	@BeforeClass
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
		expectedRepresentatinResultForUndirectedGraph = GraphTestUtil
				.loadResult(UNDIRECTED_GRAPH_REPRESENTATION_FILE_NAME);

		// directed graph data preparation
		directedGraph = GraphTestUtil.loadGraph(DIRECTED_GRAPH_FILE_NAME);
		directedGraphRepresentation = new AdjacancyListRepresentation(directedGraph).createRepresentation();
		expectedRepresentatinResultForDirectedGraph = GraphTestUtil.loadResult(DIRECTED_GRAPH_REPRESENTATION_FILE_NAME);
	}

	@Test
	public void test_adjacancy_representation_of_graph_with_no_edge() throws Exception {
		Set<Node> expectedNeighbors = new HashSet<>();
		graphWithNoEdge.getNodes()
				.forEach(node -> assertEquals(noEdgeGraphRepresentation.getNeighbors(node), expectedNeighbors));
	}

	@Test
	public void test_adjacancy_representation_of_graph_with_no_node_no_edge() throws Exception {
		Set<Node> neighbors = new HashSet<>();
		graphWithNoNodeAndNoEdge.getNodes()
				.forEach(node -> assertEquals(noNodeAndNoEdgeGraphRepresentation.getNeighbors(node), neighbors));
	}

	@Test
	public void test_adjacancy_representation_of_undirected_graph() throws Exception {
		unidirectedGraph.getNodes().forEach(node -> assertEquals(undirectedGraphRepresentation.getNeighbors(node),
				expectedRepresentatinResultForUndirectedGraph.get(node)));
	}

	@Test
	public void test_adjacancy_representation_of_directed_graph() throws Exception {
		directedGraph.getNodes().forEach(node -> assertEquals(directedGraphRepresentation.getNeighbors(node),
				expectedRepresentatinResultForDirectedGraph.get(node)));
	}

}
