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

import com.melawai.ppuc.services.SmsserverOutManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.SmsserverOut;
import com.melawai.ppuc.web.validator.SmsserverOutValidator;

@RequestMapping("/master/smsserverout")
@Controller
public class SmsserverOutController extends ParentController{

	protected static Logger logger = Logger.getLogger(SmsserverOutController.class);

	@Autowired
	private SmsserverOutManager smsserveroutManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SmsserverOutValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid SmsserverOut smsserverout, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsserverout);
			return "smsserverout/create";
		}
		uiModel.asMap().clear();
		smsserveroutManager.save(smsserverout);
		return "redirect:/master/smsserverout/" + encodeUrlPathSegment(smsserverout.getId().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new SmsserverOut());
		return "smsserverout/create";
	}

	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("smsserverout", smsserveroutManager.get(id));
		uiModel.addAttribute("itemId", id);
		return "smsserverout/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("smsserveroutList",smsserveroutManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) smsserveroutManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "smsserverout/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid SmsserverOut smsserverout, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, smsserverout);
			return "smsserverout/update";
		}
		uiModel.asMap().clear();
		smsserveroutManager.save(smsserverout);
		return "redirect:/master/smsserverout/" + encodeUrlPathSegment(smsserverout.getId().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		populateEditForm(uiModel, smsserveroutManager.get(id));
		return "smsserverout/update";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		SmsserverOut smsserverout = smsserveroutManager.get(id);
		smsserveroutManager.remove(id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/smsserverout";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("smsserverout_wap_expiry_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("smsserverout_create_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("smsserverout_sent_date_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, SmsserverOut smsserverout) {
		uiModel.addAttribute("smsserverout", smsserverout);
		addDateTimeFormatPatterns(uiModel);
	}
}
