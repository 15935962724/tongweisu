package com.tongweisu.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageCodeUtil {
	// 随机数
	private static char mapTable[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	// 图片验证码
	public final static Map<String, Object> imageCode(int width, int height,int len) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
//		if (width <= 0)
//			width = 60;
//		if (height <= 0)
//			height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.BOLD+Font.ITALIC+Font.PLAIN, 20));
		// 随机产生168条干扰线，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 168; i++) {
			g.setColor(getRandColor(150, 250));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的码
		String strEnsure = "";
		// 4代表4位验证码,如果要生成更多位的认证码,则加大数值
		for (int i = 0; i < len; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 直接生成
			String str = strEnsure.substring(i, i + 1);
			g.drawString(str, 15 * i + random.nextInt(5)+3, 20+random.nextInt(10)-1);
		}
		// 释放图形上下文
		g.dispose();
		returnMap.put("image", image);
		returnMap.put("strEnsure", strEnsure);
		return returnMap;
	}

	// 给定范围获得随机颜色
	static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * ##获取验证码
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getImagecode(HttpServletRequest request, HttpServletResponse response) {
		return (String) request.getSession().getAttribute("strEnsure");
	}

	/**
	 * ##验证验证码
	 *
	 * @param session
	 * @param imagecode
	 *            验证码
	 * @return
	 */
//	public static String checkImagecode(HttpSession session, String imagecode) {
//		Object cko = session.getAttribute("strEnsure"); // 验证码对象
//		if (cko == null) {
//			return Constants.CAPTCHA_IMAGECODE_CHECK_NO;
//		}
//		String captcha = cko.toString();
//		Date now = new Date();
//		Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
//		// 验证码的验证（不区分大小写）
//		if (StringUtils.isEmpty(imagecode) || captcha == null || !imagecode.toLowerCase().equalsIgnoreCase(captcha.toLowerCase())) {
//			return Constants.CAPTCHA_IMAGECODE_CHECK_FALSE;
//		} else if ((now.getTime() - codeTime) / 1000 / 60 > 5) {
//			// 验证码有效时长为5分钟
//			return Constants.CAPTCHA_IMAGECODE_CHECK_NO;
//		}
//		session.removeAttribute("simpleCaptcha");
//		return "ok";
//	}
}
