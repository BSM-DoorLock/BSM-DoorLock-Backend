package bssm.doorlock.domain.user.presentation;

import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import bssm.doorlock.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserUtil userUtil;

    @GetMapping()
    public UserRes getUserInfo() {
        return userUtil.getUser().toUserResponse();
    }

}
