package ait.cohort34.securoty.filter;

import ait.cohort34.accouting.dao.UserAccountRepository;
import ait.cohort34.accouting.dto.exeption.AccountNotFoundExeption;
import ait.cohort34.accouting.model.UserAccount;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AuthentificationFilter implements Filter {

    final UserAccountRepository userAccountRepository;
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (checkEndpoint(request.getMethod(), request.getServletPath())) {
            try {
                String[] credentionals = getCredentionals(request.getHeader("Authorization"));
                UserAccount userAccount = userAccountRepository.findById(credentionals[0]).orElseThrow(RuntimeException::new);
                if(!BCrypt.checkpw(credentionals[1], userAccount.getPassword())) {
                    throw new RemoteException();
                }
                request = new WrappedRequest(request, userAccount.getLogin());
            } catch (Exception e) {
                response.setStatus(401);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private boolean checkEndpoint(String method, String path) {
        //TODO Homework
        return true;
    }

    private String[] getCredentionals(String authorization) {
        String token = authorization.split(" ")[1];
        String decode = new String(Base64.getDecoder().decode(token));
        return  decode.split(":");
    }

    private class WrappedRequest extends HttpServletRequestWrapper {
        private String login;

        public WrappedRequest(HttpServletRequest request, String login) {
            super(request);
            this.login = login;
        }
        @Override
        public Principal getUserPrincipal() {
            return () -> login;
        }
    }
}
