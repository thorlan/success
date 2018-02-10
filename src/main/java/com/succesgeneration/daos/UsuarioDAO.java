package com.succesgeneration.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.succesgeneration.models.Usuario;


@Repository
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;
	
	
	/*@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> from = query.from(Usuario.class);
		
		CriteriaQuery<Usuario> select = query.select(from);
		
		Path<String> emailPath = from.get("email");
		select.where(criteriaBuilder.equal(emailPath, email));
		
		TypedQuery<Usuario> typedQuery = manager.createQuery(select);
		List<Usuario> usuarios = typedQuery.getResultList();
		
		if ( usuarios.isEmpty()) {
			throw new RuntimeException("O usuário não foi encontrado");
		}
		
		return usuarios.get(0);
	}*/
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
                .setParameter("email", email).getResultList();
		
        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("O usuário " + email + " não foi encontrado");
        }

        return usuarios.get(0);
	}


}


