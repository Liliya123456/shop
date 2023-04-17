package com.liliya.shop.config;

import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findById(username)
        .map(account -> User.builder()
            .username(account.getId())
            .password(account.getPassword())
            .roles(account.getRoles() == null ? new String[0] : account.getRoles().toArray(new String[0]))
            .build())
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
