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

import com.melawai.ppuc.services.HakBiayaManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.web.validator.HakBiayaValidator;

@RequestMapping("/master/hakbiaya")
@Controller
public class HakBiayaController extends ParentController{

	protected static Logger logger = Logger.getLogger(HakBiayaController.class);

	@Autowired
	private HakBiayaManager hakbiayaManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new HakBiayaValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/create";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new HakBiaya());
		return "hakbiaya/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{kd_group}/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("hakbiaya", hakbiayaManager.get(divisi_kd, subdiv_kd, kd_group, kd_biaya));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+kd_group+"/"+kd_biaya);
		return "hakbiaya/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("hakbiayaList",hakbiayaManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) hakbiayaManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "hakbiaya/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid HakBiaya hakbiaya, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakbiaya);
			return "hakbiaya/update";
		}
		uiModel.asMap().clear();
		hakbiayaManager.save(hakbiaya);
		return "redirect:/master/hakbiaya/" + encodeUrlPathSegment(hakbiaya.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakbiaya.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{kd_group}/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, hakbiayaManager.get(divisi_kd, subdiv_kd, kd_group, kd_biaya));
		return "hakbiaya/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{kd_group}/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakBiaya hakbiaya = hakbiayaManager.get(divisi_kd, subdiv_kd, kd_group, kd_biaya);
		hakbiayaManager.remove(divisi_kd, subdiv_kd, kd_group, kd_biaya);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakbiaya";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakbiaya_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakbiaya_sys_tgl_nonaktif_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, HakBiaya hakbiaya) {
		uiModel.addAttribute("hakbiaya", hakbiaya);
		addDateTimeFormatPatterns(uiModel);
	}
}
