package com.adgsoftware.mydomo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.adgsoftware.mydomo.engine.Command;
import com.adgsoftware.mydomo.server.commandmodules.gateway.GatewayCommand;
import com.adgsoftware.mydomo.server.commandmodules.light.LightCommand;

public class Server implements Runnable {

	private int port = 1234;
	private volatile Thread blinker;
	
    public void stop() {
        blinker = null;
    }

    public void start() {
        blinker = new Thread(this);
        blinker.start();
    }
    
    public void run() {
		ServerSocket serveur;
		Thread thisThread = Thread.currentThread();
		// installation
		try {
			serveur = new ServerSocket(port);
//			System.out.println("Possible Monitor Command:");
//			Properties p = new Properties();
//			p.load(new FileInputStream("/MonitorDictionnary.properties"));
//			for (Enumeration<Object> enume = p.keys(); enume.hasMoreElements();) {
//				System.out.println(enume.nextElement());
//
//			} TODO add help about command from plugin for monitor!

			while (blinker == thisThread) {

				System.out
						.println("Waiting connection for Monitor/Command on port ["
								+ port + "]...");
				// création de nouvelles connexions
				Socket s = serveur.accept();
				BufferedReader depuisClient; // réception de requête
				PrintWriter versClient; // envoi des réponses

				try {
					// création des flots de/vers le client
					depuisClient = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					versClient = new PrintWriter(new OutputStreamWriter(
							s.getOutputStream()), true);
					// message d'accueil
					write(Command.ACK, versClient);
					String sessionType = read(s, depuisClient);
					if (Command.MONITOR_SESSION.equalsIgnoreCase(sessionType)) {
						System.out.println("Start Monitor Session...");
						write(Command.ACK, versClient);
						// mise en route du processus par appel de la méthode
						// run
						new Thread(new MonitorSession(s, depuisClient,
								versClient)).start();

					} else if (Command.COMMAND_SESSION
							.equalsIgnoreCase(sessionType)) {
						System.out.println("Start Command Session...");
						write(Command.ACK, versClient);
						// mise en route du processus par appel de la méthode
						// run
						new Thread(new CommandSession(s, depuisClient,
								versClient)).start();

					} else {
						write(Command.NACK, versClient);
					}
				} catch (IOException e) {
					try {
						s.close();
					} catch (IOException ee) {
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur à la creation d'un objet Socket : "
					+ e.getMessage());
			System.exit(1);
		}
	}
	
	public static void main(String args[]) {

		Server s = new Server();
		
		// définition du port
		try {
			s.port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			s.port = 1234; // valeur par défaut
		}

		ControllerStateManagement.registerControllerCommand(new LightCommand());
		ControllerStateManagement.registerControllerDimensionCommand(new GatewayCommand());
		
		s.start();
	}

	private void write(String msg, PrintWriter versClient) {
		versClient.print(msg);
		versClient.flush();
		System.out.println("SERVER WRITE:[" + msg + "]");
	}

	private String read(Socket client, BufferedReader depuisClient) {
		int indice = 0;
		boolean exit = false;
		char respond[] = new char[1024];
		char c = ' ';
		int ci = 0;
		String responseString = null;

		try {
			do {
				if (client != null && !client.isInputShutdown()) {
					ci = depuisClient.read();
					if (ci == -1) {
						// System.out.println("End of read from socket.");
						// client = null;
						// break;
					} else {
						c = (char) ci;
						if (c == '#' && indice > 1
								&& '#' == respond[indice - 1]) {
							respond[indice] = c;
							exit = true;
							break;
						} else {
							respond[indice] = c;
							indice = indice + 1;
						}
					}
				}
			} while (true);
		} catch (IOException e) {
			System.out.println("Socket not available");
		}

		if (exit == true) {
			responseString = new String(respond, 0, indice + 1);
		}

		System.out.println("CLIENT WRITE: " + responseString);

		return responseString;
	}
}