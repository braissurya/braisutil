package com.melawai.ppuc.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.melawai.ppuc.services.LogEmailManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.LogEmail;
import com.melawai.ppuc.web.validator.LogEmailValidator;

@RequestMapping("/master/logemail")
@Controller
public class LogEmailController extends ParentController{

	protected static Logger logger = Logger.getLogger(LogEmailController.class);

	@Autowired
	private LogEmailManager logemailManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LogEmailValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid LogEmail logemail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, logemail);
			return "logemail/create";
		}
		uiModel.asMap().clear();
		logemailManager.save(logemail);
		return "redirect:/master/logemail/" + encodeUrlPathSegment(logemail.getId_log_email().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new LogEmail());
		return "logemail/create";
	}

	@RequestMapping(value = "/{id_log_email}", produces = "text/html")
	public String show(@PathVariable("id_log_email") Long id_log_email, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("logemail", logemailManager.get(id_log_email));
		uiModel.addAttribute("itemId", id_log_email);
		return "logemail/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("logemailList",logemailManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) logemailManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "logemail/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid LogEmail logemail, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, logemail);
			return "logemail/update";
		}
		uiModel.asMap().clear();
		logemailManager.save(logemail);
		return "redirect:/master/logemail/" + encodeUrlPathSegment(logemail.getId_log_email().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_log_email}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id_log_email") Long id_log_email, Model uiModel) {
		populateEditForm(uiModel, logemailManager.get(id_log_email));
		return "logemail/update";
	}

	@RequestMapping(value = "/{id_log_email}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_log_email") Long id_log_email, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		LogEmail logemail = logemailManager.get(id_log_email);
		logemailManager.remove(id_log_email);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/logemail";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("logemail_sys_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("logemail_sys_send_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("logemail_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, LogEmail logemail) {
		uiModel.addAttribute("logemail", logemail);
		addDateTimeFormatPatterns(uiModel);
	}
}
