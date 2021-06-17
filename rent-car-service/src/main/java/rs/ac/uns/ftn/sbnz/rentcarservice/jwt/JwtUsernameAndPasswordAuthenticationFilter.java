package rs.ac.uns.ftn.sbnz.rentcarservice.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.runtime.KieContainer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rs.ac.uns.ftn.sbnz.rentcarservice.dto.LoginDto;
import rs.ac.uns.ftn.sbnz.rentcarservice.model.LoginEvent;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.KnowledgeService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenUtils tokenUtils;

    private final KnowledgeService knowledgeService;

    private LoginDto loginDto;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils, KnowledgeService knowledgeService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.knowledgeService = knowledgeService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            LoginDto userLoginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            this.loginDto = userLoginDTO;
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getEmail(),
                        userLoginDTO.getLozinka()
                    ));

            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
                                            throws IOException, ServletException {
        String jwtToken = tokenUtils.generateToken(authResult.getName(), authResult.getAuthorities());

        response.addHeader(tokenUtils.getAuthHeader(), "Bearer " + jwtToken);
        response.addHeader(tokenUtils.getExpHeader(), String.valueOf(tokenUtils.getExpiresIn()));
        response.addHeader("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept, Authorization, Expires-In");
        response.addHeader("Access-Control-Expose-Headers", "Authorization, Expires-In");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        for(int i=0; i<10; i++)
            knowledgeService.getEventsSession().insert(new LoginEvent(this.loginDto.getEmail()));

        super.unsuccessfulAuthentication(request, response, failed);
    }
}
