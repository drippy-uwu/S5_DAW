package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Productos;
import model.Usuario;

public class Demo07 {
	// LISTAR los datos de un Usuario
	public static void main(String[] args) {
		
		// llamar a la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		
		// crear un manejador de las entidades
		EntityManager em = fabrica.createEntityManager();

		
		//Para almacenar RESULTADOS
		
		String jpql = "select p from Productos p"; //si lo ejecutamos como sql no se ejecutará porque trabajamos con jpa y este 
													//trabaja con entidades no tablas.
		List<Productos> lstProductos = em.createQuery(jpql, Productos.class).getResultList();
											//SENTENCIA / 
		
		//imprimir RESULTADOS
		for (Productos producto : lstProductos) {
			System.out.println("Codigo 		:" + producto.getId_prod());
			System.out.println("Nombre 		:" + producto.getDes_prod());
			System.out.println("Categoria 	:" + producto.getObjCategoria().getDescripcion());
			System.out.println("Proveedor 	:" + producto.getObjProveedor().getNombre_rs());
			System.out.println("=======================================");
			

		

		}
		
		em.close();

	}
}
