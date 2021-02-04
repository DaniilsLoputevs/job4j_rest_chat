//package job4j.rest.chat.services;
//
//import job4j.rest.chat.models.Person;
//import job4j.rest.chat.repositories.UserStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Deprecated
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserStore users;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserStore users) {
//        this.users = users;
//    }
//
//    /**
//     * Stage 1: check username & password in db.
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Person user = users.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new User(user.getName(), user.getPassword(), new ArrayList<>());
//    }
//
//}
