package com.example.springbootbooksellerapp.service;

import com.example.springbootbooksellerapp.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
