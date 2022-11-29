package com.tongweisu.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongfu.util.ResultUtil;
import com.tongweisu.entity.TWS_Admin;
import com.tongweisu.service.AdminService;
import com.tongweisu.util.ImageCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AdminService adminService;


	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/submit")
	public String submit(@RequestParam("username")String username,
						 @RequestParam("password") String password,
						 @RequestParam("code")String code,Map map) throws IOException {
		try {
			String params = null;
			Subject subject = SecurityUtils.getSubject();
			String sessionCode = (String) subject.getSession().getAttribute("strEnsure");
			if (!code.toUpperCase().equals(sessionCode)){
				map.put("msg"	,"验证码输入有误");
				return  "admin/index";
			}
			Map query_map = new HashMap();
			query_map.put("username",username);
			TWS_Admin admin = adminService.getAdmin(query_map);
			if (admin==null){
				map.put("msg"	,"该用户不存在");
				return  "admin/index";
			}

			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
			//进行验证，这里可以捕获异常，然后返回对应信息
			subject.login(usernamePasswordToken);
			subject.getSession().setAttribute("loginUser",username);
			logger.info("登陆成功");
			String redirect_url = (String) subject.getSession().getAttribute("adminRedirectUrl");
			if (redirect_url!=null&&!redirect_url.equals("")){
				return "redirect:"+	redirect_url;
			}
			return "redirect:/admin/main";
		} catch (IncorrectCredentialsException e) {
			map.put("msg"	,"密码错误");
			return  "admin/index";
		} catch (LockedAccountException e) {
			logger.info("该用户已被冻结");
			map.put("msg"	,"该用户已被冻结");
			return  "admin/index";
		} catch (AuthenticationException e) {
			logger.info("该用户不存在");
			map.put("msg"	,"该用户不存在");
			return  "admin/index";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("未知错误");
			map.put("msg"	,"未知错误请联系管理员");
			return  "admin/index";
		}


	}



	/**
	 * 退出登陆
	 * @return
	 */
	@RequestMapping(value = "/lognout")
	public String lognout() {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().removeAttribute("adminRedirectUrl");
		subject.logout();
		return "admin/index";
	}

	/**
	 * 管理员登录页
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminLogin")
	public String admin(Model model) {
		logger.info("管理员登录页");
		return "admin/index";
	}




	/**
	 * 验证码
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCodeImage")
	public void getCodeImage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer IMG_HEIGHT = 40;
		Integer IMG_WIDTH = 75;
		Map<String, Object> imageCode = ImageCodeUtil.imageCode(IMG_WIDTH,IMG_HEIGHT,4);
		Subject subject = SecurityUtils.getSubject();
		System.out.println("验证码为："+imageCode.get("strEnsure"));
		subject.getSession().setAttribute("strEnsure",  imageCode.get("strEnsure"));
		subject.getSession().setAttribute("codeTime", new Date().getTime());
		ImageIO.write((RenderedImage) imageCode.get("image"), "JPG", resp.getOutputStream());
	}


}
