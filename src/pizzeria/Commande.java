package pizzeria;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int idCommande;
    private double montantCommande;
    private ArrayList<Pizza> listPizza;

    public Commande (int id, double montant, ArrayList<Pizza> listPizza) {
        setIdCommande(idCommande);
        setMontantCommande(montantCommande);
        setListPizza(listPizza);
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
}

