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

import com.melawai.ppuc.services.MessageTemplateManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.MessageTemplate;
import com.melawai.ppuc.web.validator.MessageTemplateValidator;

@RequestMapping("/master/messagetemplate")
@Controller
public class MessageTemplateController extends ParentController{

	protected static Logger logger = Logger.getLogger(MessageTemplateController.class);

	@Autowired
	private MessageTemplateManager messagetemplateManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new MessageTemplateValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid MessageTemplate messagetemplate, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, messagetemplate);
			return "messagetemplate/create";
		}
		uiModel.asMap().clear();
		messagetemplateManager.save(messagetemplate);
		return "redirect:/master/messagetemplate/" + encodeUrlPathSegment(messagetemplate.getId_template().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new MessageTemplate());
		return "messagetemplate/create";
	}

	@RequestMapping(value = "/{id_template}", produces = "text/html")
	public String show(@PathVariable("id_template") Long id_template, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("messagetemplate", messagetemplateManager.get(id_template));
		uiModel.addAttribute("itemId", id_template);
		return "messagetemplate/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("messagetemplateList",messagetemplateManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) messagetemplateManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "messagetemplate/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid MessageTemplate messagetemplate, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, messagetemplate);
			return "messagetemplate/update";
		}
		uiModel.asMap().clear();
		messagetemplateManager.save(messagetemplate);
		return "redirect:/master/messagetemplate/" + encodeUrlPathSegment(messagetemplate.getId_template().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_template}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id_template") Long id_template, Model uiModel) {
		populateEditForm(uiModel, messagetemplateManager.get(id_template));
		return "messagetemplate/update";
	}

	@RequestMapping(value = "/{id_template}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_template") Long id_template, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		MessageTemplate messagetemplate = messagetemplateManager.get(id_template);
		messagetemplateManager.remove(id_template);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/messagetemplate";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("messagetemplate_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, MessageTemplate messagetemplate) {
		uiModel.addAttribute("messagetemplate", messagetemplate);
		addDateTimeFormatPatterns(uiModel);
	}
}
