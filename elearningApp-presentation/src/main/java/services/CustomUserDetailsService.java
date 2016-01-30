package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dao.SingletonDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import exception.EntityNotFoundException;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{


	private UserDAO userService;
	public static int i = 0;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		User user;
		System.out.println("******* frfer " + ssoId + "  " + (i++));
		try {

			user = SingletonDAO.getUserdao().getUserByUsername(ssoId);

			List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
			System.out.println("Good , role is : "+ authorities.get(0).getAuthority()  );
			return buildUserForAuthentication(user, authorities);

			// Collection<Role> roles = user.getRoles();
			// String role = ((Role) (roles.toArray())[0]).getRoleName();
			// GrantedAuthority authority = new
			// SimpleGrantedAuthority(role);
			// UserDetails userDetails = (UserDetails) new
			// org.springframework.security.core.userdetails.User(user.getUserName(),
			// user.getMotDePasse(), Arrays.asList(authority));
			//
			// return userDetails;
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Inside catch");
			e.printStackTrace();
		}
		

		System.out.println("*******Null");
		return null;
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
			List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getMotDePasse(),
				user.isEstConfirme(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Collection<Role> collection) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : collection) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	
	public void throwNotFound() throws EntityNotFoundException
	{
		throw new EntityNotFoundException();
	}

}