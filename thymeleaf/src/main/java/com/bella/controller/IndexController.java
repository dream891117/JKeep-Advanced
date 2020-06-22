package com.bella.controller;

import com.bella.vo.RestVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/6/19-17:34
 @Author Genie
 @Description:
 */
@RestController
public class IndexController {

	@GetMapping("/")
	public RestVo index(){
		return RestVo.SUCCESS("This is thymeleaf!");
	}

}
