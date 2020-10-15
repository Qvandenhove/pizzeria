package pizzeria;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    static ArrayList<Commande> commandes = new ArrayList<>();
    static Database database = new Database("jdbc:mysql://localhost:3306/pizzeria", "root", "");
    public static void main(String[] args){
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
                case 0:

                    break;

            }
        } while(userAction != 0);
    }

    public static void GestionPizza(){
        int idPizzaAModifier;
        boolean isValidPizza;
        String newName;
        double newPrice;
        int userAction;
        do{
            userAction = Menu.MenuPizza();

            switch (userAction) {
                case 1 -> {
                    // Ajouter une pizza au stock
                    DisplayPizzas();
                    System.out.print("Nom de la nouvelle pizza : ");
                    newName = scan.next();
                    System.out.print("Prix de la nouvelle pizza : ");
                    newPrice = scan.nextDouble();
                    database.insertPizza(newName, newPrice);
                    DisplayPizzas();
                }
                case 2 -> {
                    // Modifier une pizza en stock
                    DisplayPizzas();
                    System.out.println("Quel est l'identifiant de la pizza à modifier ?");
                    idPizzaAModifier = scan.nextInt();
                    isValidPizza = database.checkPizza(idPizzaAModifier);
                    if (isValidPizza) {
                        System.out.print("Nouveau nom de la pizza : ");
                        newName = scan.next();
                        System.out.print("Nouveau prix de la pizza : ");
                        newPrice = scan.nextDouble();
                        database.updatePizzas(idPizzaAModifier, newName, newPrice);
                        DisplayPizzas();
                    }
                }
                case 3 -> {
                    // Supprimer une pizza du stock
                    DisplayPizzas();
                    System.out.println("Quel est l'identifiant de la pizza à supprimer ?");
                    idPizzaAModifier = scan.nextInt();
                    if (database.deletePizza(idPizzaAModifier)) {
                        System.out.println("Pizza supprimée.");
                    } else {
                        System.out.println("Aucune pizza avec cet identifient à été trouvée");
                    }
                    DisplayPizzas();
                }
                case 4 -> DisplayPizzas();
            }
        }while(userAction != 0);
    }

    public static void DisplayPizzas(){
        ResultSet resultListePizzas = database.getPizzas();
        System.out.println();
        System.out.println("* --------------- *");
        try{
            while(resultListePizzas.next()){
                System.out.println(new Pizza(resultListePizzas.getInt(1), resultListePizzas.getString(2), resultListePizzas.getDouble(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        System.out.println("* --------------- *");
        System.out.println();
    }

    public static void GestionCommande(){
        ArrayList<Pizza> listPizzaCommande;
        int userAction;
        int idCommande;
        int idPizza;
        Pizza pizzaCommande;
        int quantiteCommande;
        Commande commande;
        do{
            userAction = Menu.MenuCommande();
            switch(userAction){
                case 1:
                    // Ajouter une pizza à la commande
                    database.addCommande();
                    System.out.println("Nouvelle commande crée");
                    break;
                case 2:
                    // Supprimer une commande
                    DisplayCommandes();
                    System.out.print("Quel est l'id de la commande à supprimer ?");
                    idCommande = scan.nextInt();
                    commande = database.getCommande(idCommande);
                    if (commande != null){
                        commandes.remove(commande);
                    }else{
                        System.out.println("Cette commande n'existe pas.");
                    }
                    break;
                case 3:
                    // Payer une commande
                    PayerCommande();
                    break;
                case 4:
                    // Ajouter une pizza à la commande
                    DisplayCommandes();
                    System.out.println("A quel commande voulez ajouter des pizzas ?");
                    idCommande = scan.nextInt();
                    if(database.checkCommand(idCommande)){
                        DisplayPizzas();
                        System.out.println("Quel pizza voulez vous ajouter? (Saisir l'id)");
                        idPizza = scan.nextInt();
                        if(database.checkPizza(idPizza)){
                            pizzaCommande = database.getPizza(idPizza);
                            System.out.println("Combien de " + pizzaCommande.getNomPizza() + " voulez vous ?");
                            quantiteCommande = scan.nextInt();
                            database.addPizzaToCommand(idCommande, idPizza, quantiteCommande);
                            System.out.println(quantiteCommande + " " + pizzaCommande.getNomPizza() + " ajoutées à la commande");
                        }

                    }

                    break;
                case 5:
                    // Récapitulatif de commande
                    System.out.println("Quel est la commande qui demande un récapitulatif");
                    idCommande = scan.nextInt();
                    commande = database.getCommande(idCommande);
                    if (commande != null){
                        System.out.println(commande);
                    }
                    break;
                default:
                    // Gestion des options
            }

        }while(userAction != 0);
    }

       public static void PayerCommande(){
    	
    	do{
    		System.out.println("Quel est l'identifiant de la commande ?");
            int idCommande= scan.nextInt();
            Commande commande = getCommandeById(idCommande);
    		System.out.println(idCommande);

            double montant = commande.getMontantCommande();
    		System.out.println(montant); 	
    	}
    	while(false);

    public static void DisplayCommandes(){
        Commande commande;
        System.out.println();
        System.out.println("* --------------- *");
        for (Commande value : commandes) {
            commande = value;
            System.out.println(commande);
        }

        System.out.println("* --------------- *");
        System.out.println();
    }


}
