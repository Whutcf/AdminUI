package com.smic.cf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@GetMapping("/frame")
	public String index() {
		logger.info("进入主页面");
		return "frame";
	}
	
	
	@GetMapping("/toFrame")
	public String toIndex() {
		logger.info("进入主页面");
		return "frame";
	}
	
	@GetMapping("tgls/toQdAPI")
	public String toQdAPI() {
		logger.info("进入前端API，内有js用法提示内容");
		return "tgls/qdAPI";
	}
	
	@GetMapping("tgls/toIconfont")
	public String toIconfont() {
		logger.info("进入添加分管页面");
		return "tgls/iconfont";
	}

	@GetMapping("tgls/agent/toAgent_add")
	public String toAgentAdd() {
		logger.info("进入添加分管页面");
		return "tgls/agent/agent_add";
	}
	
	@GetMapping("tgls/agent/toAgent_list")
	public String toAgentList() {
		logger.info("进入分管列表页面");
		return "tgls/agent/agent_list";
	}
}
