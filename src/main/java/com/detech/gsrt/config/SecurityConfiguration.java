package com.detech.gsrt.config;

import com.detech.gsrt.services.implementation.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;


@AllArgsConstructor
// @Configuration
// @EnableWebSecurity
public class SecurityConfiguration {

	private final UserDetailsServiceImpl userDetailsService;
/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v2/api-docs/**", // For older Swagger 2.x
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/users/save"
                );
    }
*/
    /*
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager)
			throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth
                        // Autoriser Swagger UI
                        .requestMatchers(
                        //        "**///swagger-ui/**",
                        //        "**/v3/api-docs/**",
                        //        "**/swagger-ui.html"
                     /*   ).permitAll()
                        // Protéger les autres endpoints
                        .anyRequest().authenticated()
                ); // ou autre méthode d'authentification

        http.csrf(csrf -> csrf.ignoringRequestMatchers("**///swagger-ui/**", "**/v3/api-docs/**"));
       /* http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .formLogin((login) -> login
                                .failureHandler(new CompromisedPasswordAuthenticationFailureHandler())
                        ););

    //   http.addFilterBefore(this.applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

    static class CompromisedPasswordAuthenticationFailureHandler implements AuthenticationFailureHandler {

        private final SimpleUrlAuthenticationFailureHandler defaultFailureHandler = new SimpleUrlAuthenticationFailureHandler(
                "/login?error");

        private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            if (exception instanceof CompromisedPasswordException) {
                this.redirectStrategy.sendRedirect(request, response, "/reset-password");
                return;
            }
            this.defaultFailureHandler.onAuthenticationFailure(request, response, exception);
        }
*/

}
