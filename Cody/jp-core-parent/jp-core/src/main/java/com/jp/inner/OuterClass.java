package com.jp.inner;

public class OuterClass {

	private String str = "Outer-String";

	private void printStr() {
		System.out.println(str);
	}

	class InnerClass {
		public void innerMethod() {
			String str = "Inner-String";
			System.out.println(str);
			System.out.println(OuterClass.this.str);
		}
	}

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		outerClass.printStr();
		InnerClass innerClass = outerClass.new InnerClass();
		innerClass.innerMethod();
	}

}

interface A {
	public void InterA();
}

class Outer {
	A a = new A() {
		public void InterA() {
			System.out.println("OKKKK");
		}
	};
}