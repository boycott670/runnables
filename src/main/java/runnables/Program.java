package runnables;

import java.util.logging.Logger;

public class Program
{
    private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(final String[] args)
    {
        final long start = System.currentTimeMillis();

        Processor.process(HttpPageTitleFetcher.class);

        logger.info(String.format("%.2f secs", (Double.valueOf(System.currentTimeMillis()) - start) / 1000));
    }
}
