package bssm.doorlock.domain.auth.presentation;

import bssm.doorlock.domain.auth.presentation.dto.res.AuthLoginRes;
import bssm.doorlock.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("oauth/bsm")
    public AuthLoginRes bsmOauth(@RequestParam(value = "code") String authCode, HttpServletResponse res) throws Exception {
        return authService.loginPostProcess(res, authService.bsmOauth(authCode));
    }
}
