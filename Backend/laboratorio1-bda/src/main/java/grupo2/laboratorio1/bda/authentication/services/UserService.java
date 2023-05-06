package grupo2.laboratorio1.bda.authentication.services;

import grupo2.laboratorio1.bda.authentication.entities.User;
import grupo2.laboratorio1.bda.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    VoluntarioService voluntarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    public User getUserByEmail(String email) {
        return User.voluntarioToUser(voluntarioService.getVoluntarioByCorreo(email));
    }
}
