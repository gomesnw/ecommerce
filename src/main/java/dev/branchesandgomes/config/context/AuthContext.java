package dev.branchesandgomes.config.context;

import dev.branchesandgomes.model.CustomerEntity;
import dev.branchesandgomes.model.SellerEntity;

public class AuthContext {
    private static ThreadLocal<CustomerEntity> customer = new ThreadLocal<>();
    private static ThreadLocal<SellerEntity> seller = new ThreadLocal<>();

    public static CustomerEntity getCustomer() {
        return customer.get();
    }
    public static SellerEntity getSeller() {
        return seller.get();
    }
    public static void setSeller(SellerEntity sellerEntity) {
        seller.set(sellerEntity);
    }
    public static void setCustomer(CustomerEntity customerEntity) {
        customer.set(customerEntity);
    }

    public static void cleanup() {
        customer.remove();
        seller.remove();
    }
}
