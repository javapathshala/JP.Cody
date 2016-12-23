package com.jp.inner;

public class AnonymousInnerClass {

	public void pop() {
		System.out.println("m1");
	}
}

class Food {
	AnonymousInnerClass in = new AnonymousInnerClass() {
		public void pop() {
			System.out.println("m2");
		}
	};
	
	public static void main(String args[]){
		Food food=new Food();
		food.in.pop();
		AnonymousInnerClass an=new AnonymousInnerClass();
		an.pop();
				
	}
}
