package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ChannelContract;
import com.example.demo.entity.Creator;
import com.example.demo.entity.CreatorContract;
import com.example.demo.entity.dto.ChannelContractDTO;
import com.example.demo.entity.dto.CreatorContractDTO;
import com.example.demo.entity.dto.CreatorDTO;
import com.example.demo.service.ContractService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService channelService;
    
    @PostMapping("/channel")
    public ResponseEntity<ChannelContract> createChannel(@RequestBody ChannelContractDTO request){
    	return ResponseEntity.ok(channelService.addChannel(request));
    }
	
    @PostMapping("/creator")
	public ResponseEntity<Creator> addCreator(@RequestBody CreatorDTO request){
		return ResponseEntity.ok(channelService.addCreator(request));
	}
    
	@PostMapping("/channel/creator")
	public ResponseEntity<CreatorContract> createCreator(@RequestBody CreatorContractDTO request){
		return ResponseEntity.ok(channelService.createCreatorCont(request));
	}
	
}