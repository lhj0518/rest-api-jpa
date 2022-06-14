package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Revenue;
import com.example.demo.entity.dto.BillingDTO;
import com.example.demo.entity.dto.RevenueDTO;
import com.example.demo.service.RevenueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/revenue")
@RequiredArgsConstructor
public class RevenueController {

private final RevenueService revenueService;
    
    @PostMapping("/channel")
    public ResponseEntity<Revenue> addChannelRevenue(@RequestBody RevenueDTO request){
    	return ResponseEntity.ok(revenueService.addChannelRevenue(request));
    }
    
    @GetMapping("/channel/{id}")
    public ResponseEntity<List<BillingDTO>> readChannel(@PathVariable Long id, @RequestParam("year") int year,  @RequestParam("month") int month){
    	return ResponseEntity.ok(revenueService.readChannel(id, year, month));
    }
    
    @GetMapping("/creator/{id}")
    public ResponseEntity<List<BillingDTO>> readCreator(@PathVariable Long id, @RequestParam("year") int year,  @RequestParam("month") int month){
    	return ResponseEntity.ok(revenueService.readCreator(id, year, month));
    }
    
    @GetMapping("/all/{id}")
    public ResponseEntity<Long> allRevenue(@PathVariable Long id, @RequestParam("year") int year,  @RequestParam("month") int month){
    	return ResponseEntity.ok(revenueService.allRevenue(id, year, month));
    }
    
    @GetMapping("/profit/{id}")
    public ResponseEntity<Long> profit(@PathVariable Long id, @RequestParam("year") int year,  @RequestParam("month") int month){
    	return ResponseEntity.ok(revenueService.profit(id, year, month));
    }
}
