package com.microservice.auth_microservice.web.controller;

import com.microservice.auth_microservice.domain.dto.AuthResponseDTO;
import com.microservice.auth_microservice.domain.dto.AuthTokenResponseDTO;
import com.microservice.auth_microservice.domain.dto.AuthenticatedBodyDTO;
import com.microservice.auth_microservice.domain.service.AuthService;
import com.microservice.auth_microservice.web.config.utils.ConstSecurity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final long EXPIRATION_TIME_IN_SECONDS = ConstSecurity.COOKIE_EXPIRATION_SID;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticated(
            @Valid @RequestBody AuthenticatedBodyDTO authenticatedBodyDTO,
            HttpServletResponse response
    ) {
        try {
            AuthResponseDTO authResponseDTO = authService.authenticate(authenticatedBodyDTO.getUsername(), authenticatedBodyDTO.getPassword());
            if (authResponseDTO == null) {
                return new ResponseEntity<>(new Error("Nombre de usuario o contraseña incorrectos."), HttpStatus.UNAUTHORIZED);
            }
            // Crear una nueva cookie
            Cookie cookie = new Cookie("sid", authResponseDTO.getSid());

            // Configurar propiedades de la cookie (opcional)
            cookie.setMaxAge((int) EXPIRATION_TIME_IN_SECONDS); // Duración en segundos
            cookie.setPath("/"); // Ruta del contexto de la aplicación
            cookie.setHttpOnly(true);

            authResponseDTO = AuthResponseDTO.builder()
                    .accessToken(authResponseDTO.getAccessToken())
                    .sid(null)
                    .build();

            // Agregar la cookie a la respuesta
            response.addCookie(cookie);


            return new ResponseEntity<>(new AuthTokenResponseDTO(authResponseDTO.getAccessToken()), HttpStatus.OK);
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new Error("Nombre de usuario o contraseña incorrectos."),  HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return  new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/isLoggin")
    public ResponseEntity<Boolean> isLoggin(
            @RequestHeader("Authorization") String authorizationHeader,
            @CookieValue(name = "sid", required = true) String sid
    ) {
        try{
            if (sid != null) {
                return new ResponseEntity<>(authService.isLogging(authorizationHeader.substring(7), sid), HttpStatus.OK);
            }
            return  new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        } catch (BadCredentialsException e){
            return new ResponseEntity(false,  HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return  new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(
            @RequestHeader("Authorization") String authorizationHeader,
            @CookieValue(name = "sid", required = true) String sid,
            HttpServletResponse response
    ) {
        try{
            if (sid != null) {
                Cookie cookie = new Cookie("sid", "");

                // Configurar propiedades de la cookie (opcional)
                cookie.setMaxAge(0); // Duración en segundos
                cookie.setPath("/"); // Ruta del contexto de la aplicación
                response.addCookie(cookie);

                return new ResponseEntity<>(authService.logoutUser(authorizationHeader.substring(7), sid), HttpStatus.OK);
            } else {
                return  new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (BadCredentialsException e){
            return new ResponseEntity(new Error("Credienciales Incorrectas."),  HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return  new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<String> saludo() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        try{
            return  new ResponseEntity<>("Hola si entro al test", HttpStatus.OK);
        } catch (BadCredentialsException e){
            return new ResponseEntity(new Error("Credienciales Incorrectas."),  HttpStatus.BAD_REQUEST);
        } catch (Exception ex){
            return  new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errorMessages.add(error.getField()+": "+error.getDefaultMessage());
        }
        // Construir una respuesta de error personalizada con los mensajes de error
        // ...
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        // HTTP 422 Unprocessable Entity
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessages);
    }
}
