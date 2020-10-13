package pizzeria;

import java.util.Scanner;

public class Menu {
	private static Scanner scan = new Scanner(System.in);
	
	public static int MainMenu() {
		System.out.println("1. gérer les pizzas");
		System.out.println("2. gérer les commandes");
		System.out.println("0. Arrêt du systéme");
		System.out.println("--------------");
		System.out.println("Quelle est votre choix d'option?");
		int user = scan.nextInt();
		return user;
	}
}