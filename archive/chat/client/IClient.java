package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote{

	public void envoyerMessage(String s) throws RemoteException;
	
	public void receptionnerMessage(String s) throws RemoteException;
	
	public String getId() throws RemoteException;
}
