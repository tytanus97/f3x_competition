package f3x.competition.F3XCompetition.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private static final String SECRET_KEY = "stararaszpla";


    private Boolean isTokenExpired(String token) {
         return extractExpiration(token).before(new Date());
    }

    private String extractUsername(String token) {
         return extractClaim(token,Claims::getSubject);
    }

    private Date extractExpiration(String token) {
         return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
         final Claims claims = extractAllClaims(token);
         return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
         return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    private String createToken(Map<String, Object> claims, String username) {
         return Jwts.builder().setClaims(claims).setSubject(username).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                 .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    public Boolean validateToken(String token,UserDetails userDetails) {
         final String userName = extractUsername(token);
         return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
