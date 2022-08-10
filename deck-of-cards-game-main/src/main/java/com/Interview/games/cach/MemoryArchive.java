package com.Interview.games.cach;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public abstract class MemoryArchive<T extends Trackable> {

	@Autowired
	private Dynamo dynamo;

	private Map<Long, T> cache = new ConcurrentHashMap<Long, T>();

	/**
	 *
	 */
	public T save(T object) {
		object.setId(dynamo.getCoolId());
		cache.put(object.getId(), object);
		return object;
	}

	/**
	 *
	 */
	public boolean remove(Long id) {
		return cache.remove(id) != null;
	}

	/**
	 *
	 */
	public T findById(Long id) {
		return cache.get(id);
	}
}
