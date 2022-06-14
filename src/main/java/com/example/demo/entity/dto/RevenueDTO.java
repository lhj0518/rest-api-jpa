package com.example.demo.entity.dto;

import java.time.LocalDate;

import com.example.demo.entity.Revenue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueDTO {
	private Long channelId;
	private double revenue;
	private LocalDate date;
	
}
