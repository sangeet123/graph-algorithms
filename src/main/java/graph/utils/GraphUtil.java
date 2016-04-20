package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUtil {

	public static Map<Node, Map<Node, Double>> createAdjacancyList(Graph graph) {

		List<Edge> edges = graph.getEdges();
		Map<Node, Map<Node, Double>> adjList = new HashMap<Node, Map<Node, Double>>();

		edges.forEach(edge -> {
			Node source = edge.getSource();
			Node destination = edge.getDestination();

			Map<Node, Double> link = adjList.get(source);

			if (link == null) {
				link = new HashMap<Node, Double>();
				adjList.put(source, link);
			}

			double weight = edge.getWeight();
			link.put(destination, weight);
		});
		return null;
	}

}
