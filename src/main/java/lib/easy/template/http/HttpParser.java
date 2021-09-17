package lib.easy.template.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import lib.easy.protocol.IParser;

public class HttpParser implements IParser<HttpContext> {

    @Override
    public HttpContext parse(InputStream iStream, OutputStream oStream) {
        BufferedReader stream = new BufferedReader(new InputStreamReader(iStream));

        HttpContext httpContext = new HttpContext(oStream);
        
        try {
            String startLine = stream.readLine();
            for (String context : startLine.split(" ")) {
                httpContext.getHttpInput().setStartLineContext(context);
            }

            String headerLine;
            while (!(headerLine = stream.readLine()).equals("")) {
                String[] header = headerLine.split(": ", 2);
                if (header.length < 2)
                    httpContext.getHttpInput().setHeader(header[0], "");
                else
                    httpContext.getHttpInput().setHeader(header[0], header[1]);
            }

            String bodyLine;
            StringBuilder sb = new StringBuilder();
            while ((bodyLine = stream.readLine()) != null) {
                sb.append(bodyLine + "\n");
            }

            httpContext.getHttpInput().setBody(sb.toString());

        } catch (IOException e) {
            System.out.println(e);
        } 

        return httpContext;
    }

}
