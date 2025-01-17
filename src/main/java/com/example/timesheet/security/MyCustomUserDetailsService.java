package com.example.timesheet.security;

import com.example.timesheet.model.User;
import com.example.timesheet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    // {SpringSecurity}
    // ... @UserDetailsService userDetailsService
    // User[admin] --login--> [UserDetailsService userDetailsService.loadUserByName(login)]
    // [UserDetails -> SecurityContext]

//     В нашем случае юзеры хранятся в БД в таблице UserRepository
//     Строго говоря в этой реализации UserDetailsService можно загружать данные о
//     пользователе из любого источника:
//     внешний-auth-server, ldap-server, ...

    private final UserRepository userRepository;

    public MyCustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Пытаемся загрузить пользователя: " + username);
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден."));

        System.out.println("Пользователь найден: " + user.getLogin());

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                authorities);
    }
}
