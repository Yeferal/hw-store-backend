package com.microservice.admin_microservice.domain.spec;

import com.microservice.admin_microservice.persistence.model.Account;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AccountSpecifications {

    // ACCOUNT
    public static Specification<Account> usernameContains(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<Account> creationDateBetween(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("creationDate"), startDate, endDate);
    }

    public static Specification<Account> creationDateAfter(Date date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), date);
    }

    public static Specification<Account> creationDateBefore(Date date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), date);
    }

    public static Specification<Account> isActive(Boolean active) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("active"), active);
    }

    public static Specification<Account> isVerified(Boolean verified) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("verified"), verified);
    }

    // USERPROFILE
    public static Specification<Account> userProfileFirstNameContains(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Account> userProfileLastNameContains(String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Account> userProfileEmailContains(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("email"), "%" + email + "%");
    }

    // ROLE
    public static Specification<Account> roleNameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.join("assignmentRoleList").get("role").get("name"), "%" + name + "%");
    }

    // OTROS


//    public static Specification<Account> accountNameContains(String accountName) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("accountName"), "%" + accountName + "%");
//    }
//
//    public static Specification<Account> userProfileFirstNameContains(String firstName) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("firstName"), "%" + firstName + "%");
//    }
//
//    public static Specification<Account> userProfileLastNameContains(String lastName) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("lastName"), "%" + lastName + "%");
//    }
//
//    public static Specification<Account> userProfileEmailContains(String email) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("userProfile").get("email"), "%" + email + "%");
//    }
//
//    public static Specification<Account> creationDateBetween(Date startDate, Date endDate) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("creationDate"), startDate, endDate);
//    }
//
    /*
     * Filtrar por comparación de números
     * Especificaciones para números (greater than, less than, equal to)
     * */
//    public static Specification<Account> balanceGreaterThan(Double balance) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("balance"), balance);
//    }
//
//    public static Specification<Account> balanceLessThan(Double balance) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("balance"), balance);
//    }
//
//    public static Specification<Account> balanceEqualTo(Double balance) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("balance"), balance);
//    }
//
    /*
    * Crear una especificación para el campo active
    * Especificaciones AccountSpecifications
    * */
//    public static Specification<Account> isActive(Boolean active) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("active"), active);
//    }

//    public static Specification<User> isActive() {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.isTrue(root.get("active"));
//    }
}
