package com.example.demo.entity;

import java.time.LocalDate;

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
public class Revenue {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private double revenue;
	
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
    @JsonManagedReference
	private ChannelContract channel;
	
	@Builder
	 public Revenue(ChannelContract channel, LocalDate date, double revenue) {
		 this.channel = channel;
		 this.date = date;
		 this.revenue = revenue;
	 }
	 
}
