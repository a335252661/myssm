package cn.cld.pojo;

import java.util.ArrayList;
import java.util.List;

public class ModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ModuleExample() {
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

        public Criteria andModuleNoIsNull() {
            addCriterion("moduleNo is null");
            return (Criteria) this;
        }

        public Criteria andModuleNoIsNotNull() {
            addCriterion("moduleNo is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNoEqualTo(String value) {
            addCriterion("moduleNo =", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoNotEqualTo(String value) {
            addCriterion("moduleNo <>", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoGreaterThan(String value) {
            addCriterion("moduleNo >", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoGreaterThanOrEqualTo(String value) {
            addCriterion("moduleNo >=", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoLessThan(String value) {
            addCriterion("moduleNo <", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoLessThanOrEqualTo(String value) {
            addCriterion("moduleNo <=", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoLike(String value) {
            addCriterion("moduleNo like", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoNotLike(String value) {
            addCriterion("moduleNo not like", value, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoIn(List<String> values) {
            addCriterion("moduleNo in", values, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoNotIn(List<String> values) {
            addCriterion("moduleNo not in", values, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoBetween(String value1, String value2) {
            addCriterion("moduleNo between", value1, value2, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andModuleNoNotBetween(String value1, String value2) {
            addCriterion("moduleNo not between", value1, value2, "moduleNo");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("moduleName is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("moduleName is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("moduleName =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("moduleName <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("moduleName >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("moduleName >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("moduleName <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("moduleName <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("moduleName like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("moduleName not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("moduleName in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("moduleName not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("moduleName between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("moduleName not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andPardentNoIsNull() {
            addCriterion("pardentNo is null");
            return (Criteria) this;
        }

        public Criteria andPardentNoIsNotNull() {
            addCriterion("pardentNo is not null");
            return (Criteria) this;
        }

        public Criteria andPardentNoEqualTo(String value) {
            addCriterion("pardentNo =", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoNotEqualTo(String value) {
            addCriterion("pardentNo <>", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoGreaterThan(String value) {
            addCriterion("pardentNo >", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoGreaterThanOrEqualTo(String value) {
            addCriterion("pardentNo >=", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoLessThan(String value) {
            addCriterion("pardentNo <", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoLessThanOrEqualTo(String value) {
            addCriterion("pardentNo <=", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoLike(String value) {
            addCriterion("pardentNo like", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoNotLike(String value) {
            addCriterion("pardentNo not like", value, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoIn(List<String> values) {
            addCriterion("pardentNo in", values, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoNotIn(List<String> values) {
            addCriterion("pardentNo not in", values, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoBetween(String value1, String value2) {
            addCriterion("pardentNo between", value1, value2, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNoNotBetween(String value1, String value2) {
            addCriterion("pardentNo not between", value1, value2, "pardentNo");
            return (Criteria) this;
        }

        public Criteria andPardentNameIsNull() {
            addCriterion("pardentName is null");
            return (Criteria) this;
        }

        public Criteria andPardentNameIsNotNull() {
            addCriterion("pardentName is not null");
            return (Criteria) this;
        }

        public Criteria andPardentNameEqualTo(String value) {
            addCriterion("pardentName =", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameNotEqualTo(String value) {
            addCriterion("pardentName <>", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameGreaterThan(String value) {
            addCriterion("pardentName >", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameGreaterThanOrEqualTo(String value) {
            addCriterion("pardentName >=", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameLessThan(String value) {
            addCriterion("pardentName <", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameLessThanOrEqualTo(String value) {
            addCriterion("pardentName <=", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameLike(String value) {
            addCriterion("pardentName like", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameNotLike(String value) {
            addCriterion("pardentName not like", value, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameIn(List<String> values) {
            addCriterion("pardentName in", values, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameNotIn(List<String> values) {
            addCriterion("pardentName not in", values, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameBetween(String value1, String value2) {
            addCriterion("pardentName between", value1, value2, "pardentName");
            return (Criteria) this;
        }

        public Criteria andPardentNameNotBetween(String value1, String value2) {
            addCriterion("pardentName not between", value1, value2, "pardentName");
            return (Criteria) this;
        }

        public Criteria andIsFileIsNull() {
            addCriterion("isFile is null");
            return (Criteria) this;
        }

        public Criteria andIsFileIsNotNull() {
            addCriterion("isFile is not null");
            return (Criteria) this;
        }

        public Criteria andIsFileEqualTo(Integer value) {
            addCriterion("isFile =", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotEqualTo(Integer value) {
            addCriterion("isFile <>", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileGreaterThan(Integer value) {
            addCriterion("isFile >", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileGreaterThanOrEqualTo(Integer value) {
            addCriterion("isFile >=", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileLessThan(Integer value) {
            addCriterion("isFile <", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileLessThanOrEqualTo(Integer value) {
            addCriterion("isFile <=", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileIn(List<Integer> values) {
            addCriterion("isFile in", values, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotIn(List<Integer> values) {
            addCriterion("isFile not in", values, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileBetween(Integer value1, Integer value2) {
            addCriterion("isFile between", value1, value2, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotBetween(Integer value1, Integer value2) {
            addCriterion("isFile not between", value1, value2, "isFile");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andIconClsIsNull() {
            addCriterion("iconCls is null");
            return (Criteria) this;
        }

        public Criteria andIconClsIsNotNull() {
            addCriterion("iconCls is not null");
            return (Criteria) this;
        }

        public Criteria andIconClsEqualTo(String value) {
            addCriterion("iconCls =", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsNotEqualTo(String value) {
            addCriterion("iconCls <>", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsGreaterThan(String value) {
            addCriterion("iconCls >", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsGreaterThanOrEqualTo(String value) {
            addCriterion("iconCls >=", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsLessThan(String value) {
            addCriterion("iconCls <", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsLessThanOrEqualTo(String value) {
            addCriterion("iconCls <=", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsLike(String value) {
            addCriterion("iconCls like", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsNotLike(String value) {
            addCriterion("iconCls not like", value, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsIn(List<String> values) {
            addCriterion("iconCls in", values, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsNotIn(List<String> values) {
            addCriterion("iconCls not in", values, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsBetween(String value1, String value2) {
            addCriterion("iconCls between", value1, value2, "iconCls");
            return (Criteria) this;
        }

        public Criteria andIconClsNotBetween(String value1, String value2) {
            addCriterion("iconCls not between", value1, value2, "iconCls");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
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