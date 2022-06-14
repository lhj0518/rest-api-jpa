package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Creator;
import com.example.demo.entity.CreatorContract;
import com.example.demo.entity.Revenue;
import com.example.demo.entity.dto.BillingDTO;
import com.example.demo.entity.dto.RevenueDTO;
import com.example.demo.exception.AlreadyExistException;
import com.example.demo.repository.ChannelContractRepository;
import com.example.demo.repository.CreatorContractRepository;
import com.example.demo.repository.CreatorRepository;
import com.example.demo.repository.RevenueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevenueService {
	private final ChannelContractRepository channelRepository;
	private final CreatorRepository creatorRepository;
	private final CreatorContractRepository creatorConRepository;
	private final RevenueRepository revenueRepository;
	
	public Revenue addChannelRevenue(RevenueDTO request) {
		Optional<ChannelContract> channelId = channelRepository.findById(request.getChannelId());
		if(!channelId.isPresent()) {
			throw new EntityNotFoundException("not found channel");
		}
		Revenue revenue = new Revenue();
		Optional<Revenue> revenueForDate = revenueRepository.findByChannelAndDate(channelId.get(), request.getDate());
		if(!revenueForDate.isPresent()) {
			 revenue = Revenue.builder()
					.channel(channelId.get())
					.date(request.getDate())
					.revenue(request.getRevenue())
					.build();
		} else {
			throw new AlreadyExistException();
		}
		return revenueRepository.save(revenue);
	}
	
	public List<BillingDTO> readChannel(Long id, int year, int month) {
		Optional<ChannelContract> channelId = channelRepository.findById(id);
		if(!channelId.isPresent()) {
			throw new EntityNotFoundException("not found channel");
		}
		
		double rs = channelId.get().getRs();
		
		Long channelRevenue = Math.round(monthlyChannelRevenue(channelId.get(), year, month) * rs);
		
		List<BillingDTO> list = new ArrayList<>();
		
		List<Optional<CreatorContract>> creatorForChannel = creatorConRepository.findByChannel(channelId.get());
		for(Optional<CreatorContract> cc : creatorForChannel) {
			if(!cc.isPresent()) {
				throw new EntityNotFoundException("not found creator");
			}
			BillingDTO dto = new BillingDTO();
			dto.setChannelName(channelId.get().getName());
			dto.setCreatorName(cc.get().getCreatorName());
			dto.setRevenue(Math.round(channelRevenue * cc.get().getCreatorRs()));
			list.add(dto);
		}
		return list;
	}
	
	public List<BillingDTO> readCreator(Long id, int year, int month) {
		Optional<Creator> creatorId = creatorRepository.findById(id);
		if(!creatorId.isPresent()) {
			throw new EntityNotFoundException("not found creator");
		}
		
		List<BillingDTO> list = new ArrayList<>();
		
		List<Optional<CreatorContract>> channelForCreator = creatorConRepository.findByCreator(creatorId.get());
		for(Optional<CreatorContract> cc : channelForCreator) {
			if(!cc.isPresent()) {
				throw new EntityNotFoundException("not found channel");
			}
			double channelRs = cc.get().getChannel().getRs();
			double creatorRs = cc.get().getCreatorRs();
			
			Long channelRevenue = Math.round(monthlyChannelRevenue(cc.get().getChannel(), year, month) * channelRs);
			
			BillingDTO dto = new BillingDTO();
			dto.setChannelName(cc.get().getChannel().getName());
			dto.setCreatorName(cc.get().getCreatorName());
			dto.setRevenue(Math.round(channelRevenue * creatorRs));
			list.add(dto);
		}

		return list;
	}
	
	public Long allRevenue(Long id, int year, int month) {
		Optional<ChannelContract> channelId = channelRepository.findById(id);
		if(!channelId.isPresent()) {
			throw new EntityNotFoundException("not found channel");
		}
		return monthlyChannelRevenue(channelId.get(), year, month);
	}
	
	public Long profit(Long id, int year, int month) {
		Optional<ChannelContract> channelId = channelRepository.findById(id);
		if(!channelId.isPresent()) {
			throw new EntityNotFoundException("not found channel");
		}
		
		Long re = monthlyChannelRevenue(channelId.get(), year, month);
		double rs = 1.0 - channelId.get().getRs();
		return Math.round(re * rs);
	}
	
	public Long monthlyChannelRevenue(ChannelContract channel, int year, int month) {
		Optional<Long> re = revenueRepository.findByIdAndDate(channel, year, month);
		Long revenue;
		if(!re.isPresent()) {
			revenue = 0L;
		} else {
			revenue = re.get();
		}
		
		return revenue;
	}
}
