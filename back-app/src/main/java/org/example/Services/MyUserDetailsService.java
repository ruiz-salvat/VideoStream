package org.example.Services;

import org.example.Entities.ApplicationUser;
import org.example.Entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userService.getUserByUserName(username);
        List<GrantedAuthority> authorities = getUserAuthority(applicationUser.getRoles());
        return buildUserForAuthentication(applicationUser, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(ApplicationUser applicationUser, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(applicationUser.getUserName(), applicationUser.getPassword(),
                true, true, true, true, authorities);
    }
}
