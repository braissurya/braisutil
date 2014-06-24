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

import com.melawai.ppuc.services.DivisiManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.web.validator.DivisiValidator;

@RequestMapping("/master/divisi")
@Controller
public class DivisiController extends ParentController{

	protected static Logger logger = Logger.getLogger(DivisiController.class);

	@Autowired
	private DivisiManager divisiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new DivisiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Divisi divisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, divisi);
			return "divisi/create";
		}
		uiModel.asMap().clear();
		divisiManager.save(divisi);
		return "redirect:/master/divisi/" + encodeUrlPathSegment(divisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Divisi());
		return "divisi/create";
	}

	@RequestMapping(value = "/{divisi_kd}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("divisi", divisiManager.get(divisi_kd));
		uiModel.addAttribute("itemId", divisi_kd);
		return "divisi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("divisiList",divisiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) divisiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "divisi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Divisi divisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, divisi);
			return "divisi/update";
		}
		uiModel.asMap().clear();
		divisiManager.save(divisi);
		return "redirect:/master/divisi/" + encodeUrlPathSegment(divisi.getDivisi_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, Model uiModel) {
		populateEditForm(uiModel, divisiManager.get(divisi_kd));
		return "divisi/update";
	}

	@RequestMapping(value = "/{divisi_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Divisi divisi = divisiManager.get(divisi_kd);
		divisiManager.remove(divisi_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/divisi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("divisi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("divisi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Divisi divisi) {
		uiModel.addAttribute("divisi", divisi);
		addDateTimeFormatPatterns(uiModel);
	}
}
