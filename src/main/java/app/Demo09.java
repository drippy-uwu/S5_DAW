package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;

public class Demo09 {
	// LISTAR usuarios segun el tipo de usuarios (filtro)
	
	public static void main(String[] args) {
		
		String usuario = JOptionPane.showInputDialog("Usuario :");
		String Clave = JOptionPane.showInputDialog("Clave :");
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();

		String usua = "profe@profe.com";
		String clave = "elprofe";
		
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
				
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Datos Incorrectos");
		}
	

		
		em.close();

	}
}
