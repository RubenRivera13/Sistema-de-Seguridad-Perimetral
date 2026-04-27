package main;

import java.util.List;
import java.util.Set;

import model.User;
import security.SecurityEngine;
import service.SecureService;
import util.UserFilter;

public class Main {
	
	public static void main(String[] args) {
		
		User admin = new User("AdminUser", Set.of("ADMIN"));
		User normal = new User("NormalUser", Set.of("USER"));
		User normal2 = new User("NormalUser2", Set.of("USER"));
		
		SecureService service = new SecureService();
		
		// Prueba de acceso
		SecurityEngine.executeIfAllowed(admin, service, "deleteData");
		SecurityEngine.executeIfAllowed(normal, service, "deleteData");
		SecurityEngine.executeIfAllowed(normal2, service, "deleteData");
		SecurityEngine.executeIfAllowed(admin, service, "viewData");
		SecurityEngine.executeIfAllowed(normal, service, "viewData");
		SecurityEngine.executeIfAllowed(normal2, service, "viewData");
		System.out.println("---------------------------------------------------------------------------");
		
		// Lista de usuarios
		List<User> users = List.of(admin,normal,normal2);
		
		// Filtrado Fncional
		System.out.println("Usuarios con el rol USER:");
		UserFilter.filterUserByRole(users, "USER")
		         .forEach(u -> System.out.println(u.getName()));
		
	}

}
