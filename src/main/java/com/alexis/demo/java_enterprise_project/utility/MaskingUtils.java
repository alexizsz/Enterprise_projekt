package com.alexis.demo.java_enterprise_project.utility;

import org.springframework.stereotype.Component;
/**
 * MaskingUtils används för att maskera den e-post som sparas på hemsidan.
 **/
@Component
public class MaskingUtils {

    public static String maskEmail(String email) {
        if (email == null) {
            return null;
        }
        int atIndex = email.indexOf('@');
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);

        return localPart.charAt(0) + "*" + localPart.charAt(localPart.length() - 1) + domainPart;
    }


}
