package com.bella.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.bella.vo.RestVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/6/23-23:56
 @Author Genie
 @Description:
 */
@RestController
@RequestMapping("/nacos")
public class NacosConfigController {

	/**
	 * 宠物名称
	 */
	@NacosValue(value = "${name}", autoRefreshed = true)
	private String name;

	/**
	 * 宠物性别
	 */
	@NacosValue(value = "${sex}", autoRefreshed = true)
	private String sex;

	/**
	 * 宠物年龄
	 * autoRefreshed = false 时 , nacos 更新时此变量不做任何改变 (除非重启重新更新)
	 */
	@NacosValue("${age}")
	private String age;

	@GetMapping("/getRemoteConfig")
	public RestVo get() {
		return RestVo.SUCCESS(String.format("我家有只宠物叫 : %s , 性别 : %s , 年龄 : %s",name,sex,age));
	}

}
