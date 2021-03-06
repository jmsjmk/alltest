package com.framework.hystrix.github.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;

import static org.junit.Assert.*;

/**
 * Sample {@link HystrixCommand} that does not have a fallback implemented
 * so will "fail fast" when failures, rejections, short-circuiting etc occur.
 */
public class HystrixCommand4FailsFastTest extends HystrixCommand<String> {

    private final boolean throwException;

    public HystrixCommand4FailsFastTest(boolean throwException) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.throwException = throwException;
    }

    @Override
    protected String run() {
        if (throwException) {
            throw new RuntimeException("failure from HystrixCommand4FailsFastTest");
        } else {
            return "success";
        }
    }

    public static class UnitTest {

//        @Test
        public void testSuccess() {
            assertEquals("success", new HystrixCommand4FailsFastTest(false).execute());
        }

//        @Test
        public void testFailure() {
            try {
                new HystrixCommand4FailsFastTest(true).execute();
            } catch (HystrixRuntimeException e) {
                assertEquals("failure from HystrixCommand4FailsFastTest:", e.getCause().getMessage());
                e.printStackTrace();
            }
        }
    }
}
