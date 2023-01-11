package bssm.doorlock.domain.auth.presentation;

import bssm.doorlock.domain.auth.presentation.dto.res.AuthTokenRes;
import bssm.doorlock.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${token.refresh-token.name}")
    private String REFRESH_TOKEN_NAME;

    @PostMapping("oauth/bsm")
    public AuthTokenRes bsmOauth(HttpServletRequest req) throws Exception {
        return authService.loginPostProcess(authService.bsmOauth(req.getHeader("code")));
    }

    @PutMapping("token/refresh")
    public AuthTokenRes tokenRefresh(HttpServletRequest req) {
        return authService.tokenRefresh(req.getHeader(REFRESH_TOKEN_NAME));
    }

    @DeleteMapping("logout")
    public void logout(HttpServletRequest req) {
        authService.logout(req.getHeader(REFRESH_TOKEN_NAME));
    }
}
