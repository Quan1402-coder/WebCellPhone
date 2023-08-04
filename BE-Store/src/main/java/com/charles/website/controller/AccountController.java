package com.charles.website.controller;

import com.charles.website.entity.User;
import com.charles.website.exception.BadRequestException;
import com.charles.website.model.MessageResponse;
import com.charles.website.model.dto.RegisterDTO;
import com.charles.website.model.request.ForgotPassRequest;
import com.charles.website.model.request.LoginRequest;
import com.charles.website.model.request.ResetPassRequest;
import com.charles.website.model.response.UserResponse;
import com.charles.website.services.EmailSenderService;
import com.charles.website.services.UserService;
import com.charles.website.utils.Authen;
import com.charles.website.utils.TemplateSendMail;
import com.charles.website.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService senderService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest login) {
        String username = login.getUsername();
        String password = login.getPassword();
        return ResponseEntity.ok(userService.loginAccount(username, password));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody RegisterDTO registerDTO,
                                          HttpServletRequest request){

        String token = userService.createAccount(registerDTO);

        try {
            String registerLink = Utility.getSiteURL(request) + "/api/account/verify?token=" + token;

            String subject = "Here's the link to register";

            String content = TemplateSendMail.getContent(registerLink, "Confirm Account", "First, you need to confirm your account.");

            senderService.sendEmail(registerDTO.getEmail(), subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new MessageResponse("Register success. Please check email verify!!!"));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@Validated @RequestParam("token") String token){
        log.warn(token);
        User user = userService.getByRegisterToken(token);

        if (user == null) {
            throw new BadRequestException(1402, "token wrong");
        }

        userService.verifyRegister(user);
        return ResponseEntity.ok(new MessageResponse("Verify success"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(HttpServletRequest request,
                                                   @Valid
                                                   @RequestBody ForgotPassRequest forgot){
        String token = RandomString.make(30);

        String email = forgot.getEmail();

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token;

            String subject = "Here's the link to reset your password";

            String content = TemplateSendMail.getContent(resetPasswordLink, "Reset Password", "You are asking for a password reset.");

            senderService.sendEmail(email, subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new MessageResponse("Fotgot password success. Please check email!!!"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(HttpServletRequest request,
                                                  @RequestParam("token") String token,
                                                  @RequestBody ResetPassRequest forgot) {

        User user = userService.getByResetPasswordToken(token);
        String password = forgot.getPassword();

        if (user == null) {
            throw new BadRequestException(1402, "token wrong");
        }

        userService.updatePassword(user, password);

        return ResponseEntity.ok(new MessageResponse("Reset password success"));
    }

    @PutMapping("update_info")
    public ResponseEntity<?> updateInfo(@Valid @RequestBody RegisterDTO request){
        Authen.check();

        userService.updateInfo(request);

        return ResponseEntity.ok(new MessageResponse("Sửa thành công"));
    }

    @GetMapping("info-detail")
    public ResponseEntity<?> infoDetail() {
        Authen.check();

        User user = userService.infoDetail();

        return ResponseEntity.ok(new UserResponse(user));
    }
}
