package desarrollo;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ServidorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel ipServerLabel;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	public ServidorFrame() {
		setSize(new Dimension(400, 400));
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setResizable(false);
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServidorFrame.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 394, 291);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setEditable(false);
		
		ipServerLabel = new JLabel("IP del Servidor : ");
		ipServerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		ipServerLabel.setBounds(21, 316, 363, 18);
		getContentPane().add(ipServerLabel);
		
		setVisible(true);
		
	}
	
	public void addMensaje(String str){
		this.textArea.append(str+"\n");
		this.textArea.setCaretPosition(this.textArea.getDocument().getLength());
	}
	
	public void setIPServer(String ip){
		this.ipServerLabel.setText("IP del Servidor : " + ip);
	}
}
