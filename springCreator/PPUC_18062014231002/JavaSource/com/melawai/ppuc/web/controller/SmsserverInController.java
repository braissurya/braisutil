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

import com.melawai.ppuc.services.SmsserverInManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.SmsserverIn;
import com.melawai.ppuc.web.validator.SmsserverInValidator;

@RequestMapping("/master/smsserverin")
@Controller
public class SmsserverInController extends ParentController{

	protected static Logger logger = Logger.getLogger(SmsserverInController.class);

	@Autowired
	private SmsserverInManager smsserverinManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SmsserverInValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid SmsserverIn smsserverin, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsserverin);
			return "smsserverin/create";
		}
		uiModel.asMap().clear();
		smsserverinManager.save(smsserverin);
		return "redirect:/master/smsserverin/" + encodeUrlPathSegment(smsserverin.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new SmsserverIn());
		return "smsserverin/create";
	}

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("smsserverin", smsserverinManager.get(id));
		uiModel.addAttribute("itemId", id);
		return "smsserverin/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("smsserverinList",smsserverinManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) smsserverinManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "smsserverin/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid SmsserverIn smsserverin, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsserverin);
			return "smsserverin/update";
		}
		uiModel.asMap().clear();
		smsserverinManager.save(smsserverin);
		return "redirect:/master/smsserverin/" + encodeUrlPathSegment(smsserverin.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		populateEditForm(uiModel, smsserverinManager.get(id));
		return "smsserverin/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		SmsserverIn smsserverin = smsserverinManager.get(id);
		smsserverinManager.remove(id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/smsserverin";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("smsserverin_message_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("smsserverin_receive_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("smsserverin_original_receive_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("smsserverin_process_date_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, SmsserverIn smsserverin) {
		uiModel.addAttribute("smsserverin", smsserverin);
		addDateTimeFormatPatterns(uiModel);
	}
}
