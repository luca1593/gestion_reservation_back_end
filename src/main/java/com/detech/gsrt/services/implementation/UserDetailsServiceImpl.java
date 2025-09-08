package com.detech.gsrt.services.implementation;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.detech.gsrt.modeles.Utilisateur;
import com.detech.gsrt.repository.UtilisateurRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UtilisateurRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur user = new Utilisateur();
		try {
			user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException());
		} catch (NotFoundException e) {
			log.error(String.format("User does not exist, email: %s", email));
		}

		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).build();
	}

}
