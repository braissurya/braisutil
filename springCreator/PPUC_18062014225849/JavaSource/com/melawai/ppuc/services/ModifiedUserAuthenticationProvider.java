package com.melawai.ppuc.services;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.UserManager;


@Component
public class ModifiedUserAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(ModifiedUserAuthenticationProvider.class);

    private final UserManager userManager;
    
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private SaltSource saltSource;

    @Autowired
    public ModifiedUserAuthenticationProvider(UserManager userManager) {
	if (userManager == null) {
	    throw new IllegalArgumentException("UserManager cannot be null");
	}
	this.userManager = userManager;
    }

   

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
	    throws AuthenticationException {

	boolean isAuthenticated = true;
	UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

	User loginUser = userManager.getUserByUsername(token.getName());
	if (loginUser == null) {
		logger.error("Class : ModifiedUserAuthentication --- Login Mode : AD");
	    throw new UsernameNotFoundException("Invalid username / password");
	}

	logger.info("hasil encode"+passwordEncoder.encodePassword((String) token.getCredentials(), saltSource.getSalt(loginUser)));
    isAuthenticated = passwordEncoder.isPasswordValid(loginUser.getPassword(), (String) token.getCredentials(), saltSource.getSalt(loginUser));
	

	if (!isAuthenticated)
	    throw new UsernameNotFoundException("Invalid username / password");

	Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
	return new UsernamePasswordAuthenticationToken(loginUser, token.getCredentials(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
    	return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}

