package utez.edu.mx.almacenes.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Para ejemplo rápido: usuario "admin", password "admin123" (en BCrypt)
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // El password está cifrado con BCrypt (admin123)
        String passwordEncriptado = "$2a$10$DowJZ6w.m8q2YzRQK3Pe6eMk7lwHeChbkP.RCz3DK/ZMq5l/9FYba";

        // Lista de roles
        var roles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );

        // Retornamos un UserDetails con los roles
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(passwordEncriptado)
                .authorities(roles)
                .build();
    }
}