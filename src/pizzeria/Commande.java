package pizzeria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commande {
    private int idCommande;
    private double montantCommande;
    private ArrayList<Pizza> listPizza;

    private boolean estPaye;

    public Commande (int id, double montant, ArrayList<Pizza> listPizza, boolean estPaye) {
        setIdCommande(idCommande);
        setMontantCommande(montantCommande);
        setListPizza(listPizza);
        setEstPaye(estPaye);
    } 

    public int getIdCommande() {
        return idCommande;
    } 
    
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public double getMontantCommande() {
        return montantCommande;
    }

    public void setMontantCommande(double montantCommande) {
        this.montantCommande = montantCommande;
    }

    public ArrayList<Pizza> getListPizza() {
        return listPizza;
    }

    public void setListPizza(ArrayList<Pizza> listPizza) {
        this.listPizza = listPizza;
    }

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }

    @Override
    public String toString() {
        String response = "* --------------- * \n* Commande n° " + idCommande + " * \n* Pizza Quantite  Total *\n";
        ArrayList<Pizza> copyListPizza = new ArrayList<>();
        listPizza.addAll(copyListPizza);
        int quantite;
        for (int pizza = 0;pizza < listPizza.size(); pizza++){
            Pizza pizzaAafficher = copyListPizza.get(pizza);
            quantite = Collections.frequency(copyListPizza, pizzaAafficher);
            response += "* " + pizzaAafficher.getNomPizza() + " " + quantite + (quantite * pizzaAafficher.getPrixPizza()) + " *";
            copyListPizza.remove(pizzaAafficher);
        }
        response += "* Total : " + montantCommande + " *\n* --------------- *";
        return response;
    }
}

