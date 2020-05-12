package ch.hsr.greatnamebackend.common.authentication;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import java.net.HttpURLConnection;
import java.net.URL;

public final class GoogleIdentityAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleIdentityAuthenticationProvider.class);

    private final GoogleIdentityServiceConfig googleIdentityServiceConfig;

    public GoogleIdentityAuthenticationProvider(GoogleIdentityServiceConfig googleIdentityServiceConfig) {
        this.googleIdentityServiceConfig = googleIdentityServiceConfig;
    }


    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        LOG.debug("Authentication in Progress");

        //"https://oauth2.googleapis.com/tokeninfo"
        URL url = new URL(googleIdentityServiceConfig.getTokenEndpoint() + "?id_token=" + token);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        con.disconnect();

        if (status != 200)
            throw new BadCredentialsException("Invalid Id_Token provided");

        return new GoogleIdentityAuthenticationToken(token);
    }

    @Override
    public boolean supports(Class<?> authorization) {
        return GoogleIdentityAuthenticationToken.class.isAssignableFrom(authorization);
    }
}

