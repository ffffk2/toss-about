import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 有人说必应赌法：
 * 不考虑投注封顶，不考虑不公平概率，不考虑庄家不认账，
 * 一次买x元，输了下次买x*2^(n-1)元，直至赢为止。一个轮回赚x元，无限赢。
 *
 * 通过计算机模拟，可见赌法确实必赢，但前提是本金足够多。假如x=1，在连续输20次后，下次还要买入的本金是100万，有多少人有100万现金
 *
 * 所以只要还在赌，庄家迟早把你拿下
 *
 */
public class Biying {

	public static void main(String[] args) throws InterruptedException {
		int max = 0;
		int mai = 1;
		int tmp = 0;
		long zhuan = 0;


		Random random = new Random();
		long index = 1;
		while (true) {
			if (index % 1e8 == 0) {
				System.out.println("次数: " + index);
				System.out.println("最大连续输次数: " + max);
				System.out.println("赚了: " + Math.floor(zhuan));
				System.out.println("能满足赚的最低本金: " + Math.pow(2, (max - 1)));
				System.out.println("--------------------------");
			}
			if (random.nextInt(2) == mai) {
				zhuan++;
				tmp++;
				if (tmp > max) {
					max = tmp;
					System.out.println("次数: " + index);
					System.out.println("最大连续输次数: " + max);
					System.out.println("赚了: " + Math.floor(zhuan));
					System.out.println("能满足赚的最低本金: " + Math.pow(2, (max - 1)));
					System.out.println("--------------------------");
					TimeUnit.SECONDS.sleep(2);
				}
			} else {
				tmp = 0;
			}
			index++;
		}
	}

}
/**
 *
 *
 *
 次数: 1
 最大连续输次数: 1
 赚了: 1.0
 能满足赚的最低本金: 1.0
 --------------------------
 次数: 2
 最大连续输次数: 2
 赚了: 2.0
 能满足赚的最低本金: 2.0
 --------------------------
 次数: 3
 最大连续输次数: 3
 赚了: 3.0
 能满足赚的最低本金: 4.0
 --------------------------
 次数: 18
 最大连续输次数: 4
 赚了: 12.0
 能满足赚的最低本金: 8.0
 --------------------------
 次数: 19
 最大连续输次数: 5
 赚了: 13.0
 能满足赚的最低本金: 16.0
 --------------------------
 次数: 47
 最大连续输次数: 6
 赚了: 29.0
 能满足赚的最低本金: 32.0
 --------------------------
 次数: 252
 最大连续输次数: 7
 赚了: 136.0
 能满足赚的最低本金: 64.0
 --------------------------
 次数: 253
 最大连续输次数: 8
 赚了: 137.0
 能满足赚的最低本金: 128.0
 --------------------------
 次数: 2728
 最大连续输次数: 9
 赚了: 1356.0
 能满足赚的最低本金: 256.0
 --------------------------
 次数: 2729
 最大连续输次数: 10
 赚了: 1357.0
 能满足赚的最低本金: 512.0
 --------------------------
 次数: 3042
 最大连续输次数: 11
 赚了: 1510.0
 能满足赚的最低本金: 1024.0
 --------------------------
 次数: 3043
 最大连续输次数: 12
 赚了: 1511.0
 能满足赚的最低本金: 2048.0
 --------------------------
 次数: 3044
 最大连续输次数: 13
 赚了: 1512.0
 能满足赚的最低本金: 4096.0
 --------------------------
 次数: 3045
 最大连续输次数: 14
 赚了: 1513.0
 能满足赚的最低本金: 8192.0
 --------------------------
 次数: 3046
 最大连续输次数: 15
 赚了: 1514.0
 能满足赚的最低本金: 16384.0
 --------------------------
 次数: 3047
 最大连续输次数: 16
 赚了: 1515.0
 能满足赚的最低本金: 32768.0
 --------------------------
 次数: 3048
 最大连续输次数: 17
 赚了: 1516.0
 能满足赚的最低本金: 65536.0
 --------------------------
 次数: 1335033
 最大连续输次数: 18
 赚了: 667375.0
 能满足赚的最低本金: 131072.0
 --------------------------
 次数: 1943892
 最大连续输次数: 19
 赚了: 971615.0
 能满足赚的最低本金: 262144.0
 --------------------------
 次数: 1943893
 最大连续输次数: 20
 赚了: 971616.0
 能满足赚的最低本金: 524288.0
 --------------------------
 次数: 11473785
 最大连续输次数: 21
 赚了: 5735969.0
 能满足赚的最低本金: 1048576.0
 --------------------------
 次数: 11473786
 最大连续输次数: 22
 赚了: 5735970.0
 能满足赚的最低本金: 2097152.0
 --------------------------
 次数: 11473787
 最大连续输次数: 23
 赚了: 5735971.0
 能满足赚的最低本金: 4194304.0
 --------------------------
 次数: 11473788
 最大连续输次数: 24
 赚了: 5735972.0
 能满足赚的最低本金: 8388608.0
 --------------------------
 次数: 33277165
 最大连续输次数: 25
 赚了: 1.6637334E7
 能满足赚的最低本金: 1.6777216E7
 --------------------------
 次数: 57843881
 最大连续输次数: 26
 赚了: 2.8924745E7
 能满足赚的最低本金: 3.3554432E7
 --------------------------
 次数: 100000000
 最大连续输次数: 26
 赚了: 5.0008028E7
 能满足赚的最低本金: 3.3554432E7
 --------------------------

 *
 *
 *
 *
 *
 */
