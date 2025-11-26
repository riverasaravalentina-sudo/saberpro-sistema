package com.saberpro.config;

import com.saberpro.entity.Alumno;
import com.saberpro.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Bean
    PasswordEncoder passwordEncoder() {
        // Usar DelegatingPasswordEncoder que soporta múltiples formatos
        return org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        
        // Coordinadores
        users.add(User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("COORDINACION")
                .build());

        users.add(User.builder()
                .username("coordinador")
                .password("{noop}admin123")
                .roles("COORDINACION")
                .build());

        // Cargar estudiantes dinámicamente desde la base de datos
        try {
            List<Alumno> alumnos = alumnoRepository.findAll();
            System.out.println("\n========== CARGANDO USUARIOS ==========");
            System.out.println("Total de alumnos encontrados: " + alumnos.size());
            
            for (Alumno alumno : alumnos) {
                users.add(User.builder()
                        .username(alumno.getNumeroDocumento())
                        .password("{noop}" + alumno.getNumeroDocumento())
                        .roles("ESTUDIANTE")
                        .build());
            }
            
            System.out.println("Usuarios de estudiantes creados: " + alumnos.size());
            System.out.println("Total de usuarios en el sistema: " + users.size());
            System.out.println("=======================================\n");
        } catch (Exception e) {
            System.err.println("Error cargando alumnos: " + e.getMessage());
            e.printStackTrace();
        }

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Recursos públicos
                .requestMatchers("/login", "/error", "/css/**", "/js/**", "/images/**", 
                               "/webjars/**", "/favicon.ico", "/static/**").permitAll()
                
                // H2 Console (solo desarrollo)
                .requestMatchers("/h2-console/**").permitAll()
                
                // Acceso solo para coordinación
                .requestMatchers("/admin/**", "/alumnos/nuevo", "/alumnos/editar/**", 
                               "/alumnos/eliminar/**", "/resultados/nuevo", 
                               "/resultados/editar/**", "/resultados/eliminar/**").hasRole("COORDINACION")
                
                // Reportes solo para coordinación
                .requestMatchers("/reportes/**", "/beneficios/**").hasRole("COORDINACION")
                
                // Perfil de estudiante - acceso para ESTUDIANTE
                .requestMatchers("/estudiante/**").hasRole("ESTUDIANTE")
                
                // Cualquier otra request debe estar autenticada
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            )
            // Para H2 Console (solo en desarrollo)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );

        return http.build();
    }
}