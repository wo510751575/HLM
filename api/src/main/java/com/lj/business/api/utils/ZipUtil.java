package com.lj.business.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.core.util.GUID;

/**
 * @author zjs
 * @time 2018/01/31
 * 文件压缩工具
 */
public class ZipUtil {

    private static final String TAG = "ZipUtil";

    private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);
    
    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipFilePath：压缩后的文件路径
     */
    public static String zipFiles(List<File> srcfile, String zipFilePath) {
        File zipfile = new File(zipFilePath);
        byte[] buf = new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            int size = srcfile.size();
            for (int i = 0; i < size; i++) {
                FileInputStream in = new FileInputStream(srcfile.get(i));
                out.putNextEntry(new ZipEntry(srcfile.get(i).getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
//                LogUtil.show(TAG, "压缩完成." + zipfile.getAbsolutePath());
            System.out.print("" + "压缩完成." + zipFilePath);
        } catch (Exception e) {
            e.printStackTrace();
//                LogUtil.show(TAG, "压缩异常" + e.getMessage());
            System.out.print("" + "压缩完成." + zipFilePath);
            return "";
        }
        return zipFilePath;
    }

    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipFile：压缩后的文件路径
     */
    public static String zipFiles(List<File> srcfile, File zipFile) {

        byte[] buf = new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
            int size = srcfile.size();
            for (int i = 0; i < size; i++) {
                FileInputStream in = new FileInputStream(srcfile.get(i));
                out.putNextEntry(new ZipEntry(srcfile.get(i).getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
//            LogUtil.show(TAG, "压缩完成." );
            System.out.print("" + "压缩完成." + zipFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
//            LogUtil.show(TAG, "压缩异常" + e.getMessage());
            return "";
        }

        return zipFile.getAbsolutePath();
    }


    /**
     * 功能:压缩多个文件成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     */
    public static void zipFiles(File[] srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
//            LogUtil.show(TAG, "压缩完成." + zipfile.getAbsolutePath());
            System.out.print("" + "压缩完成." + zipfile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
//            LogUtil.show(TAG, "压缩异常" + e.getMessage());
        }
    }


    /**
     * 功能:ZIP文件解压缩
     * @author 彭俊霖
     * @param zipfile：需要解压缩的文件
     * @param descDir：解压后的目标目录
     * @param visDir：访问路径
     * @return Map<原文件名,访问路径>
     */
    @SuppressWarnings("rawtypes")
	public static Map<String,Object>  unZipFiles(File zipfile, String descDir,String visDir) {
    	Map<String,Object> map=new HashMap<String,Object>();
        try {
            ZipFile zf = new ZipFile(zipfile,Charset.forName("UTF-8"));
            for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();//原文件名
                logger.info("原文件名:{}",zipEntryName);
                String postfix = zipEntryName.indexOf(".") == -1 ? ".jpg" : zipEntryName.substring(zipEntryName.indexOf("."));//如果没有后缀,默认拼".jpg"
        		String fileInputName = GUID.generateByUUID() + postfix;//重新生成随机唯一文件名
        		map.put(zipEntryName, visDir+fileInputName);
                InputStream in = zf.getInputStream(entry);
                //如果文件或文件夹不存在,就创建
                File fileInput = new File(descDir + fileInputName);
        		if (fileInput.getParentFile() != null && fileInput.getParentFile().exists() == false) {
        			if (fileInput.getParentFile().mkdirs() == false) {
        				throw new IOException("Destination '" + fileInput + "' directory cannot be created");
        			}
        		}
                OutputStream out = new FileOutputStream(descDir + fileInputName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
                logger.debug("解压完毕 :" + descDir + fileInputName);
            }
            zf.close();//先关闭
            zipfile.delete();//再删除
        } catch (Exception e) {
            logger.error("文件解压失败",e);
//            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取指定目录文件名集合
     * @param path
     * @return
     */
    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
    
    /**
     * 功能:
     *
     * @param args
     */
    public static void main(String[] args) {
        //2个源文件
        File[] files = new File[2];
        File file = new File("D:\\temp\\q1.png");
        File file1 = new File("D:\\temp\\q2.jpg");

        File[] srcfile = {file, file1};
        //压缩后的文件
        File zipfile = new File("D:\\temp1\\biao1.zip");

//        ZipUtil.zipFiles(srcfile, zipfile);
        List<File> ff = new ArrayList<>();
        ff.add(file);
        ff.add(file1);
        ZipUtil.zipFiles(ff, "D:\\temp1\\biao12.zip");
        //需要解压缩的文件
//    File file3=new File("D:\\temp1\\sheng1.zip");
        //解压后的目标目录
        String dir = "D:\\workspace\\flexTest\\src\\com\\biao\\test\\";
//    ZipUtil.unZipFiles(file, dir);
    }
}
