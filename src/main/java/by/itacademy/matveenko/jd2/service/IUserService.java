package by.itacademy.matveenko.jd2.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import by.itacademy.matveenko.jd2.bean.User;

public interface IUserService extends UserDetailsService {	
	User signIn(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;
}