package com.bella;

import java.util.ArrayList;
import java.util.List;

/**
 @Date: 2020/12/14-17:42
 @Author Genie
 @Description: Java Heap 溢出
 */
public class HeapOOM {

	static class OOMObject {
		/** OOM = object-oriented memory (面向对象的存储器) */
	}

	/**
	 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * @param args
	 */
	public static void main(String[] args) {

		List<OOMObject> list = new ArrayList<>();
		while ( true ) {
			list.add( new OOMObject() );
		}

	}

}
