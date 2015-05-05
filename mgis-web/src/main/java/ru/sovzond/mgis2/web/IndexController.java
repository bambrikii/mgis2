package ru.sovzond.mgis2.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class IndexController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView indexPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@RequestMapping(value = { "/mgis" }, method = RequestMethod.GET)
	public ModelAndView mgisPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("mgis");
		return model;
	}

}
