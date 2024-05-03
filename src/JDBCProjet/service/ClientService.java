package JDBCProjet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.dao.IDao;

public class ClientService implements IDao<Client> {

    @Override
    public boolean create(Client o) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStat = null;
        boolean success = false;

        try {
            conn = Connexion.getConnexion();
            String query = "INSERT INTO client (nom, prénom) VALUES (?, ?)";

            preparedStat = conn.prepareStatement(query);

            preparedStat.setString(1, o.getNom());
            preparedStat.setString(2, o.getPrénom());
            int rowsInserted = preparedStat.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un client a été ajouté avec succès.");
                success = true;
            }
        } finally {
            // Fermeture des ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            if (preparedStat != null) {
                preparedStat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return success;
    }

    @Override
    public List<Client> findAll() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Client> clients = new ArrayList<>();

        try {
            conn = Connexion.getConnexion();
            String query = "SELECT * FROM client";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prénom");
                Client client = new Client(id, nom, prenom);
                clients.add(client);
            }
        } finally {
            // Fermeture des ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return clients;
    }

    @Override
    public Client findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStat = null;
        ResultSet resultSet = null;
        Client client = null;

        try {
            conn = Connexion.getConnexion();
            String query = "SELECT * FROM client WHERE id=?";
            preparedStat = conn.prepareStatement(query);
            preparedStat.setInt(1, id);
            resultSet = preparedStat.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prénom");
                client = new Client(id, nom, prenom);
            }
        } finally {
            // Fermeture des ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStat != null) {
                preparedStat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return client;
    }

    @Override
    public boolean delete(Client o) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStat = null;
        boolean success = false;

        try {
            conn = Connexion.getConnexion();
            String query = "DELETE FROM client WHERE id=?";
            preparedStat = conn.prepareStatement(query);
            preparedStat.setInt(1, o.getId());
            int rowsDeleted = preparedStat.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le client a été supprimé avec succès.");
                success = true;
            }
        } finally {
            // Fermeture des ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            if (preparedStat != null) {
                preparedStat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return success;
    }

    @Override
    public boolean update(Client o) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStat = null;
        boolean success = false;

        try {
            conn = Connexion.getConnexion();
            String query = "UPDATE client SET nom=?, prénom=? WHERE id=?";
            preparedStat = conn.prepareStatement(query);
            preparedStat.setString(1, o.getNom());
            preparedStat.setString(2, o.getPrénom());
            preparedStat.setInt(3, o.getId());
            int rowsUpdated = preparedStat.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Le client a été mis à jour avec succès.");
                success = true;
            }
        } finally {
            // Fermeture des ressources dans le bloc finally pour s'assurer qu'elles sont toujours fermées
            if (preparedStat != null) {
                preparedStat.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return success;
    }
}
