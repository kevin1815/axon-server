package com.shuyun.loyalty.entity;

import java.util.Date;

public class MemberPointView {

    private Long id;

    private Long memberId;

    private Long point;

    private Date overdue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Date getOverdue() {
        return overdue;
    }

    public void setOverdue(Date overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", point=" + point +
                ", overdue=" + overdue +
                '}';
    }
}
