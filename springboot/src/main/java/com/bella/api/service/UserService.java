package com.bella.api.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @Date: 2020/6/9-16:45
 @Author Genie
 @Description:
 */
@RestController
public class UserService {

	@GetMapping("/user/info")
	public Map<String,String> info(){
		Map<String,String> map = new HashMap<>(16);
		map.put("respCode","200");
		map.put("respMsg","获取用户信息成功");
		return map;
	}

}
