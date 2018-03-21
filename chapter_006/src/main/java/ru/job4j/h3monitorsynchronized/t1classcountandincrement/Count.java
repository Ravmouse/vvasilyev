package ru.job4j.h3monitorsynchronized.t1classcountandincrement;
import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * A Count class.
 */
@ThreadSafe
public class Count {
    /**
     * A variable to be increased by one.
     */
    @GuardedBy("this")
	private int value;

    /**
     * Increments the value.
     */
    public synchronized void increment() {
		this.value++;
	}

    /**
     * @return the value.
     */
    public synchronized int get() {
		return this.value;
	}
}