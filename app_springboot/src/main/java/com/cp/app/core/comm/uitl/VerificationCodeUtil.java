package com.cp.app.core.comm.uitl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private static final int DEFAULT_HEIGHT = 48; //验证码默认高度
    private static final int DEFAULT_WIDTH = 130;//验证码默认宽度
    private static final int DEFAULT_LEN = 6;//验证码默认位数
    private static final String PNG_TYPE = "png";//PNG格式验证码
    private static final String DEFAULT_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";//默认字符源
    private static Random random  = new Random();
    /**
     * 生成默认验证码
     * @return
     */
    public static String generateVerificationCode(){
        return generateVerificationCode(VerificationCodeUtil.DEFAULT_LEN,VerificationCodeUtil.DEFAULT_CODES);
    }
    /**
     * 按指定位数生成验证码
     * @param len 位数
     * @return
     */
    public static String generateVerificationCode(int len){
        return generateVerificationCode(len,VerificationCodeUtil.DEFAULT_CODES);
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
    //输出指定验证码图片流

    /**
     * 绘制验证码
     * @param code
     * @param height
     * @param width
     * @param formatName
     * @param font
     * @return
     * @throws IOException
     */
    public static BufferedImage outCaptcha(String code, int height, int width, String formatName, Font font) throws IOException {
        if(height<=0) {
            height=VerificationCodeUtil.DEFAULT_HEIGHT;
        }
        if(width<=0) {
            width=VerificationCodeUtil.DEFAULT_WIDTH;
        }
        if(null==formatName||"".equals(formatName)){
            formatName = VerificationCodeUtil.PNG_TYPE;
        }
        BufferedImage im = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = im.createGraphics();
        //绘制背景
        graphics.setColor(Color.BLUE);
        graphics.fillRect(0,0,width,height);
        //绘制干扰线
        //写验证码
        int fsize = (int) (height*0.6);
        graphics.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,fsize));
        int fx=0;
        int fy=fsize;
        for(int i=0;i<code.length();i++){
            //字符高度随机
            //字符颜色随机
            graphics.setColor(Color.RED);
            graphics.drawString(code.charAt(i)+"",fx,fy);//绘制字符
            fx += (width/code.length())*(Math.random()*0.5+0.8);//依据宽度浮动
        }
        //扭曲图片
        //添加噪点
        graphics.dispose();//回收
//        ImageIO.write(im,formatName,output);//绘制图片
        return im;
    }
    public static void main(String[]arg) throws IOException {
        String code = VerificationCodeUtil.generateVerificationCode();
        System.out.println(code);
        BufferedImage bufferedImage = VerificationCodeUtil.outCaptcha(code,0,0,VerificationCodeUtil.PNG_TYPE,null);
        File file = new File("/Users/chupengtang/soundCode/OwnSpace/ideaworkspace/workspace_0/code.png");
        ImageIO.write(bufferedImage,VerificationCodeUtil.PNG_TYPE,file);
    }
}
