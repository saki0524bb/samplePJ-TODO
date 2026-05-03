package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	// home pageのHTMLを返す
	@RequestMapping(value = "/")
	public ModelAndView top(ModelAndView mv) {
		mv.setViewName("homepage");
		return mv;
	}
}
   

    	

       