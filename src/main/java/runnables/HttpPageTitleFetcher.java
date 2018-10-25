package runnables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Times(10)
public final class HttpPageTitleFetcher implements IndexedRunnable
{
    private static final Logger logger = Logger.getAnonymousLogger();

    private static final Pattern titlePattern = Pattern.compile("<title>(.+)</title>");

    private static int BUFFER_LENGTH = 1024;

    @Override
    public void run(final int index) throws Throwable
    {
        final HttpURLConnection request = (HttpURLConnection)new URL(String.format("http://nobodyhome.ga/thread-1-page-%d.html", index + 1)).openConnection();

        request.setConnectTimeout(0);

        request.setReadTimeout(0);

        request.setRequestMethod("GET");

        request.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");

        final BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        final StringBuffer html = new StringBuffer();

        final char[] readChars = new char[BUFFER_LENGTH];

        while (reader.read(readChars) > 0)
        {
            html.append(readChars);
        }

        final Matcher matcher = titlePattern.matcher(html);

        if (matcher.find())
        {
            logger.info(matcher.group(1));
        }
        else
        {
            logger.log(Level.WARNING, String.format("Title not found for page %d", index + 1));
        }

        request.disconnect();
    }
}
