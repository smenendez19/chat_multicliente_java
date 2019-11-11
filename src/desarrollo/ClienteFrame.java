package desarrollo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteFrame extends JFrame {
	private static final long serialVersionUID = -92165517112625306L;
	private JTextField txtEscribirMensaje;
	private JTextArea textArea;
	private JButton btnEnviar;
	private JScrollPane scrollPane;
	private String mensaje;
	private boolean mensajeEnviado = false;

	public ClienteFrame() {
		setSize(new Dimension(400, 400));
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setResizable(false);
		setTitle("Chat - Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServidorFrame.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 374, 248);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		txtEscribirMensaje = new JTextField();
		txtEscribirMensaje.setToolTipText("");
		txtEscribirMensaje.setBounds(10, 298, 355, 20);
		getContentPane().add(txtEscribirMensaje);
		txtEscribirMensaje.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mensaje = txtEscribirMensaje.getText();
				txtEscribirMensaje.setText(null);
				mensajeEnviado = true;
			}
		});
		btnEnviar.setBounds(276, 329, 89, 23);
		getContentPane().add(btnEnviar);
		
		JLabel lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMensaje.setBounds(10, 270, 89, 14);
		getContentPane().add(lblMensaje);

	}
	
	public void addMensaje(String str){
		this.textArea.append(str+"\n");
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
	}
	
	public boolean getMensajeEnviado() {
		return mensajeEnviado;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensajeEnviado(boolean valor) {
		mensajeEnviado = valor;
	}
}
