package desarrollo;

import java.io.*;
import java.net.*;

public class Cliente{
	private String ip;
	private String nickName;
	private Socket conexion = null;
	private int puerto;
	private DataOutputStream out;
	private DataInputStream in;
	private ClienteFrame ventanaChat;

	public Cliente(String ip, int puerto, String nickName, ClienteFrame ventana) {
		this.ip = ip;
		this.puerto = puerto;
		this.nickName = nickName;
		this.ventanaChat = ventana;
	}

	public void iniciar() {
		try {
			conexion = new Socket(ip, puerto);
			ventanaChat.setVisible(true);
			in = new DataInputStream(conexion.getInputStream());
			out = new DataOutputStream(conexion.getOutputStream());
			HiloCliente hilo1 = new HiloCliente(in,ventanaChat); // Hilo para recibir mensajes
			hilo1.start();
			escribir();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void escribir() throws InterruptedException {
		try {
			ventanaChat.addMensaje("Escribe un mensaje");
			out.writeUTF(nickName);
			String mensaje = "";
			while (!mensaje.equals("salir()")) {
				Thread.sleep(10);
				if (ventanaChat.getMensajeEnviado() == true) {
					mensaje = ventanaChat.getMensaje();
					ventanaChat.setMensajeEnviado(false);
					//ventanaChat.addMensaje(nickName + " : " + mensaje);
					out.writeUTF(nickName + " : " + mensaje);
					out.flush();
				}
			}
			out.writeUTF("El usuario se ha salido");
			conexion.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Cliente cli = new Cliente("localhost", 5555, "Mauro", new ClienteFrame());
		cli.iniciar();
	}

}