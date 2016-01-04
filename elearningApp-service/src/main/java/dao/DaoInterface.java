package dao;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author scupper
 *
 * @param <T> the entity type
 * @param <K> the key type
 */

public interface DaoInterface<T extends Serializable, K extends Serializable> {
	
	T create(T obj) throws Exception;
	T edit(T obj);
	T remove(K key);
	T findById(K key);
	Collection<T> findAll();
	Long count();
	
	interface Constants {
		String PERSISTANCE_UNIT_NAME = "elearning-service-PU";
	}
	
}