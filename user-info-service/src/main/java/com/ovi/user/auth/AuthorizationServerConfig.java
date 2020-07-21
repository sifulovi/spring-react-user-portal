package com.ovi.user.auth;

import com.ovi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public AuthorizationServerConfig(AuthenticationManager authenticationManager, UserService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userService = userDetailsService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("userPortal").secret(passwordEncoder.encode("secret"))
        .authorizedGrantTypes("password","refresh_token")
                .scopes("read", "write")
                .resourceIds("api")
                .accessTokenValiditySeconds(1000 * 60 * 12)
                .refreshTokenValiditySeconds(1000 * 60 * 60 * 12);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(new InMemoryTokenStore())
                .userDetailsService(userService)
                .authenticationManager(authenticationManager);
    }

}
