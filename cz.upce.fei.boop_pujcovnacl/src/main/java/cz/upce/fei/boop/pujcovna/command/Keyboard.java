
package cz.upce.fei.boop.pujcovna.command;

import java.util.Scanner;


public class Keyboard {
    private static Scanner sc = new Scanner(System.in);
    
   public static String getStringItem(String cmd) {
        if (cmd.length() != 0) {
            System.out.println(cmd);
        }
        return sc.nextLine().trim();
    }
    
   
}
