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

import com.melawai.ppuc.services.PpucHManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.web.validator.PpucHValidator;

@RequestMapping("/master/ppuch")
@Controller
public class PpucHController extends ParentController{

	protected static Logger logger = Logger.getLogger(PpucHController.class);

	@Autowired
	private PpucHManager ppuchManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PpucHValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/create";
		}
		uiModel.asMap().clear();
		ppuchManager.save(ppuch);
		return "redirect:/master/ppuch/" + encodeUrlPathSegment(ppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getTgl_ppuc().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new PpucH());
		return "ppuch/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("ppuch", ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc);
		return "ppuch/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("ppuchList",ppuchManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) ppuchManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "ppuch/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid PpucH ppuch, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppuch);
			return "ppuch/update";
		}
		uiModel.asMap().clear();
		ppuchManager.save(ppuch);
		return "redirect:/master/ppuch/" + encodeUrlPathSegment(ppuch.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppuch.getTgl_ppuc().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, Model uiModel) {
		populateEditForm(uiModel, ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc));
		return "ppuch/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		PpucH ppuch = ppuchManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		ppuchManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/ppuch";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("ppuch_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_confirm_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_approve_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_realisasi_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_conf_real_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_conf_oc_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppuch_sys_tgl_batal_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, PpucH ppuch) {
		uiModel.addAttribute("ppuch", ppuch);
		addDateTimeFormatPatterns(uiModel);
	}
}
