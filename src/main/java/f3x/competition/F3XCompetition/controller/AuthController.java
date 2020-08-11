package f3x.competition.F3XCompetition.controller;

import f3x.competition.F3XCompetition.entity.Pilot;
import f3x.competition.F3XCompetition.request.AuthenticationRequest;
import f3x.competition.F3XCompetition.response.AuthenticationResponse;
import f3x.competition.F3XCompetition.service.PilotService;
import f3x.competition.F3XCompetition.serviceImpl.CustomUserDetailsService;
import f3x.competition.F3XCompetition.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final PilotService pilotService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, PilotService pilotService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.pilotService = pilotService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        System.out.println(authenticationRequest.toString());
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        final Optional<Pilot> tmpPilot = this.pilotService.findByUsername(authenticationRequest.getUsername());
        return tmpPilot.map(pilot -> new ResponseEntity<>(new AuthenticationResponse(jwt, pilot.getPilotId()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
