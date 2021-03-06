import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Chat extends Remote {
	
	String receiveMessage(String user) throws RemoteException;
	
	void sendMessage( String message, String user) throws RemoteException;

}
