package graph.stronglyconnectedcomponents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.enums.NodeState;
import graph.graphrepresentation.AdjacancyListRepresentation;
import graph.graphrepresentation.GraphRepresentation;
import graph.model.BaseGraphRepresentationWithValidation;
import graph.model.Graph;
import graph.model.Node;
import graph.utils.GraphUtil;

public class StronglyConnectedComponents extends BaseGraphRepresentationWithValidation {
	private List<NodeWithStartAndFinishCounter> nodesList = new ArrayList<>();
	private static int timer = 0;

	public StronglyConnectedComponents(Graph graph, GraphRepresentation graphRepresentation) {
		this.graph = graph;
		this.graphRepresentation = graphRepresentation;
	}

	private class NodeWithStartAndFinishCounter {

		private int countStartedAt;

		private int countEndedAt;

		private Node node;

		public void setCountStartedAt(int countStartedAt) {
			this.countStartedAt = countStartedAt;
		}

		public int getCountEndedAt() {
			return countEndedAt;
		}

		public void setCountEndedAt(int countEndedAt) {
			this.countEndedAt = countEndedAt;
		}

		public Node getNode() {
			return node;
		}

		public void setNode(Node node) {
			this.node = node;
		}

		@Override()
		public String toString() {
			return "NodeWithStartAndFinishCounter [countStartedAt=" + countStartedAt + ", countEndedAt=" + countEndedAt
					+ ", node=" + node + "]";
		}

	}

	private void traverse(final Node node, final Map<Node, NodeState> nodeStateTracker) {

		// Create and record the start time.
		++timer;
		NodeWithStartAndFinishCounter nodeWithStartAndFinishCounter = new NodeWithStartAndFinishCounter();
		nodeWithStartAndFinishCounter.setCountStartedAt(timer);
		nodeWithStartAndFinishCounter.setNode(node);

		nodeStateTracker.put(node, NodeState.GREY);
		Set<Node> neighbors = graphRepresentation.getNeighbors(node);

		for (Node neighbor : neighbors) {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				traverse(neighbor, nodeStateTracker);
			}
		}

		// Record the end time
		++timer;
		nodeWithStartAndFinishCounter.setCountEndedAt(timer);
		nodesList.add(nodeWithStartAndFinishCounter);
		nodeStateTracker.put(node, NodeState.BLACK);
	}

	private void traverse() {
		Set<Node> nodes = graph.getNodes();
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(nodes);
		nodes.forEach(node -> {
			if (nodeStateTracker.get(node) == NodeState.WHITE) {
				traverse(node, nodeStateTracker);
			}
		});

	}

	private void addNodeThatAreStronglyConnected(final Node node, final Map<Node, NodeState> nodeStateTracker,
			GraphRepresentation transposeGraphRepresentation, Set<Node> stronglyConnectedNodes) {

		stronglyConnectedNodes.add(node);
		nodeStateTracker.put(node, NodeState.GREY);
		Set<Node> neighbors = transposeGraphRepresentation.getNeighbors(node);

		for (Node neighbor : neighbors) {
			if (nodeStateTracker.get(neighbor) == NodeState.WHITE) {
				addNodeThatAreStronglyConnected(neighbor, nodeStateTracker, transposeGraphRepresentation,
						stronglyConnectedNodes);
			}
		}
		nodeStateTracker.put(node, NodeState.BLACK);

	}

	public List<Set<Node>> getStronglyConnectedComponents() {
		if (!validator.isValidGraphRepresentation(graph, graphRepresentation)) {
			throw new IllegalStateException(validator.getErrorMessage());
		}
		traverse();
		GraphRepresentation transposeOfGraphRepresentation = new AdjacancyListRepresentation(this.graph).transpose();
		nodesList.sort((node1, node2) -> Integer.compare(node2.getCountEndedAt(), node1.getCountEndedAt()));
		Set<Node> nodes = graph.getNodes();
		Map<Node, NodeState> nodeStateTracker = GraphUtil.getNodeStateTracker(nodes);
		List<Set<Node>> stronglyConnectedList = new ArrayList<>();
		nodesList.forEach(nodeInList -> {
			if (nodeStateTracker.get(nodeInList.getNode()) == NodeState.WHITE) {
				Set<Node> setToBeAdded = new HashSet<>();
				addNodeThatAreStronglyConnected(nodeInList.getNode(), nodeStateTracker, transposeOfGraphRepresentation,
						setToBeAdded);
				stronglyConnectedList.add(setToBeAdded);
			}
		});

		return stronglyConnectedList;
	}
}
