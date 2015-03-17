package com.samsung.sdsc.training.java.ning;

public class Egg implements Pet {
	private String growthRate;
	private String name;
    private int hungryRate;
	
	private int tili = 100;
    
	 public Egg(String name){
		  this.name=name;
		  this.hungryRate = 100;
		  hungryAct();
		 }
    
	public void setHungryRate(int hungryRate) {
		this.hungryRate = hungryRate;
	}

	@Override
	public int getHungryRate() {
		// TODO Auto-generated method stub
		return hungryRate;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(String growthRate) {
		this.growthRate = growthRate;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	private void hungryAct() {
		// TODO Auto-generated method stub

		//Thread thread = new Thread(time); 
		//thread.start(); 
	}
	
	public void TimeHandler() {
		tili-=2;
		if ( tili > 0 &&tili < 20) {
			System.out.println("wo e");
		}
		else if (tili <=0) {
		//dead();
		}
		
	}
	
	public void wei(int xue)
	{
		tili +=xue;
	}

}
