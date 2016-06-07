package graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.miscelleneous.GraphCycleTester;
import graph.model.Graph;
import graph.util.GraphTestUtil;

public class UndirectedGraphCycleTest {

	private static final String GRAPH_WITH_CYCLE_FILE_NAME = "UnDirectedGraphWithCycle";
	private static final String GRAPH_WITHOUT_CYCLE_FILE_NAME = "UnDirectedGraphWithoutCycle";

	private static Graph graphWithCycle;
	private static Graph graphWithoutCycle;

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		graphWithCycle = GraphTestUtil.loadGraph(GRAPH_WITH_CYCLE_FILE_NAME);
		graphWithoutCycle = GraphTestUtil.loadGraph(GRAPH_WITHOUT_CYCLE_FILE_NAME);
	}

	@Test()
	public void assert_undirected_graph_has_cycle_using_union_find() throws Exception {
		assertTrue(GraphCycleTester.hasUndirectedGraphCycle(graphWithCycle));

	}

	@Test()
	public void assert_undirected_graph_does_not_have_cycle_using_union_find() throws Exception {
		assertFalse(GraphCycleTester.hasUndirectedGraphCycle(graphWithoutCycle));
	}

	@Test()
	public void assert_undirected_graph_has_cycle_using_dfs_variation() throws Exception {
		assertTrue(GraphCycleTester.hasGraphCycle(graphWithCycle));
	}

	@Test()
	public void assert_undirected_graph_does_not_have_cycle_using_dfs_variation() throws Exception {
		assertFalse(GraphCycleTester.hasGraphCycle(graphWithoutCycle));
	}

}
