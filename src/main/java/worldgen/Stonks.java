package worldgen;

public class Stonks {

	public static int round = 1;
	public static int totalSkateboardsSold = 0;
	
	public static void main(String args[]) {
		
		double initialInvestment = 3000;
		double cost = 389;
		double sellingPrice = 499;
		
		//System.out.print(money(initialInvestment, cost, earned));
		money(initialInvestment, cost, sellingPrice);
	}
	
	public static double money(double investment, double cost, double sellingPrice)
	{
		int numberOfBoards = (int) (investment / cost);
		totalSkateboardsSold += numberOfBoards;
		double overFlow = investment % cost;
		
		double money = overFlow + numberOfBoards * sellingPrice;
		
		if(totalSkateboardsSold > 41000)
			return money;
		
		System.out.println("Round "+ round + ": " + money + "       Number of Skateboards Sold: " + numberOfBoards + "         Total Skateboards Sold: " + totalSkateboardsSold);

		
		round++;
		
		return money(money, cost, sellingPrice);
	}
}
