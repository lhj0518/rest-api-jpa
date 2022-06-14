package com.example.demo.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Creator;
import com.example.demo.entity.CreatorContract;
import com.example.demo.entity.dto.ChannelContractDTO;
import com.example.demo.entity.dto.CreatorContractDTO;
import com.example.demo.entity.dto.CreatorDTO;
import com.example.demo.exception.AlreadyExistException;
import com.example.demo.repository.ChannelContractRepository;
import com.example.demo.repository.CreatorContractRepository;
import com.example.demo.repository.CreatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService {

	private final ChannelContractRepository channelRepository;
	private final CreatorRepository creatorRepository;
	private final CreatorContractRepository creatorConRepository;
	
	public ChannelContract readChannel(Long id) {
		Optional<ChannelContract> channel = channelRepository.findById(id);
		if(!channel.isPresent()) {
			throw new EntityNotFoundException("cannot channel");
		}
		return channel.get();
	}
	
	public ChannelContract addChannel(ChannelContractDTO request) {
		ChannelContract channel = ChannelContract.builder()
				.name(request.getName())
				.rs(request.getRs())
				.build();
		return channelRepository.save(channel);
	}
	
	public Creator addCreator(CreatorDTO request) {
		Creator creator = Creator.builder()
				.name(request.getName())
				.build();

		return creatorRepository.save(creator);
	}
	
	public CreatorContract createCreatorCont(CreatorContractDTO request) {
		Optional<ChannelContract> channelId = channelRepository.findById(request.getChannelId());
		if(!channelId.isPresent()) {
			throw new EntityNotFoundException("not found channel");
		}
		
		Optional<Creator> creatorId = creatorRepository.findById(request.getCreatorId());
		if(!creatorId.isPresent()) {
			throw new EntityNotFoundException("not found creator");
		}
		
		CreatorContract creator = new CreatorContract();
		
		Optional<CreatorContract> creatorForChannel = creatorConRepository.findByChannelAndCreator(channelId.get(), creatorId.get());
		if(!creatorForChannel.isPresent()) {
			creator = CreatorContract.builder()
					.channel(channelId.get())
					.creator(creatorId.get())
					.creatorRs(request.getRs())
					.creatorName(creatorId.get().getName())
					.build();
	
		} else {
			throw new AlreadyExistException();
		}
		return creatorConRepository.save(creator);
	}	
}
