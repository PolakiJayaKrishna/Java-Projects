package com.nothing.urlshortener.repository;

import com.nothing.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping,Long> {

    UrlMapping findByShortKey(String shortKey);
}