package com.gosuncn.ics.sms.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;

/*******************************************************************************
 * Description: 图片水印工具类
 * @author zhaoronglei
 * @version 1.0
 */
public class ImageRemarkUtil {

    // 水印透明度
    private static float alpha = 0.5f;
    // 水印横向位置,以左上角为原点
    private static int positionX = 150;
    // 水印纵向位置
    private static int positionY = 300;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 72);
    // 水印文字颜色
    private static Color color = Color.red;

    /**
     * 设置水印参数
     *
     * @param alpha 水印透明度
     * @param x     水印x位置，以左上角为原点
     * @param y     水印y位置
     * @param font  水印文字字体
     * @param color 水印文字颜色
     */
    public static void setMarkOptions(float alpha, int x,
                                      int y, Font font, Color color) {
        if (alpha != 0.0f)
            ImageRemarkUtil.alpha = alpha;
        if (positionX != 0)
            ImageRemarkUtil.positionX = x;
        if (positionY != 0)
            ImageRemarkUtil.positionY = y;
        if (font != null)
            ImageRemarkUtil.font = font;
        if (color != null)
            ImageRemarkUtil.color = color;
    }

    /**
     * 给图片添加水印图片
     *
     * @param iconPath   水印图片完整路径
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     * @param response   将要写入图片流的HttpServletResponse
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath, HttpServletResponse response) {
        markImageByIcon(iconPath, srcImgPath, targetPath, null, response);
    }

    /**
     * 给图片添加水印图片
     *
     * @param iconPath   水印图片完整路径
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath) {
        markImageByIcon(iconPath, srcImgPath, targetPath, null, null);
    }

    /**
     * 给图片添加水印图片
     *
     * @param iconPath   水印图片完整路径
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     * @param degree     水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath, Integer degree) {
        markImageByIcon(iconPath, srcImgPath, targetPath, degree, null);
    }

    /**
     * 给图片添加水印图片、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片完整路径
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     * @param degree     水印图片旋转角度
     * @param response   将要写入图片流的HttpServletResponse
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targetPath, Integer degree, HttpServletResponse response) {
        Date start = new Date();
        OutputStream os = null;
        InputStream is;
        Image srcImg;
        try {
            //远程，被覆盖的图片
            if (srcImgPath.contains("http")) {
                URL url1 = new URL(srcImgPath);
                is = url1.openStream();
            } else {
                //本地
                is = getFile(srcImgPath);
            }
            srcImg = ImageIO.read(is);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 3、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            // 5、得到Image对象。
            Image img = ImageIO.read(getFile(iconPath));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 6、水印图片的位置
            g.drawImage(img, positionX, positionY, null);
            // 5、设置水印文字颜色
            //g.setColor(color);
            // 6、设置水印文字Font
            //g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            //g.drawString("复印无效", positionWidth, positionHeight);
            // 7、释放资源
            g.dispose();
            // 8、生成图片
            if (response == null) {
                os = new FileOutputStream(targetPath);
            } else {
                response.setHeader("Cache-Control", "no-store, no-cache");
                response.setContentType("image/jpeg");
                os = response.getOutputStream();
            }
            //9 将缓存数据写入输出流里
            ImageIO.write(buffImg, "JPG", os);
            Date end = new Date();
            System.out.println("图片完成添加水印图片");
            System.out.println("处理时间:" + (end.getTime() - start.getTime()) / 1000 + "秒");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给图片添加水印文字
     *
     * @param logoText   水印文字
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     */
    public static void markImageByText(String logoText, String srcImgPath,
                                       String targetPath) {
        markImageByText(logoText, srcImgPath, targetPath, null);
    }

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText   水印文字
     * @param srcImgPath 源图片完整路径
     * @param targetPath 目标图片完整路径
     * @param degree
     */
    public static void markImageByText(String logoText, String srcImgPath,
                                       String targetPath, Integer degree) {

        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, positionX, positionY);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targetPath);
            ImageIO.write(buffImg, "JPG", os);

            System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static FileInputStream getFile(String srcImgPath) throws FileNotFoundException {
        /*String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; //项目路径
        filePath = filePath.replaceAll("file:/", "");
        filePath = filePath.replaceAll("%20", " ");
        filePath = filePath.trim() + srcImgPath.trim();
        if (filePath.indexOf(":") != 1) {
            filePath = File.separator + filePath;
        }
        return new FileInputStream(new File(filePath));*/
        return new FileInputStream(new File(srcImgPath));
    }

    public static void main(String[] args) {
        // ImageIcon image= new ImageIcon(ClassLoader.getSystemResource("img/112.png"));
        String srcImgPath = "C:/Users/huweijian/Pictures/timg1.jpg";
        String logoText = "仅作租房时信息认证，他用无效。";
        String iconPath = "C:/Users/huweijian/Pictures/123.png";

        String targerTextPath = "C:/Users/huweijian/Pictures/timg111.jpg";
        String targerTextPath2 = "C:/Users/huweijian/Pictures/timg1111.jpg";

        String targerIconPath = "C:/Users/huweijian/Pictures/811.jpg";
        String targerIconPath2 = "C:/Users/huweijian/Pictures/8111.jpg";

        System.out.println("给图片添加水印文字开始...");
        // 给图片添加水印文字
        markImageByText(logoText, srcImgPath, targerTextPath);
        // 给图片添加水印文字,水印文字旋转-45
        markImageByText(logoText, srcImgPath, targerTextPath2, -45);
        System.out.println("给图片添加水印文字结束...");

        System.out.println("给图片添加水印图片开始...");
        setMarkOptions(0.3f, 10, 100, null, null);
        // 给图片添加水印图片
        markImageByIcon(iconPath, srcImgPath, targerIconPath);
        // 给图片添加水印图片,水印图片旋转-45
        markImageByIcon(iconPath, srcImgPath, targerIconPath2, -45);
        System.out.println("给图片添加水印图片结束...");
    }
}