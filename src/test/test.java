package test;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arrayList = new ArrayList<>();

		for (int i = 9; i >= 0; i--) {
			arrayList.add(i);
		}
		System.out.println(arrayList);
		System.out.println(arrayList.get(2));
		arrayList.remove(2);
		System.out.println(arrayList);
	}

}
