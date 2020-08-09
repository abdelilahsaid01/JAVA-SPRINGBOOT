package com.brightcoding.ws.Security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    
    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
     } 
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, 	// Recevoir une réponse
                                    HttpServletResponse res, 	//Envoyer une réponse
                                    FilterChain chain) throws IOException, ServletException {
        
        String header = req.getHeader(SecurityConstants.HEADER_STRING);	//Récupérer le header.String "Authorization"
        
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {	//Vérifier si le header "Ahtorization" contient le Token
            chain.doFilter(req, res);
            return;
        }
        
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }   
    
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        
        if (token != null) {
            
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");	//Remplacer le Bearer par un vide pour récuppérer le toker
            
            String user = Jwts.parser()
                    .setSigningKey( SecurityConstants.TOKEN_SECRET )	 //Vérifier si la signature du header est la meme que la clé generé par spring	
                    .parseClaimsJws( token )
                    .getBody()
                    .getSubject();
            
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());	//Vérifier dans la base de donnée si user existe pour donnée les ressouce
            }
            
            return null;
        }
        
        return null;
    }
    

}