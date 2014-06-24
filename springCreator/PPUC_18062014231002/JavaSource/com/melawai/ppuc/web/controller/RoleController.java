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

import com.melawai.ppuc.services.RoleManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.Role;
import com.melawai.ppuc.web.validator.RoleValidator;

@RequestMapping("/master/role")
@Controller
public class RoleController extends ParentController{

	protected static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleManager roleManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new RoleValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, role);
			return "role/create";
		}
		uiModel.asMap().clear();
		roleManager.save(role);
		return "redirect:/master/role/" + encodeUrlPathSegment(role.getId_role().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new Role());
		return "role/create";
	}

	@RequestMapping(value = "/{id_role}", produces = "text/html")
	public String show(@PathVariable("id_role") Long id_role, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("role", roleManager.get(id_role));
		uiModel.addAttribute("itemId", id_role);
		return "role/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("roleList",roleManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) roleManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "role/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, role);
			return "role/update";
		}
		uiModel.asMap().clear();
		roleManager.save(role);
		return "redirect:/master/role/" + encodeUrlPathSegment(role.getId_role().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{id_role}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("id_role") Long id_role, Model uiModel) {
		populateEditForm(uiModel, roleManager.get(id_role));
		return "role/update";
	}

	@RequestMapping(value = "/{id_role}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("id_role") Long id_role, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Role role = roleManager.get(id_role);
		roleManager.remove(id_role);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/role";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
	}
	void populateEditForm(Model uiModel, Role role) {
		uiModel.addAttribute("role", role);
		addDateTimeFormatPatterns(uiModel);
	}
}
