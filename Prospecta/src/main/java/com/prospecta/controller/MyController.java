package com.prospecta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.Models.ApiData;
import com.prospecta.Models.ApiEntry;
import com.prospecta.Models.ApiEntryDTO;
import com.prospecta.Models.Entry;
import com.prospecta.exception.EntryException;
import com.prospecta.service.EntryService;


@RestController
public class MyController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntryService entryService;
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<ApiEntryDTO>> findByCategoryHandler(@PathVariable String category) {
		
		ApiData data  = restTemplate.getForObject("https://api.publicapis.org/entries", ApiData.class);
		
		List<ApiEntry> entries = data.getEntries();
		
		List<ApiEntryDTO> filteredList = new ArrayList<>();
		
		for(ApiEntry e:entries) {
			if(e.getCategory().length()>=category.length()) {
				if(e.getCategory().substring(0, category.length()).equalsIgnoreCase(category)) {
				filteredList.add(new ApiEntryDTO(e.getApi(),e.getDescription()));
				}
			}
		}
		return new ResponseEntity<>(filteredList,HttpStatus.OK);
	}
	
	@GetMapping("/entries/random")
	public ResponseEntity<ApiEntry> findByRandomHandler() {
		
		ApiData data  = restTemplate.getForObject("https://api.publicapis.org/entries", ApiData.class);
		
		List<ApiEntry> entries = data.getEntries();
		
		Random random = new Random();
		
		int index = (random.nextInt(entries.size()));
		
		return new ResponseEntity<>(entries.get(index),HttpStatus.OK);
	}
	
	
	// Here We can Add new Entries from postForObject method but endPoint for post is not given
	// So I am creating Normal Api for post new Entry to database
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> registerEntryHandler(@RequestBody Entry entry) throws EntryException{
		
		Entry savedEntry = entryService.registerEntry(entry);
		
		return new ResponseEntity<Entry>(savedEntry,HttpStatus.ACCEPTED);
	}
	

}
