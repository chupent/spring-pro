package com.cp.app.core.comm.uitl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName QRCodeUtil
 * @Description TODO
 * @createdate 2019/7/9 星期二 12:02
 */
public class QRCodeUtil {
    /**
     *
     * @param text 内容
     * @param savePath 保存位置
     * @param format 文件类型
     * @param w 宽度
     * @param h 高度
     */
    public static void generatorQRCodeImg(String text,String savePath,String format,int w,int h){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,w,h);
            Path path = FileSystems.getDefault().getPath(savePath+System.currentTimeMillis()+"."+format);
            MatrixToImageWriter.writeToPath(bitMatrix,format,path);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param text 内容
     * @param format 文件类型
     * @param w 宽度
     * @param h 高度
     * @return 二维码字节数组
     */
    public static byte[] generatorQRCodeBytes(String text,String format,int w,int h){
        byte[] bytes = null;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream stream = null;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text,BarcodeFormat.QR_CODE,w,h);
            stream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,format,stream);
            bytes = stream.toByteArray();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null==stream){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }
    /**
     *
     * @param text
     * @param format
     * @param w
     * @param h
     * @return
     */
    public static String generatorQRCodeString(String text,String format,int w,int h){
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] bytes = QRCodeUtil.generatorQRCodeBytes(text,format,w,h);
        return encoder.encode(bytes);
    }

    public static void main(String[] args){
        QRCodeUtil.generatorQRCodeImg("1234567","/Users/chupengtang/soundCode/OwnSpace/ideaworkspace/workspace_0/","png",100,100);
    }
}
