package com.summer.whm.web.common.session.id;

import java.util.UUID;

public class JdkUUIDGenerator implements SessionIdGenerator {
	public String get() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		UUID.randomUUID();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			UUID.randomUUID();
		}
		time = System.currentTimeMillis() - time;
		System.out.println(time);
		System.out.println(new JdkUUIDGenerator().get());
	}
}
