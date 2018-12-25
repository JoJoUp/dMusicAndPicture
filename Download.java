import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download extends Thread {
    private URL url;
    private String file;
    private String type;

    Download(String url, String file,String type) throws MalformedURLException {
        this.url = new URL(url);
        this.file = file;
        this.type = type;
    }

    public void run() {
        try (ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
             FileOutputStream stream = new FileOutputStream(file)) {
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            System.out.println("Скачивание файла " + file + " прошло успешно");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при скачивании файла " + file);
        }if(type == "mp3") {
            play();
        }
    }
    public void play() {
        try (FileInputStream inputStream = new FileInputStream(  file)) {
            try {
                Player player = new Player(inputStream);
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}