package com.saberpro.repository;

import com.saberpro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar usuario por nombre de usuario
     */
    Optional<Usuario> findByUsername(String username);

    /**
     * Buscar usuario por email
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Buscar usuarios activos
     */
    List<Usuario> findByActivoTrue();

    /**
     * Buscar usuarios inactivos
     */
    List<Usuario> findByActivoFalse();

    /**
     * Buscar usuarios por rol
     */
    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = :nombreRol")
    List<Usuario> findByRolNombre(@Param("nombreRol") String nombreRol);

    /**
     * Verificar si existe usuario por username
     */
    boolean existsByUsername(String username);

    /**
     * Verificar si existe usuario por email
     */
    boolean existsByEmail(String email);

    /**
     * Buscar usuarios por username o email
     */
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Usuario> buscarPorTexto(@Param("texto") String texto);

    /**
     * Contar usuarios por rol
     */
    @Query("SELECT r.nombre, COUNT(u) FROM Usuario u JOIN u.roles r GROUP BY r.nombre")
    List<Object[]> contarUsuariosPorRol();
}