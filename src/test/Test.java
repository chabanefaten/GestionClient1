package test;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.service.ClientService;

public class Test {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = Connexion.getConnexion();

            // Création des clients
            Client client1 = new Client("chabane", "faten");
            Client client2 = new Client("chadi", "b");
            Client client3 = new Client("ahmed", "mayssen");
            Client client4 = new Client("dotta", "lara");
            Client client5 = new Client("saif", "bm");

            // Création du service client
            ClientService clientService = new ClientService();

            // Ajout des clients
            clientService.create(client1);
            clientService.create(client2);
            clientService.create(client3);
            clientService.create(client4);
            clientService.create(client5);

            // Affichage de la liste des clients
            List<Client> clients = clientService.findAll();
            System.out.println("Liste des clients :");
            for (Client client : clients) {
                System.out.println(client);
            }

            // Affichage du client avec id = 3
            int idClient = 3;
            Client clientWithId3 = clientService.findById(idClient);
            if (clientWithId3 != null) {
                System.out.println("Client avec id=" + idClient + " : " + clientWithId3);
            } else {
                System.out.println("Aucun client trouvé avec id=" + idClient);
            }

            // Suppression du client avec id = 3
            if (clientService.delete(clientWithId3)) {
                System.out.println("Le client avec id=" + idClient + " a été supprimé avec succès.");
            } else {
                System.out.println("Impossible de supprimer le client avec id=" + idClient);
            }

            // Modification du client avec id = 2
            int idClient2 = 2;
            Client clientToUpdate = clientService.findById(idClient2);
            if (clientToUpdate != null) {
                clientToUpdate.setNom("NouveauNom");
                clientToUpdate.setPrénom("NouveauPrenom");
                if (clientService.update(clientToUpdate)) {
                    System.out.println("Le client avec id=" + idClient2 + " a été mis à jour avec succès.");
                } else {
                    System.out.println("Impossible de mettre à jour le client avec id=" + idClient2);
                }
            } else {
                System.out.println("Aucun client trouvé avec id=" + idClient2);
            }

            // Affichage de la liste des clients après les opérations
            System.out.println("Liste des clients après les opérations :");
            List<Client> updatedClients = clientService.findAll();
            for (Client client : updatedClients) {
                System.out.println(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion après utilisation
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
