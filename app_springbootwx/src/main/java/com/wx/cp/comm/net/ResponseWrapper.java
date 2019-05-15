package com.wx.cp.comm.net;

import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ResponseWrapper
 * @Description TODO
 * @createdate 2019/5/13 星期一 16:13
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ByteArrayOutputStream bos ;
    private PrintWriter writer ;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        this.bos = new ByteArrayOutputStream();
        this.writer = new PrintWriter(bos);
        this.getContent();
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {

            }
            private TeeOutputStream tee = new TeeOutputStream(ResponseWrapper.super.getOutputStream(), bos);
            @Override
            public void write(int b) throws IOException {
                tee.write(b);
            }
        };
    }
    @Override
    public PrintWriter getWriter() throws IOException {
        return new TeePrintWriter(super.getWriter(), writer);
    }
    public String getContent() {
        try {
            String res = new String(bos.toByteArray(), this.getResponse().getCharacterEncoding());
            LOGGER.info(res);
            return res;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
