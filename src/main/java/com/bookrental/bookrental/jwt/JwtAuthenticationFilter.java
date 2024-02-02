package com.bookrental.bookrental.jwt;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.exception.AppException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger templogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtHelper jwtHelper;

    private final UserDetailsService userDetailsService;
    private final CustomMessageSource customMessageSource;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization
        String requestHeader = request.getHeader("Authorization");
        //Bearer 2352345235sdfrsfgsdfsdf
        templogger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            //looking good
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                templogger.info("Illegal Argument while fetching the username !!");
//                e.printStackTrace();
                throw new AppException(customMessageSource.get(e.getMessage()));
            } catch (ExpiredJwtException e) {
                templogger.info("Given jwt token is expired !!");
//                e.printStackTrace();
                throw new AppException(customMessageSource.get(e.getMessage()));
            } catch (MalformedJwtException e) {
                templogger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
//                throw new AppException(customMessageSource.get(e.getMessage()));
            } catch (Exception e) {
//                e.printStackTrace();
                throw new AppException(customMessageSource.get(e.getMessage()));
            }

        } else {
            templogger.info("Invalid Header Value !! ");
        }


        //
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (Boolean.TRUE.equals(validateToken)) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                templogger.info("Validation fails !!");
            }
        }
        filterChain.doFilter(request, response);
    }
}