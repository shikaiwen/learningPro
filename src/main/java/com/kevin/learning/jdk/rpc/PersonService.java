package com.kevin.learning.jdk.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PersonService extends Remote{

	public List<PersonEntity> GetList() throws RemoteException;
}
