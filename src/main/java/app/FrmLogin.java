package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Productos;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}
	private JPasswordField txtClave;
	
	
	void registrar() {
		
		
		String usuario = leerUsuario();
		String Clave = txtClave.getText();
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();

		//String usua = "profe@profe.com";
		//String clave = "elprofe";
		
		if (usuario == null || Clave == null) {
			return ;
		}
		
		// select * from tb_usuarios where idtipo = ?
		String jpql = "select u from Usuario u where u.usr_usua = :xusu and u.cla_usua = :xclav"; 
		
		try {
			Usuario u = em.createQuery(jpql, Usuario.class)
						.setParameter("xusu", usuario)
						.setParameter("xclav", Clave)
						.getSingleResult();
						//getSingleResult para imprimir solo un resultado y almacenarlo en un objeto equide
			
			FrmManteProd f = new FrmManteProd();
			f.setVisible(true);
			dispose();
			JOptionPane.showMessageDialog(f, "Bienvenido");
				
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Datos Incorrectos");
		}
	

		
		em.close();
		
	}

	private String leerUsuario() {
		
		if (!txtUsuario.getText().matches("\\w+_*@[a-z]+.[com-pe]+")) {
			JOptionPane.showMessageDialog(this, "Usuario Incorrecto");
		}
		return null;
	}
}
