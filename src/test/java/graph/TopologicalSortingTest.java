package graph;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.GraphNode;
import graph.model.Node;
import graph.topologicalsorting.TopologicalSorting;
import graph.util.GraphTestUtil;

public class TopologicalSortingTest {

	private static final String TEST_DATA_FILE = "TopologicalSortingTestData";

	private static Graph topologicalTestGraph;

	private static GraphRepresentation topologicalTestGraphRepresentation;

	private static TopologicalSorting tpSorting;

	private static List<Node> tpOrder;

	private static Node node1;

	private static Node node2;

	private static Node node3;

	private static Node node4;

	private static Node node5;

	private static Node node6;

	private static Node node7;

	private static Node node8;

	private static Node node9;

	private static Node node10;

	private static Node node11;

	private static Node node12;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		// directed graph data preparation
		topologicalTestGraph = GraphTestUtil.loadGraph(TEST_DATA_FILE);
		topologicalTestGraphRepresentation = new AdjacancyListRepresentation(topologicalTestGraph)
				.createRepresentation();
		tpSorting = new TopologicalSorting(topologicalTestGraph, topologicalTestGraphRepresentation);
		tpOrder = tpSorting.getTopologicalOrder();

		node1 = new GraphNode("1");
		node2 = new GraphNode("2");
		node3 = new GraphNode("3");
		node4 = new GraphNode("4");
		node5 = new GraphNode("5");
		node6 = new GraphNode("6");
		node7 = new GraphNode("7");
		node8 = new GraphNode("8");
		node9 = new GraphNode("9");
		node10 = new GraphNode("10");
		node11 = new GraphNode("11");
		node12 = new GraphNode("12");

	}

	@Test()
	public void node5_is_dependent_on_node4() throws Exception {
		assertTrue(tpOrder.indexOf(node4) < tpOrder.indexOf(node5));
	}

	@Test()
	public void node4_is_dependent_on_node2() throws Exception {
		assertTrue(tpOrder.indexOf(node2) < tpOrder.indexOf(node4));
	}

	@Test()
	public void node3_is_dependent_on_node4() throws Exception {
		assertTrue(tpOrder.indexOf(node3) < tpOrder.indexOf(node4));
	}

	@Test()
	public void node7_is_dependent_on_node6() throws Exception {
		assertTrue(tpOrder.indexOf(node6) < tpOrder.indexOf(node7));
	}

	@Test()
	public void node8_is_dependent_on_node7() throws Exception {
		assertTrue(tpOrder.indexOf(node7) < tpOrder.indexOf(node8));
	}

	@Test()
	public void node10_is_dependent_on_node8() throws Exception {
		assertTrue(tpOrder.indexOf(node8) < tpOrder.indexOf(node10));
	}

	@Test()
	public void node9_is_dependent_on_node7() throws Exception {
		assertTrue(tpOrder.indexOf(node7) < tpOrder.indexOf(node9));
	}

	@Test()
	public void node11_is_dependent_on_node9() throws Exception {
		assertTrue(tpOrder.indexOf(node9) < tpOrder.indexOf(node11));
	}

	@Test()
	public void node12_is_dependent_on_node9() throws Exception {
		assertTrue(tpOrder.indexOf(node9) < tpOrder.indexOf(node12));
	}

	@Test()
	public void node2_node3_and_node6_are_dependent_on_node1() throws Exception {
		assertTrue(tpOrder.indexOf(node1) < tpOrder.indexOf(node2) && tpOrder.indexOf(node1) < tpOrder.indexOf(node6)
				&& tpOrder.indexOf(node1) < tpOrder.indexOf(node3));
	}

}
