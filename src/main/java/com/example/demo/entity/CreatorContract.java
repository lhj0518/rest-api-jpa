package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreatorContract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double creatorRs;
	
	private String creatorName;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
    @JsonManagedReference
	private ChannelContract channel;

	@ManyToOne
	@JoinColumn(name = "creator_id")
    @JsonManagedReference
	private Creator creator;

	@Builder
	 public CreatorContract(ChannelContract channel, Creator creator, double creatorRs, String creatorName) {
		 this.channel = channel;
		 this.creator = creator;
		 this.creatorRs = creatorRs;
		 this.creatorName = creatorName;
	 }
	
}
