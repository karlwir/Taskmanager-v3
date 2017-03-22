package se.kawi.taskmanager.resource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import se.kawi.taskmanager.model.Team;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidTeamNew.Validator.class)
public @interface ValidTeamNew {
	
    String message() default "Invalid user";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
	public class Validator implements ConstraintValidator<ValidTeamNew, Team> {

		@Override
		public void initialize(ValidTeamNew constraintAnnotation) {}

		@Override
		public boolean isValid(Team team, ConstraintValidatorContext context) {
			return team != null &&
				   team.getTeamName() != null;
		}
    }
}
