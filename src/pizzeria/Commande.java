package pizzeria;

import java.sql.*;
import java.util.Collections;

import static pizzeria.Main.database;

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

    public String toString() {
        String response = "* --------------- * \n* Commande n° " + idCommande + " * \n* Pizza Quantite  Total *\n";
        ResultSet listePizza = database.getPizzaInCommand(idCommande);
        int quantite;
        try{
            while(listePizza.next()){
                Pizza pizzaAafficher = new Pizza(listePizza.getInt(1));
                quantite = listePizza.getInt(2);
                response += "* " + pizzaAafficher.getNomPizza() + "  x" + quantite + "  " + (float) (Math.round(quantite * pizzaAafficher.getPrixPizza() *100)/100) + " *\n";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        response += "* Total : " + Math.round(montantCommande * 100) / 100 + " *\n* --------------- *";
        return response;
    }
}

