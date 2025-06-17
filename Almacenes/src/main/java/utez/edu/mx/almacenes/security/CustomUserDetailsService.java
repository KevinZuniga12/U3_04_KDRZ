package utez.edu.mx.almacenes.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Aquí podrías inyectar tu repositorio de usuarios, p.ej:
    // @Autowired
    // private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Busca el usuario en BD usando userRepository

        // Para ejemplo rápido: usuario "admin", password "admin123" (en BCrypt)
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // El password está cifrado con BCrypt (admin123)
        String passwordEncriptado = "$2a$10$DowJZ6w.m8q2YzRQK3Pe6eMk7lwHeChbkP.RCz3DK/ZMq5l/9FYba";

        // Retornamos un UserDetails de Spring Security
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(passwordEncriptado)
                .authorities(new ArrayList<>())  // Puedes poner roles aquí
                .build();
    }
}
