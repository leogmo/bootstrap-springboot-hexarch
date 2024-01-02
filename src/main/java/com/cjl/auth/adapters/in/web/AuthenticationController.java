package com.cjl.auth.adapters.in.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjl.auth.adapters.out.jpa.AccountJpaRepositoryAdapter;
import com.cjl.auth.application.usecase.account.LoginDTO;
import com.cjl.auth.application.usecase.account.LoginResponseDTO;
import com.cjl.auth.application.usecase.account.LoginUseCase;
import com.cjl.auth.application.usecase.account.PassRecoverDTO;
import com.cjl.auth.application.usecase.account.PasswordRecoveryUseCase;
import com.cjl.auth.application.usecase.account.SignupDTO;
import com.cjl.auth.application.usecase.account.SignupUseCase;
import com.cjl.auth.application.usecase.account.UpdatePasswordDTO;
import com.cjl.auth.application.usecase.account.UpdatePasswordUseCase;
import com.cjl.auth.domain.User;
import com.cjl.auth.infrastructure.security.jwt.JwtTokenUtil;

import jakarta.ws.rs.core.Response;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	AccountJpaRepositoryAdapter accountJpaRepositoryAdapter;

	@Autowired
	LoginUseCase loginUseCase;

	@Autowired
	SignupUseCase signupUseCase;

	@Autowired
	PasswordRecoveryUseCase passwordRecoveryUseCase;

	@Autowired
	UpdatePasswordUseCase updatePasswordUseCase;


	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {

		final Optional<User> user = accountJpaRepositoryAdapter.findByEmail(authenticationRequest.getEmail());

		if (!user.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		}
		final String token = jwtTokenUtil.generateToken(user.get());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

	@PostMapping("/password/recovery")
	public Response passRecover(@RequestBody PassRecoverDTO passRecoverDTO) throws Exception {
		passwordRecoveryUseCase.recover(passRecoverDTO);
		return Response.ok().build();
	}

	@PostMapping("/signup")
	public Response signup(@RequestBody SignupDTO signupDTO) throws Exception {
		return Response.ok(signupUseCase.signup(signupDTO)).build();
	}

//    @GetMapping("/password/recovery/email")
//    public Response recoverEmail(SignupDTO signupDTO){
//        return Response.ok(identity.getIdentity().getPrincipal().getName()).build();
//    }

	@PostMapping("/password/update")
	public Response passwordUpdate(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
		try {
			updatePasswordUseCase.update(updatePasswordDTO);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

	}

}
