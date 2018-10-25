package runnables;

import java.util.logging.Logger;

@Times(256)
public final class DummyIndexedRunnable implements IndexedRunnable
{
    private static final Logger logger = Logger.getLogger(DummyIndexedRunnable.class.getSimpleName());

    public void run(final int index) throws Throwable
    {
        logger.info(String.valueOf(index));
    }
}
