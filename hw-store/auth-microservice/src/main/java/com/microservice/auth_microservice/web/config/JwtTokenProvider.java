package com.microservice.auth_microservice.web.config;

import com.microservice.auth_microservice.persistence.prepository.JwtTokenRedisPRepository;
import com.microservice.auth_microservice.web.config.utils.ConstSecurity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Autowired
    JwtTokenRedisPRepository jwtTokenRedisRepository;

    //Método para crear un token por medio de la authentication
    public String generateToken(Authentication authentication, List<String> roles, String browserId) {

        String username = authentication.getName();
        Date timeNow = new Date();
        Date expiretionToken = new Date(timeNow.getTime() + ConstSecurity.JWT_EXPIRATION_TOKEN);

        byte[] keyBytes = Decoders.BASE64.decode(ConstSecurity.JWT_SIGN);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        //Linea para generar el token
        String token = Jwts.builder() //Construimos un token JWT llamado token
                .setSubject(username) //Aca establecemos el nombre de usuario que está iniciando sesión
                .claim("roles", roles)
                .setIssuedAt(new Date()) //Establecemos la fecha de emisión del token en el momento actual
                .setExpiration(expiretionToken) //Establecemos la fecha de caducidad del token
                /*Utilizamos este método para firmar
                nuestro token y de esta manera evitar la manipulación o modificación de este*/
                .signWith(key)
                .compact(); //Este método finaliza la construcción del token y lo convierte en una cadena compacta

        // Guardar el token en Redis
        jwtTokenRedisRepository.storeToken(username, browserId, token);
        return token;
    }

    //Método para extraer un Username apartir de un token
    public String getUsernameJwt(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(ConstSecurity.JWT_SIGN);
        Key key = Keys.hmacShaKeyFor(keyBytes);

//        Claims claims = Jwts.parser() // El método parser se utiliza con el fin de analizar el token
//                .setSigningKey(ConstSecurity.JWT_SIGN)// Establece la clave de firma, que se utiliza para verificar la firma del token
//                .parseClaimsJws(token) //Se utiliza para verificar la firma del token, apartir del String "token"
//                .getBody(); /*Obtenemos el claims(cuerpo) ya verificado del token el cual contendrá la información de
//                nombre de usuario, fecha de expiración y firma del token*/
//        return claims.getSubject(); //Devolvemos el nombre de usuario
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.getSubject(); //Devolvemos el nombre de usuario
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                ((List<?>) claims.get("roles")).stream()
                        .map(role -> new SimpleGrantedAuthority((String) role))
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        //Aca establecimos información adicional de la autenticación, como por ejemplo la dirección ip del usuario, o el agente de usuario para hacer la solicitud etc.
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    //Método para validar el token
    public Boolean validToken(String token, String browserId) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(ConstSecurity.JWT_SIGN);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            //Validación del token por medio de la firma que contiene el String token(token)
            //Si son idénticas validara el token o caso contrario saltara la excepción de abajo
            String username = getUsernameJwt(token);

            // Verificar si el token existe en Redis
            return jwtTokenRedisRepository.isTokenValid(username, browserId, token);
        } catch (Exception e) {
            System.out.println(e);
            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
        }
    }

    //Metodo para Logout Eliminar el Token de Redis
    public boolean deleteToken(String username, String browserId) {
        try {
            jwtTokenRedisRepository.deleteToken(username, browserId);
            return true;
        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
            return false;
        }
    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(ConstSecurity.JWT_SIGN);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
