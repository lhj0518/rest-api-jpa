package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Creator;
import com.example.demo.entity.CreatorContract;
import com.example.demo.repository.ChannelContractRepository;
import com.example.demo.repository.CreatorContractRepository;
import com.example.demo.repository.CreatorRepository;
@SpringBootTest
class ContractControllerTest {

	@Autowired
	ChannelContractRepository channelRepository;
	
	@Autowired
	CreatorRepository creatorRepository;
	
	@Autowired
	CreatorContractRepository ccRepository;
	
	@Test
    @Transactional
    @Rollback(false)
	public void createChannel() {
		String name = "test1";
		double rs  = 0.3;
		ChannelContract channel = ChannelContract.builder()
				.name(name)
				.rs(rs)
				.build();
		
		ChannelContract create = channelRepository.save(channel);
		Optional<ChannelContract> find = channelRepository.findById(create.getId());
		
		assertThat(find.get().getId()).isEqualTo(channel.getId());
	}
	
	@Test
    @Transactional
    @Rollback(false)
	public void createCreator() {
		String name = "test2";
		Creator creator = Creator.builder()
				.name(name)
				.build();
		
		Creator create = creatorRepository.save(creator);
		Optional<Creator> find = creatorRepository.findById(create.getId());
		
		assertThat(find.get().getId()).isEqualTo(creator.getId());
	}
	
	@Test
    @Transactional
    @Rollback(false)
	public void createCC() {
		ChannelContract channel = ChannelContract.builder()
				.name("channel_test_1")
				.rs(0.5)
				.build();
		channelRepository.save(channel);
		Creator creator = Creator.builder()
				.name("creator_test_1")
				.build();
		creatorRepository.save(creator);
		CreatorContract cc = CreatorContract.builder()
				.channel(channel)
				.creator(creator)
				.creatorName(creator.getName())
				.creatorRs(0.3)
				.build();
		
		ccRepository.save(cc);
	}

}
