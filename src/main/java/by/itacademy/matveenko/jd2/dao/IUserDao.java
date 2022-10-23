package by.itacademy.matveenko.jd2.dao;

import java.sql.SQLException;

import by.itacademy.matveenko.jd2.bean.User;
import by.itacademy.matveenko.jd2.service.ServiceException;

public interface IUserDao {	
	User findUserByLogin(String login) throws DaoException;
	boolean saveUser(User user) throws DaoException, ServiceException;
	User findById(Integer id) throws SQLException, DaoException;	
}