package com.jp.patterns.behaviour.observer;

public class TestLoan {
	public static void main(String args[]) {
		// this will maintain all loans information
		Newspaper printMedia = new Newspaper();
		Internet onlineMedia = new Internet();

		LoanComponent personalLoan = new LoanComponent("Personal Loan", 12.5f, "Standard Charterd");
		personalLoan.registerObserver(printMedia);
		personalLoan.registerObserver(onlineMedia);
		personalLoan.setInterest(3.9f);
		personalLoan.removeObserver(onlineMedia);

		personalLoan.setInterest(4.9f);

	}
}
