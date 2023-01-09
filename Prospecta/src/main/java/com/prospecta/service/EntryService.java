package com.prospecta.service;

import com.prospecta.Models.Entry;
import com.prospecta.exception.EntryException;

public interface EntryService {
	
	Entry registerEntry(Entry entry) throws EntryException;

}
