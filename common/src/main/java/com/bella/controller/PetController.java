package com.bella.controller;

import java.util.List;
import com.bella.controller.vo.PetVo;
import com.bella.vo.RestVo;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/6/17-15:10
 @Author Genie
 @Description: 宠物 - Controller
 */
@RequestMapping("/api/pet")
@RestController
public class PetController {

	/**
	 * 获取宠物列表
	 *
	 * @return
	 */
	@GetMapping("/list")
	public RestVo<List<PetVo>> info(){
		return RestVo.SUCCESS(Lists.list(new PetVo().setName("Bella").setSex("女").setAge("3岁"),new PetVo().setName("Yomi").setSex("女").setAge("1岁")),"我的宠物列表");
	}

}
