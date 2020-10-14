package pizzeria;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    static ArrayList<Pizza> pizzaDisponible = new ArrayList<>();
    static ArrayList<Commande> commandes = new ArrayList<>();
    public static void main(String[] args){
        Database database = new Database("jdbc:mysql://localhost:3306/pizzeria", "root", "");
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
        Pizza pizzaAModifier = null;
        int userAction;
        do{
            userAction = Menu.MenuPizza();
            switch(userAction){
                case 1:
                    // Ajouter une pizza au stock
                    int idPizza = pizzaDisponible.size() == 0 ? 0 : pizzaDisponible.get(pizzaDisponible.size() - 1).getIdPizza();
                    System.out.print("Nom de la nouvelle pizza : ");
                    String newName = scan.next();
                    System.out.print("Prix de la nouvelle pizza : ");
                    double newPrice = scan.nextDouble();
                    pizzaDisponible.add(new Pizza(idPizza, newName, newPrice));
                    DisplayPizzas();
                    break;
                case 2:
                    // Modifier une pizza en stock
                    DisplayPizzas();
                    System.out.println("Quel est l'identifiant de la pizza à modifier ?");
                    idPizzaAModifier = scan.nextInt();
                    pizzaAModifier = getPizzaById(idPizzaAModifier);
                    if(pizzaAModifier != null){
                        System.out.print("Nouveau nom de la pizza : ");
                        pizzaAModifier.setNomPizza(scan.next());
                        System.out.print("Nouveau prix de la pizza : ");
                        pizzaAModifier.setPrixPizza(scan.nextDouble());
                        DisplayPizzas();
                    }
                    break;
                case 3:
                    // Supprimer une pizza du stock
                    DisplayPizzas();
                    System.out.println("Quel est l'identifiant de la pizza à supprimer ?");
                    idPizzaAModifier = scan.nextInt();
                    pizzaAModifier = getPizzaById(idPizzaAModifier);
                    if(pizzaAModifier != null){
                        pizzaDisponible.remove(pizzaAModifier);
                        System.out.println("Pizza supprimée.");
                        DisplayPizzas();
                    }
                    break;
            }
        }while(userAction != 0);

    }

    public static Pizza getPizzaById(int idCherche){
        for(int i = 0;i < pizzaDisponible.size();i++){
            if (idCherche == pizzaDisponible.get(i).getIdPizza()){
                return pizzaDisponible.get(i);
            }
        }
        return null;
    }

    public static void DisplayPizzas(){
        Pizza pizza;
        System.out.println();
        System.out.println("* --------------- *");
        for(int i = 0;i< pizzaDisponible.size();i++){
            pizza = pizzaDisponible.get(i);
            System.out.println(pizza);
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
                    idCommande = commandes.size() == 0 ? 0 : commandes.get(commandes.size() - 1 ).getIdCommande();
                    commandes.add(new Commande(idCommande, 0, new ArrayList<Pizza>(), false));
                    System.out.println("Commande numéro " + idCommande + " ajoutée");
                    break;
                case 2:
                    // Supprimer une pizza à la commande
                    DisplayCommandes();
                    System.out.print("Quel est l'id de la commande à supprimer ?");
                    idCommande = scan.nextInt();
                    commande = getCommandeById(idCommande);
                    if (commande != null){
                        commandes.remove(commande);
                    }else{
                        System.out.println("Cette commande n'existe pas.");
                    }
                    break;
                case 3:
                    // Payer une commande
                    break;
                case 4:
                    // Ajouter une pizza à la commande
                    DisplayCommandes();
                    System.out.println("A quel commande voulez ajouter des pizzas ?");
                    idCommande = scan.nextInt();
                    commande = getCommandeById(idCommande);
                    if(commande != null){
                        DisplayPizzas();
                        System.out.println("Quel pizza voulez vous ajouter (Saisir l'id)?");
                        idPizza = scan.nextInt();
                        pizzaCommande = getPizzaById(idPizza);
                        if(pizzaCommande != null){
                            System.out.println("Combien en voulez vous ?");
                            quantiteCommande = scan.nextInt();
                            for(int i = 0;i < quantiteCommande;i++){
                                listPizzaCommande = commande.getListPizza();
                                listPizzaCommande.add(pizzaCommande);
                                commande.setListPizza(listPizzaCommande);
                            }
                            System.out.println(quantiteCommande + " " + pizzaCommande.getNomPizza() + " ajoutées à la commande");
                        }

                    }

                    break;
                case 5:
                    // Récapitulatif de commande
                    System.out.println("Quel est la commande qui demande un récapitulatif");
                    idCommande = scan.nextInt();
                    commande = getCommandeById(idCommande);
                    if (commande != null){
                        System.out.println(commande);
                    }
                    break;
                default:
                    // Gestion des options
            }

        }while(userAction != 0);
    }

    public static Commande getCommandeById(int idCommande){
        Commande commande;
        for (int i = 0;i < commandes.size(); i++){
            if (idCommande == commandes.get(i).getIdCommande()){
                return commandes.get(i);
            }
        }
        return null;
    }

    public static void DisplayCommandes(){
        Commande commande;
        System.out.println();
        System.out.println("* --------------- *");
        for(int i = 0;i< commandes.size();i++){
            commande = commandes.get(i);
            System.out.println(commande);
        }

        System.out.println("* --------------- *");
        System.out.println();
    }


}
