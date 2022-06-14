package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
	Optional<Revenue> findByChannelAndDate(ChannelContract channel, LocalDate date);
	
	@Query(value = "select sum(r.revenue) from Revenue r "
	        + "where r.channel = ?1 and YEAR(r.date) = ?2 and MONTH(r.date) = ?3")
	Optional<Long> findByIdAndDate(ChannelContract channel, int year, int month);
	
}
