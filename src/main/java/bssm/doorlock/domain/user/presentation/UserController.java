package bssm.doorlock.domain.user.presentation;

import bssm.doorlock.domain.user.presentation.dto.res.UserInfoRes;
import bssm.doorlock.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserUtil userUtil;

    @GetMapping()
    public UserInfoRes getUserInfo() {
        return userUtil.getUser().toUserInfoResponse();
    }

}
