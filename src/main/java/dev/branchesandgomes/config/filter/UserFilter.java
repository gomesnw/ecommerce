package dev.branchesandgomes.config.filter;

import dev.branchesandgomes.config.context.AuthContext;
import dev.branchesandgomes.model.entity.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {

            if (userDetails.getCustomer() != null) {
                AuthContext.setCustomer(userDetails.getCustomer());
            }

            if (userDetails.getSeller() != null) {
                AuthContext.setSeller(userDetails.getSeller());
                AuthContext.setTenantId(userDetails.getSeller().getTenantId());
            }

        } else {
            AuthContext.setCustomer(null);
            AuthContext.setSeller(null);
            AuthContext.setTenantId(null);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            AuthContext.cleanup();
        }
    }
}