package pizzeria;

import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        boolean shutdown = false;
        int userAction;
        do{
            userAction = Menu.MainMenu();
            switch(userAction){
                case 1:
                    GestionPizza();
                    break;
                case 2:
                    GestionCommande();
                    break;
                default:
                    shutdown = true;
            }

        }while(shutdown);
    }

    public static void GestionPizza(){

    }

    public static void GestionCommande(){

    }
}
