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

import com.melawai.ppuc.services.HakApproveManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.web.validator.HakApproveValidator;

@RequestMapping("/master/hakapprove")
@Controller
public class HakApproveController extends ParentController{

	protected static Logger logger = Logger.getLogger(HakApproveController.class);

	@Autowired
	private HakApproveManager hakapproveManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new HakApproveValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid HakApprove hakapprove, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakapprove);
			return "hakapprove/create";
		}
		uiModel.asMap().clear();
		hakapproveManager.save(hakapprove);
		return "redirect:/master/hakapprove/" + encodeUrlPathSegment(hakapprove.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new HakApprove());
		return "hakapprove/create";
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", produces = "text/html")
	public String show(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("hakapprove", hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya));
		uiModel.addAttribute("itemId", user_id+"/"+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+kd_group+"/"+kd_biaya);
		return "hakapprove/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("hakapproveList",hakapproveManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) hakapproveManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "hakapprove/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid HakApprove hakapprove, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, hakapprove);
			return "hakapprove/update";
		}
		uiModel.asMap().clear();
		hakapproveManager.save(hakapprove);
		return "redirect:/master/hakapprove/" + encodeUrlPathSegment(hakapprove.getUser_id().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDivisi_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getSubdiv_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getDept_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_group().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(hakapprove.getKd_biaya().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, Model uiModel) {
		populateEditForm(uiModel, hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya));
		return "hakapprove/update";
	}

	@RequestMapping(value = "/{user_id}/{divisi_kd}/{subdiv_kd}/{dept_kd}/{kd_group}/{kd_biaya}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("user_id") String user_id, @PathVariable("divisi_kd") String divisi_kd, @PathVariable("subdiv_kd") String subdiv_kd, @PathVariable("dept_kd") String dept_kd, @PathVariable("kd_group") String kd_group, @PathVariable("kd_biaya") String kd_biaya, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		HakApprove hakapprove = hakapproveManager.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		hakapproveManager.remove(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/hakapprove";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("hakapprove_drtgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_sptgl_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("hakapprove_sys_tgl_nonaktif_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, HakApprove hakapprove) {
		uiModel.addAttribute("hakapprove", hakapprove);
		addDateTimeFormatPatterns(uiModel);
	}
}
