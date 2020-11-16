package space.harbour.java.hm6;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;


public class WebCrawlerTest extends TestCase {
    public WebCrawler githubPages = new WebCrawler();

    @Test
    public void test01() {
        ExecutorService table = Executors.newFixedThreadPool(8);
        Future<CopyOnWriteArraySet> future = null;
        int urlSize = 0;

        try {
            githubPages.toVisit.add(new URL("https://vasart.github.io/supreme-potato/index.html"));
            while (!githubPages.toVisit.isEmpty()) {
                future = table.submit(new WebCrawler.UrlVistior());
                urlSize = future.get().size();
            }
            table.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(urlSize, 4);
    }

    @Test
    public void test02() {
        ExecutorService table = Executors.newFixedThreadPool(8);
        Future<CopyOnWriteArraySet> future = null;
        try {
            githubPages.toVisit.add(new URL("https://vasart.github.io/supreme-potato/index.html"));
            while (!githubPages.toVisit.isEmpty()) {
                future = table.submit(new WebCrawler.UrlVistior());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(githubPages.toVisit.isEmpty());
    }

    @Test
    public void test03() {
        ExecutorService table = Executors.newFixedThreadPool(8);
        Future<CopyOnWriteArraySet> future = null;
        Object[] futureSet = null;
        int urlSize = 0;

        try {
            githubPages.toVisit.add(new URL("https://vasart.github.io/supreme-potato/index.html"));
            while (!githubPages.toVisit.isEmpty()) {
                future = table.submit(new WebCrawler.UrlVistior());
                futureSet = future.get().toArray();
            }
            table.shutdown();
        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("https://vasart.github.io/supreme-potato/social.html", futureSet[3].toString());
    }

}