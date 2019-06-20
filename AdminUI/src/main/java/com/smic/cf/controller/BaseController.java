package com.smic.cf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tgls/base")
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@GetMapping("/toBaseAdd")
	public String add() {
		logger.info("进入表单含编辑器！");
		return "tgls/base/base_add";
	}

	@GetMapping("/toBaseList")
	public String list() {
		logger.info("进入常用列表！");
		return "tgls/base/base_list";
	}

	@GetMapping("/toBaseCustomerList")
	public String customerList() {
		logger.info("进入可新增的列表！");
		return "tgls/base/base_customList";
	}

	@GetMapping("/toBaseCustomerNewList")
	public String customerNewList() {
		logger.info("进入可新增的列表(手写)！");
		return "tgls/base/base_customNewList";
	}

	@GetMapping("/toPages")
	public String pages() {
		logger.info("进入页签切换Table！");
		return "tgls/base/pages";
	}

	@GetMapping("/toMaintain")
	public String maintainPage() {
		logger.info("页面维护中！");
		return "tgls/base/maintain";
	}

}
