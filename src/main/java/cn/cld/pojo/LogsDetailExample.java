package cn.cld.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogsDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogsDetailExample() {
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

        public Criteria andLogsIdIsNull() {
            addCriterion("logsId is null");
            return (Criteria) this;
        }

        public Criteria andLogsIdIsNotNull() {
            addCriterion("logsId is not null");
            return (Criteria) this;
        }

        public Criteria andLogsIdEqualTo(Integer value) {
            addCriterion("logsId =", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdNotEqualTo(Integer value) {
            addCriterion("logsId <>", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdGreaterThan(Integer value) {
            addCriterion("logsId >", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("logsId >=", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdLessThan(Integer value) {
            addCriterion("logsId <", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdLessThanOrEqualTo(Integer value) {
            addCriterion("logsId <=", value, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdIn(List<Integer> values) {
            addCriterion("logsId in", values, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdNotIn(List<Integer> values) {
            addCriterion("logsId not in", values, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdBetween(Integer value1, Integer value2) {
            addCriterion("logsId between", value1, value2, "logsId");
            return (Criteria) this;
        }

        public Criteria andLogsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("logsId not between", value1, value2, "logsId");
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

        public Criteria andOperatorTimeIsNull() {
            addCriterion("operatorTime is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIsNotNull() {
            addCriterion("operatorTime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeEqualTo(Date value) {
            addCriterion("operatorTime =", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotEqualTo(Date value) {
            addCriterion("operatorTime <>", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThan(Date value) {
            addCriterion("operatorTime >", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operatorTime >=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThan(Date value) {
            addCriterion("operatorTime <", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThanOrEqualTo(Date value) {
            addCriterion("operatorTime <=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIn(List<Date> values) {
            addCriterion("operatorTime in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotIn(List<Date> values) {
            addCriterion("operatorTime not in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeBetween(Date value1, Date value2) {
            addCriterion("operatorTime between", value1, value2, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotBetween(Date value1, Date value2) {
            addCriterion("operatorTime not between", value1, value2, "operatorTime");
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

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
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

        public Criteria andUndefined1IsNull() {
            addCriterion("undefined1 is null");
            return (Criteria) this;
        }

        public Criteria andUndefined1IsNotNull() {
            addCriterion("undefined1 is not null");
            return (Criteria) this;
        }

        public Criteria andUndefined1EqualTo(String value) {
            addCriterion("undefined1 =", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1NotEqualTo(String value) {
            addCriterion("undefined1 <>", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1GreaterThan(String value) {
            addCriterion("undefined1 >", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1GreaterThanOrEqualTo(String value) {
            addCriterion("undefined1 >=", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1LessThan(String value) {
            addCriterion("undefined1 <", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1LessThanOrEqualTo(String value) {
            addCriterion("undefined1 <=", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1Like(String value) {
            addCriterion("undefined1 like", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1NotLike(String value) {
            addCriterion("undefined1 not like", value, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1In(List<String> values) {
            addCriterion("undefined1 in", values, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1NotIn(List<String> values) {
            addCriterion("undefined1 not in", values, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1Between(String value1, String value2) {
            addCriterion("undefined1 between", value1, value2, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined1NotBetween(String value1, String value2) {
            addCriterion("undefined1 not between", value1, value2, "undefined1");
            return (Criteria) this;
        }

        public Criteria andUndefined2IsNull() {
            addCriterion("undefined2 is null");
            return (Criteria) this;
        }

        public Criteria andUndefined2IsNotNull() {
            addCriterion("undefined2 is not null");
            return (Criteria) this;
        }

        public Criteria andUndefined2EqualTo(String value) {
            addCriterion("undefined2 =", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2NotEqualTo(String value) {
            addCriterion("undefined2 <>", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2GreaterThan(String value) {
            addCriterion("undefined2 >", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2GreaterThanOrEqualTo(String value) {
            addCriterion("undefined2 >=", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2LessThan(String value) {
            addCriterion("undefined2 <", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2LessThanOrEqualTo(String value) {
            addCriterion("undefined2 <=", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2Like(String value) {
            addCriterion("undefined2 like", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2NotLike(String value) {
            addCriterion("undefined2 not like", value, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2In(List<String> values) {
            addCriterion("undefined2 in", values, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2NotIn(List<String> values) {
            addCriterion("undefined2 not in", values, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2Between(String value1, String value2) {
            addCriterion("undefined2 between", value1, value2, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined2NotBetween(String value1, String value2) {
            addCriterion("undefined2 not between", value1, value2, "undefined2");
            return (Criteria) this;
        }

        public Criteria andUndefined3IsNull() {
            addCriterion("undefined3 is null");
            return (Criteria) this;
        }

        public Criteria andUndefined3IsNotNull() {
            addCriterion("undefined3 is not null");
            return (Criteria) this;
        }

        public Criteria andUndefined3EqualTo(String value) {
            addCriterion("undefined3 =", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3NotEqualTo(String value) {
            addCriterion("undefined3 <>", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3GreaterThan(String value) {
            addCriterion("undefined3 >", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3GreaterThanOrEqualTo(String value) {
            addCriterion("undefined3 >=", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3LessThan(String value) {
            addCriterion("undefined3 <", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3LessThanOrEqualTo(String value) {
            addCriterion("undefined3 <=", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3Like(String value) {
            addCriterion("undefined3 like", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3NotLike(String value) {
            addCriterion("undefined3 not like", value, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3In(List<String> values) {
            addCriterion("undefined3 in", values, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3NotIn(List<String> values) {
            addCriterion("undefined3 not in", values, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3Between(String value1, String value2) {
            addCriterion("undefined3 between", value1, value2, "undefined3");
            return (Criteria) this;
        }

        public Criteria andUndefined3NotBetween(String value1, String value2) {
            addCriterion("undefined3 not between", value1, value2, "undefined3");
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