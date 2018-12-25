import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download extends Thread {
    private URL url;
    private String file;

    Download(String url, String file) throws MalformedURLException {
        this.url = new URL(url);
        this.file = file;
    }

    public void run() {
        try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            System.out.println("Скачивание файла " + file + " прошло успешно");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при скачивании файла " + file);
        }
    }
}