package graph.model;

import graph.graphrepresentation.GraphRepresentation;

public abstract class BaseGraphRepresentation extends BaseGraph {
	protected GraphRepresentation graphRepresentation;

	public GraphRepresentation getGraphRepresentation() {
		return graphRepresentation;
	}

	public void setGraphRepresentation(final GraphRepresentation graphRepresentation) {
		this.graphRepresentation = graphRepresentation;
	}

}
