package com.detech.gsrt.services;

import com.detech.gsrt.dto.UtilisateurDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("autentication")
@Slf4j
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {

        UtilisateurDto dto = null;
        try {
            dto = this.utilisateurService.trouverParEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(dto.getRole().getNom());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
*/
        return new org.springframework.security.core.userdetails.User(
                dto.getEmail(),
                dto.getPassword(),
                List.of(new SimpleGrantedAuthority(dto.getRole().getNom()))
        );

    }
}
