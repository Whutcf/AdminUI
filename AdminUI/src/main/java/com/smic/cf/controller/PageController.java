package com.smic.cf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
/**
 * 
 * @ClassName PageController
 * @Description 页面跳转Controller 
 * @author cai feng
 * @date 2019年6月22日
 *
 */
public class PageController {

	@GetMapping("/frame")
	public String index() {
		log.info("进入主页面");
		return "frame";
	}
	
	@GetMapping("/")
	public String index1() {
		log.info("进入主页面");
		return "frame";
	}

	@GetMapping("/toFrame")
	public String toIndex() {
		log.info("进入主页面");
		return "frame";
	}
	
	@GetMapping("/toLogin")
	public String toLogin() {
		log.info("跳转登录页面!");
		return "login1";
	}
	
	@RequestMapping("tgls/toModifyPassword")
	public String toModifyPassword() {
		log.info("进入修改密码页面！");
		return "tgls/modify_password";
	}

	@GetMapping("tgls/toQdAPI")
	public String toQdAPI() {
		log.info("进入前端API，内有js用法提示内容");
		return "tgls/qdAPI";
	}

	@GetMapping("tgls/toIconfont")
	public String toIconfont() {
		log.info("进入添加分管页面");
		return "tgls/iconfont";
	}

	@GetMapping("tgls/agent/toAgent_add")
	public String toAgentAdd() {
		log.info("进入添加分管页面");
		return "tgls/agent/agent_add";
	}

	@GetMapping("tgls/agent/toAgent_list")
	public String toAgentList() {
		log.info("进入分管列表页面");
		return "tgls/agent/agent_list";
	}
}
