package security;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import annotations.RolesAllowed;
import model.User;

public class SecurityEngine {

    public static void executeIfAllowed(User user, Object service, String methodName) {

        try {
            Method method = service.getClass().getMethod(methodName);

            Optional<RolesAllowed> annotation =
                    Optional.ofNullable(method.getAnnotation(RolesAllowed.class));

            boolean hasAccess = annotation
                    .map(RolesAllowed::value)
                    .map(allowedRoles ->
                            Arrays.stream(allowedRoles)
                                    .anyMatch(role -> user.getRoles().contains(role))
                    )
                    .orElse(false);

            if (hasAccess) {
                method.invoke(service);
            } else {
                System.out.println("Acceso denegado para: " + user.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}