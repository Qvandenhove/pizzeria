package pizzeria;
import java.sql.*;

public class Database {
    private Connection connec;

    public Connection getConnec(){
        return this.connec;
    }

    public void setConnec(Connection connec){
        this.connec = connec;
    }
    public Database(String link, String user, String pwd){
        try{
            System.out.println("Bonjour");
            Class.forName("com.mysql.jdbc.Driver");
            setConnec( DriverManager.getConnection(link, user, pwd));
        }catch(Exception e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");
        }
    }

    public void insertPizza(String nom, double prix){
        try{
            connec.setAutoCommit(false);
            PreparedStatement addPizza = getConnec().prepareStatement("INSERT INTO pizza (nom, prix) VALUE(?, ?)");
            addPizza.setString(1, nom);
            addPizza.setDouble(2, prix);
            addPizza.executeUpdate();
            connec.commit();

        }catch(SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }

    }

    public ResultSet getPizzas(){
        ResultSet rsult = null;
        try{
            Statement stmt = connec.createStatement();
            rsult = stmt.executeQuery("SELECT * FROM pizza");

        }catch(SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }
        return rsult;
    }

    public void updatePizzas(int id, String newName, double newPrice){
        try{
            connec.setAutoCommit(false);
            PreparedStatement updatePizza = getConnec().prepareStatement("UPDATE pizza SET nom=?, prix=? WHERE id=?");
            updatePizza.setString(1, newName);
            updatePizza.setDouble(2, newPrice);
            updatePizza.setInt(3, id);
            updatePizza.executeUpdate();
            connec.commit();
        }catch(SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }
    }

    public boolean checkPizza(int id){
        boolean isValid = false;
        try{
            ResultSet pizzas = getPizzas();
            while(pizzas.next() && !isValid){
                if (pizzas.getInt(1) == id){
                    isValid = true;
                }
            }
        }catch (SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }
        return isValid;
    }

    public Pizza getPizza(int id){
        Pizza searchedPizza = null;
        try {
            if (checkPizza(id)) {
                PreparedStatement getPizza = connec.prepareStatement("SELECT * FROM pizza WHERE id=?");
                getPizza.setInt(1, id);
                ResultSet result = getPizza.executeQuery();
                result.next();
                searchedPizza = new Pizza(result.getInt(1), result.getString(2), result.getDouble(3));
            }
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }
        return searchedPizza;
    }

    public boolean deletePizza(int id){
        boolean success = false;
        try{
            PreparedStatement deletePizza = connec.prepareStatement("DELETE FROM pizza WHERE id = ?");
            connec.setAutoCommit(false);
            deletePizza.setInt(1, id);
            success = deletePizza.executeUpdate() == 1;
            connec.commit();
        }catch(SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");

        }
        return success;
    }

    public void addCommande(){
        try{
            connec.setAutoCommit(false);
            Statement addCommande = connec.createStatement();
            addCommande.executeUpdate("INSERT INTO Commande (montant, estPaye) VALUES (0, 0)");
            connec.commit();
        }catch(SQLException e){
            // e.printStackTrace();
            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");
        }
    }

    public void addPizzaToCommand(int idCommande, int idPizza, int quantite){
        try{
            PreparedStatement addPizzaToCommand = connec.prepareStatement("INSERT INTO pizzaCommandee VALUE(?, ?, ?)");
            addPizzaToCommand.setInt(1, idCommande);
            addPizzaToCommand.setInt(2, idPizza);
            addPizzaToCommand.setInt(3, quantite);
            addPizzaToCommand.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
//            System.out.println("Une erreur est survenue, merci de reesayer plus tard.");
        }
    }

    public ResultSet getPizzaInCommand(int idCommande){
        ResultSet resultPizzas = null;
        try{
            PreparedStatement getPizzaInCommand = connec.prepareStatement("SELECT idPizza, quantite FROM pizzaCommandee WHERE idCommande = ?");
            getPizzaInCommand.setInt(1, idCommande);
            resultPizzas = getPizzaInCommand.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultPizzas;
    }

    public Commande getCommande(int idCommande){
        Commande commande = null;
        try {
            PreparedStatement getCommande = connec.prepareStatement("SELECT * from commande WHERE id = ?");
            getCommande.setInt(1, idCommande);
            ResultSet resultCommand = getCommande.executeQuery();
            resultCommand.next();
            commande = new Commande(resultCommand.getInt(1), resultCommand.getDouble(2), resultCommand.getBoolean(3));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return commande;
    }

    public boolean checkCommand(int id){
        boolean isValid = false;
        try{
            ResultSet commandes = getCommands();
            while(commandes.next() && !isValid){
                if (commandes.getInt(1) == id){
                    isValid = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public ResultSet getCommands(){
        ResultSet commandes = null;
        try{
            Statement getCommands = connec.createStatement();
            commandes = getCommands.executeQuery("SELECT * FROM commande");


        }catch(SQLException e){
            e.printStackTrace();
        }
        return commandes;
    }

    public Boolean DeleteCommand(int id) {
    	boolean success = false;
    	
    	try {
    		if(checkCommand(id)) {
    			PreparedStatement DeleteCommand = connec.prepareStatement("DELETE FROM commande WHERE id = ?");
    			connec.setAutoCommit(false);
                DeleteCommand.setInt(1, id);
                
                success = DeleteCommand.executeUpdate() == 1;
                connec.commit();
                System.out.println("La commande a été supprimé!");
    		}
    	}
    	catch(SQLException e){
    		System.out.println("La requete a rencontrer une erreur");
    	}
    	
    	return success;
    	
    }


    public void disconnect(){
        try{
            connec.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
