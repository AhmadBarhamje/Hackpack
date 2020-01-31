import java.util.*;
import java.io.*;

public class AUT {
	public static void main(String[] args) { new AUT(); }

	int n, k;
	int[] costs, amt;
	
	public AUT() {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		k = s.nextInt();
		k = Math.min(k, 40*40);
		
		costs = new int[n];
		amt = new int[n];
		for(int i = 0; i < n; i++) costs[i] = s.nextInt();
		for(int i = 0; i < n; i++) amt[i] = s.nextInt();
		
		dp = new int[k+1][n][41];
		for(int[][] dd : dp)
			for(int[] d : dd) Arrays.fill(d, -1);

		int res = 0;
		for(int i = 0; i <= 40; i++) {
			res = Math.max(res, solve(k, 0, i));
		}
		System.out.println(res);
	}
	
	int oo = (int)1e5;
	int[][][] dp;
	int solve(int money, int pos, int buyLeft) {
		if(pos == n) {
			if(buyLeft > 0) return -oo;
			return 0;
		}
		int ret = dp[money][pos][buyLeft];
		if(ret != -1) return ret;
		int res = -oo;
		
		int min = Math.min(amt[pos], buyLeft);
		for(int howMuch = 0; howMuch <= min; howMuch++) {
			int cost = howMuch * costs[pos];
			if(cost > money) break;

			int nLeft = buyLeft-howMuch;
			int prof = costs[pos]*(howMuch + (min - howMuch));
			ret = solve(money - cost, pos + 1, nLeft);
			if(ret != -oo) { 
				res = Math.max(res, ret + prof);
			}
		}
		
		return dp[money][pos][buyLeft] = res;
	}
	
}
