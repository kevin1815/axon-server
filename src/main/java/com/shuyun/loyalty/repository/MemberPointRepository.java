package com.shuyun.loyalty.repository;

import com.shuyun.loyalty.entity.MemberPointView;

public interface MemberPointRepository {

    void save(MemberPointView view);

    MemberPointView get(Long identifier);

}
