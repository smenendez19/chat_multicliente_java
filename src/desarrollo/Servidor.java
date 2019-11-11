package desarrollo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private ServerSocket server = null;
	private Socket cliente = null;
	private int puerto;
	private ServidorFrame serverFrame;
	private List<Socket> listaClientes = new ArrayList<Socket>();

	public Servidor(int puerto,ServidorFrame f) throws UnknownHostException {
		this.listaClientes = new ArrayList<Socket>();
		this.puerto = puerto;
		this.serverFrame = f;
	}

	public void conexion() {
		try {
			server = new ServerSocket(puerto);
			serverFrame.setIPServer(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void escuchar() {
		while (true) {
			try {
				cliente = server.accept();
				listaClientes.add(cliente);
				HiloServidor hiloServer = new HiloServidor(serverFrame,listaClientes,cliente);
				hiloServer.start();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Se crea una instancia de la clase Servidor
		Servidor os = new Servidor(5555, new ServidorFrame());
		os.conexion();
		os.escuchar();

	}
}