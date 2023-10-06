package com.checkins.autosuggestion.repo;

import com.checkins.autosuggestion.model.Location;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface AutosuggestionRepository extends RedisDocumentRepository<Location, Integer> {
}
