package com.saberpro.config;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usar DelegatingPasswordEncoder que soporta múltiples formatos
        return org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Coordinadores
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("COORDINACION")
                .build();

        UserDetails coordinador = User.builder()
                .username("coordinador")
                .password("{noop}admin123")
                .roles("COORDINACION")
                .build();

        // Estudiantes - username y password = número de documento
        UserDetails estudiante1 = User.builder()
                .username("1098765001")
                .password("{noop}1098765001")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante2 = User.builder()
                .username("1098765002")
                .password("{noop}1098765002")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante3 = User.builder()
                .username("1098765003")
                .password("{noop}1098765003")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante4 = User.builder()
                .username("1098765004")
                .password("{noop}1098765004")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante5 = User.builder()
                .username("1098765005")
                .password("{noop}1098765005")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante6 = User.builder()
                .username("1098765006")
                .password("{noop}1098765006")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante7 = User.builder()
                .username("1098765007")
                .password("{noop}1098765007")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante8 = User.builder()
                .username("1098765008")
                .password("{noop}1098765008")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante9 = User.builder()
                .username("1098765009")
                .password("{noop}1098765009")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante10 = User.builder()
                .username("1098765010")
                .password("{noop}1098765010")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante11 = User.builder()
                .username("1098765011")
                .password("{noop}1098765011")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante12 = User.builder()
                .username("1098765012")
                .password("{noop}1098765012")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante13 = User.builder()
                .username("1098765013")
                .password("{noop}1098765013")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante14 = User.builder()
                .username("1098765014")
                .password("{noop}1098765014")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante15 = User.builder()
                .username("1098765015")
                .password("{noop}1098765015")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante16 = User.builder()
                .username("1098765016")
                .password("{noop}1098765016")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante17 = User.builder()
                .username("1098765017")
                .password("{noop}1098765017")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante18 = User.builder()
                .username("1098765018")
                .password("{noop}1098765018")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante19 = User.builder()
                .username("1098765019")
                .password("{noop}1098765019")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante20 = User.builder()
                .username("1098765020")
                .password("{noop}1098765020")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante21 = User.builder()
                .username("1098765021")
                .password("{noop}1098765021")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante22 = User.builder()
                .username("1098765022")
                .password("{noop}1098765022")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante23 = User.builder()
                .username("1098765023")
                .password("{noop}1098765023")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante24 = User.builder()
                .username("1098765024")
                .password("{noop}1098765024")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante25 = User.builder()
                .username("1098765025")
                .password("{noop}1098765025")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante26 = User.builder()
                .username("1098765026")
                .password("{noop}1098765026")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante27 = User.builder()
                .username("1098765027")
                .password("{noop}1098765027")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante28 = User.builder()
                .username("1098765028")
                .password("{noop}1098765028")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante29 = User.builder()
                .username("1098765029")
                .password("{noop}1098765029")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante30 = User.builder()
                .username("1098765030")
                .password("{noop}1098765030")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante31 = User.builder()
                .username("1098765031")
                .password("{noop}1098765031")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante32 = User.builder()
                .username("1098765032")
                .password("{noop}1098765032")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante33 = User.builder()
                .username("1098765033")
                .password("{noop}1098765033")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante34 = User.builder()
                .username("1098765034")
                .password("{noop}1098765034")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante35 = User.builder()
                .username("1098765035")
                .password("{noop}1098765035")
                .roles("ESTUDIANTE")
                .build();

        UserDetails estudiante36 = User.builder()
                .username("1098765036")
                .password("{noop}1098765036")
                .roles("ESTUDIANTE")
                .build();

        return new InMemoryUserDetailsManager(
            admin, coordinador,
            estudiante1, estudiante2, estudiante3, estudiante4, estudiante5,
            estudiante6, estudiante7, estudiante8, estudiante9, estudiante10,
            estudiante11, estudiante12, estudiante13, estudiante14, estudiante15,
            estudiante16, estudiante17, estudiante18, estudiante19, estudiante20,
            estudiante21, estudiante22, estudiante23, estudiante24, estudiante25,
            estudiante26, estudiante27, estudiante28, estudiante29, estudiante30,
            estudiante31, estudiante32, estudiante33, estudiante34, estudiante35,
            estudiante36
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Recursos públicos
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**", 
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