package com.tongweisu.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongfu.util.DateUtil;
import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.entity.TWS_Article;
import com.tongweisu.entity.TWS_Category;
import com.tongweisu.service.AdminService;
import com.tongweisu.service.ArticleService;
import com.tongweisu.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;

	@Autowired
	private CategoryService categoryService;
	/**
	 * 添加分类
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model ) {
		List list = categoryService.getCategorys(null);
		model.addAttribute("list", JSON.toJSON(list).toString());
		List<TWS_Category> categoryTree = categoryService.getAll(null);
		model.addAttribute("categoryTree", categoryTree);
		return "admin/category/add";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model ) {
		List list = categoryService.getCategorys(null);
		System.out.println(JSON.toJSON(list).toString());
		List<TWS_Category> categoryTree = categoryService.getAll(null);
		model.addAttribute("list", JSON.toJSON(list).toString());
		model.addAttribute("categoryTree", categoryTree);
		model.addAttribute("lists", list);
		return "admin/category/list";
	}


}
