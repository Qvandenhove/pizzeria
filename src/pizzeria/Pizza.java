package pizzeria;

public class Pizza {

    public int idPizza;
    public String nomPizza;
    public double prixPizza;

    public Pizza (int id, String nom, double prix) {
        this.idPizza = id;
        this.nomPizza = nom;
        this.prixPizza = prix;
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
}
