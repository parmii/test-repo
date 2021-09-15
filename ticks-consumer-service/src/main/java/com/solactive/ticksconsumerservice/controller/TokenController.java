package com.solactive.ticksconsumerservice.controller;

import com.solactive.ticksconsumerservice.request.AuthenticationRequest;
import com.solactive.ticksconsumerservice.response.AuthenticationResponse;
import com.solactive.ticksconsumerservice.service.MyUserDetailsService;
import com.solactive.ticksconsumerservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.solactive.ticksconsumerservice.constants.Constants.GENERATE_TOKEN_API_SUMMARY;
import static com.solactive.ticksconsumerservice.constants.Constants.MSG_JWT_GENERATED_SUCCESSFULLY;
import static com.solactive.ticksconsumerservice.constants.ErrorConstants.AUTHENTICATION_FAILED_MESSAGE;

@RestController
@Slf4j
public class TokenController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final MyUserDetailsService userDetailsService;

    public TokenController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, MyUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method created a JWT token using username and password which is used for authentication
     * and authorization of GET ticks/{RIC} API
     * @param authenticationRequest
     * @return AuthenticationResponse which contains a JWT token
     * @throws Exception in case of wrong username and password
     */
    @Operation(summary = GENERATE_TOKEN_API_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MSG_JWT_GENERATED_SUCCESSFULLY,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationResponse.class))})})
    @PostMapping(value = "/generateToken", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.error(AUTHENTICATION_FAILED_MESSAGE,e);
            throw new Exception(AUTHENTICATION_FAILED_MESSAGE, e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
