package unimiskolc.springboot.integration;


import org.apache.commons.io.FileUtils;
import org.springframework.messaging.Message;
import unimiskolc.springboot.model.User;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserEnricher {

    public String fillEmpty(Message<User> user) {

        String pass = "pass";

        if (user.getPayload().getUserPassword() != null) {
            pass = user.getPayload().getUserPassword();
        }
        return pass;

    }
}
