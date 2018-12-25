
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;


public class Main {
    private static String INPUT_OF_FILE = "src\\inFile.txt";
    private static String PATH_TO_MUSIC = "";

    public static void main(String[] args) throws InterruptedException {
        String Url;
        String file;
        String[] array = new String[4];
        int count = 0;
        try (BufferedReader inFile = new BufferedReader(new FileReader(INPUT_OF_FILE))) {
            while ((Url = inFile.readLine()) != null) {
                for (String k : Url.split(" ")) {
                    array[count] = k;
                    count++;
                }
            }
            PATH_TO_MUSIC = array[3];
            Download downloadMusicParallel = new Download(array[0], array[1], "jpg");
            Download downloadPictureParallel = new Download(array[2], PATH_TO_MUSIC, "mp3");


            downloadPictureParallel.start();
            downloadMusicParallel.start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void play() {

    }
}
