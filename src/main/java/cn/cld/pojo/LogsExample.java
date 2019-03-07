package cn.cld.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogsExample() {
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

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Integer value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Integer value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Integer value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Integer value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Integer value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Integer> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Integer> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Integer value1, Integer value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Integer value1, Integer value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNull() {
            addCriterion("operatorType is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIsNotNull() {
            addCriterion("operatorType is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeEqualTo(String value) {
            addCriterion("operatorType =", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotEqualTo(String value) {
            addCriterion("operatorType <>", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThan(String value) {
            addCriterion("operatorType >", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("operatorType >=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThan(String value) {
            addCriterion("operatorType <", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLessThanOrEqualTo(String value) {
            addCriterion("operatorType <=", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeLike(String value) {
            addCriterion("operatorType like", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotLike(String value) {
            addCriterion("operatorType not like", value, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeIn(List<String> values) {
            addCriterion("operatorType in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotIn(List<String> values) {
            addCriterion("operatorType not in", values, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeBetween(String value1, String value2) {
            addCriterion("operatorType between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorTypeNotBetween(String value1, String value2) {
            addCriterion("operatorType not between", value1, value2, "operatorType");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectIsNull() {
            addCriterion("operatorObject is null");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectIsNotNull() {
            addCriterion("operatorObject is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectEqualTo(String value) {
            addCriterion("operatorObject =", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectNotEqualTo(String value) {
            addCriterion("operatorObject <>", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectGreaterThan(String value) {
            addCriterion("operatorObject >", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectGreaterThanOrEqualTo(String value) {
            addCriterion("operatorObject >=", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectLessThan(String value) {
            addCriterion("operatorObject <", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectLessThanOrEqualTo(String value) {
            addCriterion("operatorObject <=", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectLike(String value) {
            addCriterion("operatorObject like", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectNotLike(String value) {
            addCriterion("operatorObject not like", value, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectIn(List<String> values) {
            addCriterion("operatorObject in", values, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectNotIn(List<String> values) {
            addCriterion("operatorObject not in", values, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectBetween(String value1, String value2) {
            addCriterion("operatorObject between", value1, value2, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorObjectNotBetween(String value1, String value2) {
            addCriterion("operatorObject not between", value1, value2, "operatorObject");
            return (Criteria) this;
        }

        public Criteria andOperatorTransIsNull() {
            addCriterion("operatorTrans is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTransIsNotNull() {
            addCriterion("operatorTrans is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTransEqualTo(Integer value) {
            addCriterion("operatorTrans =", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransNotEqualTo(Integer value) {
            addCriterion("operatorTrans <>", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransGreaterThan(Integer value) {
            addCriterion("operatorTrans >", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransGreaterThanOrEqualTo(Integer value) {
            addCriterion("operatorTrans >=", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransLessThan(Integer value) {
            addCriterion("operatorTrans <", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransLessThanOrEqualTo(Integer value) {
            addCriterion("operatorTrans <=", value, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransIn(List<Integer> values) {
            addCriterion("operatorTrans in", values, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransNotIn(List<Integer> values) {
            addCriterion("operatorTrans not in", values, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransBetween(Integer value1, Integer value2) {
            addCriterion("operatorTrans between", value1, value2, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andOperatorTransNotBetween(Integer value1, Integer value2) {
            addCriterion("operatorTrans not between", value1, value2, "operatorTrans");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andDataCountIsNull() {
            addCriterion("dataCount is null");
            return (Criteria) this;
        }

        public Criteria andDataCountIsNotNull() {
            addCriterion("dataCount is not null");
            return (Criteria) this;
        }

        public Criteria andDataCountEqualTo(Integer value) {
            addCriterion("dataCount =", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotEqualTo(Integer value) {
            addCriterion("dataCount <>", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountGreaterThan(Integer value) {
            addCriterion("dataCount >", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("dataCount >=", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountLessThan(Integer value) {
            addCriterion("dataCount <", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountLessThanOrEqualTo(Integer value) {
            addCriterion("dataCount <=", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountIn(List<Integer> values) {
            addCriterion("dataCount in", values, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotIn(List<Integer> values) {
            addCriterion("dataCount not in", values, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountBetween(Integer value1, Integer value2) {
            addCriterion("dataCount between", value1, value2, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotBetween(Integer value1, Integer value2) {
            addCriterion("dataCount not between", value1, value2, "dataCount");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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