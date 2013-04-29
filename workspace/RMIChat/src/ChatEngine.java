import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class ChatEngine  implements Chat{
	
	/**
	 * The HashMap mapping a name of user to a Queue of messages. The queue of messages represents the messages 
	 * to receive for a user
	 */
	private HashMap<String, LinkedList<String>> messages;
	
	
	public ChatEngine() {
		super();
		this.messages = new HashMap<String, LinkedList<String>>();
	}

	@Override
	public String receiveMessage(String user) {
		try{
			return this.messages.get(user).removeLast();
		}catch(NoSuchElementException e){
			return "";
		}
	}

	@Override
	public void sendMessage(String message, String user) {
		LinkedList<String> tmp = this.messages.get(user);
		tmp.addFirst(message);
		messages.put(user, tmp);
	}
	
	 public static void main(String[] args) {
	        if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
	        try {
	            String name = "Chat";
	            Chat engine = new ChatEngine();
	            Chat stub =
	                (Chat) UnicastRemoteObject.exportObject(engine, 0);
	            Registry registry = LocateRegistry.getRegistry();
	            
	            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress()+"/"+name;
	            
	            registry.rebind(url, stub);
	            System.out.println("ComputeEngine bound");
	        } catch (Exception e) {
	            System.err.println("ComputeEngine exception:");
	            e.printStackTrace();
	        }
	    }
	
	

}
