package graph.shortestpathalgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graph.model.BaseGraphWithValidator;
import graph.model.Node;

public class FloydWarshall extends BaseGraphWithValidator implements ShortestPathAlgorithm {

	private Map<Integer, Node> indexToNodeMapper;
	private Map<Node, Integer> nodeToIndexMapper;
	private double[][] distanceMatrix;
	private int[][] pathMatrix;

	private void prepareMapper() {
		indexToNodeMapper = new HashMap<>();
		nodeToIndexMapper = new HashMap<>();
		int id = 0;
		for (Node node : graph.getNodes()) {
			indexToNodeMapper.put(id, node);
			nodeToIndexMapper.put(node, id++);
		}
	}

	private void initializeMatrix() {
		int size = graph.getNodes().size();
		distanceMatrix = new double[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (i == j) {
					distanceMatrix[i][j] = 0;
					pathMatrix[i][j] = i;
				} else {
					distanceMatrix[i][j] = Double.MAX_VALUE;
					pathMatrix[i][j] = -1;
				}
			}
		}

		graph.getEdges().forEach(edge -> {
			final Node source = edge.getSource();
			final Node destination = edge.getDestination();
			final int si = nodeToIndexMapper.get(source);
			final int di = nodeToIndexMapper.get(destination);
			distanceMatrix[si][di] = edge.getEdgeWeight();
			pathMatrix[si][di] = si;
		});
	}

	private void initialize() {
		prepareMapper();
		initializeMatrix();
	}

	private void computeShortestPath(int k) {
		for (int i = 0; i < distanceMatrix.length; ++i) {
			for (int j = 0; j < distanceMatrix[i].length; ++j) {
				double newDistance = distanceMatrix[i][k] + distanceMatrix[k][j];
				if (newDistance < distanceMatrix[i][j]) {
					pathMatrix[i][j] = k;
					distanceMatrix[i][j] = newDistance;
				}
			}
		}
	}

	@Override()
	public void findShortestPath() {
		if (validator.isValid(this)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		initialize();
		for (int k = 0; k < distanceMatrix.length; ++k) {
			computeShortestPath(k);
		}
	}

	public List<Node> getPath(Node source, Node destination) {
		StringBuilder errorMessage = new StringBuilder();
		boolean isSourceNodeValid = validator.isValidNode(graph, source);
		errorMessage.append(validator.getErrorMessage());
		boolean isDestinationNodeValid = validator.isValidNode(graph, destination);
		errorMessage.append(validator.getErrorMessage());
		if (!(isSourceNodeValid && isDestinationNodeValid)) {
			throw new IllegalArgumentException(errorMessage.toString());
		}

		List<Node> path = new LinkedList<>();
		int si = nodeToIndexMapper.get(source);
		int di = nodeToIndexMapper.get(destination);

		if (pathMatrix[si][di] == -1) {
			return path;
		}

		do {
			path.add(0, indexToNodeMapper.get(di));
			di = pathMatrix[si][di];
		} while (si != di);

		return path;
	}

}
