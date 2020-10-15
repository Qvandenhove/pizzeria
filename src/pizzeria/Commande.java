package pizzeria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commande {
    private int idCommande;
    private double montantCommande;

    private boolean estPaye;

    public Commande (int id, double montant, boolean estPaye) {
        setIdCommande(id);
        setMontantCommande(montant);
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

    public boolean isEstPaye() {
        return estPaye;
    }

    public void setEstPaye(boolean estPaye) {
        this.estPaye = estPaye;
    }

//    @Override
//    public String toString() {
//        String response = "* --------------- * \n* Commande n° " + idCommande + " * \n* Pizza Quantite  Total *\n";
//        ArrayList<Pizza> copyListPizza = new ArrayList<>();
//        copyListPizza.addAll(listPizza);
//        int quantite;
//        for (int pizza = 0;pizza < copyListPizza.size(); pizza++){
//            Pizza pizzaAafficher = copyListPizza.get(pizza);
//            quantite = Collections.frequency(copyListPizza, pizzaAafficher);
//            response += "* " + pizzaAafficher.getNomPizza() + "  x" + quantite + "  " + (float) (Math.round(quantite * pizzaAafficher.getPrixPizza() *100)/100) + " *\n";
//            copyListPizza.remove(pizzaAafficher);
//        }
//        response += "* Total : " + Math.round(montantCommande * 100) / 100 + " *\n* --------------- *";
//        return response;
//    }
}

