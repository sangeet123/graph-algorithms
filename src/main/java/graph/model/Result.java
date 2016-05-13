package graph.model;

public class Result {
	private Node parentNode;
	private Double priority;
	private final Node sourceNode;

	public Result(final Node sourceNode) {
		this.sourceNode = sourceNode;
	}

	public Result(final Node sourceNode, Double priority) {
		this.sourceNode = sourceNode;
		this.priority = priority;
	}

	public void setPriority(final double priority) {
		this.priority = priority;
	}

	public Double getPriority() {
		return this.priority;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(final Node parentNode) {
		this.parentNode = parentNode;
	}

	public final Node getSource() {
		return this.sourceNode;
	}
}