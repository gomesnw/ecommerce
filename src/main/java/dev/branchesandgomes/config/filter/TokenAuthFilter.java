package dev.branchesandgomes.config.filter;

import dev.branchesandgomes.model.entity.UserDetailsImpl;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.model.enums.RoleType;
import dev.branchesandgomes.service.GetCustomerByExternalIdService;
import dev.branchesandgomes.service.GetSellerByExternalIdService;
import dev.branchesandgomes.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TokenAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final GetCustomerByExternalIdService getCustomerByExternalIdService;
    private final GetSellerByExternalIdService getSellerByExternalIdService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        String token = recoveryToken(authorizationHeader);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UUID userExternalId = jwtService.getUserExternalIdFromToken(token);
                RoleType userType = jwtService.getUserTypeFromToken(token);
                UserDetailsImpl userDetails = switch (userType) {
                    case CUSTOMER -> {
                        CustomerEntity customer = getCustomerByExternalIdService.execute(userExternalId);
                        yield new UserDetailsImpl(customer);
                    }
                    case SELLER -> {
                        SellerEntity seller = getSellerByExternalIdService.execute(userExternalId);
                        yield new UserDetailsImpl(seller);
                    }
                };

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\": 401, \"message\": \"invalid JWT token\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
