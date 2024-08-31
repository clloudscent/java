package com.example.board.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RequestWrapper extends HttpServletRequestWrapper {
    private byte[] inputStream;

    public RequestWrapper(HttpServletRequest req) throws IOException {
        super(req);
        InputStream is = super.getInputStream();
        inputStream = is.readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(inputStream);
        return new ServletImpl(bai);
    }

    public static class ServletImpl extends ServletInputStream {
        private final InputStream is;

        public ServletImpl(InputStream is){
            this.is = is;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }

        @Override
        public int read() throws IOException {
            return is.read();
        }
    }
}
