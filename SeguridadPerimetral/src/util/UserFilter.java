package util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.User;

public class UserFilter {
	
	public static List<User> filterUserByRole(List<User> users, String role) {
		
		Predicate<User> hasRole =
				user -> user.getRoles().contains(role);
				
	   return users.stream()
			  .filter(hasRole)
			  .collect(Collectors.toList());
	}

}
