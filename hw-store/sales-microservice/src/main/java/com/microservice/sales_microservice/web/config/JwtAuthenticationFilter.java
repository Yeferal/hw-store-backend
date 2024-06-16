package com.microservice.sales_microservice.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /*Con el siguiente método extraeremos  el token JWT de la cabecera de nuestra petición Http("Authorization")
     * luego lo validaremos y finalmente se retornará*/
    private String getRequestToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            //Aca si se encuentra el token JWT, se devuelve una subcadena de "bearerToken" que comienza después de los primeros 7 caracteres hasta el final de la cadena
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }


    @Override                       //Solicitud entrante
    protected void doFilterInternal(HttpServletRequest request,
                                    //Respuesta saliente
                                    HttpServletResponse response,
                                    //Mecanismo para invocar el siguiente filtro en la siguiente cadena de filtros
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/hardware-store/api/swagger-ui.html") || requestUri.startsWith("/hardware-store/api/swagger-ui") ||
                requestUri.startsWith("/hardware-store/api/v3/api-docs") || requestUri.startsWith("/hardware-store/api/swagger-resources") ||
                requestUri.startsWith("/hardware-store/api/webjars")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtener el token desde la solicitud
        String token = getRequestToken(request);
        Cookie[] cookies = request.getCookies();
        String valueCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sid".equals(cookie.getName())) {
                    valueCookie = cookie.getValue();
                    break;
                }
            }
            if (valueCookie == null){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se encuentra el SID");
                return; // Detener el procesamiento de la cadena de filtros aquí
            }
        } else {
            // Token no válido, puedes manejarlo de alguna manera (por ejemplo, enviar una respuesta de error)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No se encuentra las cookies");
            return; // Detener el procesamiento de la cadena de filtros aquí
        }

        // Validar el token
        if (StringUtils.hasText(token) && jwtTokenProvider.validToken(token, valueCookie)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } else {
            // Token no válido, puedes manejarlo de alguna manera (por ejemplo, enviar una respuesta de error)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no válido");
            return; // Detener el procesamiento de la cadena de filtros aquí
        }
        // Permitir que la solicitud continúe hacia el siguiente filtro en la cadena de filtro.
        filterChain.doFilter(request, response);
    }
}
