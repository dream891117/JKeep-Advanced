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
	 * - 开启 Nacos 自动更新
	 */
	@NacosValue(value = "${name}", autoRefreshed = true)
	private String name;

	/**
	 * 宠物性别
	 * - 开启 Nacos 自动更新
	 */
	@NacosValue(value = "${sex}", autoRefreshed = true)
	private String sex;

	/**
	 * 宠物年龄
	 * - 关闭 Nacos 自动更新
	 * autoRefreshed = false 时 , nacos 更新时此变量不做任何改变 (除非重启重新更新)
	 */
	@NacosValue("${age}")
	private String age;

	/**
	 * 喜好
	 * - 本地 config 配置 love 属性
	 * - 开启 Nacos 自动刷新
	 *
	 * 注意 :
	 * 1. 项目启动时首先优【先加载本地属性】
	 * 2. 但是如果 nacos 修改了此属性 , 仍旧会【自动更新】
	 *
	 * 建议 : 要么本地配置 , 要么Nacos配置。 只保留一个配置。 除非 autoRefreshed = false .
	 *
	 */
	@NacosValue(value = "${love}",autoRefreshed = true)
	private String love;

	@GetMapping("/getRemoteConfig")
	public RestVo get() {
		return RestVo.SUCCESS(String.format("我家有只宠物叫 : %s , 性别 : %s , 年龄 : %s , 喜好 : %s",name,sex,age,love));
	}

}
