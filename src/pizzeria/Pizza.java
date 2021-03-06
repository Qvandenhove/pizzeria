package pizzeria;

import static pizzeria.Main.database;

public class Pizza {

    private int idPizza;
    private String nomPizza;
    private double prixPizza;

    public Pizza (int id, String nom, double prix) {
        setIdPizza(id);
        setNomPizza(nom);
        setPrixPizza(prix);
    }

    public Pizza(int id){
        Pizza pizza = database.getPizza(id);
        setPrixPizza(pizza.getPrixPizza());
        setNomPizza(pizza.getNomPizza());
        setIdPizza(pizza.getIdPizza());
    }

    public int getIdPizza() {
        return idPizza;
    }
             
    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getNomPizza() {
        return nomPizza;
    }

    public void setNomPizza(String nomPizza) {
        this.nomPizza = nomPizza;
    }

    public double getPrixPizza() {
        return prixPizza;
    }

    public void setPrixPizza(double prixPizza) {
        this.prixPizza = prixPizza;
    }

    @Override
    public String toString() {
        return getIdPizza() + "  " + getNomPizza() + "  " + getPrixPizza();
    }
}
