package demo.design.level.low.parkinglot.services.authentication.services.implementations;

import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.LoginRequest;
import demo.design.level.low.parkinglot.services.authentication.api.dto.responses.AuthenticatedUserResponse;
import demo.design.level.low.parkinglot.services.authentication.entities.User;
import demo.design.level.low.parkinglot.services.authentication.entities.Role;
import demo.design.level.low.parkinglot.services.authentication.configurations.SecurityProperties;
import demo.design.level.low.parkinglot.services.authentication.services.UserAuthenticationService;
import demo.design.level.low.parkinglot.services.authentication.services.UserService;
import demo.design.level.low.parkinglot.services.authentication.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private UserService userService;

    /** This method handle authentication remotely from dealerEngine authentication-api
     * @param loginRequest
     * @return 0
     * @throws BadCredentialsException
     */
    @Override
    public AuthenticatedUserResponse validateUser(final LoginRequest loginRequest,
                                                  final HttpServletRequest httpServletRequest)
            throws BadCredentialsException {

        log.info("login request received for Username: {}", loginRequest.getUsername());
        Optional<User> user = this.userService.findByUsername(loginRequest.getUsername());
        if (user.isPresent()) {
            return this.prepareAuthenticatedUserResponse(user.get(), loginRequest, httpServletRequest);
        }
        throw new BadCredentialsException(this.securityProperties.getBadCredentialMessage());
    }

    private AuthenticatedUserResponse prepareAuthenticatedUserResponse(final User user, final LoginRequest loginRequest,
                                                                       final HttpServletRequest httpServletRequest) {
        int generatedRandomNumber = this.jwtUtils.generateRandomNumber();
        String generatedUTID      = this.jwtUtils.prepareUTID(generatedRandomNumber);
        String jti                = this.jwtUtils.generateJti();
        Calendar calendar         = Calendar.getInstance();
        Date dateExp              = this.jwtUtils.generateExpirationDate(calendar);
        log.info("Expiration Date for the auth token {}", dateExp.toString());
        String secret             = this.securityProperties.getSecret().get(generatedRandomNumber);
        Role role = user.getRoles().stream().findFirst().get();
        String accessToken        = this.jwtUtils.generateAccessToken(
                loginRequest.getUsername(), role.getName().name(), generatedUTID,
                jti, dateExp, secret, null);
        return new AuthenticatedUserResponse(generatedUTID, jti, accessToken, user.getName(), role.getName().name());
    }

}
