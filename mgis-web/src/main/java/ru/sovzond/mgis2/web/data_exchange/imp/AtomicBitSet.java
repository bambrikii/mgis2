package ru.sovzond.mgis2.web.data_exchange.imp;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Alexander Arakelyan on 19.11.15.
 * <p>
 * http://stackoverflow.com/questions/12424633/atomicbitset-implementation-for-java
 */
public class AtomicBitSet {
	private final AtomicIntegerArray array;

	public AtomicBitSet(int length) {
		int intLength = (length + 31) / 32;
		array = new AtomicIntegerArray(intLength);
	}

	public void set(long n) {
		int bit = 1 << n;
		int idx = (int) (n >>> 5);
		while (true) {
			int num = array.get(idx);
			int num2 = num | bit;
			if (num == num2 || array.compareAndSet(idx, num, num2))
				return;
		}
	}

	public boolean get(long n) {
		int bit = 1 << n;
		int idx = (int) (n >>> 5);
		int num = array.get(idx);
		return (num & bit) != 0;
	}
}
