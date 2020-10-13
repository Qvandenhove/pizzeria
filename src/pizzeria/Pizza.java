package pizzeria;

public class Pizza {

    private int idPizza;
    private String nomPizza;
    private double prixPizza;

    public Pizza (int id, String nom, double prix) {
        setIdPizza(idPizza);
        setNomPizza(nomPizza);
        setPrixPizza(prixPizza);
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
}
