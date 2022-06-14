package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Creator;
import com.example.demo.entity.CreatorContract;

@Repository
public interface CreatorContractRepository extends JpaRepository<CreatorContract, Long>{
	Optional<CreatorContract> findByChannelAndCreator(ChannelContract channel, Creator creator);
	
	List<Optional<CreatorContract>> findByChannel(ChannelContract channel);
	
	List<Optional<CreatorContract>> findByCreator(Creator creator);
}
