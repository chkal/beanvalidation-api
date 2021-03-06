/*
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package javax.validation.metadata;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintTarget;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.groups.Default;

/**
 * Describes a single constraint and its composing constraints.
 * <p>
 * {@code T} is the constraint's annotation type.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public interface ConstraintDescriptor<T extends Annotation> {

	/**
	 * Returns the annotation describing the constraint declaration.
	 * If a composing constraint, attribute values are reflecting
	 * the overridden attributes of the composing constraint
	 *
	 * @return the annotation for this constraint
	 */
	T getAnnotation();

	/**
	 * The non-interpolated error message
	 *
	 * @return the non-interpolated error message
	 *
	 * @since 1.1
	 */
	String getMessageTemplate();

	/**
	 * The set of groups the constraint is applied on.
	 * If the constraint declares no group, a set with only the {@link Default}
	 * group is returned.
	 *
	 * @return the groups the constraint is applied on
	 */
	Set<Class<?>> getGroups();

	/**
	 * The set of payload the constraint hosts.
	 *
	 * @return payload classes hosted on the constraint or an empty set if none
	 */
	Set<Class<? extends Payload>> getPayload();

	/**
	 * The {@link ConstraintTarget} value of {@code validationAppliesTo} if the constraint
	 * hosts it or {@code null} otherwise.
	 *
	 * @return the {@code ConstraintTarget} value or {@code null}
	 *
	 * @since 1.1
	 */
	ConstraintTarget getValidationAppliesTo();

	/**
	 * List of the constraint validation implementation classes.
	 *
	 * @return list of the constraint validation implementation classes
	 */
	List<Class<? extends ConstraintValidator<T, ?>>> getConstraintValidatorClasses();

	/**
	 * Returns a map containing the annotation attribute names as keys and the
	 * annotation attribute values as value.
	 * <p>
	 * If this constraint is used as part of a composed constraint, attribute
	 * values are reflecting the overridden attribute of the composing constraint.
	 *
	 * @return a map containing the annotation attribute names as keys
	 *         and the annotation attribute values as value
	 */
	Map<String, Object> getAttributes();

	/**
	 * Return a set of composing {@link ConstraintDescriptor}s where each
	 * descriptor describes a composing constraint. {@code ConstraintDescriptor}
	 * instances of composing constraints reflect overridden attribute values in
	 * {@link #getAttributes()}  and {@link #getAnnotation()}.
	 *
	 * @return a set of {@code ConstraintDescriptor} objects or an empty set
	 *         in case there are no composing constraints
	 */
	Set<ConstraintDescriptor<?>> getComposingConstraints();

	/**
	 * @return {@code true} if the constraint is annotated with {@link ReportAsSingleViolation}
	 */
	boolean isReportAsSingleViolation();
}
