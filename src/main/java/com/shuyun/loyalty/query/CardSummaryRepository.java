package com.shuyun.loyalty.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSummaryRepository extends JpaRepository<CardSummary, String> {
}
