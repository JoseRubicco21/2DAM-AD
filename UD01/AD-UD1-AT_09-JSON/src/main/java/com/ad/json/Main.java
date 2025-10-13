package com.ad.json;
import java.util.Scanner;
import com.ad.json.menu.implementations.MainMenu;
import com.ad.json.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new MainMenu(sc);
    }
}