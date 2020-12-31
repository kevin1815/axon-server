package com.shuyun.loyalty.repository;

import com.shuyun.loyalty.entity.MemberPointView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemberPointRepositoryImpl implements MemberPointRepository {

    private final Map<Long, MemberPointView> viewMap = new ConcurrentHashMap<>();

    @Override
    public void save(MemberPointView view) {
        viewMap.putIfAbsent(view.getId(), view);
    }

    public MemberPointView get(Long identifier) {
        return viewMap.get(identifier);
    }

}
