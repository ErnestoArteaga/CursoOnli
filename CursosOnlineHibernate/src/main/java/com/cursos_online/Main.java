package com.cursos_online;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import com.cursos_online.entidades.Curso;
import com.cursos_online.entidades.Estudiante;
public class Main {
	final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	
	static SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

	public static void main(String[] args) {
		
	Curso cur1 = new Curso("Fundamentos de Java");
	Curso cur2 = new Curso("Hibernate oara principiantes");
	
	ingresarCurso(cur1);
	ingresarCurso(cur2);
		
	modificarCurso(2,"Ingles");
	eliminarCurso(1);
		
	Estudiante estu1 = new Estudiante(0,"Ernesto","Arteaga");
	Estudiante estu2 = new Estudiante(0,"Rosa","Mariam");
	Estudiante estu3 = new Estudiante(0,"Loor","Sanchez");
	
	ingresarestu(estu1);
	ingresarestu(estu2);
	ingresarestu(estu3);
	
	modificarEstudiante(3,"Andres","Ponguillo");
	eliminarEstudiante(4);
	
	
	Curso cr2 = new Curso("Hibernate oara principiantes");
	modificarCurso(cr2);
	
	List<Curso> cursos = getCursos();
	
	for(Curso temp:cursos) {
		System.out.println(temp);
	}
	

	List<Estudiante> estudiantes = getEstudiantes();
	
	for(Estudiante tem:estudiantes) {
		System.out.println(tem);
	}
	
	List<Estudiante> estudiante =getEstudiantesPorNombre("Rosa");
	for(Estudiante e: estudiante) {
		System.out.println(e);
	}
	
	
}
	
	static void modificarCurso(int id, String nombre) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso=
			(Curso)session.get(Curso.class,id);
		curso.setDescripcion(nombre);
		session.update(curso);
		session.getTransaction().commit();
		session.close();
	}
		
	
	static void eliminarCurso(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Curso curso=
			(Curso)session.get(Curso.class,id);
		session.delete(id);
		session.getTransaction().commit();
		session.close();
	}
		
	
	

	
	static void modificarEstudiante(int id, String nombre, String, apellido) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiante=
			(Estudiante)session.get(Estudiante.class,id);
		session.update(estudiante);
		session.getTransaction().commit();
		session.close();
	}
		
	
	static void eliminarEstudiante(int id) {
		ession session = sessionFactory.openSession();
		session.beginTransaction();
		Estudiante estudiante=
			(Estudiante)session.get(Estudiante.class,id);
		session.delete(id);
		session.getTransaction().commit();
		session.close();
	}
		
	
	
	static List<Estudiante>getEstudiantesPorNombre(String nombre){
		Session session = sessionFactory.openSession();
		Query query = session
				.createQuery("from Estudiante where nombre=:nombre");
		query.setParameter("nombre", nombre);
		
		List<Estudiante> estudiante = (List<Estudiante>) query.getResultList();
		return estudiante;
	}
	
	
	static List<Curso>getCursos(){
		Session session = sessionFactory.openSession();
		List<Curso> cursos = session.createQuery("from Curso", Curso.class).list();
		return cursos;
	}
	static List<Estudiante>getEstudiantes(){
		Session session = sessionFactory.openSession();
		List<Estudiante> estudiantes = session.createQuery("from Estudiante", Estudiante.class).list();
		return estudiantes;
	}
	
	
	static void ingresarCurso(Curso curso){	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(curso);
		
		session.getTransaction().commit();
		session.close();
	}
	
	static void ingresarestu(Estudiante estudiante){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(estudiante);
		
		session.getTransaction().commit();
		session.close();
		//System.out.println(estudiante.getId());
	}

}

