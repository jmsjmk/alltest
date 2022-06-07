package com.framework.jmx;

public interface StudentMXBean {
    /**
     * Obtain the time that pooled object was last borrowed.
     *
     * @return The last borrowed time for the pooled object formated as
     *         <code>yyyy-MM-dd HH:mm:ss Z</code>
     */
    String getLastBorrowTimeFormatted();

    /**
     * Obtain the stack trace recorded when the pooled object was last borrowed.
     *
     * @return The stack trace showing which code last borrowed the pooled
     *         object
     */
    String getLastBorrowTrace();

}
