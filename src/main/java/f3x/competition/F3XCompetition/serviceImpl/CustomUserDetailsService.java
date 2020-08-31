package f3x.competition.F3XCompetition.serviceImpl;

import f3x.competition.F3XCompetition.entity.PilotCredential;
import f3x.competition.F3XCompetition.repository.PilotCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PilotCredentialRepository pilotCredentialRepository;

    @Autowired
    public CustomUserDetailsService(PilotCredentialRepository pilotCredentialRepository) {
        this.pilotCredentialRepository = pilotCredentialRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PilotCredential pilotCredential = this.pilotCredentialRepository.findByUsername(username);
        if(pilotCredential == null) throw new UsernameNotFoundException("User doesn't exist");
        return new org.springframework.security.core.userdetails.User(pilotCredential.getUsername()
                ,pilotCredential.getPassword(),new ArrayList<>());
    }
}
