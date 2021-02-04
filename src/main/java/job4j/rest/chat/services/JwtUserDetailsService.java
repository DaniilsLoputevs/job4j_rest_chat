package job4j.rest.chat.services;

import job4j.rest.chat.models.Person;
import job4j.rest.chat.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;
    
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//    @Autowired
//    UserStore userStore;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getName(), user.getPassword(), new ArrayList<>());
        /* use this, if you use NOT Spring DAO data store{@code List, Set, map, etc...}. */
//        return new User(user.getName(), passwordEncoder.encode(user.getPassword()), new ArrayList<>());
    }
    
}