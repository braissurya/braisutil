package com.melawai.ppuc.web.controller;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

/**
* Abstract ParentController sebagai parent dari semua controller
* Cuman untuk meletakkan reference data saja dan beberapa variable
*/
public abstract class ParentController {

	protected static Logger logger = Logger.getLogger(ParentController.class);

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {}
		return pathSegment;
	}
}
