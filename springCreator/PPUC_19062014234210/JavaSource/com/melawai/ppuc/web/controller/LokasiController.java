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

import com.melawai.ppuc.services.LokasiManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.web.validator.LokasiValidator;

@RequestMapping("/master/lokasi")
@Controller
public class LokasiController extends ParentController{

	protected static Logger logger = Logger.getLogger(LokasiController.class);

	@Autowired
	private LokasiManager lokasiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new LokasiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Lokasi lokasi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, lokasi);
			return "lokasi/create";
		}
		uiModel.asMap().clear();
		lokasiManager.save(lokasi);
		return "redirect:/master/lokasi/" + encodeUrlPathSegment(lokasi.getLok_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Lokasi());
		return "lokasi/create";
	}

	@RequestMapping(value = "/{lok_kd}", produces = "text/html")
	public String show(@PathVariable("lok_kd") String lok_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("lokasi", lokasiManager.get(lok_kd));
		uiModel.addAttribute("itemId", lok_kd);
		return "lokasi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("lokasiList",lokasiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) lokasiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "lokasi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Lokasi lokasi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, lokasi);
			return "lokasi/update";
		}
		uiModel.asMap().clear();
		lokasiManager.save(lokasi);
		return "redirect:/master/lokasi/" + encodeUrlPathSegment(lokasi.getLok_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{lok_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("lok_kd") String lok_kd, Model uiModel) {
		populateEditForm(uiModel, lokasiManager.get(lok_kd));
		return "lokasi/update";
	}

	@RequestMapping(value = "/{lok_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("lok_kd") String lok_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Lokasi lokasi = lokasiManager.get(lok_kd);
		lokasiManager.remove(lok_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/lokasi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("lokasi_tgl_tutup_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("lokasi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("lokasi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Lokasi lokasi) {
		uiModel.addAttribute("lokasi", lokasi);
		addDateTimeFormatPatterns(uiModel);
	}
}
