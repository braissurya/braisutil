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

import com.melawai.ppuc.services.GroupUserManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.GroupUser;
import com.melawai.ppuc.web.validator.GroupUserValidator;

@RequestMapping("/master/groupuser")
@Controller
public class GroupUserController extends ParentController{

	protected static Logger logger = Logger.getLogger(GroupUserController.class);

	@Autowired
	private GroupUserManager groupuserManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new GroupUserValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid GroupUser groupuser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupuser);
			return "groupuser/create";
		}
		uiModel.asMap().clear();
		groupuserManager.save(groupuser);
		return "redirect:/master/groupuser/" + encodeUrlPathSegment(groupuser.getGroup_kd().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new GroupUser());
		return "groupuser/create";
	}

	@RequestMapping(value = "/{group_kd}", produces = "text/html")
	public String show(@PathVariable("group_kd") String group_kd, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("groupuser", groupuserManager.get(group_kd));
		uiModel.addAttribute("itemId", group_kd);
		return "groupuser/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("groupuserList",groupuserManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) groupuserManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "groupuser/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid GroupUser groupuser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, groupuser);
			return "groupuser/update";
		}
		uiModel.asMap().clear();
		groupuserManager.save(groupuser);
		return "redirect:/master/groupuser/" + encodeUrlPathSegment(groupuser.getGroup_kd().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{group_kd}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("group_kd") String group_kd, Model uiModel) {
		populateEditForm(uiModel, groupuserManager.get(group_kd));
		return "groupuser/update";
	}

	@RequestMapping(value = "/{group_kd}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("group_kd") String group_kd, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		GroupUser groupuser = groupuserManager.get(group_kd);
		groupuserManager.remove(group_kd);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/groupuser";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("groupuser_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, GroupUser groupuser) {
		uiModel.addAttribute("groupuser", groupuser);
		addDateTimeFormatPatterns(uiModel);
	}
}
