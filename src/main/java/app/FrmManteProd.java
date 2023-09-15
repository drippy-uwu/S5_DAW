package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Productos;
import model.Proveedor;
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
import javax.swing.DefaultComboBoxModel;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedores;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(324, 29, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 414, 185);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.setBounds(171, 404, 89, 23);
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 102, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categoría :");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(122, 42, 144, 20);
		txtDescripcion.setColumns(10);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 136, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setBounds(122, 133, 77, 20);
		txtStock.setColumns(10);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 164, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(122, 161, 77, 20);
		txtPrecio.setColumns(10);
		contentPane.add(txtPrecio);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(122, 100, 102, 22);
		cboProveedores.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		contentPane.add(cboProveedores);
		
		JLabel lblProveedor = new JLabel("Proveedor :");
		lblProveedor.setBounds(10, 104, 102, 14);
		contentPane.add(lblProveedor);
		
		llenaCombo();
		listado();
		llenaComboProveedor();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager em = fabrica.createEntityManager();

		//Para almacenar RESULTADOS
		
		String jpql = "select c from Categoria c";
		
		List<Categoria> lstCategorias = em.createQuery(jpql, Categoria.class).getResultList();
											//SENTENCIA / 

		cboCategorias.addItem("Seleccione");
		
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
			

			
			
		}
		em.close();
	}
	
	void llenaComboProveedor() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager em = fabrica.createEntityManager();

		//Para almacenar RESULTADOS
		
		String jpql = "select p from Proveedor p";
		
		List<Proveedor> lstProveedors = em.createQuery(jpql, Proveedor.class).getResultList();
											//SENTENCIA / 

		
		
		for (Proveedor p : lstProveedors) {
			cboProveedores.addItem(p.getNombre_rs());
			

			
			
		}
		em.close();
		
	}
	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager em = fabrica.createEntityManager();

		//Para almacenar RESULTADOS
		
		String jpql = "select p from Productos p";
		
		List<Productos> lstProductos = em.createQuery(jpql, Productos.class).getResultList();
											//SENTENCIA / 

		//imprimir RESULTADOS
		for (Productos producto : lstProductos) {
			imprimir("Codigo 	: " + producto.getId_prod());
			imprimir("Nombre 	: " + producto.getDes_prod());
			imprimir("Categoria 	: " + producto.getObjCategoria().getDescripcion());
			imprimir("Proveedor 	: " + producto.getObjProveedor().getNombre_rs());
			imprimir("=======================================");

			
		}
		em.close();
			
	}
	void imprimir (String texto) {
		txtSalida.append(texto + "\n");
	}
	void registrar() {
		
		//entradas 
		String codigo = txtCodigo.getText();
		String nombre = txtDescripcion.getText();
		int categoria = cboCategorias.getSelectedIndex();
		int proveedor = cboProveedores.getSelectedIndex();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int estado = 1;
		
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		
		// objeto a grabar
		Productos p = new Productos();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setIdcategoria(categoria);
		p.setIdproveedor(proveedor);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setEst_prod(estado);
		
		System.out.println(categoria);
		try {
			manager.getTransaction().begin();
			manager.persist(p); 
			manager.getTransaction().commit();
			JOptionPane.showMessageDialog(this, "Registro Ok");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error: " + e.getCause().getMessage());
		}
		manager.close();
		listado();
	}
}
