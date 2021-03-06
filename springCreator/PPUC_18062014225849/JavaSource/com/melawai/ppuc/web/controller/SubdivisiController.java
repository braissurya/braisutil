package com.melawai.ppuc.web.controller;

import java.io.UnsupportedEncodingException;

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

import com.melawai.ppuc.services.SubdivisiManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.web.validator.SubdivisiValidator;

@RequestMapping("/master/subdivisi")
@Controller
public class SubdivisiController extends ParentController{

	protected static Logger logger = Logger.getLogger(SubdivisiController.class);

	@Autowired
	private SubdivisiManager subdivisiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SubdivisiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Subdivisi subdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, subdivisi);
			return "subdivisi/create";
		}
		uiModel.asMap().clear();
		subdivisiManager.save(subdivisi);
		return "redirect:/master/subdivisi/" + encodeUrlPathSegment(subdivisi.getSubdiv_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Subdivisi());
		return "subdivisi/create";
	}

	@RequestMapping(value = "/{subdiv_kd}", produces = "text/html")
	public String show(@PathVariable("subdiv_kd") String subdiv_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("subdivisi", subdivisiManager.get(subdiv_kd));
		uiModel.addAttribute("itemId", subdiv_kd);
		return "subdivisi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("subdivisiList",subdivisiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) subdivisiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "subdivisi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Subdivisi subdivisi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, subdivisi);
			return "subdivisi/update";
		}
		uiModel.asMap().clear();
		subdivisiManager.save(subdivisi);
		return "redirect:/master/subdivisi/" + encodeUrlPathSegment(subdivisi.getSubdiv_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{subdiv_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("subdiv_kd") String subdiv_kd, Model uiModel) {
		populateEditForm(uiModel, subdivisiManager.get(subdiv_kd));
		return "subdivisi/update";
	}

	@RequestMapping(value = "/{subdiv_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("subdiv_kd") String subdiv_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Subdivisi subdivisi = subdivisiManager.get(subdiv_kd);
		subdivisiManager.remove(subdiv_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/subdivisi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("subdivisi_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("subdivisi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, Subdivisi subdivisi) {
		uiModel.addAttribute("subdivisi", subdivisi);
		addDateTimeFormatPatterns(uiModel);
	}
}
