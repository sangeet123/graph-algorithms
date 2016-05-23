package graph.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graph.model.Edge;
import graph.model.Graph;
import graph.model.GraphEdge;
import graph.model.GraphNode;
import graph.model.Node;

public class GraphTestUtil {

	private static class GraphProperties {
		public int noOfNodes;
		public int noOfEdges;
		public boolean hasEdgeWeight;
	}

	public static Map<Node, Set<Node>> loadResult(final String fileName) throws IOException {
		Map<Node, Set<Node>> neighborList = new HashMap<>();
		File file = new File(resourceToString(fileName));
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		try {
			while ((line = buffer.readLine()) != null) {
				line = line.trim();
				String[] nodeNeighbors = line.split(":");
				if (nodeNeighbors.length == 2) {
					neighborList.put(new GraphNode(nodeNeighbors[0]), getNeighborsList(nodeNeighbors[1]));
				} else {
					neighborList.put(new GraphNode(nodeNeighbors[0]), new HashSet<Node>());
				}
			}
		} finally {
			buffer.close();
		}
		return neighborList;
	}

	private static Set<Node> getNeighborsList(final String commaSeparatedNeighbors) {
		String[] neighbors = commaSeparatedNeighbors.split(",");
		Set<Node> neighborsSet = new HashSet<>();

		for (int i = 0; i < neighbors.length; ++i) {
			neighborsSet.add(new GraphNode(neighbors[i]));
		}
		return neighborsSet;
	}

	public static Graph loadGraph(final String fileName) throws IOException {
		Set<Node> nodes = new HashSet<>();
		Set<Edge> edges = new HashSet<>();
		GraphProperties properties = null;
		boolean headerInfoFound = false;
		boolean isReadingNode = false;
		boolean isReadingEdge = false;
		int noOfNodesRead = 0;
		int noOfEdgesRead = 0;
		File file = new File(resourceToString(fileName));
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line;
		try {
			while ((line = buffer.readLine()) != null) {
				line = line.trim();
				if (!isLineAComment(line) && !isEmptyLine(line)) {
					if (!headerInfoFound && isLineHeader(line)) {
						properties = getGraphProperties(line);
						headerInfoFound = true;
						isReadingNode = true;
					} else if (isReadingNode && noOfNodesRead < properties.noOfNodes) {
						nodes.add(readNode(line));
						++noOfNodesRead;
						if (noOfNodesRead == properties.noOfNodes) {
							isReadingNode = false;
							isReadingEdge = true;
						}
					} else if (isReadingEdge && noOfNodesRead < properties.noOfEdges) {
						edges.add(readEdge(line, properties.hasEdgeWeight));
						++noOfEdgesRead;
						if (noOfEdgesRead == properties.noOfEdges) {
							isReadingEdge = false;
						}
					}
				}
			}
		} finally

		{
			buffer.close();
		}
		Graph graph = new Graph();
		graph.setNodes(nodes);
		graph.setEdges(edges);
		return graph;
	}

	private static boolean isEmptyLine(final String line) {
		return line.trim().equals("");
	}

	private static Edge readEdge(final String line, boolean hasEdgeWeight) {
		String[] edgeCredentials = line.split(" ");

		Node source = new GraphNode(edgeCredentials[0]);
		Node destination = new GraphNode(edgeCredentials[1]);
		Edge edge = new GraphEdge(source, destination);

		if (hasEdgeWeight) {
			((GraphEdge) edge).setWeight(Double.parseDouble(edgeCredentials[2]));
		}

		return edge;
	}

	private static Node readNode(final String line) {
		return new GraphNode(line);
	}

	private static boolean isLineAComment(final String line) {
		return line.trim().startsWith("#");
	}

	public static GraphProperties getGraphProperties(final String line) {
		GraphProperties property = new GraphProperties();
		String[] keyVal = line.split(" ");

		for (int i = 0; i < keyVal.length; ++i) {
			String lowerCase = keyVal[i].toLowerCase();
			if (lowerCase.contains("nodecount")) {
				property.noOfNodes = Integer.parseInt(getValueFromKeyVal(lowerCase));
			} else if (lowerCase.contains("edgecount")) {
				property.noOfEdges = Integer.parseInt(getValueFromKeyVal(lowerCase));
			} else if (lowerCase.contains("edgehasweight")) {
				String val = getValueFromKeyVal(lowerCase);
				if (val.equals("true")) {
					property.hasEdgeWeight = true;
				} else if (val.equals("false")) {
					property.hasEdgeWeight = false;
				} else {
					throw new RuntimeException("only true or false are values allowed for <edgehasweight>");
				}
			} else {
				throw new RuntimeException(
						"Only keywords <nodecount> <edgecount> and <edgehasweight> allowed on header");
			}
		}

		return property;
	}

	private static String getValueFromKeyVal(final String keyVal) {
		int indexOfEquals = keyVal.indexOf("=");

		if (indexOfEquals == -1) {
			throw new RuntimeException(
					"keywords such as <nodecount> <edgecount> and <edgehasweight> must be assigned to val");
		}

		return keyVal.substring(indexOfEquals + 1);
	}

	private static boolean isLineHeader(final String line) {
		String lowerCaseLine = line.toLowerCase();
		boolean hasKeyWordNode = lowerCaseLine.contains("nodecount");
		boolean hasKeyWordEdge = lowerCaseLine.contains("edgecount");
		boolean hasKeyWordEdgeHasWeight = lowerCaseLine.contains("edgehasweight");

		if (hasKeyWordEdge && hasKeyWordNode && hasKeyWordEdgeHasWeight) {
			return true;
		}

		if (!(hasKeyWordEdge || hasKeyWordNode || hasKeyWordEdgeHasWeight)) {
			return false;
		}

		throw new RuntimeException(
				"Corrupt header information. Header must have keywords <nodecount> <edgecount> and <edgehasweight>");
	}

	public static String resourceToString(String fileName) {
		return Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
	}

}
