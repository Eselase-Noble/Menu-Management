//package com.nobleson.dashboardmanagement.service;
//
//import com.nobleson.dashboardmanagement.model.User;
//import com.nobleson.dashboardmanagement.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private static final Logger log = LoggerFactory.getLogger(UserService.class);
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    /**
//     * Get all the users in the system.
//     * @return
//     */
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//
//    /**
//     *
//     * @param username the username identifying the user whose data is required.
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User appUser = userRepository.findByUsername(username).orElseThrow(() ->
//                new UsernameNotFoundException("User details not found for user " + username));
//
//
//        // Handle the single authority
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getAuthority().getName());
//
//        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), List.of(grantedAuthority));
//    }
//
//
//
//
//
//
//}
