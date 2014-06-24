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

import com.melawai.ppuc.services.PpucDManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.web.validator.PpucDValidator;

@RequestMapping("/master/ppucd")
@Controller
public class PpucDController extends ParentController{

	protected static Logger logger = Logger.getLogger(PpucDController.class);

	@Autowired
	private PpucDManager ppucdManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PpucDValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid PpucD ppucd, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppucd);
			return "ppucd/create";
		}
		uiModel.asMap().clear();
		ppucdManager.save(ppucd);
		return "redirect:/master/ppucd/" + encodeUrlPathSegment(ppucd.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getTgl_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new PpucD());
		return "ppucd/create";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("ppucd", ppucdManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya));
		uiModel.addAttribute("itemId", divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc+"/"+kd_biaya);
		return "ppucd/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("ppucdList",ppucdManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) ppucdManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "ppucd/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid PpucD ppucd, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, ppucd);
			return "ppucd/update";
		}
		uiModel.asMap().clear();
		ppucdManager.save(ppucd);
		return "redirect:/master/ppucd/" + encodeUrlPathSegment(ppucd.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getLok_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getNo_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getTgl_ppuc().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(ppucd.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, ppucdManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya));
		return "ppucd/update";
	}

	@RequestMapping(value = "/{divisi_kd}/{subdiv_kd}/{dept_kd}/{lok_kd}/{no_ppuc}/{tgl_ppuc}/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("lok_kd") String lok_kd, @PathVariable("no_ppuc") String no_ppuc, @PathVariable("tgl_ppuc") Date tgl_ppuc, @PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		PpucD ppucd = ppucdManager.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya);
		ppucdManager.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, kd_biaya);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/ppucd";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("ppucd_tgl_ppuc_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("ppucd_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, PpucD ppucd) {
		uiModel.addAttribute("ppucd", ppucd);
		addDateTimeFormatPatterns(uiModel);
	}
}
