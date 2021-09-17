package lib.easy.template.http;

import java.io.IOException;

import lib.easy.protocol.IFrameworkFlow;

public class Main {
    public static void main(String[] args) {
        IFrameworkFlow<HttpContext> handler = new IFrameworkFlow<HttpContext>(){
            @Override
            public void run(HttpContext context) {
                System.out.println(context.content.getBody());
            }
        };
        
        Http http = new Http(9999, handler);

        try {
            http.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
