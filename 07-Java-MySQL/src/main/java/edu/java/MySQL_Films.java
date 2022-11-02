package edu.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL_Films {
    private Connection connexion = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void traitementAvecLaBD() throws Exception {
        try {
            // Chargement du pilote MySQL, chaque BD a son propre pilote
            //Class.forName("com.mysql.jdbc.Driver");
            // Connexion à  la base de données
            connexion = DriverManager
                    .getConnection("jdbc:mysql://localhost/umbdfilms?"
                            + "user=root&password=");

            // Statement permet d'envoyer des requêtes à la base de données
            statement = connexion.createStatement();
            // ResultSet est le résultat de la requête
            resultSet = statement
                    .executeQuery("SELECT * FROM films");
            // Appel de la méthode pour afficher le résultat 
            System.out.println("\n********* DONNÉES AU DÉPART ************");
            afficherResultat(resultSet);


            // PreparedStatements pour des rquêtes paramétrées
            preparedStatement = connexion
                    .prepareStatement("INSERT INTO  films VALUES (0, ?, ?, ?, ? , ?, ?)");
            // Les paramétres commencent à 1
            preparedStatement.setString(1, "Rapides et dangereux 2");
            preparedStatement.setInt(2, 180);
            preparedStatement.setString(3, "Un autre");
            preparedStatement.setString(4, "Aucune");
            preparedStatement.setDouble(5, 4.25);
            preparedStatement.setString(6, "https://ia.media-imdb.com/images/M/MV5BMTU4NzMyNDk1OV5BMl5BanBnXkFtZTcwOTEwMzU1MQ@@._V1_SX300.jpg");
            preparedStatement.executeUpdate();

            preparedStatement = connexion
                    .prepareStatement("SELECT * from FILMS");
            resultSet = preparedStatement.executeQuery();
            System.out.println("\n********* DONNÉES APRÈS L'AJOUT ************");
            afficherResultat(resultSet);

            // Supprimer un film
            preparedStatement = connexion
                    .prepareStatement("DELETE FROM films WHERE idf= ? ; ");
            preparedStatement.setInt(1, 14);
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("SELECT * FROM films");
            System.out.println("\n********* DONNÉES APRÈS SUPPRÉSION ************");
            afficherResultat(resultSet);
            
            // Modifier un film
            preparedStatement = connexion
                    .prepareStatement("UPDATE films SET duree=? WHERE idf= ? ; ");
            preparedStatement.setInt(1, 120);
            preparedStatement.setInt(2, 15);
            preparedStatement.executeUpdate();

            resultSet = statement
                    .executeQuery("SELECT * FROM films");
            System.out.println("\n********* DONNÉES APRÈS MODIFICATION ************");
            afficherResultat(resultSet);

            afficherMetaResultat(resultSet);

        } catch (Exception e) {
            throw e;
        } finally {
            fermer();
        }

    }

    private void afficherMetaResultat(ResultSet resultSet) throws SQLException {
        // Afficher les meta données de la base de données
        System.out.println("\n********* META DONNÉES ************");
        System.out.println("Les colonnes de la table sont : ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Colonne " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
        System.out.println("---------------------------");
    }

    private void afficherResultat(ResultSet resultSet) throws SQLException {
        // ResultSet : le curseur est avant  le premier élément.
        // Au début resultSet.next() place le curseur sur le premier élément
        while (resultSet.next()) {
            // Il est possible d'obtenir la colonne par son nom
            // mais aussi possible d'obtenir par son numéro,
            // qui débute à 1.
            // Par exemple : resultSet.getSTring(2);
            String titre = resultSet.getString("titre");
            int duree = resultSet.getInt("duree");
            String res = resultSet.getString("res");
            
            System.out.println("Titre : " + titre);
            System.out.println("Durée : " + duree);
            System.out.println("Réalisateur : " + res);
            System.out.println("---------------------------");
        }
    }

    // Libérer les ressources
    private void fermer() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connexion != null) {
                connexion.close();
            }
        } catch (Exception e) {

        }
    }
}
