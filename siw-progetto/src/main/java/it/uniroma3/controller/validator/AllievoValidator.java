package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Allievo;

@Component
public class AllievoValidator implements Validator {

	   @Override
	    public void validate(Object o, Errors errors) {
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberphone", "required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthplace", "required");
	    }

	    @Override
	    public boolean supports(Class<?> aClass) {
	        return Allievo.class.equals(aClass);
	    }	
}
