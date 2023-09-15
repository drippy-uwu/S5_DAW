package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	// LISTAR los datos de un Usuario CON INNER JOIN
	public static void main(String[] args) {
		
		// llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// crear un manejador de las entidades
		EntityManager em = fabrica.createEntityManager();

		
		//Para almacenar RESULTADOS
		
		String jpql = "select u from Usuario u"; //si lo ejecutamos como sql no se ejecutará porque trabajamos con jpa y este 
													//trabaja con entidades no tablas.
		List<Usuario> lstUsuario = em.createQuery(jpql, Usuario.class).getResultList();
											//SENTENCIA / 
		
		//imprimir RESULTADOS
		for (Usuario usuario : lstUsuario) {
			System.out.println("Codigo 	:" + usuario.getCod_usua());
			System.out.println("Nombre 	:" + usuario.getNom_usua()+ " "+ usuario.getApe_usua());
			System.out.println("Tipo 	:" + usuario.getIdtipo());
			System.out.println("=======================================");
	


		}
		
		em.close();

	}
}
