package space.harbour.java.hm6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebCrawler {
    static ConcurrentLinkedQueue<URL> toVisit = new ConcurrentLinkedQueue<>();
    static CopyOnWriteArraySet<URL> alreadyVisited = new CopyOnWriteArraySet<>();

    public static class UrlVistior implements Callable {
        public static String getContentOfWebPage(URL url) {
            final StringBuilder content = new StringBuilder();

            try (InputStream is = url.openConnection().getInputStream();
                    InputStreamReader in = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(in)) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Failed to retrieve content of " + url.toString());
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        public CopyOnWriteArraySet call() throws Exception {

            synchronized (toVisit) {
                synchronized (alreadyVisited) {
                    //get URL for the head of the queue
                    //System.out.println(Thread.currentThread().getName());
                    AtomicReference<URL> url = new AtomicReference<>(toVisit.poll());


                    // mark it as visited by adding to the set
                    if (url != null) {
                        alreadyVisited.add(url.get());
                        AtomicReference<String> content =
                                new AtomicReference<>(getContentOfWebPage(url.get()));

                        String regex = "\\b(https?|http)://"
                                + "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]"
                                + "*[-a-zA-Z0-9+&@#/%=~_|]";

                        Pattern regexPattern = Pattern.compile(regex);
                        Matcher regexMatcher = regexPattern.matcher(content.get());
                        while (regexMatcher.find()) {
                            AtomicReference<String> link =
                                    new AtomicReference<>(regexMatcher.group());
                            try {
                                AtomicReference<URL> newUrl
                                        = new AtomicReference<>(new URL(link.get()));
                                if (!toVisit.contains(newUrl.get())
                                        && !alreadyVisited.contains(newUrl.get())) {
                                    toVisit.add(newUrl.get());
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            //System.out.println(alreadyVisited);
            return alreadyVisited;
        }
    }


    public static void main(String[] args) {
        ExecutorService table = Executors.newFixedThreadPool(8);
        Future<CopyOnWriteArraySet> future = null;
        int urlSize = 0;

        try {
            toVisit.add(new URL("https://vasart.github.io/supreme-potato/index.html"));
            while (!toVisit.isEmpty()) {
                future = table.submit(new UrlVistior());
                System.out.println(future.get());
                urlSize = future.get().size();
            }
            table.shutdown();

            System.out.println(urlSize);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
