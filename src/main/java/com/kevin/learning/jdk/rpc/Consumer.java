package com.kevin.learning.jdk.rpc;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Consumer {

	public static void main(String[] args) throws Exception, RemoteException, NotBoundException {
		
		PersonService personService = (PersonService)Naming.lookup("rmi://127.0.0.1:6600/PersonService");
		List<PersonEntity> pList = personService.GetList();
		
		System.out.println(personService);
		
		
	}
}
