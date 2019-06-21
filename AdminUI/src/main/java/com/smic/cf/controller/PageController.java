package com.smic.cf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {

	@GetMapping("/frame")
	public String index() {
		log.info("进入主页面");
		return "frame";
	}

	@GetMapping("/toFrame")
	public String toIndex() {
		log.info("进入主页面");
		return "frame";
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
