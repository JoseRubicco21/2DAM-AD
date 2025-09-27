import java.io.File;

import encrypter.Encrypter;

public class App {
    public static void main(String[] args) throws Exception {
        File codecFile = new File("./src/codec.txt");
        File baseFile = new File("./src/datos/decrypt/texto.txt");
        File encryptedFile = new File("./src/datos/encrypt/" + baseFile.getName().split("\\.")[0] + "_codificado");
        Encrypter encrypter = new Encrypter(codecFile);
        encrypter.encriptar(baseFile);
        encrypter.desencriptar(encryptedFile);
    }
}
