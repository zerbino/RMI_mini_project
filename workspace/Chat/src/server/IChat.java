package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.IClient;

public interface IChat extends Remote{

	public void recevoirMessage(String s) throws RemoteException;
	
	public void diffuserMessage(String s) throws RemoteException;

	public void addClient(IClient client1) throws RemoteException;

}
