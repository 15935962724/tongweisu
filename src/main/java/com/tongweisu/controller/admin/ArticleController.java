package com.tongweisu.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tongfu.util.DateUtil;
import com.tongfu.util.FileUtils;
import com.tongfu.util.HtmlSpiritUtil;
import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.entity.TWS_Article;
import com.tongweisu.service.AdminService;
import com.tongweisu.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/article")
public class ArticleController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;

	@Autowired
	private ArticleService articleService;

	/**
	 * 文章列表
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize,
					   String title, String startDate, String endDate,Long type ) {
		Map<String,Object> map = new HashMap<>(	);
		map.put("title",title);
		map.put("type",type);
		if (startDate!=null&&!startDate.equals("")){
			Date start_Date = DateUtil.getStringtoDate( startDate+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			map.put("startDate",start_Date);
		}
		if (endDate!=null&&!endDate.equals("")){
			Date end_Date = DateUtil.getStringtoDate( endDate+" 23:59:59","yyyy-MM-dd HH:mm:ss");
			map.put("endDate",end_Date);
		}
		Page<TWS_Article> page  = PageHelper.startPage(pageNum,pageSize);
		List<TWS_Article> all = articleService.getAll(map);
		PageInfo<TWS_Article> pageInfo = new PageInfo<TWS_Article>();
		pageInfo.setTotal(page.getTotal());
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(page.getPages());
		pageInfo.setList(all);
		model.addAttribute("page",pageInfo);
		model.addAttribute("title",title);
		model.addAttribute("type",type);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "admin/article/list";
	}


	/**
	 * 添加新闻
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model ,Long articleCategory) {
		model.addAttribute("articleCategory",articleCategory);
		return "admin/article/add";
	}

	@RequestMapping("/edit")
	public String edit(Model model,Long id) {
//		Collection<Role> roles = roleService.getAll(null);
		TWS_Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article",article);
		return "admin/article/edit";
	}

	/**
	 * 保存文章
	 * @param model
	 * @param article
	 * @param file
	 * @return
	 */
	@PostMapping("/save")
	public String save(Model model,TWS_Article article ) {
		TWS_Admin admin = adminService.getCurrent();
		article.setAuthor(admin.getName());
		article.setCreateDate(new Date());
		article.setModifyDate(new Date());
		article.setHits(0l);
		article.setIsDeleted(false);
		Integer conunt = articleService.insertSelective(article);
		logger.info("添加结果:"+conunt);
		return "redirect:list";
	}

	@PostMapping("/update")
	public String update(Model model,TWS_Article article) {
//		String articleLogo = FileUtils.upload(file,article_logo);
//		if (articleLogo!=null){
//			article.setImage(articleLogo);
//		}
		Integer conunt = articleService.updateByPrimaryKeySelective(article);
		logger.info("结果:"+conunt);
		return "redirect:list";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(@RequestParam Long id) {
		Integer deleteByPrimaryKey = articleService.deleteByPrimaryKey(id);
		return deleteByPrimaryKey>0;
	}

	@RequestMapping(value = "/deleteIds", method = RequestMethod.GET)
	@ResponseBody
	public boolean deletes(@RequestParam Long[] id) {
		Integer deleteByPrimaryKey = articleService.deleteByPrimaryKey(id);
		return deleteByPrimaryKey>0;

	}



}
