package lib.easy.protocol;

/**
 * Reverse control to users who can customize their flow
 * to process requests.
 */
public interface IFrameworkFlow<TContext> {
    void run(TContext context);
}
