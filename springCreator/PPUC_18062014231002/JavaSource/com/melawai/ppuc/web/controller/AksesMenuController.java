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

import com.melawai.ppuc.services.AksesMenuManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.AksesMenu;
import com.melawai.ppuc.web.validator.AksesMenuValidator;

@RequestMapping("/master/aksesmenu")
@Controller
public class AksesMenuController extends ParentController{

	protected static Logger logger = Logger.getLogger(AksesMenuController.class);

	@Autowired
	private AksesMenuManager aksesmenuManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new AksesMenuValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid AksesMenu aksesmenu, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, aksesmenu);
			return "aksesmenu/create";
		}
		uiModel.asMap().clear();
		aksesmenuManager.save(aksesmenu);
		return "redirect:/master/aksesmenu/" + encodeUrlPathSegment(aksesmenu.getGroup_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(aksesmenu.getMenu_id().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new AksesMenu());
		return "aksesmenu/create";
	}

	@RequestMapping(value = "/{group_kd}/{menu_id}", produces = "text/html")
	public String show(@PathVariable("group_kd") String group_kd, @PathVariable("menu_id") Long menu_id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("aksesmenu", aksesmenuManager.get(group_kd, menu_id));
		uiModel.addAttribute("itemId", group_kd+"/"+menu_id);
		return "aksesmenu/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("aksesmenuList",aksesmenuManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) aksesmenuManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "aksesmenu/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid AksesMenu aksesmenu, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, aksesmenu);
			return "aksesmenu/update";
		}
		uiModel.asMap().clear();
		aksesmenuManager.save(aksesmenu);
		return "redirect:/master/aksesmenu/" + encodeUrlPathSegment(aksesmenu.getGroup_kd().toString(), httpServletRequest)+"/" + encodeUrlPathSegment(aksesmenu.getMenu_id().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{group_kd}/{menu_id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("group_kd") String group_kd, @PathVariable("menu_id") Long menu_id, Model uiModel) {
		populateEditForm(uiModel, aksesmenuManager.get(group_kd, menu_id));
		return "aksesmenu/update";
	}

	@RequestMapping(value = "/{group_kd}/{menu_id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("group_kd") String group_kd, @PathVariable("menu_id") Long menu_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		AksesMenu aksesmenu = aksesmenuManager.get(group_kd, menu_id);
		aksesmenuManager.remove(group_kd, menu_id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/aksesmenu";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("aksesmenu_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, AksesMenu aksesmenu) {
		uiModel.addAttribute("aksesmenu", aksesmenu);
		addDateTimeFormatPatterns(uiModel);
	}
}
