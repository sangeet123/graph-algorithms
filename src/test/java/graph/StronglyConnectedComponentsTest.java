package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.Graph;
import graph.model.GraphNode;
import graph.model.Node;
import graph.stronglyconnectedcomponents.StronglyConnectedComponents;
import graph.util.GraphTestUtil;

public class StronglyConnectedComponentsTest {

	private static final String TEST_GRAPH_FILE_NAME = "StronglyConnectedComponentsTestData";

	private static Graph testGraph;

	private static GraphRepresentation testGraphRepresentation;

	private static StronglyConnectedComponents scc;

	private static List<Set<Node>> sccSet;

	@BeforeClass()
	public static void load_test_data() throws Exception {
		testGraph = GraphTestUtil.loadGraph(TEST_GRAPH_FILE_NAME);
		testGraphRepresentation = new AdjacancyListRepresentation(testGraph).createRepresentation();
		scc = new StronglyConnectedComponents(testGraph, testGraphRepresentation);
		sccSet = scc.getStronglyConnectedComponents();
	}

	@Test()
	public void verify_size_of_scc_is_4() throws Exception {
		assertEquals(sccSet.size(), 4);
	}

	@Test()
	public void verify_strongly_connected_components_for_nodes_1_2_3_4_5() throws Exception {

		Node node1 = new GraphNode("1");
		Node node2 = new GraphNode("2");
		Node node3 = new GraphNode("3");
		Node node4 = new GraphNode("4");
		Node node5 = new GraphNode("5");

		Set<Node> set = new HashSet<>();
		set.add(node1);
		set.add(node2);
		set.add(node3);
		set.add(node4);
		set.add(node5);

		assertTrue(sccSet.contains(set));

	}

	@Test()
	public void verify_strongly_connected_components_for_nodes_7_8_9_10_11_12() throws Exception {

		Node node7 = new GraphNode("7");
		Node node8 = new GraphNode("8");
		Node node9 = new GraphNode("9");
		Node node10 = new GraphNode("10");
		Node node11 = new GraphNode("11");
		Node node12 = new GraphNode("12");

		Set<Node> set = new HashSet<>();
		set.add(node7);
		set.add(node8);
		set.add(node9);
		set.add(node10);
		set.add(node11);
		set.add(node12);

		assertTrue(sccSet.contains(set));

	}

	@Test()
	public void verify_strongly_connected_components_for_nodes_14_15() throws Exception {

		Node node14 = new GraphNode("14");
		Node node15 = new GraphNode("15");

		Set<Node> set = new HashSet<>();
		set.add(node14);
		set.add(node15);

		assertTrue(sccSet.contains(set));

	}

	@Test()
	public void verify_strongly_connected_components_for_nodes_17() throws Exception {
		Node node17 = new GraphNode("17");

		Set<Node> set = new HashSet<>();
		set.add(node17);

		assertTrue(sccSet.contains(set));

	}
}
