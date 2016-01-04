package dao;



import java.text.ParseException;

import entities.User;
import exception.AuthenticationException;
import exception.ConfirmationException;
import exception.EntityNotFoundException;

public interface UserDAO extends DaoInterface<User, Long> {
	
	public User getUserByUsername(String username) throws EntityNotFoundException;
	
	public User getUserByEmail(String email) throws EntityNotFoundException;
	 
	public User authenticatePersonUsingUsername(String username, String password) throws AuthenticationException;
	
	public User authenticatePersonUsingEmail(String email, String password) throws AuthenticationException;
	
	public User authenticatePersonViaSocialN(String username, String password) throws AuthenticationException;
	
	public boolean confirmInscription(String email, String token) throws ConfirmationException;
	
	
	public boolean findEmail(String email) throws EntityNotFoundException;
	
	
	// Infos={Nom,Prenom,MotdePasse,dateDeNaissance}
	public User modifierInformation(User user,Object... Infos) throws ParseException;

}
