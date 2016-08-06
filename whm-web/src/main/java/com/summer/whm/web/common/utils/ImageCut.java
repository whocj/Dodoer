package com.summer.whm.web.common.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageCut {
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
            System.out.println("srcWidth= " + srcWidth + "/tsrcHeight= " + srcHeight);
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
            e.printStackTrace();
        }
    }

    /** */
    /**
     * @param args
     */
    public static void main(String[] args) {
        // abscut("upload//200805151335353535.jpg", 10, 10, 100, 140);
        abscut("e:/castle.jpg", 10, 11, 500, 281);
    }
}
