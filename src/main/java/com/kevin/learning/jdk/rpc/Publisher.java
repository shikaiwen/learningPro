package com.kevin.learning.jdk.rpc;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 示例学习地址
 * http://www.cnblogs.com/leslies2/archive/2011/05/20/2051844.html
 * @author root
 *
 */

public class Publisher {

	public static void main(String[] args) throws RemoteException, Exception, AlreadyBoundException {
		PersonService personService=new PersonServiceImpl();
		LocateRegistry.createRegistry(6600);
		Naming.bind("rmi://127.0.0.1:6600/PersonService", personService);
		System.out.println("PersonService started ...");
	}
}
