package com.connext.wms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InRepertoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InRepertoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInRepoIdIsNull() {
            addCriterion("in_repo_id is null");
            return (Criteria) this;
        }

        public Criteria andInRepoIdIsNotNull() {
            addCriterion("in_repo_id is not null");
            return (Criteria) this;
        }

        public Criteria andInRepoIdEqualTo(String value) {
            addCriterion("in_repo_id =", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdNotEqualTo(String value) {
            addCriterion("in_repo_id <>", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdGreaterThan(String value) {
            addCriterion("in_repo_id >", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdGreaterThanOrEqualTo(String value) {
            addCriterion("in_repo_id >=", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdLessThan(String value) {
            addCriterion("in_repo_id <", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdLessThanOrEqualTo(String value) {
            addCriterion("in_repo_id <=", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdLike(String value) {
            addCriterion("in_repo_id like", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdNotLike(String value) {
            addCriterion("in_repo_id not like", value, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdIn(List<String> values) {
            addCriterion("in_repo_id in", values, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdNotIn(List<String> values) {
            addCriterion("in_repo_id not in", values, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdBetween(String value1, String value2) {
            addCriterion("in_repo_id between", value1, value2, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andInRepoIdNotBetween(String value1, String value2) {
            addCriterion("in_repo_id not between", value1, value2, "inRepoId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(String value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(String value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(String value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(String value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(String value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(String value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLike(String value) {
            addCriterion("express_id like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotLike(String value) {
            addCriterion("express_id not like", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<String> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<String> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(String value1, String value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(String value1, String value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNull() {
            addCriterion("express_company is null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNotNull() {
            addCriterion("express_company is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyEqualTo(String value) {
            addCriterion("express_company =", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotEqualTo(String value) {
            addCriterion("express_company <>", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThan(String value) {
            addCriterion("express_company >", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("express_company >=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThan(String value) {
            addCriterion("express_company <", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("express_company <=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLike(String value) {
            addCriterion("express_company like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotLike(String value) {
            addCriterion("express_company not like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIn(List<String> values) {
            addCriterion("express_company in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotIn(List<String> values) {
            addCriterion("express_company not in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyBetween(String value1, String value2) {
            addCriterion("express_company between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("express_company not between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusIsNull() {
            addCriterion("in_repo_status is null");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusIsNotNull() {
            addCriterion("in_repo_status is not null");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusEqualTo(String value) {
            addCriterion("in_repo_status =", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusNotEqualTo(String value) {
            addCriterion("in_repo_status <>", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusGreaterThan(String value) {
            addCriterion("in_repo_status >", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusGreaterThanOrEqualTo(String value) {
            addCriterion("in_repo_status >=", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusLessThan(String value) {
            addCriterion("in_repo_status <", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusLessThanOrEqualTo(String value) {
            addCriterion("in_repo_status <=", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusLike(String value) {
            addCriterion("in_repo_status like", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusNotLike(String value) {
            addCriterion("in_repo_status not like", value, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusIn(List<String> values) {
            addCriterion("in_repo_status in", values, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusNotIn(List<String> values) {
            addCriterion("in_repo_status not in", values, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusBetween(String value1, String value2) {
            addCriterion("in_repo_status between", value1, value2, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andInRepoStatusNotBetween(String value1, String value2) {
            addCriterion("in_repo_status not between", value1, value2, "inRepoStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNull() {
            addCriterion("sync_status is null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNotNull() {
            addCriterion("sync_status is not null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusEqualTo(String value) {
            addCriterion("sync_status =", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotEqualTo(String value) {
            addCriterion("sync_status <>", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThan(String value) {
            addCriterion("sync_status >", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThanOrEqualTo(String value) {
            addCriterion("sync_status >=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThan(String value) {
            addCriterion("sync_status <", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThanOrEqualTo(String value) {
            addCriterion("sync_status <=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLike(String value) {
            addCriterion("sync_status like", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotLike(String value) {
            addCriterion("sync_status not like", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIn(List<String> values) {
            addCriterion("sync_status in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotIn(List<String> values) {
            addCriterion("sync_status not in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusBetween(String value1, String value2) {
            addCriterion("sync_status between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotBetween(String value1, String value2) {
            addCriterion("sync_status not between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoIsNull() {
            addCriterion("receiving_repo is null");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoIsNotNull() {
            addCriterion("receiving_repo is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoEqualTo(String value) {
            addCriterion("receiving_repo =", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoNotEqualTo(String value) {
            addCriterion("receiving_repo <>", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoGreaterThan(String value) {
            addCriterion("receiving_repo >", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoGreaterThanOrEqualTo(String value) {
            addCriterion("receiving_repo >=", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoLessThan(String value) {
            addCriterion("receiving_repo <", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoLessThanOrEqualTo(String value) {
            addCriterion("receiving_repo <=", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoLike(String value) {
            addCriterion("receiving_repo like", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoNotLike(String value) {
            addCriterion("receiving_repo not like", value, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoIn(List<String> values) {
            addCriterion("receiving_repo in", values, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoNotIn(List<String> values) {
            addCriterion("receiving_repo not in", values, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoBetween(String value1, String value2) {
            addCriterion("receiving_repo between", value1, value2, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andReceivingRepoNotBetween(String value1, String value2) {
            addCriterion("receiving_repo not between", value1, value2, "receivingRepo");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReviserIsNull() {
            addCriterion("reviser is null");
            return (Criteria) this;
        }

        public Criteria andReviserIsNotNull() {
            addCriterion("reviser is not null");
            return (Criteria) this;
        }

        public Criteria andReviserEqualTo(String value) {
            addCriterion("reviser =", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserNotEqualTo(String value) {
            addCriterion("reviser <>", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserGreaterThan(String value) {
            addCriterion("reviser >", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserGreaterThanOrEqualTo(String value) {
            addCriterion("reviser >=", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserLessThan(String value) {
            addCriterion("reviser <", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserLessThanOrEqualTo(String value) {
            addCriterion("reviser <=", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserLike(String value) {
            addCriterion("reviser like", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserNotLike(String value) {
            addCriterion("reviser not like", value, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserIn(List<String> values) {
            addCriterion("reviser in", values, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserNotIn(List<String> values) {
            addCriterion("reviser not in", values, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserBetween(String value1, String value2) {
            addCriterion("reviser between", value1, value2, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviserNotBetween(String value1, String value2) {
            addCriterion("reviser not between", value1, value2, "reviser");
            return (Criteria) this;
        }

        public Criteria andReviseTimeIsNull() {
            addCriterion("revise_time is null");
            return (Criteria) this;
        }

        public Criteria andReviseTimeIsNotNull() {
            addCriterion("revise_time is not null");
            return (Criteria) this;
        }

        public Criteria andReviseTimeEqualTo(Date value) {
            addCriterion("revise_time =", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeNotEqualTo(Date value) {
            addCriterion("revise_time <>", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeGreaterThan(Date value) {
            addCriterion("revise_time >", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("revise_time >=", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeLessThan(Date value) {
            addCriterion("revise_time <", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeLessThanOrEqualTo(Date value) {
            addCriterion("revise_time <=", value, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeIn(List<Date> values) {
            addCriterion("revise_time in", values, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeNotIn(List<Date> values) {
            addCriterion("revise_time not in", values, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeBetween(Date value1, Date value2) {
            addCriterion("revise_time between", value1, value2, "reviseTime");
            return (Criteria) this;
        }

        public Criteria andReviseTimeNotBetween(Date value1, Date value2) {
            addCriterion("revise_time not between", value1, value2, "reviseTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}