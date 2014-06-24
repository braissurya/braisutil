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

import com.melawai.ppuc.services.LogSmsManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.LogSms;
import com.melawai.ppuc.web.validator.LogSmsValidator;

@RequestMapping("/master/logsms")
@Controller
public class LogSmsController extends ParentController{

	protected static Logger logger = Logger.getLogger(LogSmsController.class);

	@Autowired
	private LogSmsManager logsmsManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LogSmsValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid LogSms logsms, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, logsms);
			return "logsms/create";
		}
		uiModel.asMap().clear();
		logsmsManager.save(logsms);
		return "redirect:/master/logsms/" + encodeUrlPathSegment(logsms.getId_log_sms().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new LogSms());
		return "logsms/create";
	}

	@RequestMapping(value = "/{id_log_sms}", produces = "text/html")
	public String show(@PathVariable("id_log_sms") Long id_log_sms, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("logsms", logsmsManager.get(id_log_sms));
		uiModel.addAttribute("itemId", id_log_sms);
		return "logsms/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("logsmsList",logsmsManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) logsmsManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "logsms/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid LogSms logsms, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, logsms);
			return "logsms/update";
		}
		uiModel.asMap().clear();
		logsmsManager.save(logsms);
		return "redirect:/master/logsms/" + encodeUrlPathSegment(logsms.getId_log_sms().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_log_sms}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id_log_sms") Long id_log_sms, Model uiModel) {
		populateEditForm(uiModel, logsmsManager.get(id_log_sms));
		return "logsms/update";
	}

	@RequestMapping(value = "/{id_log_sms}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_log_sms") Long id_log_sms, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		LogSms logsms = logsmsManager.get(id_log_sms);
		logsmsManager.remove(id_log_sms);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/logsms";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("logsms_sys_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("logsms_sys_send_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("logsms_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, LogSms logsms) {
		uiModel.addAttribute("logsms", logsms);
		addDateTimeFormatPatterns(uiModel);
	}
}
