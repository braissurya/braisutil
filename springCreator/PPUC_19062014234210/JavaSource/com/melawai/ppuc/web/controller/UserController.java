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

import com.melawai.ppuc.services.UserManager;
import com.melawai.ppuc.web.controller.ParentController;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.web.validator.UserValidator;

@RequestMapping("/master/user")
@Controller
public class UserController extends ParentController{

	protected static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "user/create";
		}
		uiModel.asMap().clear();
		userManager.saveUserLogin(user);
		return "redirect:/master/user/" + encodeUrlPathSegment(user.getUser_id().toString(), httpServletRequest);
	}

	@RequestMapping(params = "form", produces = "text/html")
	public String createForm(Model uiModel) {
		populateEditForm(uiModel, new User());
		return "user/create";
	}

	@RequestMapping(value = "/{user_id}", produces = "text/html")
	public String show(@PathVariable("user_id") String user_id, Model uiModel) {
		addDateTimeFormatPatterns(uiModel);
		uiModel.addAttribute("user", userManager.get(user_id));
		uiModel.addAttribute("itemId", user_id);
		return "user/show";
	}

	@RequestMapping(produces = "text/html")
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
		if (page == null) {
			page=1;
		}

			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("userList",userManager.selectPagingList(search,sortFieldName,sortOrder, firstResult, sizeNo) );
			float nrOfPages = (float) userManager.selectPagingCount(search) / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		addDateTimeFormatPatterns(uiModel);
		return "user/list";
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
	public String update(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, user);
			return "user/update";
		}
		uiModel.asMap().clear();
		userManager.saveUserLogin(user);
		return "redirect:/master/user/" + encodeUrlPathSegment(user.getUser_id().toString(), httpServletRequest);
	}

	@RequestMapping(value = "/{user_id}", params = "form", produces = "text/html")
	public String updateForm(@PathVariable("user_id") String user_id, Model uiModel) {
		populateEditForm(uiModel, userManager.get(user_id));
		return "user/update";
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE, produces = "text/html")
	public String delete(@PathVariable("user_id") String user_id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		User user = userManager.get(user_id);
		userManager.remove(user_id);
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/master/user";
	}
	void addDateTimeFormatPatterns(Model uiModel) {
		uiModel.addAttribute("user_sys_tgl_create_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("user_sys_tgl_update_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
		uiModel.addAttribute("user_sys_tgl_nonaktif_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
	}
	void populateEditForm(Model uiModel, User user) {
		uiModel.addAttribute("user", user);
		addDateTimeFormatPatterns(uiModel);
	}
}
