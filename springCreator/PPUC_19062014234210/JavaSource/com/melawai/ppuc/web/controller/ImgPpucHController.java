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

import com.melawai.ppuc.services.ImgPpucHManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.ImgPpucH;
import com.melawai.ppuc.web.validator.ImgPpucHValidator;

@RequestMapping("/master/imgppuch")
@Controller
public class ImgPpucHController extends ParentController{

	protected static Logger logger = Logger.getLogger(ImgPpucHController.class);

	@Autowired
	private ImgPpucHManager imgppuchManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ImgPpucHValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid ImgPpucH imgppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, imgppuch);
			return "imgppuch/create";
		}
		uiModel.asMap().clear();
		imgppuchManager.save(imgppuch);
		return "redirect:/master/imgppuch/" + encodeUrlPathSegment(imgppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getTgl_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getNo_realisasi().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new ImgPpucH());
		return "imgppuch/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{no_realisasi}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("no_realisasi") String no_realisasi, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("imgppuch", imgppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc+"/"+no_realisasi);
		return "imgppuch/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("imgppuchList",imgppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) imgppuchManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "imgppuch/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid ImgPpucH imgppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, imgppuch);
			return "imgppuch/update";
		}
		uiModel.asMap().clear();
		imgppuchManager.save(imgppuch);
		return "redirect:/master/imgppuch/" + encodeUrlPathSegment(imgppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getTgl_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(imgppuch.getNo_realisasi().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{no_realisasi}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("no_realisasi") String no_realisasi, Model uiModel) {
		populateEditForm(uiModel, imgppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi));
		return "imgppuch/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{no_realisasi}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("no_realisasi") String no_realisasi, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		ImgPpucH imgppuch = imgppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
		imgppuchManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/imgppuch";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("imgppuch_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("imgppuch_tgl_realisasi_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("imgppuch_tgl_entry_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("imgppuch_sys_tgl_confirm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("imgppuch_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, ImgPpucH imgppuch) {
		uiModel.addAttribute("imgppuch", imgppuch);
		addDateTimeFormatPatterns(uiModel);
	}
}
