package lib.easy.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Implement a protocol with this interface which make the protocol
 * efficient and provide flexibility for users.
 * <p>
 * A protocol maker can provide a default parser for contents and
 * thread pool for executing the parsing and the spawning which 
 * execute the function the user would like to happen.
 */
public interface IProtocol<TContext> {
    
    /**
     * A port a protocol is to listen
     * 
     * @param port
     */
    void setPort(int port);
    
    /**
     * Provide a setter for users to customize the parser to parser
     * contents.
     * 
     * @param parser {@link IParser}
     */
    void setParser(IParser<TContext> parser);

    /**
     * Provide a setter for users to customize the thread pool to
     * execute a handler.
     * 
     * @param threadPool
     */
    void setThreadPool(IThreadPool threadPool);

    /**
     * Make a protocol more extensible.
     * 
     * @param frameworkFlow an object processing a parsed value and doing
     * response
     */
    void setFrameworkFlow(IFrameworkFlow<TContext> frameworkFlow);

    /**
     * Listen on a port and return a {@link Socket}
     * 
     * @return {@link Socket}
     */
    Socket accept() throws IOException;

    /**
     * Put the request into the thread pool to boost the throughout.
     * 
     * @param request an object from a listening
     */
    void spawn(Socket request) throws IOException;

    /**
     * Define a protocol flow by the protocol maker or providers. Either,
     * use the framework flow for inversing control back to the protocol 
     * user.
     * 
     * @param iStream
     * @param oStream
     * @param frameworkHandler
     */
    void protocolFlow(InputStream iStream, OutputStream oStream, IFrameworkFlow<TContext> frameworkFlow);

    /**
     * Repeatedly listen on a port and handle the requests.
     * <p>
     * It leverage the `accept()` and `spawn(Socket)`.
     */
    void run() throws IOException;
}
