package com.melawai.ppuc.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.melawai.ppuc.model.Divisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: $date.long 
 * @Description	: Validator for table Divisi
 * @Revision	:
 */
public class DivisiValidator implements Validator {

	@Override
	public boolean supports(Class cls) {
		return Divisi.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	}

}

