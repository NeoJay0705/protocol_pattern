package lib.easy.template.http;

import java.io.IOException;
import java.io.OutputStream;

public class HttpContext {

    class HttpResponse {

        HttpContent content;
        OutputStream oStream;

        public HttpResponse(OutputStream oStream) {
            this.content = new HttpContent();
            this.oStream = oStream;
        }

        public HttpContent getHttpOutput() {
            return content;
        }

        public void send() {
            

            try {
                oStream.write(content.getAll().getBytes());
                oStream.flush();
            } catch (IOException e) {
                System.out.println(e);
            }
            
        }
    }

    HttpContent content;
    HttpResponse response;

    public HttpContext(OutputStream oStream) {
        this.content = new HttpContent();
        this.response = new HttpResponse(oStream);
    }

    public HttpContent getHttpInput() {
        return content;
    }

    public HttpResponse getResponse() {
        return response;
    }
}
