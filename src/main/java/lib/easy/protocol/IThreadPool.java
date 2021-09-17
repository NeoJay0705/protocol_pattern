package lib.easy.protocol;

/**
 * A runtime for customized handlers and parsing tasks
 */
public interface IThreadPool {
    void execute(Runnable runner);
}
