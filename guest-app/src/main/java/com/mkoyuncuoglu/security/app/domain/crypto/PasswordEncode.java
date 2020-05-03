package com.mkoyuncuoglu.security.app.domain.crypto;

import java.nio.charset.Charset;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncode {

    public String encode(String pass) {
        String hashedVal = Base64.getEncoder().encodeToString(DigestUtils.sha1(pass.getBytes(Charset.forName("UTF-8"))));
        return "{SHA}" + hashedVal;
    }
}
