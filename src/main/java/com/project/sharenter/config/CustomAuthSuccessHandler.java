package com.project.sharenter.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


//Customizes the successes handler to choose the destination URL of the user based on its role and authority
@Configuration
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if(response.isCommitted()){
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
        return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority grantedAuthority : authorities){
            roles.add(grantedAuthority.getAuthority());
        }
        if(roles.contains("ROLE_RENTER")){
            url = "/renter/dashboard";
        }else if(roles.contains("ROLE_SHARER")) {
            url = "/sharer/dashboard";
        }
        return url;
    }
}
