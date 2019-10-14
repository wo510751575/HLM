package com.ye.business.hx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ye.business.hx.emus.PatientType;

/**
 * 生成各种单号
 * 
 * @author bobo
 *
 */
public class GenerateNo {

	private static GenerateNo goInstance = null;

	private GenerateNo() {

	}

	public static GenerateNo getInstance() {
		if (goInstance == null) {
			goInstance = new GenerateNo();
		}
		return goInstance;
	}

	public static Integer ordersInt = 0;// 全局数

	/**
	 * 病例号
	 * 
	 * @return
	 */
	public synchronized String getCaseNo(String type) {
		ordersInt++;// 自增
		Integer countInteger = 4 - ordersInt.toString().length();// 算补位
		String bu = "";
		for (int i = 0; i < countInteger; i++) {// 补字符串
			bu += "0";
		}
		bu += ordersInt.toString();
		if (ordersInt > 9999) {
			ordersInt = 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");// 时间戳
		String str = sdf.format(new Date());
		return type.toString() + str + bu;
	}
	
	
	/**
	 * 账单号
	 * 
	 * @return
	 */
	public static String getBillNo() {
		return GenerateNo.getInstance().getCaseNo("B");
	}
	
	/**
	 * 支付号
	 * 
	 * @return
	 */
	public static String getPayNo() {
		return GenerateNo.getInstance().getCaseNo("P");
	}
	
	
	public static void main(String[] args) {
		new Thread() {@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("1:"+GenerateNo.getInstance().getCaseNo(PatientType.PT.name()));
			}
		}.start();
		
		new Thread() {@Override
			public void run() {
				System.out.println("2:"+GenerateNo.getInstance().getCaseNo(PatientType.PT.name()));
			}
		}.start();
		
		new Thread() {@Override
			public void run() {
				System.out.println("3:"+GenerateNo.getInstance().getCaseNo(PatientType.PT.name()));
			}
		}.start();
		
		new Thread() {@Override
			public void run() {
				System.out.println("4:"+GenerateNo.getInstance().getCaseNo(PatientType.PT.name()));
			}
		}.start();
		System.out.println("5:"+ GenerateNo.getInstance().getCaseNo(PatientType.PT.name()));
		
		new Thread() {@Override
			public void run() {
				System.out.println("6:"+GenerateNo.getBillNo()+GenerateNo.getPayNo());
			}
		}.start();
	}
}
