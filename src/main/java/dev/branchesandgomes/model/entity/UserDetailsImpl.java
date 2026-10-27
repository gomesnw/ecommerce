package dev.branchesandgomes.model.entity;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserDetailsImpl implements UserDetails {
    private CustomerEntity customer;
    private SellerEntity seller;

    public UserDetailsImpl(SellerEntity seller) {
        this.seller = seller;
    }

    public UserDetailsImpl(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = customer != null ? "ROLE_CUSTOMER" : "ROLE_SELLER";
        return List.of(() -> role);
    }

    @Override
    public String getPassword() {
        return seller != null ? seller.getPassword() : null;
    }

    @Override
    public @Nullable String getUsername() {
        if (customer != null) {
            return customer.getPhone();
        }

        if (seller != null) {
            return seller.getEmail();
        }

        return null;
    }

    @Override
    public boolean isEnabled() {
        if (customer != null) {
            return customer.getActive();
        }

        if (seller != null) {
            return seller.getActive();
        }

        return false;
    }
}