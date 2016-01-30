package dao;

import dao.impl.UserDaoImpl;

/**
 * 
 * @author sophia
 * 
 * cette classe peut être comme suit:
 * 
 * public enum SingletonDAO {
 * INSTANCE;
	
	private final static CoursDAO coursDAO = new CoursDAO();
	
	private SingletonDAO(){
		
	}
	
	public static CoursDAO getCoursDAO(){
		return coursDAO;
	}
}
 
 * à verifier pourquoi cette 2ème methode est avantageuse.
 */

public class SingletonDAO {
	
	private final static CoursDAO coursDAO = new CoursDAO();
	private final static ChapitreDAO chapitreDAO = new ChapitreDAO();
	private final static LeconDAO leconDAO = new LeconDAO();
	private final static CategorieDAO categorieDAO = new CategorieDAO();
	private final static MotCleDAO motCleDAO = new MotCleDAO();
	private final static RoleDao roleDAO = new RoleDao();

	
	
	private final static UserDAO userDao = new UserDaoImpl();
	
	private SingletonDAO(){	
	}
	
	public static CoursDAO getCoursDAO(){
		return coursDAO;
	}
	
	public static ChapitreDAO getChapitreDAO(){
		return chapitreDAO;
	}
	
	public static LeconDAO getLeconDAO(){
		return leconDAO;
	}

	public static CategorieDAO getCategoriedao() {
		return categorieDAO;
	}

	public static MotCleDAO getMotcledao() {
		return motCleDAO;
	}

	public static UserDAO getUserdao() {
		return userDao;
	}

	public static RoleDao getRoledao() {
		return roleDAO;
	}
}
