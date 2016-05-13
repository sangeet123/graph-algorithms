package graph.model;

import graph.graphvalidator.GraphValidator;
import graph.graphvalidator.GraphValidatorImpl;

public abstract class BaseGraphRepresentationWithValidation extends BaseGraphRepresentation {
	/*
	 * By default GraphValidatorImpl is used
	 */
	protected GraphValidator validator = new GraphValidatorImpl();

	public GraphValidator getValidator() {
		return validator;
	}

	public void setValidator(final GraphValidator validator) {
		this.validator = validator;
	}

}
