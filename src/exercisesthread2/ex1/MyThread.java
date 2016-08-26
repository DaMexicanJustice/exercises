/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercisesthread2.ex1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import static java.nio.file.Files.size;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Patrick Johansen
 */
public class MyThread extends Thread {
    
    private int sum;
    private static long start = System.nanoTime();

    public MyThread(String url) throws IOException {
        sum = 0;
        byte[] bytes = getBytesFromUrl(url);
        for (byte b : bytes) {
            sum += b & 0xFF;
        }
    }
    
    public int getBytes() {
        return sum;
    }
    
    public long getLifeTime() {
        return start;
    }

    public static byte[] getBytesFromUrl(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.connect();
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
            InputStream is = connection.getInputStream();
            byte[] bytebuff = new byte[4096];
            int read;
            while ((read = is.read(bytebuff)) > 0) {
                bis.write(bytebuff, 0, read);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        start = System.nanoTime();
        return bis.toByteArray();
    }

}
