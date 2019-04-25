package br.com.thebooks.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionSingleton {
	private SessionFactory sessionFactory;
	private static SessionSingleton sessionSingleton;
	
	private SessionSingleton(){
		if(sessionFactory == null || sessionFactory.isClosed()){
			this.sessionFactory = new Configuration().configure().buildSessionFactory();
		}
	}
	public static SessionSingleton getInstanceSession(){
		if(sessionSingleton == null){
			sessionSingleton = new SessionSingleton();
		}
		return sessionSingleton;
		
	}
	public SessionFactory getInstanceSessionFactory(){
		return sessionFactory;
	}
}
