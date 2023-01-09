package com.prospecta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.Models.Entry;
import com.prospecta.exception.EntryException;
import com.prospecta.repository.EntryRepo;

@Service
public class EntryServiceImpl implements EntryService{
	
	@Autowired
	private EntryRepo entryRepo;

	@Override
	public Entry registerEntry(Entry entry) throws EntryException {
		
		if(entry == null ) throw new EntryException("Enter Valid Entry...!");
		
		Entry newEntry = entryRepo.save(entry);
		
		return newEntry;
	}

}
