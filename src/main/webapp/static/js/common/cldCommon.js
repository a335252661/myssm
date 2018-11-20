/**
 * @author 光芒
 *
 * @requires jQuery
 *
 * 格式化日期时间
 */
function DateTimeFormatter(val) {
    var date=new Date(val);
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month>9?month:('0'+month);
    var day=date.getDate();
    day=day>9?day:('0'+day);
    var hh=date.getHours();
    hh=hh>9?hh:('0'+hh);
    var mm=date.getMinutes();
    mm=mm>9?mm:('0'+mm);
    var ss=date.getSeconds();
    ss=ss>9?ss:('0'+ss);
    var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
    return time;
}

/**
 * form表单转化为json参数
 */
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/**
 * 选中datagrid转json
 */
function selectionsToJson(selections) {
    var deleteObj = new Array();
    $.each(selections, function() {
        deleteObj.push({
            "id" : this.id,
            "updateDate" : this.updateDate,
            "isuse":this.isuse
        });
    });
    return deleteObj;
}

/**
 * 封装的 json 请求
 */
$.requestJson = function(uri,param, okCallback, failCallback){
    param = param ? param :{};
    $.ajax({
        url:uri,
        dataType:"json",
        type:"POST",
        data:JSON.stringify(param),
        contentType:"application/json; charset=utf-8",
        success:function(data, textStatus, jqXHR){
            okCallback(data, textStatus, jqXHR);
        },
        error:function(data, textStatus, errorThrown){
            failCallback(data, textStatus, errorThrown);
        }
    });
};