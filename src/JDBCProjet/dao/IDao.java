package JDBCProjet.dao;

import java.sql.SQLException;
import java.util.List;


public interface IDao <T>{
	boolean create ( T o) throws SQLException; //Méthode permettant d'ajouter un objet o de type T.
	List<T> findAll ( )throws SQLException;// Méthode permettant de renvoyer la liste des objets de type T.
	T findById (int id)throws SQLException;// Méthode permettant de renvoyer un objet dont id est passé enparamètre.
	boolean delete (T o)throws SQLException;//Méthode permettant de supprimer un objet o de type T.
	boolean update (T o)throws SQLException; // Méthode permettant de modifier un objet o de type T.
	
}
