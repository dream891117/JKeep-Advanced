package com.bella.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 @Date: 2020/6/11-10:19
 @Author Genie
 @Description:
 */
@Controller
public class ThymeleafController {

	/**
	 * 跳转页
	 * 不需要添加后缀 .html
	 */
	final String INDEX_PAGE = "/index";

	/**
	 * 跳转的页的根目录 /resources/templates
	 *
	 * 跳转页定义 /目录/页面名 eg. /th/test
	 *
	 *  如果没有目录, 直接 /页面名
	 */
	final String TH_TEST_PAGE = "/th/test";

	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("name","Thymeleaf官网");
		model.addAttribute("website","https://www.thymeleaf.org/");
		return INDEX_PAGE;
	}

	@GetMapping("/test")
	public String test(Model model){
		Map<String,String> map = new HashMap<>(16);
		map.put("200000","上海市");
		map.put("100000","北京市");
		map.put("510000","广州市");
		model.addAttribute("areaMap",map);
		model.addAttribute("remoteImagePath","https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png");
		model.addAttribute("localImagePath","/images/Bella2.jpg");
		return TH_TEST_PAGE;
	}

}
