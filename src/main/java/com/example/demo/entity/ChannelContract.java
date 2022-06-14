package com.example.demo.entity;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChannelContract {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double rs;
    
    @JsonBackReference
    @OneToMany(mappedBy = "channel")
    private List<CreatorContract> creator = new ArrayList<>();
    
    @JsonBackReference
    @OneToMany(mappedBy = "channel")
    private List<Revenue> revenue = new ArrayList<>();
    
    @Builder
	 public ChannelContract(String name, double rs) {
		 this.name = name;
		 this.rs = rs;
	 }
    
}