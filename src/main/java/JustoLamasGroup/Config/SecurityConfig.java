package JustoLamasGroup.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ---------- PÚBLICO (formularios web) ----------
                        .requestMatchers("/api/leads/**").permitAll()

                        // Show dates visibles para todos
                        .requestMatchers(HttpMethod.GET, "/api/show-dates").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/show-dates/open").permitAll()

                        // Reservas creadas por el público (Set a Date / Reserve Ticket)
                        .requestMatchers(HttpMethod.POST, "/api/reservations").permitAll()

                        // ---------- ADMIN (dashboard + vistas admin) ----------
                        .requestMatchers("/api/tours/**").hasRole("ADMIN")

                        // Otras operaciones de show-dates (POST, PATCH, DELETE, etc.)
                        .requestMatchers("/api/show-dates/**").hasRole("ADMIN")

                        // Otras operaciones de reservations (GET lista, DELETE, PATCH, etc.)
                        .requestMatchers("/api/reservations/**").hasRole("ADMIN")

                        // Resto de rutas (páginas React, estáticos, etc.) libres
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("jladmin")
                .password(encoder.encode("55555")) // cámbialo en producción
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
