package com.example.demo.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorContractDTO {
	private Long channelId;
	private Long creatorId;
	private String name;
	private double rs;
	
}
