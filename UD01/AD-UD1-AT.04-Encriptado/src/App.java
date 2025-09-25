import java.io.File;

import encrypter.Encrypter;

public class App {
    public static void main(String[] args) throws Exception {
        File codecFile = new File("./src/codec.txt");
        FileBuilder fb = new FileBuilder("./src");
        Encrypter encrypter = new Encrypter(codecFile, fb);
    }
}
