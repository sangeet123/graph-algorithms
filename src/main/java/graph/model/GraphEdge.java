package graph.model;

public class GraphEdge extends Edge {

	private Double weight;

	public GraphEdge(final Node source, final Node destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override()
	public double getEdgeWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
