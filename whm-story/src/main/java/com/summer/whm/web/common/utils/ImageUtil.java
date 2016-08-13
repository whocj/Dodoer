package com.summer.whm.web.common.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸等
 */
public class ImageUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ImageUtil.class);

    /** * 图片文件读取 * **/
    private static BufferedImage InputImage(String srcImgPath) {
        BufferedImage srcImage = null;
        try {
            FileInputStream in = new FileInputStream(srcImgPath);
            srcImage = javax.imageio.ImageIO.read(in);
        } catch (IOException e) {
            LOG.error("读取图片文件出错！", e);
        }
        return srcImage;
    }

    /**
     * * 将图片按照指定的图片尺寸压缩 * * @param srcImgPath :源图片路径 * @param outImgPath * :输出的压缩图片的路径 * @param new_w * :压缩后的图片宽 * @param
     * new_h * :压缩后的图片高
     */
    public static void compressImage(String srcImgPath, String outImgPath, int new_w, int new_h) {
        BufferedImage src = InputImage(srcImgPath);
        disposeImage(src, outImgPath, new_w, new_h);
    }

    /**
     * 指定长或者宽的最大值来压缩图片
     * 
     * @param srcImgPath * :源图片路径
     * @param outImgPath * :输出的压缩图片的路径
     * @param maxLength * :长或者宽的最大值
     */
    public static void compressImage(String srcImgPath, String outImgPath, int maxLength) {
        // 得到图片
        BufferedImage src = InputImage(srcImgPath);
        if (null != src) {
            int old_w = src.getWidth();
            // 得到源图宽
            int old_h = src.getHeight();
            // 得到源图长
            int new_w = 0;
            // 新图的宽
            int new_h = 0;
            // 新图的长
            // 根据图片尺寸压缩比得到新图的尺寸
            if (old_w > maxLength || old_h > maxLength) {
                if (old_w > old_h) {
                    // 图片要缩放的比例
                    new_w = maxLength;
                    new_h = (int) Math.round(old_h * ((float) maxLength / old_w));
                } else {
                    new_w = (int) Math.round(old_w * ((float) maxLength / old_h));
                    new_h = maxLength;
                }
            } else {
                new_w = old_w;
                new_h = old_h;
            }

            disposeImage(src, outImgPath, new_w, new_h);
        }
    }

    /** * 处理图片 * * @param src * @param outImgPath * @param new_w * @param new_h */
    private synchronized static void disposeImage(BufferedImage src, String outImgPath, int new_w, int new_h) {
        // 得到图片
        int old_w = src.getWidth();
        // 得到源图宽
        int old_h = src.getHeight();
        // 得到源图长
        BufferedImage newImg = null;
        // 判断输入图片的类型
        switch (src.getType()) {
            case 13:
                //gif图片裁剪后，只能是静态图
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_4BYTE_ABGR);
                break;
            default:
                newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                break;
        }
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, old_w, old_h, null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
        // 调用方法输出图片文件
        OutImage(outImgPath, newImg);
    }

    /**
     * * 将图片文件输出到指定的路径，并可设定压缩质量 * * @param outImgPath * @param newImg * @param per
     */
    private static void OutImage(String outImgPath, BufferedImage newImg) {
        // 判断输出的文件夹路径是否存在，不存在则创建
        File file = new File(outImgPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }// 输出到文件流
        try {
            ImageIO.write(newImg, outImgPath.substring(outImgPath.lastIndexOf(".") + 1), new File(outImgPath));
        } catch (FileNotFoundException e) {
            LOG.error("找不到文件", e);
        } catch (IOException e) {
            LOG.error("找不到文件", e);
        }
    }

    /**
     * 图像切割
     * 
     * @param srcImageFile 源图像地址
     * @param x 目标切片起点x坐标
     * @param y 目标切片起点y坐标
     * @param destWidth 目标切片宽度
     * @param destHeight 目标切片高度
     */
    public static void abscut(String srcImageFile, int x, int y, int destWidth, int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度
            LOG.info("srcWidth= " + srcWidth + "/tsrcHeight= " + srcHeight);
            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
                // 改进的想法:是否可用多线程加快切割速度
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", new File(srcImageFile));
            }
        } catch (Exception e) {
            LOG.error("图片裁剪失败", e);
        }
    }

    public static void main(String[] args) {
        ImageUtil.compressImage("D:/mm.gif",
                "D:/mm2.gif", 500);
    }
}