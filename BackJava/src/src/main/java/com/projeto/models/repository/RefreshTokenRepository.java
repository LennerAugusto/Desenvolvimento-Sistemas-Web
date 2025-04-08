package com.projeto.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.models.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

}
