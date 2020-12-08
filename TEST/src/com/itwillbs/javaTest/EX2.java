package com.itwillbs.javaTest;

public class EX2 {

	public static void main(String[] args) {
		
		int[] arr = {1,2,2,4,1,2,7,8,2,10};
		
		int ck = 0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]%3 == 0){
				System.out.println("3의 배수 :"+arr[i]);
			}else{
				ck++;
				if(ck ==10){
					System.out.println("해당사항 없음");
				}
			}
		}
		
		

	}

}
