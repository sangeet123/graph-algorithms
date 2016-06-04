package unionfind;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.model.GraphNode;
import graph.model.Node;

public class UnionFindTest {

	// not exactly what the definition of unit test is
	// however trying to simulate the sequences of steps
	@Test()
	public void test_for_union_find() throws Exception {
		Node a = new GraphNode("a");
		Node b = new GraphNode("b");
		Node c = new GraphNode("c");
		Node d = new GraphNode("d");
		Node e = new GraphNode("f");

		UnionFind<Node> uf = new UnionFind<>();
		uf.add(a);
		uf.add(b);
		uf.add(c);
		uf.add(d);
		uf.add(e);

		// test all node are in their own set
		assertEquals(a, uf.find(a));
		assertEquals(b, uf.find(b));
		assertEquals(c, uf.find(c));
		assertEquals(d, uf.find(d));
		assertEquals(e, uf.find(e));

		// merge a and b and test
		uf.union(a, b);
		assertEquals(a, uf.find(a));
		assertEquals(a, uf.find(b));

		// merge b and c
		uf.union(b, c);
		assertEquals(a, uf.find(a));
		assertEquals(a, uf.find(b));
		assertEquals(a, uf.find(c));

		// merge d and e
		uf.union(d, e);
		assertEquals(d, uf.find(d));
		assertEquals(d, uf.find(e));

		// merge e and c
		uf.union(e, c);
		assertEquals(a, uf.find(a));
		assertEquals(a, uf.find(b));
		assertEquals(a, uf.find(c));
		assertEquals(a, uf.find(d));
		assertEquals(a, uf.find(e));


	}

}
