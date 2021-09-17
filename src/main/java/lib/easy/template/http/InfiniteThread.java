package lib.easy.template.http;

import lib.easy.protocol.IThreadPool;

public class InfiniteThread implements IThreadPool {

    @Override
    public void execute(Runnable runner) {
        new Thread(runner).start();;
    }
    
}
