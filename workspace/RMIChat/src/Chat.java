import java.rmi.Remote;


public interface Chat extends Remote {
	
	String receiveMessage(String user);
	
	void sendMessage( String message, String user);

}
