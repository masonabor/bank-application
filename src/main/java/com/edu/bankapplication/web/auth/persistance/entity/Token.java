package com.edu.bankapplication.web.auth.persistance.entity;

public interface Token<T> {
    T getTokenHash();
}
