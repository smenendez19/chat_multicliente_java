package desarrollo;

import java.io.*;

public class HiloCliente extends Thread {
	private DataInputStream in;
	private ClienteFrame ventana;

	public HiloCliente(DataInputStream in,ClienteFrame ventana) {
		this.in = in;
		this.ventana = ventana;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ventana.addMensaje(in.readUTF());
			} catch (IOException e) {
				System.out.println(e);
				break;
			}
		}
	}

}
