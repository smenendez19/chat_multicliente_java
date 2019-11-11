package desarrollo;

import java.io.*;
import java.net.*;
import java.util.List;

public class HiloServidor extends Thread {

	private ServidorFrame serverFrame;
	private List<Socket> listaClientes;
	private Socket cliente;

	public HiloServidor(ServidorFrame ventana,List<Socket> listaClientes,Socket cliente) {
		serverFrame = ventana;
		this.listaClientes = listaClientes;
		this.cliente = cliente;
	}

	public void run() {
		serverFrame.addMensaje("Se inicio una nueva conexion : " + cliente.getLocalAddress().getHostAddress());
		try {
			String user = new DataInputStream(cliente.getInputStream()).readUTF();
			serverFrame.addMensaje("Usuario conectado : " + user);
		} catch (IOException e) {
			serverFrame.addMensaje("Error de conexion , se reinicio el cliente");
		}
		while (true) {
			try {
				String mensaje = new DataInputStream(cliente.getInputStream()).readUTF();
				serverFrame.addMensaje(mensaje);
				for(Socket cli : listaClientes) {
					new DataOutputStream(cli.getOutputStream()).writeUTF(mensaje);
				}
			} catch (IOException e) {
				System.out.println(e);
				break;
			}
		}
	}
	
	
}
