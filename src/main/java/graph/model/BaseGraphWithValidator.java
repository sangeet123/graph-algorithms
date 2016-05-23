package graph.model;

import graph.graphvalidator.GraphValidator;
import graph.graphvalidator.GraphValidatorImpl;

public abstract class BaseGraphWithValidator extends BaseGraph {
	/*
	 * By default GraphValidatorImpl is used
	 */
	protected GraphValidator validator = new GraphValidatorImpl();

	public GraphValidator getGraphValidator() {
		return validator;
	}

	public void setGraphValidator(final GraphValidator validator) {
		this.validator = validator;
	}

}
