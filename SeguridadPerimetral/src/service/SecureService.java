package service;

import annotations.RolesAllowed;

public class SecureService {
	
	@RolesAllowed({"ADMIN"})
	public void deleteData() {
		System.out.println("Datos eliminados");
	}
	
	@RolesAllowed({"USER", "ADMIN"})
	public void viewData() {
		System.out.println("Mostrando datos");
	}

}
