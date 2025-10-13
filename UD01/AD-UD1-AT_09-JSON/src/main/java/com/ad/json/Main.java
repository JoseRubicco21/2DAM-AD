package com.ad.json;
import java.util.Scanner;
import com.ad.json.menu.implementations.MainMenu;
import com.ad.json.menu.Menu;
import com.ad.json.menu.exceptions.InvalidInputException;
import com.ad.json.menu.exceptions.InvalidOptionException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new MainMenu(sc);

        while (menu.isActive()) {
            try{
                menu.display().choose(sc).execute();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (InvalidOptionException e){
                System.out.println(e.getMessage());
            }
        }
    }
}