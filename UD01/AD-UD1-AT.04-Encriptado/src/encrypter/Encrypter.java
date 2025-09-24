package encrypter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Encrypter {

    private File codecFile;
    private Map<String, String> encryptedMap;
    private Map<String, String> decryptedMap;


    public Encrypter(File codecFile) {
        this.codecFile = codecFile;
        initMaps();
    }

    public File getCodecFile() {
        return codecFile;
    }

    public void setCodecFile(File codecFile) {
        this.codecFile = codecFile;
    }

    public Map<String, String> getEncryptedMap() {
        return encryptedMap;
    }

    public void setEncryptedMap(Map<String, String> encryptedMap) {
        this.encryptedMap = encryptedMap;
    }

    public Map<String, String> getDecryptedMap() {
        return decryptedMap;
    }

    public void setDecryptedMap(Map<String, String> decryptedMap) {
        this.decryptedMap = decryptedMap;
    }

    private void initMaps() throws ArrayStoreException {
        try(BufferedReader bf = new BufferedReader(new FileReader(codecFile))){
            String[] base = bf.readLine().trim().split("");
            String[] key = bf.readLine().trim().split("");

            if (base.length != key.length) throw new ArrayStoreException("codec does not match 1:1 aborting.");
            
            this.setEncryptedMap(mapCodecs(base, key));
            this.setDecryptedMap(mapCodecs(key, base));


        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private Map<String, String> mapCodecs(String[] sourceArray, String[] targetArray){
        Map<String, String> resultMap = new HashMap<String, String>();

        for(int i = 0; i < sourceArray.length; i++){
            resultMap.put(sourceArray[i], targetArray[i]);
        }

        return resultMap;
    }

    public String valorEncriptado(char value, Map<String, String> encodeMap){
        return encodeMap.getOrDefault(String.valueOf(value), "");
    }   

    // This is the same implementation as the ValorEncriptado function.
    // As the implementation is using symmetric encription. So technically
    // It is uneccesary.
    public String valorDesencriptado(char value, Map<String, String> decodeMap){
        return decodeMap.getOrDefault(String.valueOf(value),"");
    }

    public void encriptar(File file){
        try(FileInputStream fs = new FileInputStream(file)){
            byte[] byteChars = fs.readAllBytes();
                String input = new String(byteChars, StandardCharsets.UTF_8);
                String res = input.chars()
                  .mapToObj(c -> valorEncriptado((char)c, decryptedMap))
                  .collect(Collectors.joining());
            String filename = file.getName().split("\\.")[0];
            File destination = new File("./src/datos/encrypt/"+filename+"_codificado");
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(destination))){
                bw.write(res);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void desencriptar(File file){
     try(FileInputStream fs = new FileInputStream(file)){   
        byte[] byteChars = fs.readAllBytes();
            
        String input = new String(byteChars, StandardCharsets.UTF_8);
        String res = input.chars()
                    .mapToObj(c -> valorEncriptado((char)c, decryptedMap))
                    .collect(Collectors.joining());


            String filename = file.getName().split("\\.")[0];
            File destination = new File("./src/datos/decrypt/"+ filename +"_decodificado");
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(destination))){
                bw.write(res);
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
    
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

