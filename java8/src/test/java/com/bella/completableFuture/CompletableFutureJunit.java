package com.bella.completableFuture;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

/**
 @Date: 2020/9/15-10:54
 @Author Genie
 @Description:
 */
public class CompletableFutureJunit {

	@Test
	public void testSimpleCompletableFuture() {
		CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("Hello ");
		assertTrue(completableFuture.isDone());
		try {
			System.out.println( " [ Result ] : " + completableFuture.get() );
			assertEquals("Hello mghio", completableFuture.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}




}
