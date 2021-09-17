package lib.easy.protocol;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Parse raw bytes to a protocol object.
 */
public interface IParser<TContext> {
    TContext parse(InputStream iStream, OutputStream oStream);
}
