package graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.miscelleneous.GraphCycleTester;
import graph.model.Graph;
import graph.util.GraphTestUtil;

public class DirectedGraphCycleTest {

	private static final String GRAPH_WITH_CYCLE_FILE_NAME = "DirectedGraphWithCycle";
	private static final String GRAPH_WITHOUT_CYCLE_FILE_NAME = "DirectedGraphWithoutCycle";

	private static Graph graphWithCycle;
	private static Graph graphWithoutCycle;

	/*
	 * note that union find algorithm cannot be used to detect the cycle for
	 * directed graph. Take for example 1 ->2 and 1->3 and 3->2. Now sequences
	 * of union operations merges 1 and 2 and 3 into same set while first two
	 * edges are encountered. And when the 3->2 edge is encountered it would see
	 * that both 2 and 3 are in same set and returns true which is not correct.
	 */

	@BeforeClass()
	public static void load_all_test_graph_representation() throws Exception {
		graphWithCycle = GraphTestUtil.loadGraph(GRAPH_WITH_CYCLE_FILE_NAME);
		graphWithoutCycle = GraphTestUtil.loadGraph(GRAPH_WITHOUT_CYCLE_FILE_NAME);
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
