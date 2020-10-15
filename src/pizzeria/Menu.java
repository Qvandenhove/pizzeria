package pizzeria;

import java.util.Scanner;

public class Menu {
	private static Scanner scan = new Scanner(System.in);
	
	public static int MainMenu() {
		System.out.println("1. Gérer les pizzas");
		System.out.println("2. Gérer les commandes");
		System.out.println("0. Arrêt du systéme");
		System.out.println("--------------");
		System.out.println("Choisissez une option");
		int user = scan.nextInt();
		return user;
	}
		
	public static int MenuPizza() {
		System.out.println("1. Ajouter une pizzas");
		System.out.println("2. Modifier une pizza");
		System.out.println("3. Supprimer une pizza");
		System.out.println("4. Afficher les pizzas");
		System.out.println("0. Revenir au menu principale");
		System.out.println("--------------");
		System.out.println("Choisissez une option");
		int user = scan.nextInt();
		return user;
	}

	public static int MenuCommande( ) {
		System.out.println("1. Ajouter une nouvelle commande");
		System.out.println("2. Supprimer une pizza de la commande");
		System.out.println("3. Payer la commande");
		System.out.println("4. Ajouter une pizza à une commande");
		System.out.println("5. Récapitulatif commande");
		System.out.println("0. Revenir au menu principale");
		System.out.println("--------------");
		System.out.println("Choisissez une option");
		int user = scan.nextInt();
		return user;
	}
}
