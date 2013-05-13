
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.IClient;

public class Chat implements IChat {

	private List<IClient> clients = new ArrayList<IClient>();
	
	@Override
	public void recevoirMessage(String s) throws RemoteException{
		// TODO Auto-generated method stub
		System.out.println("3/ Message received: " + s);
		this.diffuserMessage(s);
	}

	@Override
	public void diffuserMessage(String s) throws RemoteException{
		// TODO Auto-generated method stub
		for (IClient cl: clients){
			System.out.println("4/ Message: " + s + " sent to client: " + cl);
			cl.receptionnerMessage(s);
		}
	}

	public void addClient(IClient c)  throws RemoteException{
		clients.add(c);
		System.out.println("Client added: " + c);
	}
	public static void main(String[] args) throws RemoteException{
		//if (System.getSecurityManager() == null) {
		//	System.setSecurityManager(new SecurityManager());
		//}
		try {
			String name = "ServerChat";
			Chat monChat = new Chat();
			// on enregistre le server/service dans l'annuaire
			IChat stub = (IChat) UnicastRemoteObject.exportObject(monChat, 0);
			// mettre IChat
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("Chat bound");
		} catch (Exception e) {
			System.err.println("Chat server exception:");
			e.printStackTrace();
		}
		/*
		Chat chat = new Chat();
		IClient client1 = new Client("client1", chat);
		chat.addClient(client1);
		IClient client2 = new Client("client2", chat);
		chat.addClient(client2);
		while(true){
			String nomClient = Clavier.lireString();
			String message;
			if (nomClient.equals(client1.getId())){
				message = Clavier.lireString();
				client1.envoyerMessage(message);
			}else{
				message = Clavier.lireString();
				client2.envoyerMessage(message);
			}
		}
	*/
	}

public List<IClient> getClients() {
	return clients;
}

public void setClients(List<IClient> clients) {
	this.clients = clients;
}

}
