package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_08;

public class Code_05_Cow {

	public static int cowNumber1(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		return cowNumber1(n - 1) + cowNumber1(n - 3);
	}

	public static int cowNumber2(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int res = 3;
		int pre = 2;
		int prepre = 1;
		int tmp1 = 0;
		int tmp2 = 0;
		for (int i = 4; i <= n; i++) {
			tmp1 = res;
			tmp2 = pre;
			res = res + prepre;
			pre = tmp1;
			prepre = tmp2;
		}
		return res;
	}
	public static int cowNumber3(int n){
		if (n<=0){
			return 0;
		}
		if (n==1||n==2||n==3){
			return n;
		}
		return cowNumber3(n-1)+cowNumber3(n-3);
	}

	public static int cowNumber4(int n){
		if (n<=0){
			return 1;
		}
		if (n==1||n==2||n==3){
			return n;
		}
		int res=3,pre=2,prepre=1,temp1=0,temp2=0;
		for (int i=4;i<=n;i++){
			temp1=res;
			temp2=pre;
			res += prepre;
			pre=temp1;
			prepre=temp2;
		}
		return res;
	}

	public static int cowNumber5(int n){
		if(n<=0){
			return 0;
		}
		if (n==1||n==2||n==3){
			return n;
		}
		int res=3,pre=2,prepre=1,temp1=0,temp2=0;
		for (int i=4;i<=n;i++){
			temp1=res;
			temp2=pre;
			res +=prepre;
			pre=temp1;
			prepre=temp2;
		}
		return res;
	}


	public static void main(String[] args) {
		int n = 20;
		System.out.println(cowNumber1(n));
		System.out.println(cowNumber2(n));
		System.out.println(cowNumber3(n));
		System.out.println(cowNumber4(n));
		System.out.println(cowNumber5(n));
	}

}
