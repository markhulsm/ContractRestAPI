package com.example;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Contract 
{
	enum Status 
	{
		Approved, Denied;
	}
	
	enum Type
	{
		Sales, Express;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long businessNumber;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date activationDate;
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	
	public Contract(String name, Long businessNumber, Double amount, Type type)
	{
		this.name = name;
		this.businessNumber = businessNumber;
		this.amount = amount;
		this.type = type;
		status = Status.Denied;
		
		validate();
	}
	
	protected Contract()
	{
		
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Long getBusinessNumber()
	{
		return businessNumber;
	}
	
	public void setBusinessNumber(Long number)
	{
		businessNumber = number;
	}
	
	public Date getActivationDate()
	{
		return activationDate;
	}
	
	public void setActivationDate(Date date)
	{
		activationDate = date;
	}
	
	public Double getAmount()
	{
		return amount;
	}
	
	public void setAmount(Double amount)
	{
		this.amount = amount;
		
		validate();
	}
	
	public Status getStatus()
	{
		return status;
	}
	
	public void setStatus(Status status)
	{
		this.status = status;
	}
	
	public Type getType()
	{
		return this.type;
	}
	
	public void setType(Type type)
	{
		this.type = type;
		
		validate();
	}
	
	private void validate()
	{
		if(status == Status.Denied )
		{
			if(type == Type.Sales || amount < 50000)
			{
				status = Status.Approved;
				activationDate = new Date();
			}
		}
		else
		{
			if(type == Type.Express && amount >= 50000)
			{
				status = Status.Denied;
			}
		}
	}
}
