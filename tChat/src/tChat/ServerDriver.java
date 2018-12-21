package tChat;

public class ServerDriver {
	private int port; 
	public ServerDriver(int port) {
		this.port = port;
	}
	/*
	 * To run in Eclipse:
	 * Debug -> Debug Configurations -> Arguments -> [Port]
	 * */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("ERROR.");
			System.exit(0);
		}
		new ServerDriver(Integer.parseInt(args[0]));
	}
}