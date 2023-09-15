package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo08 {
	// LISTAR usuarios segun el tipo de usuarios (filtro)
	
	public static void main(String[] args) {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		EntityManager em = fabrica.createEntityManager();

		String usua = "profe@profe.com";
		String clave = "elprofe";
		
		// select * from tb_usuarios where idtipo = ?
		String jpql = "select u from Usuario u where u.usr_usua = :xusu and u.cla_usua = :xclav"; 
		List<Usuario> lstUsuario = em.createQuery(jpql, Usuario.class).setParameter("xusu", usua).setParameter("xclav", clave).getResultList();
											 							//getresult para imprimir, si queremos parametrizar, lo 
																		//declaramos luego
		
		//imprimir RESULTADOS
		for (Usuario usuario : lstUsuario) {
			System.out.println("Codigo 	:" + usuario.getCod_usua());
			System.out.println("Nombre 	:" + usuario.getNom_usua()+ " "+ usuario.getApe_usua());
			System.out.println("Tipo 	:" + usuario.getObjTipo().getDescripcion());
			System.out.println("=======================================");
	


		}
		
		em.close();

	}
}
