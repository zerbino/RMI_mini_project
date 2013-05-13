package client;

import java.io.Console;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.IChat;

public class Client implements IClient {

	private String id;
	private IChat chat;
	
	public Client(String id, IChat chat){
		this.setId(id);
		this.setChat(chat);
	}
	
	@Override
	public void envoyerMessage(String s) throws RemoteException{
		chat.recevoirMessage(s);
		System.out.println("2/ Message send: " + s);
	}

	@Override
	public void receptionnerMessage(String s) throws RemoteException{
		System.out.println("5/ Message received by client: " + s);
	}

	public IChat getChat() {
		return chat;
	}

	public void setChat(IChat chat) {
		this.chat = chat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	 public static void main(String args[]) {
		 //if (System.getSecurityManager() == null) {
		//	 System.setSecurityManager(new SecurityManager());
		 //}
		 try {
			 String name = "ServerChat";
			 Registry registry = LocateRegistry.getRegistry();
			 IChat chat = (IChat) registry.lookup(name);
			 System.out.println("Serveur trouvé");
	     
			 IClient client1 = new Client("client1", chat);
			 // question
			 IClient stub1 = (IClient) UnicastRemoteObject.exportObject(client1, 2);
			 System.out.println("---2");
			 chat.addClient(stub1);
			 System.out.println("---3");
			 
			 Console c = System.console();
			 if (c == null) {
				 System.err.println("No console.");
				 System.exit(1);
			 }
			 String message = "";
			 
			 while (message != "exit") {
				 message = c.readLine("Enter a message: ");
				 System.out.println("1/ Message read from keyboard: " + message);
				 client1.envoyerMessage(message);
			 }
	     
		 } catch (Exception e) {
			 System.err.println("Client exception:");
			 e.printStackTrace();
		 }
	 }    
}

