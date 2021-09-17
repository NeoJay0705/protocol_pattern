package lib.easy.template.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lib.easy.protocol.IFrameworkFlow;
import lib.easy.protocol.IParser;
import lib.easy.protocol.IProtocol;
import lib.easy.protocol.IThreadPool;

public class Http implements IProtocol<HttpContext> {

    ServerSocket serverSocket;
    int port;
    IParser<HttpContext> parser;
    IThreadPool threadPool;
    IFrameworkFlow<HttpContext> frameworkFlow;

    public Http(int port, IThreadPool threadPool, IParser<HttpContext> parser, IFrameworkFlow<HttpContext> frameworkFlow) {
        setPort(port);
        setThreadPool(threadPool);
        setParser(parser);
        setFrameworkFlow(frameworkFlow);

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Can't listen on port " + port + "\nCaused by: " + e);
        }
    }

    public Http(int port, IFrameworkFlow<HttpContext> frameworkFlow) {
        this(port, new InfiniteThread(), new HttpParser(), frameworkFlow);
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void setParser(IParser<HttpContext> parser) {
        this.parser = parser;
    }

    @Override
    public void setThreadPool(IThreadPool threadPool) {
        this.threadPool = threadPool;
    }
    
    @Override
    public void setFrameworkFlow(IFrameworkFlow<HttpContext> frameworkFlow) {
        this.frameworkFlow = frameworkFlow;
    }

    @Override
    public Socket accept() throws IOException {
        return serverSocket.accept();
    }

    @Override
    public void spawn(Socket request) throws IOException {
        final InputStream iStream;
        final OutputStream oStream;
        try {
            iStream = request.getInputStream();
            oStream = request.getOutputStream();
        } catch (IOException e) {
            throw e;
        }

        threadPool.execute(new Runnable(){

            @Override
            public void run() {
                protocolFlow(iStream, oStream, frameworkFlow);
            }
            
        });
    }

    @Override
    public void run() throws IOException {
        System.out.println("Server run on port " + port);
        while (true) {
            spawn(accept());
        }
    }

    @Override
    public void protocolFlow(InputStream iStream, OutputStream oStream, IFrameworkFlow<HttpContext> frameworkFlow) {
        HttpContext httpContext = parser.parse(iStream, oStream);

        frameworkFlow.run(httpContext);
    }
}
