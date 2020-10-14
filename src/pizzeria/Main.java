package pizzeria;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    static ArrayList<Pizza> pizzaDisponible = new ArrayList<>();
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
        Pizza pizzaAModifier = null;
        int userAction;
        do{
            userAction = Menu.MenuPizza();
            switch(userAction){
                case 1:
                    int idPizza = pizzaDisponible.size() == 0 ? 0 : pizzaDisponible.get(pizzaDisponible.size() - 1).getIdPizza();
                    System.out.print("Nom de la nouvelle pizza : ");
                    String newName = scan.next();
                    System.out.print("Prix de la nouvelle pizza : ");
                    double newPrice = scan.nextDouble();
                    pizzaDisponible.add(new Pizza(idPizza, newName, newPrice));
                    DisplayPizzas();
                    break;
                case 2:
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

    public static void GestionCommande(){

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
}
