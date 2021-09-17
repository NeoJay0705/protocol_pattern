package lib.easy.template.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpContent {

    List<String> startLine;
    Map<String, String> header;
    String body;

    public HttpContent() {
        this.startLine = new ArrayList<String>();
        this.header = new HashMap<String,String>();
        this.body = "";
    }

    public void setStartLineContext(String context) {
        startLine.add(context);
    }

    public void setHeader(String key, String value) {
        header.put(key, value);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStartLineContext(int index) {
        if ( index < startLine.size() )
            return startLine.get(index);

        return null;
    }

    public String getStartLine() {
        return String.join(" ", startLine);
    }

    public String getHeader(String key) {
        return header.getOrDefault(key, null);
    }

    public String getHeader() {
        StringBuilder sb = new StringBuilder();
        header.forEach((key, val) -> {
            sb.append(key + ": " + val + "\n");
        });

        return sb.toString();
    }

    public String getBody() {
        return body;
    }

    public String getAll() {
        StringBuilder sb = new StringBuilder();

        sb.append(getStartLine());
        sb.append("\n");
        sb.append(getHeader());
        sb.append("\n\n");
        sb.append(getBody());

        return sb.toString();
    }
}
