package com.lj.oms.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swetake.util.Qrcode;

/**
 * 
 * 
 * 类说明：二维码工具类
 *  
 * 
 * <p/>
 * 详细描述：
 *   
 * 
 * @author zengchuiyu
 *   
 * CreateDate: 2017-4-22
 */
public class QrcodeUtils {
	
	private static Logger logger = LoggerFactory.getLogger(QrcodeUtils.class);
		  
	/**
	 * 绘制二维码
	 * 
	 * @param contents
	 *            二维码内容
	 * @return image 二维码图片
	 * */
	public static BufferedImage encodeImg(String contents) {
		BufferedImage image = null;
		try {
			Qrcode qrcode = new Qrcode();
			/*** 表示的字符串长度： 容错率(ECC) 显示编码模式(EncodeMode)及版本(Version)有关 ***/
			/*
			 * 二维码的纠错级别(排错率)，共有四级：可选L(7%)、M(15%)、Q(25%)、H(30%)(最高H)。
			 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用信息就越少,对二维码清晰度的要求越小
			 */
			/**
	         * 纠错等级分为
	         * level L : 最大 7% 的错误能够被纠正；
	         * level M : 最大 15% 的错误能够被纠正；
	         * level Q : 最大 25% 的错误能够被纠正；
	         * level H : 最大 30% 的错误能够被纠正；
	         */
			qrcode.setQrcodeErrorCorrect('M');
			// 编码模式：Numeric 数字, Alphanumeric 英文字母,Binary 二进制,Kanji 汉字(第一个大写字母表示)
			qrcode.setQrcodeEncodeMode('B');
			/*
			 * 二维码的版本号：也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同。
			 * 1-40总共40个版本，版本1为21*21矩阵，版本每增1，二维码的两个边长都增4； 版本2
			 * 为25x25模块，最高版本为是40，是177*177的矩阵；
			 */
			qrcode.setQrcodeVersion(10);
			// 获取内容的字节数组，设置编码格式
			byte[] contentBytes = contents.getBytes("UTF-8");
			
			// 图片尺寸,会根据version的变大，而变大，自己需要计算
	        // API文档规定计算图片宽高的方式 ，v是本次测试的版本号
	        int v =qrcode.getQrcodeVersion();
	        int width = 67 + 12 * (v - 1);
	        int height = 67 + 12 * (v - 1);
			
			
	        //缓冲区
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			// 获取画笔
			Graphics2D gs = image.createGraphics();
			// 设置背景色 白色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, width, height);
			// 设定图像颜色 黑色
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致二维码生产错误(解析失败出错)
			int pixoff = 2;
			// 二维码输出
			/**
	         * 容易踩坑的地方
	         * 1.注意for循环里面的i，j的顺序，
	         *   s[j][i]二维数组的j，i的顺序要与这个方法中的 gs.fillRect(j*3+pixoff,i*3+pixoff, 3, 3);
	         *   顺序匹配，否则会出现解析图片是一串数字
	         * 2.注意此判断if (d.length > 0 && d.length < 120)
	         *   是否会引起字符串长度大于120导致生成代码不执行，二维码空白
	         *   根据自己的字符串大小来设置此配置
	         */
			if (contentBytes.length > 0 && contentBytes.length < 150) {
				boolean[][] code = qrcode.calQrcode(contentBytes);
				int codeLen = code.length;
				for (int i = 0; i < codeLen; i++) {
					for (int j = 0; j < codeLen; j++) {
						if (code[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				logger.error("This is content bytes length not in [0,150].");
			}
			
			/* 判断是否需要添加logo图片 */  
//            if(qrcode.isFlag()){  
//                int width_4 = imgSize / 4;  
//                int width_8 = width_4 / 2;  
//                int height_4 = imgSize / 4;  
//                int height_8 = height_4 / 2;  
//                Image img = ImageIO.read(new File(qrcode.getLogoPath()));  
//                gs.drawImage(img, width_4 + width_8, height_4 + height_8,width_4,height_4, null);  
//                gs.dispose();    
//                bufImg.flush();  
//            }  
            
			gs.dispose();
			image.flush();
			System.out.println("二维码生成完毕");
		} catch (Exception e) {
			logger.error("生成二维码失败" + e.getMessage());
		}
		return image;
	}
	
	
	/**
	 * 绘制二维码
	 * 
	 * @param contents
	 *            二维码内容
	 * @return image 二维码图片
	 * */
	public static BufferedImage encodeImgOtherMethod(String contents) {
		BufferedImage image = null;
		try {
			Qrcode qrcode = new Qrcode();
			/*** 表示的字符串长度： 容错率(ECC) 显示编码模式(EncodeMode)及版本(Version)有关 ***/
			/*
			 * 二维码的纠错级别(排错率)，共有四级：可选L(7%)、M(15%)、Q(25%)、H(30%)(最高H)。
			 * 纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，那么能存储的有用信息就越少,对二维码清晰度的要求越小
			 */
			qrcode.setQrcodeErrorCorrect('M');
			// 编码模式：Numeric 数字, Alphanumeric 英文字母,Binary 二进制,Kanji 汉字(第一个大写字母表示)
			qrcode.setQrcodeEncodeMode('B');
			/*
			 * 二维码的版本号：也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同。
			 * 1-40总共40个版本，版本1为21*21矩阵，版本每增1，二维码的两个边长都增4； 版本2
			 * 为25x25模块，最高版本为是40，是177*177的矩阵；
			 */
			qrcode.setQrcodeVersion(10);
			// 获取内容的字节数组，设置编码格式
			byte[] contentBytes = contents.getBytes("UTF-8");
			// 图片尺寸,会根据version的变大，而变大，自己需要计算
			int imgSize = 315;
			image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			// 获取画笔
			Graphics2D gs = image.createGraphics();
			// 设置背景色 白色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);
			// 设定图像颜色 黑色
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致二维码生产错误(解析失败出错)
			int pixoff = 25;
			// 二维码输出
			if (contentBytes.length > 0 && contentBytes.length < 150) {
				boolean[][] code = qrcode.calQrcode(contentBytes);
				int codeLen = code.length;
				for (int i = 0; i < codeLen; i++) {
					for (int j = 0; j < codeLen; j++) {
						if (code[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				logger.error("This is content bytes length not in [0,150].");
			}
			
			/* 判断是否需要添加logo图片 */  
//            if(qrcode.isFlag()){  
//                int width_4 = imgSize / 4;  
//                int width_8 = width_4 / 2;  
//                int height_4 = imgSize / 4;  
//                int height_8 = height_4 / 2;  
//                Image img = ImageIO.read(new File(qrcode.getLogoPath()));  
//                gs.drawImage(img, width_4 + width_8, height_4 + height_8,width_4,height_4, null);  
//                gs.dispose();    
//                bufImg.flush();  
//            }  
            
			gs.dispose();
			image.flush();
		} catch (Exception e) {
			logger.error("生成二维码失败" + e.getMessage());
		}
		return image;
	}

	/**
	 * 二维码输出到文件
	 * 
	 * @param contents
	 *            二维码内容
	 * @param format
	 *            图片格式
	 * @param file
	 *            输出文件
	 * */
	public static void writeToFile(String contents, String format, File file) {
		BufferedImage image = encodeImgOtherMethod(contents);
		try {
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			logger.error("二维码写入文件失败" + e.getMessage());
		}
	}

	/**
	 * 二维码流式输出
	 * 
	 * @param contents
	 *            二维码内容
	 * @param format
	 *            图片格式
	 * @param stream
	 *            输出流
	 * */
	public static void writeToStream(String contents, String format, OutputStream stream) {
		BufferedImage image = encodeImg(contents);
		try {
			ImageIO.write(image, format, stream);
		} catch (IOException e) {
			logger.error("二维码写入流失败" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		QrcodeUtils.writeToFile("http://192.168.3.7/api/member/zkLogin.do?merchantNo=e96f64d736564a768c9ab9f23f4962df&shopNo=LJ_bb0a003e802b4617999bd8bd8e9fec30", "png", new File("D:/qrcode.png"));
	}
}
