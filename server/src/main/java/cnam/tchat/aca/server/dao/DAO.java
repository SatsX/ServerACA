package cnam.tchat.aca.server.dao;

public interface DAO<T> {
	
	public T find(Object id) throws DAOException;
	public void create(T obj) throws DAOException;
	public void update(T obj) throws DAOException;
	public void delete(T obj) throws DAOException;

}
