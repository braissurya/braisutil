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

import com.melawai.ppuc.services.MFungsiManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.MFungsi;
import com.melawai.ppuc.web.validator.MFungsiValidator;

@RequestMapping("/master/mfungsi")
@Controller
public class MFungsiController extends ParentController{

	protected static Logger logger = Logger.getLogger(MFungsiController.class);

	@Autowired
	private MFungsiManager mfungsiManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new MFungsiValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid MFungsi mfungsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, mfungsi);
			return "mfungsi/create";
		}
		uiModel.asMap().clear();
		mfungsiManager.save(mfungsi);
		return "redirect:/master/mfungsi/" + encodeUrlPathSegment(mfungsi.getKd_fungsi().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new MFungsi());
		return "mfungsi/create";
	}

	@RequestMapping(value = "/{kd_fungsi}", produces = "text/html")
	public String show(@PathVariable("kd_fungsi") String kd_fungsi, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("mfungsi", mfungsiManager.get(kd_fungsi));
		uiModel.addAttribute("itemId", kd_fungsi);
		return "mfungsi/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("mfungsiList",mfungsiManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) mfungsiManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "mfungsi/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid MFungsi mfungsi, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, mfungsi);
			return "mfungsi/update";
		}
		uiModel.asMap().clear();
		mfungsiManager.save(mfungsi);
		return "redirect:/master/mfungsi/" + encodeUrlPathSegment(mfungsi.getKd_fungsi().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{kd_fungsi}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("kd_fungsi") String kd_fungsi, Model uiModel) {
		populateEditForm(uiModel, mfungsiManager.get(kd_fungsi));
		return "mfungsi/update";
	}

	@RequestMapping(value = "/{kd_fungsi}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("kd_fungsi") String kd_fungsi, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		MFungsi mfungsi = mfungsiManager.get(kd_fungsi);
		mfungsiManager.remove(kd_fungsi);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/mfungsi";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("mfungsi_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, MFungsi mfungsi) {
		uiModel.addAttribute("mfungsi", mfungsi);
		addDateTimeFormatPatterns(uiModel);
	}
}
