package com.cp.app.core.comm.uitl;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName VerificationCodeUtil
 * @Description TODO 验证码生成工具类
 * @createdate 2019/6/20 星期四 10:20
 */
public class VerificationCodeUtil {
    private static final int DEFAULT_HEIGHT = 40; //验证码默认高度
    private static final int DEFAULT_WIDTH = 140;//验证码默认宽度
    private static final int DEFAULT_LEN = 6;//验证码默认位数
    private static final String PNG_TYPE = "png";//PNG格式验证码
    private static final String DEFAULT_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz";//默认字符源
    private static Random random  = new Random();
    /**
     * 生成默认验证码
     * @return
     */
    public static String generateVerificationCode(){
        return generateVerificationCode(VerificationCodeUtil.DEFAULT_LEN,VerificationCodeUtil.DEFAULT_CODES);
    }
    /**
     * 按指定位数、字符源生成验证码
     * @param len 位数
     * @param source 字符源
     * @return
     */
    public static String generateVerificationCode(int len, String source) {
        if (null == source || "".equals(source)) {
            source = VerificationCodeUtil.DEFAULT_CODES;
        }
        if (len <= 0) {
            len = VerificationCodeUtil.DEFAULT_LEN;
        }
        StringBuilder sb = new StringBuilder(len); //字符源中随机取 len位字符
        int slen = source.length()-1;
        for (int i = 0 ; i<len;i++){
            int n = random.nextInt(slen);
            sb.append(source.charAt(n));
        }
        return sb.toString();
    }
    /**
     * 绘制验证码
     * @param code
     * @param height
     * @param width
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream outCaptcha(String code, int height, int width) throws IOException {
        if(height<=0) {
            height=VerificationCodeUtil.DEFAULT_HEIGHT;
        }
        if(width<=0) {
            width=VerificationCodeUtil.DEFAULT_WIDTH;
        }
        BufferedImage im = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = im.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置边框色
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, width, height);
        //绘制背景
        Color color = getRandColor(200,250);
        graphics.setColor(color);
        graphics.fillRect(0,0,width,height);

        drawLine(graphics,width,height,10); //绘制干扰线
        noisyPoint(im,width,height,0.05f);//加噪点
        shear(graphics,width,height,color); //扭曲图片
        deawCode(graphics,width,height,code);//写验证码
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(im,VerificationCodeUtil.PNG_TYPE,baos);
        graphics.dispose();//回收
        return baos;
    }

    /**
     * 生成base64数组
     * @param code
     * @param height
     * @param width
     * @return
     * @throws IOException
     */
    public static String outCaptchaToBase64(String code, int height, int width) throws IOException{
        ByteArrayOutputStream stream = VerificationCodeUtil.outCaptcha(code,height,width);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(stream.toByteArray());
    }


    /**
     * 随机生成颜色
     * @param fc
     * @param bc
     * @return
     */
    private static Color getRandColor(int fc, int bc){
        if(fc>255){
            fc = 255;
        }
        if(bc>255){
            bc = 255;
        }
        int r =fc+random.nextInt(bc-fc);
        int g =fc+random.nextInt(bc-fc);
        int b =fc+random.nextInt(bc-fc);
       return new Color(r,g,b);
    }

    /**
     * RGB随机生成
     * @return
     */
    private static int getRandomIntColor(){
        int[] rgb = new int[5];
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = random.nextInt(255);
        }
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }
    /**
     * 添加干扰线
     * @param graphics2D
     * @param width
     * @param height
     * @param n
     */
    private static void drawLine(Graphics2D graphics2D,int width,int height,int n){
        graphics2D.setColor(getRandColor(160,200));
        for (int i = 0; i<n;i++){
            int x1 = random.nextInt(width-1);
            int y1 = random.nextInt(height-1);
            int x2 = random.nextInt(6)+1;
            int y2 = random.nextInt(12)+1;
            graphics2D.drawLine(x1,y1,x1+x2+20,y1+y2+20);
        }
    }
    /**
     * 添加噪点
     * @param im
     * @param width
     * @param height
     * @param rate
     */
    private static void noisyPoint(BufferedImage im,int width,int height,float rate){
        int area = (int) (rate*width*height);
        for (int i=0;i<area;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            im.setRGB(x,y,rgb);
        }
    }
    /**
     * 绘制验证码
     * @param graphics2D
     * @param height
     * @param width
     * @param code
     */
    private static void deawCode(Graphics2D graphics2D,int width,int height,String code){
        //写验证码
        int fsize =height-10;
        int lenght = code.length();
        graphics2D.setFont(new Font("Algerian", Font.ITALIC, fsize));
        char[] chars = code.toCharArray();
        for (int i=0;i<lenght;i++){
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (width / lenght) * i + fsize/2, height/2);
            graphics2D.setTransform(affine);
            graphics2D.setColor(getRandColor(100, 160));
            graphics2D.drawChars(chars, i, 1, ((width-10) / lenght) * i + 5, height-10);
        }
    }
    /**
     * 扭曲图片
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }
    private static void shearX(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }
    private static void shearY(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10; // 50;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }



    public static void main(String[]arg) throws IOException {
        long begin = System.currentTimeMillis();
        String code = VerificationCodeUtil.generateVerificationCode();
        ByteArrayOutputStream stream = VerificationCodeUtil.outCaptcha(code,0,0);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String string = base64Encoder.encode(stream.toByteArray());
        long end = System.currentTimeMillis();
        System.out.println("用时:"+(end-begin)/1000+"s");
        System.out.println(string);
    }
}
