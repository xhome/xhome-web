package org.xhome.web.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xhome.common.constant.Status;
import org.xhome.common.util.RandomUtils;
import org.xhome.web.response.CommonResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @project xhome-web
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201311:02:00 PM
 * @description 
 */
public class ResponseUtils {
	
	public static void responseJSON(HttpServletResponse response, short status, String message, Object data) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(data);
		if (json != null && json.startsWith("{")) {
			response.getWriter().print("{\"status\":" + status + ",\"message\":\"" + message + "\"," + json.substring(1));
		} else {
			response.getWriter().print("{\"status\":" + status + ",\"message\":\"" + message + "\",\"data\":" + json + "}");
		}
	}
	
	public static void responseJSON(HttpServletResponse response, String message, Object data) throws IOException {
		responseJSON(response, Status.SUCCESS, message, data);
	}
	
	public static void responseJSON(HttpServletResponse response, short status, Object data) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(data);
		if (json != null && json.startsWith("{")) {
			response.getWriter().print("{\"status\":" + status + "," + json.substring(1));
		} else {
			response.getWriter().print("{\"status\":" + status + ",\"data\":" + json + "}");
		}
	}
	
	public static void responseJSON(HttpServletResponse response, short status, String message) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print("{\"status\":" + status + ",\"message\":\"" + message + "\"}");
	}
	
	public static void responseJSON(HttpServletResponse response, CommonResult data) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(data);
		response.getWriter().print(json);
	}
	
	public static void responseJSON(HttpServletResponse response, Object data) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(data);
		response.getWriter().print(json);
	}
	
	/**
	 * 使浏览器返回上一页
	 * @param response
	 */
	public static void goBack(HttpServletResponse response) {
		goHistory(response, -1);
	}
	
	/**
	 * 使浏览器返回历史页面
	 * @param response
	 * @param history 负数后退，正数前进
	 */
	public static void goHistory(HttpServletResponse response, int history) {
		PrintWriter out = null;
		try {
			response.setContentType("text/javascript");
			out = response.getWriter();
			out.println("<script language='javascript'>history.go(" + history
					+ ");</script>");
		} catch (Exception e) {} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {}
		}
	}
	
	/**
	 * 相应随机验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static String responseAuthCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		//设置页面不缓存
		response.setHeader("Pragma","No-cahe");
		response.setHeader("Cache-Control", "no-Cahe");
		response.setDateHeader("Expires", 0);
		response.setBufferSize(0);
		//创建图像
		int width=60,height=20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取图形上下文
		Graphics g=image.getGraphics();
		//生成随机类
		Random random=new Random();
		//设定背景色
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		//设定字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		//画边框
		//g.setColor(new Color());
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String Rands = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(RandomUtils.randomString(1));
			Rands += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return Rands;
	}
	

	private static Color getRandColor(int fc,int bc) {
		Random random =new Random();
		if(fc>255)
			fc=255;
		if(bc>255)
			bc=255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
