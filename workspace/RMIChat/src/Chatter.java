import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

public class Chatter {

	private Chat chat;
	private String userName;
	private ArrayList<String> messagesEnregistres;

	public Chatter(Chat chat, String userName) {
		super();
		this.chat = chat;
		this.userName = userName;
		this.messagesEnregistres = new ArrayList<String>();
		this.messagesEnregistres.add("Coucou coquin");
		this.messagesEnregistres.add("t'as la cam?");
		this.messagesEnregistres.add("Enlève ton sweat");
	}

	public String getUserName() {
		return this.userName;
	}

	public int getRemainingMessages() {
		return this.messagesEnregistres.size();
	}

	public void sendMessage(String message, String user) {
		chat.sendMessage(message, user);
	}

	public String receiveMessage() {
		return chat.receiveMessage(this.userName);
	}

	public void sendAndReceive(Chatter chatter) {
		System.out.println(this.receiveMessage());
		sendMessage(this.messagesEnregistres.remove(0), chatter.getUserName());
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Chat";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Chat chat = (Chat) registry.lookup(name);
			Chatter chatter = new Chatter(chat, "JeanLaChaudiere");
			Chatter chatter1 = new Chatter(chat, "SophieCoquine44");
			
			while (chatter.getRemainingMessages() > 0
					&& chatter1.getRemainingMessages() > 0) {

				chatter.sendAndReceive(chatter1);
				chatter1.sendAndReceive(chatter);
			}

		} catch (Exception e) {
			System.err.println("Chatter exception:");
			e.printStackTrace();
		}
	}

}
