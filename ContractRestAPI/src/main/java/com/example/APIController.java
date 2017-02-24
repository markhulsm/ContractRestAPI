package com.example;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController 
{
	@Autowired
    DBRepository repository;
	
    @RequestMapping(value="/contract/{id}", method= RequestMethod.GET)
    public Contract getContract(@PathVariable long id) 
    {
    	Optional<Contract> contract = repository.findById(id);
    	
    	if(contract.isPresent())
    	{
    		return contract.get();
    	}
    	else
    	{
    		return null;
    	}
    }

    @RequestMapping(value="/contract/{id}", method= {RequestMethod.POST, RequestMethod.PUT})
    public Contract updateContract(@PathVariable long id, @RequestParam(value="name") Optional<String> name, @RequestParam(value="businessNumber") Optional<Long> businessNumber, @RequestParam(value="amount") Optional<Double> amount, @RequestParam(value="type") Optional<Contract.Type> type) 
    {
    	Optional<Contract> contract = repository.findById(id);
    	if(contract.isPresent())
    	{
    		if(name.isPresent())
    		{
    			contract.get().setName(name.get());
    		}
    		if(businessNumber.isPresent())
    		{
    			contract.get().setBusinessNumber(businessNumber.get());
    		}
    		if(amount.isPresent())
    		{
    			contract.get().setAmount(amount.get());
    		}
    		if(type.isPresent())
    		{
    			contract.get().setType(type.get());
    		}
    		repository.save(contract.get());
    		
    		return contract.get();
    	}
    	else
    	{
    		return null;
    	}
    }

    @RequestMapping(value="/contract/{id}", method= RequestMethod.DELETE)
    public void deleteContract(@PathVariable long id) 
    {
    	repository.delete(id);
    }

    @RequestMapping(value="/contract", method= {RequestMethod.POST, RequestMethod.PUT})
    public Contract addContract(@RequestParam(value="name") String name, @RequestParam(value="businessNumber") long businessNumber, @RequestParam(value="amount") double amount, @RequestParam(value="type") Contract.Type type) 
    {
    	Contract contract = new Contract(name, businessNumber, amount, type);
    	
    	contract = repository.save(contract);
    	
    	return contract;
    }
    
    @RequestMapping(value="/contracts/{status}", method= RequestMethod.GET)
    public List<Contract> getContracts(@PathVariable Contract.Status status) 
    {
		return repository.findByStatus(status);
    }
}
