package com.Interview.games.cach;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**

 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Dynamo {

	private AtomicLong coolId = new AtomicLong(1);

	public Long getCoolId() {
		return coolId.getAndIncrement();
	}
}
