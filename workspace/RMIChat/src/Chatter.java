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

	public void sendMessage(String message, String user) {
		chat.sendMessage(message, user);
	}

	public String receiveMessage() {
		return chat.receiveMessage(this.userName);
	}

	public void loop() {
		while (true) {
			System.out.println(this.receiveMessage());
			Random random = new Random();
			boolean post = (random.nextInt() < 0.5);
			if (post && this.messagesEnregistres.size() != 0)
				this.sendMessage(this.messagesEnregistres
						.remove(this.messagesEnregistres.size() - 1),
						this.userName);
		}
	}

	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "Chat";
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Chat chat = (Chat) registry.lookup(name);
			Chatter chatter = new Chatter(chat,"JeanLaChaudiere");
			Chatter chatter1 = new Chatter(chat, "SophieCoquine44");
			chatter.loop();
			chatter1.loop();

		} catch (Exception e) {
			System.err.println("ComputePi exception:");
			e.printStackTrace();
		}
	}

}
