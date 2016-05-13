package graph.model;

import graph.graphvalidator.GraphValidator;

public abstract class BaseGraphWithValidator extends BaseGraph {
	protected GraphValidator validator;

	public GraphValidator getGraphValidatro() {
		return validator;
	}

	public void setGraphValidatro(final GraphValidator validator) {
		this.validator = validator;
	}

}
