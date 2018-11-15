$(document).ready(function(){
	//禁止复制，右键
//	document.oncontextmenu = new Function("return false;")
//	$(document).keydown(function(e){
//		if(e.ctrlKey && (e.keyCode==67 || e.which ==67)){
//			return false;
//		}
//	});
	// 对应ie没有console.log的情况
	if (!window.debug) {
		window.debug = function(message) {
			try {
				if (!window.console) {
					window.console = {};
					window.console.log = function() {
						return;
					};
				}
				window.console.log(message + ' ');
			} catch (e) {
			}
		};
		console = window.console;
	}
	
	// 阻断 BackSpace 防止不小心造成页面回退
	$(document).keydown(function(e) {
	    var nodeName = e.target.nodeName.toLowerCase();

	    if (e.which === 8) {
	        if (((nodeName === 'input' && (e.target.type === 'text'||e.target.type === 'password')) ||
	        		nodeName === 'textarea') 
	            && !e.target.readOnly && !e.target.disabled) {
	            // do nothing

	        } else {
	            e.preventDefault();
	        }
	    }
	});

function enterToTab(e) {
	if($(e.target).hasClass('input-fixed')){
		return true;
	}
	// 盘点画面用：确认键向下时：不向后移动：箱数和包数输入后向下移动，不向后移动
	if($(e.target).hasClass('numberbox')){
		if($(e.target).numberbox('options').enterDown){
			return true;
		}
	}
	// 门店退欠货详细用，输完数据按确认之后执行
	if (e.keyCode == 13 && e.target.tagName.toLowerCase() != 'textarea'){
		// 门店退欠货详细用，输完数据按确认之后执行
		if($(e.target).hasClass('validatebox-text')){
			if($(e.target).validatebox('options').onValChangeForInput){
				$(e.target).validatebox('options').onValChangeForInput.call(this);
			}
		}
	}
	//log('enterToTab ' + e.keyCode);
    if (e.keyCode == 13 && e.target.tagName.toLowerCase() != 'textarea') {
    	$(this).emulateTab();
    	var $f = $(':focus');
    	while($f.is('[readonly]') && !$f.hasClass('combo-text') && !$f.hasClass('input-fixed')){
    		$f.emulateTab();
    		$f = $(':focus');
    	}
    	$f.select();
		e.preventDefault();
    	return false;
    } else {
    	return true;
    }
}



/**
* 使用实例
* formatDate("yyyy-MM-dd",time);
*/
function formatDate(dataFormate, time) {
	if(null==time || undefined ==time || time==''){
		return "";
	}
	if(null==dataFormate || undefined ==dataFormate || dataFormate==''){
	dataFormate = "yyyy-MM-dd HH:mm:ss";
	}
	var date = new Date();
	time = time+"";
	if(time.length!=13){
		if(time.charAt(10) == 'T'){
			date.setTime(Date.fromISO(time));
		}else{
			time = time.substring(0,time.length-1);
			var a=time.split(/[^0-9]/);
			var d=new Date(a[0],a[1]-1,a[2],a[3],a[4],a[5]);
			date.setTime(d.getTime());
		}
	}else {
		date.setTime(time);
	}
	return date.pattern(dataFormate);
}

Date.prototype.pattern = function(fmt) {
  var o = {
      "M+" : this.getMonth() + 1, //月份     
      "d+" : this.getDate(), //日     
      "h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时     
      "H+" : this.getHours(), //小时     
      "m+" : this.getMinutes(), //分     
      "s+" : this.getSeconds(), //秒     
      "q+" : Math.floor((this.getMonth() + 3) / 3), //季度     
      "S" : this.getMilliseconds()
  //毫秒     
  };
  var week = {
      "0" : "日",
      "1" : "一",
      "2" : "二",
      "3" : "三",
      "4" : "四",
      "5" : "五",
      "6" : "六"
  };
  if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
              .substr(4 - RegExp.$1.length));
  }
  if (/(E+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1,
              ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
                      : "")
                      + week[this.getDay() + ""]);
  }
  if (/(e+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1,
              ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
                      : "")
                      + this.getDay());
  }
  for ( var k in o) {
      if (new RegExp("(" + k + ")").test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                  : (("00" + o[k]).substr(("" + o[k]).length)));
      }
  }
  return fmt;
};

//将from 表单序列化为 json 对象
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

//全局的ajax访问，处理ajax清求时sesion超时  
$.ajaxSetup({
    complete:function(XMLHttpRequest,textStatus){   
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus
        if(sessionstatus == "timeout"){
        	//如果超时就处理 ，指定要跳转的页面  
    		window.location.replace("login.htm");   
        }   
    }   
}); 

/**
 * 封装的 json 请求
 * 
 * @param uri
 * 		请求地址
 * @param param
 * 		参数
 * @param okCallback (data, comment)
 * 		服务器成功返回，并且JsonResult为ok时的回调方法
 * 		data 为JsonResult的data
 * 		comment 为JsonResult的data
 * @param failCallback (data, comment)
 * 		服务器成功返回，但JsonResult【不】为ok时的回调方法，若有JsonResult有消息则在点完确定后执行回调
 * 		data 为JsonResult的data
 * 		comment 为JsonResult的data
 * @param lockScreen 锁屏
 */
$.requestJson = function(uri,param, okCallback, failCallback, lockScreen){
	if(lockScreen == undefined || lockScreen){
		lockBody();
	}
	param = param ? param :{};
	$.ajax({
		url:uri,
		dataType:"json",
		type:"POST",
		data:JSON.stringify(param),
		contentType:"application/json; charset=utf-8",
		success:function(data, textStatus, jqXHR){
			commonReqSuccessHandler(data, okCallback, failCallback, lockScreen);
		},
		error:function(jqXHR, textStatus, errorThrown){
			if(lockScreen == undefined || lockScreen){
				unlockBody();
			}
			log(jqXHR.status + '-' + textStatus + '-' + errorThrown);
			$.showMessage('E000000200', null, function(){
//			$.showMessage('E000000200' + '-' + jqXHR.status + '-' + textStatus + '-' + errorThrown, null, function(){
			});
		}
	});
};

/**
 * 共通请求成功回掉
 */
function commonReqSuccessHandler(data, okCallback, failCallback, lockScreen){
	if(lockScreen == undefined || lockScreen){
		unlockBody();
	}
	if(data == null){
		log('return data is null');
		return;
	}
	if(data.ok){
		if($.isFunction(okCallback)){
			okCallback(data.data, data.comment);
		}
	}else{
		if(data.message){
			showMsg(data.message, data.msgType, function(){
				if($.isFunction(failCallback)){
					failCallback(data.data, data.comment);
				}
			});
		}else{
			if($.isFunction(failCallback)){
				failCallback(data.data, data.comment);
			}
		}
	}
}

/**
 * form ajax 提交，提交前进行进行验证
 * 
 * options ：{}	
 * 		   	属性：
 * 			url							地址
 * 			validate:（bool）			是否验证，默认验证
 * 			onParam:function(data)		可以在from参数的基础上再做处理
 * 			onSuccess:function(data, comment)
 * 										操作成功: JsonResult.isOk() == true
 * 			onFail:function(data, comment)
 * 										操作失败: JsonResult.isOk() == false
 * 			onError:function(jqXHR, textStatus, errorThrown)
 * 										ajax异常
 */
$.fn.ajaxFormSubmit = function(options){
	var isValidate = options.validate == undefined ? true : options.validate;
	if(isValidate && !$(this).form('validate')){
		return;
	}
	
	var data = $(this).serializeObject();
	if(options.onParam){
		options.onParam(data);
	}
	
	$.requestJson(options.url, data, function(data, comment){
		if(options.onSuccess){
			options.onSuccess(data, comment);
		}
	}, function(data, comment){
		if(options.onFail){
			options.onFail(data, comment);
		}
		
	}, function(jqXHR, textStatus, errorThrown){
		if(options.onError){
			options.onError(jqXHR, textStatus, errorThrown);
		}
	});
};


/**
 * form submit 提交，下载文件时提交
 * 
 * options ：{}	
 * 		   	属性：
 * 			url							地址
 * 			queryData		            可以在from参数的基础上再做处理
 * 			okCallback:function()
 * 										操作成功: JsonResult.isOk() == true
 * 			failCallback:function()
 * 										操作失败: JsonResult.isOk() == false
 */
function downloadSubmit(options){
	$('#downloadForm').form('submit', {
	    url:options.url,
	    onSubmit: function(param){
	    	if(options.queryData!=null){
	    		param = $.extend(param, options.queryData);
	    	}
	    },
	    success:function(data){
	    	// 反转义
//	    	data = HTMLDecode(data); 	
	        var dataObj = JSON.parse(data);
			commonReqSuccessHandler(dataObj, options.okCallback, options.failCallback, true);
	    }
	});
}

/**
 * form submit 提交
 * $form 要提交的form
 * options ：{}	
 * 		   	属性：
 * 			url							地址
 * 			queryData		            可以在from参数的基础上再做处理
 * 			okCallback:function()
 * 										操作成功: JsonResult.isOk() == true
 * 			failCallback:function()
 * 										操作失败: JsonResult.isOk() == false
function formSubmit($form,options){
	$form.form('submit', {
	    url:options.url,
	    onSubmit: function(param){
	    	if(options.queryData!=null){
	    		param = $.extend(param, options.queryData);
	    	}
	    },
	    success:function(data){
			commonReqSuccessHandler(data, options.okCallback, options.failCallback, true);
	    }
	});
}
 */

//判断标签是否存在
function isTagExist(tagId){
	var obj = $("#"+tagId);
	if(obj){
		return true;
	}
	return false;
}

//unix时间专为当前时间
hx_getnowtime=function(unixtime){
	var iunixtime=parseInt(unixtime);
	var date=new Date(iunixtime);
	return (date).toLocaleDateString() + " " + (date).toLocaleTimeString();
};

// html 解码  将后台读取的html内容还原为html内容，放在富文本中显示
UTIL_HtmlDiscode=function(theString){
 return theString.replaceAll("&gt;", ">")
.replaceAll("&lt;", "<")
.replaceAll("&nbsp;", " ")
.replaceAll(" &nbsp;", " ")
.replaceAll("&quot;", "\"")
.replaceAll("&#39;", "\'");
};

Date.prototype.format = function(format) 
{ 
var o = 
{ 
"M+" : this.getMonth()+1, //month 
"d+" : this.getDate(), //day 
"h+" : this.getHours(), //hour 
"m+" : this.getMinutes(), //minute 
"s+" : this.getSeconds(), //second 
"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
"S" : this.getMilliseconds() //millisecond 
} ;

if(/(y+)/.test(format)) 
{ 
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
} 

for(var k in o) 
{ 
if(new RegExp("("+ k +")").test(format)) 
{ 
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
} 
} 
return format; 
} ;


Date.fromISO= (function(){
    var testIso = '2011-11-24T09:00:27+0200';
    // Chrome
    var diso= Date.parse(testIso);
    if(diso===1322118027000) return function(s){
        return new Date(Date.parse(s));
    };
    // JS 1.8 gecko
    var noOffset = function(s) {
      var day= $.map(s.slice(0,-5).split(/\D/), function(itm){
        return parseInt(itm, 10) || 0;
      });
      day[1]-= 1;
      day= new Date(Date.UTC.apply(Date, day));  
      var offsetString = s.slice(-5);
      var offset = parseInt(offsetString,10)/100; 
      if (offsetString.slice(0,1)=="+") offset*=-1;
      day.setHours(day.getHours()+offset);
      return day.getTime();
    };
    if (noOffset(testIso)===1322118027000) {
       return noOffset;
    }  
    return function(s){ // kennebec@SO + QTax@SO
        var day, tz, 
//        rx = /^(\d{4}\-\d\d\-\d\d([tT][\d:\.]*)?)([zZ]|([+\-])(\d{4}))?$/,
        rx = /^(\d{4}\-\d\d\-\d\d([tT][\d:\.]*)?)([zZ]|([+\-])(\d\d):?(\d\d))?$/,
            
        p= rx.exec(s) || [];
        if(p[1]){
            day= p[1].split(/\D/).map(function(itm){
                return parseInt(itm, 10) || 0;
            });
            day[1]-= 1;
            day= new Date(Date.UTC.apply(Date, day));
            if(!day.getDate()) return NaN;
            if(p[5]){
                tz= parseInt(p[5], 10)/100*60;
                if(p[6]) tz += parseInt(p[6], 10);
                if(p[4]== "+") tz*= -1;
                if(tz) day.setUTCMinutes(day.getUTCMinutes()+ tz);
            }
            return day;
        }
        return NaN;
    };
})();

/**
 * 验证字符串是否为空
 * 
 * hasText(undefind) = false
 * hasText(null) = false
 * hasText("") = false
 * hasText(" ") = true
 * hasText("Hello") = true
 * 
 *  @param str
 */
function hasText(str){
	if (str == null 
			|| str == undefined 
			|| $.trim(str).length == 0){
		return false;
	}
	return true;
}

//JS 四舍五入 自定义小数位数，空缺补0,添加千位符
function round(num,n){
	var   dd=1;  
	var   tempnum;  
	for(var i=0;i<n;i++){  
	dd*=10;  
	}  
	tempnum=num*dd;  
	tempnum=Math.round(tempnum);
	tempnum=formatnumber(tempnum/dd,n);
	tempnum=milliFormat(tempnum);
	return tempnum;  
}

//HTML转义
function HTMLEncode(html)
{
	var temp = document.createElement ("div");
	(temp.textContent != null) ? (temp.textContent = html) : (temp.innerText = html);
	var output = temp.innerHTML;
	temp = null;
	return output;
}

//HTML反转义
function HTMLDecode(text)
{
	var temp = document.createElement("div");
	temp.innerHTML = text;
	var output = temp.innerText || temp.textContent;
	temp = null;
	return output;
}

//空缺补0
function formatnumber(value, num){
    var a, b, c, i;
    a = value.toString();
    b = a.indexOf(".");
    c = a.length;
    if (num == 0) {
        if (b != -1) {
            a = a.substring(0, b);
        }
    } else {
        if (b == -1) {
            a = a + ".";
            for (i = 1; i <= num; i++) {
                a = a + "0";
            }
        } else {
            a = a.substring(0, b + num + 1);
            for (i = c; i <= b + num; i++) {
                a = a + "0";
            }
        }
    }
    return a;
}

//添加千位符
function milliFormat(s){
	s = s+"";
	var s1=s,s2="";
	var pIdx=s.indexOf(".");
	if(pIdx>=0){
		s1=s.substring(0,pIdx);
		s2=s.substring(pIdx+1,s.length);
	}
	var p=/(\d+)(\d{3})/;
	while(p.test(s1)){
		s1=s1.replace(p,"$1,$2");
	}
	if(pIdx>=0){
		s1 = s1 + "." + s2;
	}
	return s1;
}


/*
 * 处理过长的字符串，截取并添加省略号
 * 注：半角长度为1，全角长度为2
 * 
 * pStr:字符串
 * pLen:截取长度
 * 
 * return: 截取后的字符串
 */
function autoAddEllipsis(pStr, pLen) {

	var _ret = cutString(pStr, pLen);
	var _cutFlag = _ret.cutflag;
	var _cutStringn = _ret.cutstring;

	if ("1" == _cutFlag) {
		return _cutStringn + "...";
	} else {
		return _cutStringn;
	}
}

/*
 * 取得指定长度的字符串
 * 注：半角长度为1，全角长度为2
 * 
 * pStr:字符串
 * pLen:截取长度
 * 
 * return: 截取后的字符串
 */
function cutString(pStr, pLen) {

	// 原字符串长度
	var _strLen = pStr.length;

	var _cutString = '';

	// 默认情况下，返回的字符串是原字符串的一部分
	var _cutFlag = "1";

	var _lenCount = 0;

	var _ret = false;

	if (_strLen <= pLen/2) {
		_cutString = pStr;
		_ret = true;
	}

	if (!_ret) {
		for (var i = 0; i < _strLen ; i++ ) {
			if (isFull(pStr.charAt(i))) {
				_lenCount += 2;
			} else {
				_lenCount += 1;
			}

			if (_lenCount > pLen) {
				_cutString = pStr.substring(0, i);
				_ret = true;
				break;
			} else if (_lenCount == pLen) {
				_cutString = pStr.substring(0, i + 1);
				_ret = true;
				break;
			}
		}
	}
	
	if (!_ret) {
		_cutString = pStr;
		_ret = true;
	}

	if (_cutString.length == _strLen) {
		_cutFlag = "0";
	}

	return {"cutstring":_cutString, "cutflag":_cutFlag};
}

/*
 * 判断是否为全角
 * 
 * pChar:长度为1的字符串
 * return: true:全角
 * 			false:半角
 */
function isFull (pChar) {
	if ((pChar.charCodeAt(0) > 128)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 是否含有中文
 * @param str 
 * @returns {Boolean}
 */
function isContainChinese(str){   
	if(/.*[\u4e00-\u9fa5]+.*$/.test(str)){   
		return true;   
	}else{
		return false;   
	}
}   

/**
 * 冻结屏幕
 */
function lockBody(){
	$("body").mask("请稍等...");
}

/**
 * 解锁屏幕
 */
function unlockBody(){
	$("body").unmask();
}

/**
 * 返回屏幕是否lock
 */
function isBodyLocked(){
	return $("body").isMasked();
}

// log
function log(s){
	if(console){
		console.log(s);
	}
}

/**
 * 导入，控件与easyui linkbutton相结合
 * 
 * @param options JSON类 参数设置
 * 可配参数：
 * 	url
 * 		string 上传路径		
 * 	sizeLimit 
 * 		number 文件最大size。默认：5 * 1024 * 1024
 * 	validTypes 
 * 		array 验证文件类型。默认：['xlsx'] 
 *  beforePop
 *  	function 弹出文件选择框之前执行，返回false的话将阻止pop
 *  okCallback
 *  	function(data, comment) 
 *  failCallback
 * 		function(data, comment) 
 */ 
var fileUpLoader;
// $.fn.uploader = function(options){
// 	var a = $(this);
// 	var text = a.text();
// 	a.removeClass('easyui-linkbutton')	// 删除easyui-linkbutton，避免再次初始化控件
// 		.linkbutton()					// 初始化控件
// 		.unbind('click.linkbutton')		// 接触《a》的点击事件，否则上传控件点击不到
// 		.addClass('uploader');			// 加css class
// 	var textSpan = a.find('.l-btn-text');
// 	// 对调text和icon的位置，是整个按钮点下去都可以弹出选择框
// 	textSpan.before(a.find('.l-btn-icon'));
//
// 	// 上传初始化
// 	var op = $.extend({
// 		sizeLimit : 50 * 1024 * 1024,
// 		validTypes:['xlsx'],
// 		autoUpload:true
// 	}, options);
//
// 	textSpan.fineUploader({
// 		request : {
// 			inputName : 'file',
// 			endpoint : op.url,
// 			forceMultipart : true
// 		},
//         autoUpload: op.autoUpload,
// 		multiple : false,
// 		validation : {
// 			sizeLimit : op.sizeLimit,
// 			allowedExtensions : op.validTypes
// 		},
// 		messages : {
// 			sizeError:msgFormat('E000000069', op.sizeLimit / (1024 * 1024)),
// 			typeError:msgFormat('E000000070', op.validTypes.join('|'))
// 		},
//         showMessage: function(message){
//         	//$.showMessage(message);
//         },
// 		debug : false
//     }).on('complete', function(event, id, fileName, responseJSON) {
// 		// 解锁屏
// 		log('complete');
// 		unlockBody();
// 		if(responseJSON.success){
// 			if($.isFunction(op.okCallback)){
// 				op.okCallback(responseJSON.data, responseJSON.comment);
// 			}
// 		}
// 	}).on('submit', function(event, id, name){
// 		log('submit');
// 		lockBody();
// 		//return false;
// 		if(!op.autoUpload){
// 			unlockBody();
// 		}
// 		if(op.onSubmit){
// 			if(op.onSubmit.call(this, id, name)){
// 				$(this).fineUploader('setParams', op.onSubmit.call(this, id, name), id);
// 			}else{
// 				unlockBody();
// 				return false;
// 			}
// 		}
// 	}).on('progress', function(event,id, name, uploadedBytes, totalBytes){
// 		log('progress - ' + id + ' ' + name + ' ' + uploadedBytes +' ' + totalBytes);
// 	}).on('error', function(event,id, name, errorReason){
// 		unlockBody();
// 		if(!errorReason){
// 			errorReason = msgFormat('E000000200');
// 		}
// 		showMsg(errorReason, 'E', function(){
// 			if($.isFunction(op.failCallback)){
// 				op.failCallback(data.data, data.comment);
// 			}
// 		});
// 	});
//
// 	fileUpLoader = textSpan;
// 	// 文本
// 	a.find('.qq-upload-button').children(':first').addClass('qq-uploader-button-text').html(text);
// 	a.find('input[type="file"]').click(function(e){
// 		if($.isFunction(op.beforePop)){
// 			if(op.beforePop() === false){
// 				e.preventDefault();
// 			}
// 		}
// 	});
// };

/**
 * 消息弹窗封装
 * @param id 	 消息id
 * @param params 数组，消息参数，或者字符串（只有一个参数的时候适用）
 * @param fn	 回调
 * @param firstButtonNoFocus	 第一个按钮是否需要光标定位，并且背景色变红
 * @param backcolorChange	     背景色是否改变为红色
 */
$.showMessage = function(id, params,fn,firstButtonNoFocus,backcolorChange){
	var msgText = msgFormat(id, params);
	var type = id.charAt(0);
	showMsg(msgText, type, fn);
	// 假如第一个确认按钮不需要光标定位
	if(firstButtonNoFocus){
		// 定位取消
		$("div.messager-button").children("a:first").blur();
	}
	// 背景色需要变为红色
	if(backcolorChange){
		// window-mask：背景色变为红色
		$("div.window-mask").addClass('datagrid-row-check-pdt-not-exists');
	}
};

function showMsg(msgText, type, fn){
	msgText = "<div class=\"message-text-body\">"+msgText+"</div>";
	if(type == 'I'){
		$.messager.alert('',msgText, 'info', fn);
	}else if(type == 'W'){
		$.messager.alert('',msgText, 'warning', fn);
	}else if(type == 'E'){
		$.messager.alert('',msgText, 'error', fn);
	}else if(type == 'Q'){
		$.messager.confirm('',msgText, fn);
	}else{
		$.messager.alert('',msgText, 'error', fn);
	}
	
	// 询问框和信息框不需要保存
	if(type != 'Q' && type != 'I'){
		// 添加保存文件按钮
		var saveMessage="<a href='javascript:void(0)' onclick='messageContentSave()'class='l-btn l-btn-small'   style='margin-left: 10px;'>";
		saveMessage+="<span class='l-btn-left'>";
		saveMessage+="<span class='l-btn-text'>保存</span>";
		saveMessage+="</span></a>";
		$(".messager-button").prepend(saveMessage);
	}
}
// 判断是否是IE
function commIsIE() { //ie判断
	 if (!!window.ActiveXObject || "ActiveXObject" in window)
		 return true;
	 else
		 return false;
}

//保存文件
function messageContentSave(){

	var message = $('.message-text-body').html();
	if(!hasText(message)){
		message = '';
	}
	// 是否是IE
	if(commIsIE()){
		// 导出
		downloadSubmit({
			url:"messageDownload",
			queryData: {'message':message}
		});
	}else{
		// 下载
		if($('#messageDownloadA').get(0) != undefined && $('#messageDownloadA').length>0){
			var data = "\ufeff"+message.replace(/<br>/g,'\r\n');  
			var blob = new Blob([data], { type: 'text/txt,charset=UTF-8'});  
			// var txtUrl = "data:text/txt;charset=utf-8,"+data;  ;
			var txtUrl = URL.createObjectURL(blob);
			$('#messageDownloadA').get(0).href = txtUrl;
			$('#messageDownloadA').get(0).click();
		}
	}
}

/**
 * 消息格式化方法，如果消息id不存在，则返回 [消息id] +（费定义）
 * 
 * @param msgId 		消息id
 * @param msgParams		消息参数
 * @returns {String}	格式化后的消息内容
 */
function msgFormat(msgId, msgParams){
	var msgContent = msg[msgId];
	if(msgContent == undefined) {
		msgContent = msgId + "（未定义）";
	}else{
		if(msgParams){
			var p = msgParams;
			if(!$.isArray(p)){
				p = [p];
			}
			
			for (var i = 0; i < p.length; i++) {
		        var reg = "\\{" + i + "\\}";
		        msgContent = msgContent.replace(new RegExp(reg, "g"), p[i]);
		    }
		}
	}
	
	return msgContent;
}

// 供应商comboloader
var comboSupplierLoader = function(param,success,error){
	return comboLoader('supplier', param, success,error);
};
// 周转箱comboloader
var comboCaseLoader = function(param,success,error){
	return comboLoader('caseInfo', param, success,error);
};

// 仓库comboloader
var comboWarehouseLoader = function(param,success,error){
	return comboLoader('warehouse', param, success,error);
};

// 权限仓库comboloader
var comboRoleWarehouseLoader = function(param,success,error){
	return comboLoader('roleWarehouse', param, success,error);
};

//权限仓库或者存在途仓库类型comboloader
var comboRoleAndIntransitWarehouseLoader = function(param,success,error){
	return comboLoader('roleAndIntransitWarehouse', param, success,error);
};


//非虚拟仓库comboloader
var comboWarehouseNoVirtualLoader = function(param,success,error){
	return comboLoader('warehouseNoVirtual', param, success,error);
};

// 区域comboloader
var comboZoneLoader = function(param,success,error){
	return comboLoader('zone', param, success,error);
};

//商品信息
var comboProductLoader=function(param,success,error){
	return comboLoader('product', param, success,error);
};

//库存商品信息
var combostockProductLoader=function(param,success,error){
	return comboLoader('stockProduct', param, success,error);
};

//库存商品信息类型为基本并且不是构成物
var combostockCombinationsProductLoader=function(param,success,error){
	return comboLoader('combinationsStockProduct', param, success,error);
};
//类型为基本并且不是构成物或者类型为教材的商品
var comboCombinationsDetailProductLoader=function(param,success,error){
	return comboLoader('combinationsDetailProduct', param, success,error);
};

// 员工信息
var comboEmployeeLoader=function(param,success,error){
	return comboLoader('employee', param, success,error);
};

// 用户页面专用员工信息autoComplete
var comboEmployeeForUserLoader=function(param,success,error){
	return comboLoader('employeeForUser', param, success,error);
};

//商品类别comboloader
var comboProductCategoryLoader=function(param,success,error){
	return comboLoader('productCategory', param, success,error);
};

//商品最小类别comboloader
var comboProductLowestCategoryLoader=function(param,success,error){
	return comboLoader('productLowestCategory', param, success,error);
};

//货主comboloader
var comboConsignorLoader=function(param,success,error){
	return comboLoader('consignor', param, success,error);
};

//权限货主comboloader
var comboRoleConsignorLoader = function(param,success,error){
	return comboLoader('roleConsignor', param, success,error);
};

//部门comboloader
var comboDepartmentLoader=function(param,success,error){
	return comboLoader('department', param, success,error);
};

//承运商comboloader
var comboCarrierLoader=function(param,success,error){
	return comboLoader('carrier', param, success,error);
};

//储位comboloader
var comboLocationLoader=function(param,success,error){
	return comboLoader('location', param, success,error);
};

//分拣位comboloader
var comboSortingLocationLoader=function(param,success,error){
	return comboLoader('sortingLocation', param, success,error);
};

//配送工具comboloader
var comboDeliveryModeLoader=function(param,success,error){
	return comboLoader('deliveryMode', param, success,error);
};

//盘点策略comboloader
var comboStocktakeStrategyLoader=function(param,success,error){
	return comboLoader('stocktakeStrategy', param, success,error);
};

var comboShopLoader=function(param,success,error){
	return comboLoader('shop', param, success,error);
};

//要货方comboloader
var comboBuyerLoader=function(param,success,error){
	var type = param.type;
	if(type==undefined||type==""||type==null){
		return;
	}
	return comboLoader(type, param, success,error);
};

//波次comboloader
var comboWaveInfoLoader = function(param,success,error){
	return comboLoader('waveNo', param, success,error);
};

//路线规划comboloader
var comboRoutePlanLoader = function(param,success,error){
	return comboLoader('routePlan', param, success,error);
};

/**
 * combo共通loader
 * 
 * @param type
 * @param param
 * @param success
 * @param error
 * @returns {Boolean}
 */
function comboLoader(type, param,success,error){
    var q = param.q || '';
    var noSelect = param.noSelect;//初始设置的参数是否需要检索,默认不设置是false
    if(noSelect){//如果是不需要检索则返回false
    	return false;
    }
    if (q.length < $CONFIG.auto_complete_min_char){return false;}
    $.ajax({
        url: 'combo',
        type:'POST',
        dataType: 'json',
		contentType:"application/json; charset=utf-8",
        data: JSON.stringify({
            'startsWith': q,
            'type':type,
            'condition':param.condition
        }),
        success: function(data){
            success(data);
        },
        error: function(){
            error.apply(this, arguments);
        }
    });
    return true;
}

/**
 * 记住当前页面所打开的dialog，以便在画面关闭的时候自动销毁.
 * ! 注意，必须在生成 dialog 之前调用
 * @param id
 * @param pageNo 非必须，有权限控制的dialog画面则必须，规则为跟对应vm文件同名
 * @param needRemember true或者缺省为需要记录在父画面中以便于在关闭时销毁
 * 				 false不需要在父画面销毁
 */
function rememberDialog(id, pageNo,needRemember) {
	if(needRemember==undefined || needRemember ==true){
		var panel = $(id).parents('div.panel-body').eq(0);
		if (panel) {
			var op = panel.panel('options');
			if (op.dialogs) {
				op.dialogs.push(id);
			} else {
				op.dialogs = [id];
			}
		}
	}
	var options = $(id).attr('data-options');
	var onload = 'onLoad:bindRememberDialogOnload';
	if(options){
		options += ',' + onload;
	}else{
		options = onload;
	}
	if(pageNo){
		options += ",pageNo:'" + pageNo + "'";
	}
	$(id).attr('data-options', options);
}

/**
 * 绑定rememberDialog onload事件
 */
function bindRememberDialogOnload(){
	//log('detail dialog load ~~');
	var pageNo = $(this).dialog('options').pageNo;
	if(pageNo!=undefined){
		var RolePageFunctions = getRolePageFunctions(pageNo);//角色权限按钮
		if(RolePageFunctions==undefined){
			RolePageFunctions="";
		}
		var BtnQuit=pageNo+"BtnQuit";
		//var pageFunctions = $(this).find(".dialog-bottom-buttons a");//页面按钮
		var pageFunctions = $("a.easyui-linkbutton",$(this));//页面按钮
		$.each(pageFunctions,function(i,e){
			var id=$(e).attr("id");
			if(RolePageFunctions.indexOf(id)==-1){//按钮无权限，移除按钮
				if(id!=BtnQuit){
					$(e).remove();
				}
			}
		});
	}
	focusFirstInput($(this));
	// 调用自定义的onLoad事件
	$(this).dialog('options').exOnLoad.call(this);
}

/**
 * 商品选择共通，selectBack(data)返回函数，返回一个data商品信息的object
 * @param callBack
 */
function productInfoSelect(selectBack,params){
	var div="<div id='productSelectDialog' class='detail-dialog' title='商品选择'>";
	$("body").append(div);
	var url="productSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#productSelectDialog").dialog({
		width:1060,
		height:570,
		href:url,
		onClose:function(){
			$("#productSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 配送工具选择共通 
 */
function deliveryModeSelect(selectBack,params){
	var div="<div id='deliveryModeSelectDialog' class='detail-dialog' title='配送工具选择'>";
	$("body").append(div);
	var url="deliveryModeSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#deliveryModeSelectDialog").dialog({
		width:900,
		height:500,
		href:url,
		onClose:function(){
			$("#deliveryModeSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 用户选择共通 
 */
function userSelect(selectBack,params){
	var div="<div id='userSelectDialog' class='detail-dialog' title='用户选择'>";
	$("body").append(div);
	var url="userSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#userSelectDialog").dialog({
		width:1035,
		height:500,
		href:url,
		onClose:function(){
			$("#userSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 条码选择共通 
 */
function barcodeSelect(selectBack,params){
	var div="<div id='barcodeSelectDialog' class='detail-dialog' title='商品条码'>";
	$('body').append(div);
	var url='productBarcodeSelect?';
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	url=url.substring(0, url.length-1);
	$("#barcodeSelectDialog").dialog({
		width:630,
		height:500,
		href:url,
		onClose:function(){
			$("#barcodeSelectDialog").dialog("destroy");
		},
		mycallBack:selectBack
	}).dialog("open");
}

/**
 * 商品类别选择共通，selectBack(data)返回函数，返回一个data商品类别信息的object
 * @param callBack
 * @param params 参数 map型
 */
function productCategorySelect(selectBack,params){
	var div="<div id='productCategorySelectDialog' class='detail-dialog' title='商品类别选择'>";
	$("body").append(div);
	var url="productCategorySelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#productCategorySelectDialog").dialog({
		width:740,
		height:520,
		href:url,
		onClose:function(){
			$("#productCategorySelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}


/**
 * 供应商选择共通，selectBack(data)返回函数，返回一个data供应商信息的object
 * @param callBack
 */
function supplierInfoSelect(selectBack,params){
	var div="<div id='supplierSelectDialog' class='detail-dialog' title='供应商选择'>";
	$("body").append(div);
	var url="supplierSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#supplierSelectDialog").dialog({
		width:900,
		height:500,
		href:url,
		onClose:function(){
			$("#supplierSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 储位选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function locationInfoSelect(selectBack,params){
	var div="<div id='locationSelectDialog' class='detail-dialog' title='储位选择'>";
	$("body").append(div);
	var url="locationSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#locationSelectDialog").dialog({
		width:1060,
		height:550,
		href:url,
		onClose:function(){
			$("#locationSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 库存选择共通，selectBack(data)返回函数
 * @param callBack
 */
function stockInfoSelect(selectBack,params){
	var div="<div id='stockSelectDialog' class='detail-dialog' title='库存选择'>";
	$("body").append(div);
	var url="stockSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#stockSelectDialog").dialog({
		width:940,
		height:560,
		href:url,
		onClose:function(){
			$("#stockSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * new 库存选择共通，selectBack(data)返回函数
 * @author huangzhihao
 * @param callBack
 */
function stockLocationSelect(selectBack,params){
	var div="<div id='stockLocationSelectDialog' class='detail-dialog' title='库存选择'>";
	$("body").append(div);
	var url="stockLocationSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#stockLocationSelectDialog").dialog({
		width:929,
		height:550,
		href:url,
		onClose:function(){
			$("#stockSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 区域选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function zoneInfoSelect(selectBack,params){
	var div="<div id='zoneSelectDialog' class='detail-dialog' title='区域选择'>";
	$("body").append(div);
	var url="zoneSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#zoneSelectDialog").dialog({
		width:879,
		height:500,
		href:url,
		onClose:function(){
			$("#zoneSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 周转箱选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function caseInfoSelect(selectBack,params){
	var div="<div id='caseSelectDialog' class='detail-dialog' title='周转箱选择'>";
	$("body").append(div);
	var url="caseInfoSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#caseSelectDialog").dialog({
		width:879,
		height:500,
		href:url,
		onClose:function(){
			$("#caseSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 货主选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function consignorInfoSelect(selectBack,params){
	var div="<div id='consignorSelectDialog' class='detail-dialog' title='货主选择'>";
	$("body").append(div);
	var url="consignorSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#consignorSelectDialog").dialog({
		width:829,
		height:500,
		href:url,
		onClose:function(){
			$("#consignorSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}
/**
 * 仓库查询
 * @param selectBack
 * @param params
 */
function warehouseSelect(selectBack,params){
	var div="<div id='warehouseSelectDialog' class='detail-dialog' title='仓库选择'>";
	$("body").append(div);
	var url="warehouseSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者& 
	url=url.substring(0,url.length-1); 
	$("#warehouseSelectDialog").dialog({
		width:910,
		height:500,
		href:url,
		onClose:function(){
			$("#warehouseSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 承运商选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function carrierInfoSelect(selectBack,params){
	var div="<div id='carrierInfoSelectDialog' class='detail-dialog' title='承运商选择'>";
	$("body").append(div);
	var url="carrierSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#carrierInfoSelectDialog").dialog({
		width:829,
		height:500,
		href:url,
		onClose:function(){
			$("#carrierInfoSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 司机选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function driverInfoSelect(selectBack,params){
	var div="<div id='driverSelectDialog' class='detail-dialog' title='司机选择'>";
	$("body").append(div);
	var url="driverSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#driverSelectDialog").dialog({
		width:829,
		height:500,
		href:url,
		onClose:function(){
			$("#driverSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 容器选择共通，selectBack(data)返回函数，返回一个data储位信息的object
 * @param callBack
 */
function containerInfoSelect(selectBack,params){
	var div="<div id='containerSelectDialog' class='detail-dialog' title='容器选择'>";
	$("body").append(div);
	var url="containerSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#containerSelectDialog").dialog({
		width:679,
		height:500,
		href:url,
		onClose:function(){
			$("#containerSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}


/**
 * 门店选择共通，selectBack(data)返回函数，返回一个data门店信息的object
 * @param callBack
 */
function shopInfoSelect(selectBack,params){
	var div="<div id='shopSelectDialog' class='detail-dialog' title='门店选择'>";
	$("body").append(div);
	var url="shopSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shopSelectDialog").dialog({
		width:979,
		height:590,
		href:url,
		onClose:function(){
			$("#shopSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 车辆选择共通，selectBack(data)返回函数，返回一个data车辆信息的object
 * @param callBack
 */
function vehicleInfoSelect(selectBack,params){
	var div="<div id='vehicleSelectDialog' class='detail-dialog' title='车辆选择'>";
	$("body").append(div);
	var url="vehicleSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#vehicleSelectDialog").dialog({
		width:900,
		height:620,
		href:url,
		onClose:function(){
			$("#vehicleSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 路线选择共通，selectBack(data)返回函数，返回一个data路线信息的object
 * @param callBack
 */
function routePlanInfoSelect(selectBack,params){
	var div="<div id='routePlanSelectDialog' class='detail-dialog' title='路线选择'>";
	$("body").append(div);
	var url="routePlanSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#routePlanSelectDialog").dialog({
		width:1000,
		height:595,
		href:url,
		onClose:function(){
			$("#routePlanSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 路线规划详细共通，selectBack(data)返回函数，返回一个data路线规划详细信息的object
 * @param callBack
 */
function routePlanDetailSelect(selectBack,params){
	var div="<div id='routePlanDetailSelectDialog' class='detail-dialog' title='路线规划详细'>";
	$("body").append(div);
	var url="routePlanDetail?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#routePlanDetailSelectDialog").dialog({
		width:1055,
		height:660,
		href:url,
		onClose:function(){
			$("#routePlanDetailSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 门店装货明细 选择共通，selectBack(data)返回函数，返回一个data门店信息的object
 * @param callBack
 */
function shopOrderItemSelect(selectBack,params){
	var div="<div id='shopOrderItemSelectDialog' class='detail-dialog' title='门店装货明细'>";
	$("body").append(div);
	var url="shopOrderItemSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shopOrderItemSelectDialog").dialog({
		width:1010,
		height:600,
		href:url,
		onClose:function(){
			$("#shopOrderItemSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 门店拣货进度详细 selectBack(data)返回函数，返回一个data门店信息的object
 * @param callBack
 */
function shopProgressDetailSelect(selectBack,params){
	var div="<div id='shopProgressDetailDialog' class='detail-dialog' title='门店拣货进度详细'>";
	$("body").append(div);
	var url="shopProgressDetail?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shopProgressDetailDialog").dialog({
		width:1010,
		height:590,
		href:url,
		onClose:function(){
			$("#shopProgressDetailDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 门店拣货进度 选择门店共通，selectBack(data)返回函数，返回一个data门店信息的object
 * @param callBack
 */
function shopProgressSelect(selectBack,params){
	var div="<div id='shopProgressSelectDialog' class='detail-dialog' title='门店拣货进度'>";
	$("body").append(div);
	var url="shopProgressSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shopProgressSelectDialog").dialog({
		width:979,
		height:640,
		href:url,
		onClose:function(){
			$("#shopProgressSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 路线方案选择共通，selectBack(data)返回函数，返回一个data门店信息的object
 * @param callBack
 */
function routeSolutionInfoSelect(selectBack,params){
	var div="<div id='routeSolutionSelectDialog' class='detail-dialog' title='路线方案选择'>";
	$("body").append(div);
	var url="routeSolutionSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#routeSolutionSelectDialog").dialog({
		width:979,
		height:625,
		href:url,
		onClose:function(){
			$("#routeSolutionSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 波次查询
 * @param selectBack
 * @param params
 */
function waveInfoSelect(selectBack,params){
	var div="<div id='waveInfoSelectDialog' class='detail-dialog' title='波次选择'>";
	$("body").append(div);
	var url="waveInfoSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#waveInfoSelectDialog").dialog({
		width:810,
		height:500,
		href:url,
		onClose:function(){
			$("#waveInfoSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 历史未派车，selectBack(data)返回函数，返回一个data历史未派车信息的object
 * @param callBack
 */
function historyNotDispatchSelect(selectBack,params){
	var div="<div id='historyNotDispatchDialog' class='detail-dialog' title='历史未派车'>";
	$("body").append(div);
	var url="historyNotDispatch?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#historyNotDispatchDialog").dialog({
		width:910,
		height:590,
		href:url,
		onClose:function(){
			$("#historyNotDispatchDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 出库单号查询
 * @param selectBack
 * @param params
 */
function shipmentNoSelect(selectBack,params){
	var div="<div id='shipmentNoSelectDialog' class='detail-dialog' title='出库单号选择'>";
	$("body").append(div);
	var url="shipmentNoSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shipmentNoSelectDialog").dialog({
		width:700,
		height:550,
		href:url,
		onClose:function(){
			$("#shipmentNoSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}


/**
 * 出库单号查询
 * @param selectBack
 * @param params
 */
function shopOrderSelect(selectBack,params){
	var div="<div id='shopOrderSelectDialog' class='detail-dialog' title='订单选择'>";
	$("body").append(div);
	var url="shopOrderSelect?";
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	//去除末尾的?或者&
	url=url.substring(0,url.length-1);
	$("#shopOrderSelectDialog").dialog({
		width:1000,
		height:580,
		href:url,
		onClose:function(){
			$("#shopOrderSelectDialog").dialog('destroy');
		},
		mycallBack:selectBack
	}).dialog('open');
}

/**
 * 打印等待画面
 * 
 * @param reportIds 帐票名称集合
 * @param callBack
 */
function openPrintWait(reportIds,callBack){
	var div="<div id='waitPrintDialog' class='detail-dialog' title='打印结果'>";
	$("body").append(div);
	var url="waitPrint?reportIds="+reportIds;
	$("#waitPrintDialog").dialog({
		width:420,
		height:200,
		href:url,
		onClose:function(){
			var timers = $("#waitPrintDialog").dialog("options").timers;
			if(timers){
				for(var i = 0;i<timers.length;i++){
					clearTimeout(timers[i]);
					clearInterval(timers[i]);
				}
			}
			$("#waitPrintDialog").dialog('destroy');
			callBack.call();
		},
	}).dialog('open');
}

/**
 * 指定便分拣单或者复验单 
 */
function expColdPickingListDialogSelect(selectBack,params,title){
	var div="<div id='expColdPickingListDialog' class='detail-dialog' title='"+title+"'>";
	$('body').append(div);
	var url='expColdPickingListWaveChange?';
	for(var key in params){
		url=url+key+"="+params[key]+"&";
	}
	url=url.substring(0, url.length-1);
	$("#expColdPickingListDialog").dialog({
		width:250,
		height:140,
		href:url,
		onClose:function(){
			$("#expColdPickingListDialog").dialog("destroy");
		}
	}).dialog("open");
}

/**
 * 控件disable时，去掉绑定的单击事件
 * 
 */
function linkButtonListUnbind(){
	for (var i = 0; i < arguments.length; i++) {
		var isDisabled=arguments[i].linkbutton("options").disabled;
		if(isDisabled){
			arguments[i].unbind('click');
		}
 	}
}


/**
 * 浮点数加法运算
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
	m=Math.pow(10,Math.max(r1,r2));
	return (Math.round(arg1*m)+Math.round(arg2*m))/m;
} 

/**
 * 浮点数加法运算
 */
function accAddX(arg){
	var res = arg;
	for (var i = 1; i < arguments.length; i++) {
		res = accAdd(res,arguments[i]);
 	}
	return res;                 
}

/**
 * 浮点数减法运算
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accSubtr(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;};
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;};
	m=Math.pow(10,Math.max(r1,r2));
	//动态控制精度长度
	//n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m)/*.toFixed(n)*/;
}

/**
 * 浮点数减法运算
 */
function accSubtrX(arg){
	var res = arg;
	for (var i = 1; i < arguments.length; i++) {
		res = accSubtr(res,arguments[i]);
 	}
	return res;                 
}

/**
 * 浮点数乘法运算
 */
function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length;}catch(e){}
	try{m+=s2.split(".")[1].length;}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
} 

/**
 * 浮点数乘法运算
 */
function accMulX(arg){
	var res = arg;
	for (var i = 1; i < arguments.length; i++) {
		res = accMul(res,arguments[i]);
 	}
	return res;                 
}

/**
 * 浮点数除法运算
 * 
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try{t1=arg1.toString().split(".")[1].length;}catch(e){}
	try{t2=arg2.toString().split(".")[1].length;}catch(e){}
	with(Math){
		r1=Number(arg1.toString().replace(".",""));
		r2=Number(arg2.toString().replace(".",""));
		return (r1/r2)*pow(10,t2-t1);
	}
} 

/**
 * 求模
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accMod(arg1, arg2){
	var t1=0,t2=0;
	try{t1=arg1.toString().split(".")[1].length;}catch(e){}
	try{t2=arg2.toString().split(".")[1].length;}catch(e){}
	with(Math){
		m=Math.pow(10,Math.max(t1,t2));
		return (accMul(arg1,m)%accMul(arg2,m))/m;
	}
}

/**
 * 分数乘法运算
 * 
 * @param arg1 分数字符串或浮点数
 * @param arg2 分数字符串或浮点数
 * @returns
 */
function accMulFraction(arg1,arg2){
	var number1=new Array();
	var number2=new Array();
	if(typeof(arg1)=='string'){
		number1 = arg1.split('/');
		if(number1.length==1){
			number1[1]=1;
		}
	}else{
		number1[0]=arg1;
		number1[1]=1;
	}
		
	if(typeof(arg2)=='string'){
		number2 = arg2.split('/');
		if(number2.length==1){
			number2[1]=1;
		}
	}else{
		number2[0]=arg2;
		number2[1]=1;
	}
	if(number1.length>2||number2.length>2){
		return NaN;
	}else{
		return accDiv(accMul(number1[0],number2[0]),accMul(number1[1],number2[1]));
	}
}

//将已作废的行背景色变灰
function invalidStyle(index,row){
	if (row.isuse==0){
		return {'class':'invalid-style'};
	}
}

//将已作废的行背景色变灰
function linkCellStyler(value,row,index){
	return {'class':'table-link-cell'};
}

//将isuse 替换成有效/已作废
function isuseFormat(value,row,index){
    if (value==10){
    	return '有效';
    }
    if (value==0) {
    	return '已作废';
    }
}
//将性别 替换成男/女
function sexFormat(value, row, index) {
	if (value == 0) {
		return '男';
	}
	if (value == 1) {
		return '女';
	}
}

// 将生日转换为年龄
function getAgeFormat(value) {
	if(value==null||value==''||value==undefined){
		return '';
	}
	var birthDayDate =new Date(Date.fromISO(value));
    var birthYear = birthDayDate.getYear();
    var birthMonth = birthDayDate.getMonth() + 1;
    var birthDay = birthDayDate.getDate();
    
    var now = new Date();
    var nowYear = now.getYear();
    var nowMonth = now.getMonth() + 1;
    var nowDay = now.getDate();

    if(nowYear == birthYear){
    	returnAge = 0;//同年 则为0岁
    }else{
    	var ageDiff = nowYear - birthYear ; //年之差
    	if(ageDiff > 0){
    		if(nowMonth == birthMonth){
    			var dayDiff = nowDay - birthDay;//日之差
    			if(dayDiff < 0){
    				returnAge = ageDiff - 1;
    			}else{
    				returnAge = ageDiff;
    			}
    		}else{
    			var monthDiff = nowMonth - birthMonth;//月之差
    			if(monthDiff < 0){
    				returnAge = ageDiff - 1;
    			}else{
    				returnAge = ageDiff;
    			}
    		}
    	}else{
    		returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
    	}
    }
    return returnAge;//返回周岁年龄    
}

/**
 * 数字前补0
 * 
 * @param num 数字
 * @param length 长度
 * @returns 返回值
 */
function addZeroBeforeNum(num,length){
	num=new Number(num);//转成数值型
	if(isNaN(num)){//如果不是数值型清空
		return "";
	}else{//将数字转字符型
		str=num.toString();
	}
	return new Array(Number(length) - str.length + 1).join('0') + str;//补0
}

function commonLinkCellClick(event){
	event = event || window.event;
	var t = event.target || event.srcElement;
	var $a = $(t);
	var grid = $a.parents('.datagrid-view').eq(0).children('table').eq(0);
	grid.datagrid('options').exOnClickLinkCell.call(grid[0], $a.parents('tr').attr('datagrid-row-index'), $a.attr('field'), $a.text());
	if (window.event) {
		event.cancelBubble=true;     // ie下阻止冒泡
	} else {
		event.stopPropagation();     // 其它浏览器下阻止冒泡
	}
}

//表格数值格化方法
function linkCellFormatter(value,row,index){
	if(hasText(value)){
		return '<a class=\'table-link-cell\' field=\'' + this.field + '\' index=\''+index+'\' onclick=\'commonLinkCellClick(event);\'>'+value+'</a>';
	}
	return '';
}

//表格数值格化方法
function numberFormatter(value,row,index){
	if (value){
		value = value.toString().replace(/,/g,'');
		return round(value,$CONFIG.qty_decimal);
	} else {
		return value;
	}
}
//表格数值格化方法
function numberTextFormatter(value,row,index){
	if (value){
		value = value.toString().replace(/,/g,'');
		value = round(value,$CONFIG.qty_decimal);
		return value.toString().replace(/,/g,'');
	} else {
		return value;
	}
}

//表格整数数值格化方法
function integerFormatter(value,row,index){
	if (value){
		value = value.toString().replace(/,/g,'');
		return round(value,0);
	} else {
		return value;
	}
}


// 金额数值格化方法
function amountFormatter(value,row,index){
	if (value){
		value = value.toString().replace(/,/g,'');
		return round(value,$CONFIG.amount_decimal);
	} else {
		return value;
	}
}


//价格时段画面价格金额数值格化方法：保留4位小数
function unitPriceFormatter(value,row,index){
	if (value){
		value = value.toString().replace(/,/g,'');
		return round(value,4);
	} else {
		return value;
	}
}

/**
 * datagrid可编辑text中赋值时金额formatter,去千分位
 * 
 * @param value
 * @returns
 */
function amountTextFormatter(value){
	if (value){
		value = value.toString().replace(/,/g,'');
		value = round(value,$CONFIG.amount_decimal);
		return value.toString().replace(/,/g,'');
	} else {
		return value;
	}
}

//表格数值格化并且显示超链接方法
function numberLinkCellFormatter(value,row,index){
	if(hasText(value)){
		return '<a class=\'table-link-cell\' field=\'' + this.field + '\' index=\''+index+'\' onclick=\'commonLinkCellClick(event);\'>'+milliFormat(value)+'</a>';
	}
	return '';
}

/**
 * 获取最大值
 * getMax(5,2) -> 999.99
 * getMax(6,2) -> 9999.99
 * getMax(6,3) -> 999.99(默认显示为精度2)
 * getMax(6,3,3) -> 999.999
 * getMax(6,3,0) -> 999
 * 
 * @param len 表字段长度
 * @param d   表字段精度
 * @param c	  精度，非必须，不设则视为默认精度
 * @returns {Number}
 */
function getMax(len, d, c){
	c = (c != undefined && c != null) ? c : $CONFIG.amount_decimal;
	return Math.pow(10, len - d) - Math.pow(10, -c);
}


/**
 * 获取页面权限按钮
 * 
 * @param pageNo 页面id
 * @returns
 */
function getRolePageFunctions(pageNo){
	var pageFunctions = eval("$ROLE_RIGHT."+pageNo);
	if(pageFunctions){
		return pageFunctions.split(",");
	}else{
		return [];
	}
		
}

/**
 * 将null字段转换成''
 * @param obj
 */
function nullToEmpty(obj){
	if(obj==null||obj==undefined){
		return '';
	}else{
		return obj;
	}
		
}

/**
 * 带时间的日期格式的Formatter
 * 
 * @param date
 */
function dateTimeFormatter(date){
	dataFormate = "yyyy-MM-dd HH:mm:ss";
	return dateFormatter(date,dataFormate);
}

/**
 * 不带秒的日期格式的Formatter
 * 
 * @param date
 */
function dateNoSecondTimeFormatter(date,dataFormate){
	if(date==undefined){
		return;
	}
	if(null==dataFormate || undefined ==dataFormate || dataFormate==''){
		dataFormate = "yyyy-MM-dd HH:mm";
		}
	 if(date.length!=0&&date.length!=13){
		 if(date.charAt(10) == 'T'){
			 return formatDate(dataFormate,date);
		 }
	 }
	 return date;
}


/**
 * 不带时间的日期格式ForMatter
 * 
 * @param date
 * @param dataFormate
 * @returns
 */
function dateFormatter(date,dataFormate){
	if(date==undefined){
		return;
	}
	if(null==dataFormate || undefined ==dataFormate || dataFormate==''){
		dataFormate = "yyyy-MM-dd";
		}
	 if(date.length!=0&&date.length!=13){
		 if(date.charAt(10) == 'T'){
			 return formatDate(dataFormate,date);
		 }
	 }
	 return date;
}

/**
 * 将定时器的id添加到tab的options中，方便在关闭tab时清除定时器
 * 
 * @param tab
 * @param timer
 */
function setTimersToTabs(tab,timer){
	var timers = $("#"+tab).panel("options").timers;
	if(timers){
		timers.push(timer);
	}else{
		$("#"+tab).panel("options").timers = [timer];
	}		
}

/**
 * 手动清除tab中的Timers
 * 
 * @param tab
 * @returns
 */
function destroyTimers(tab){
	var options = $("#"+tab).panel("options");
	var timers = options.timers;
	
	if(!timers) return;
	for(var i = 0;i<timers.length;i++){
		clearTimeout(timers[i]);
		clearInterval(timers[i]);
	}
	options.timers =[];
}

function setTimersToDialogs(dialog,timer){
	var timers = $("#"+dialog).dialog("options").timers;
	if(timers){
		timers.push(timer);
	}else{
		$("#"+dialog).dialog("options").timers = [timer];
	}
}

/**
 * 定时器
 * 
 * @param callBack 定时器处理方法
 * @returns
 */
function timer(callBack){
	return setTimeout(function(){callBack();},$CONFIG.file_import_export_timer);
}

/**
 * 打印定时器
 * 
 * @param callBack 定时器处理方法
 * @returns
 */
function previewPrintTimer(callBack){ 
	return setTimeout(function(){callBack();},$CONFIG.preview_print_timer);
}

/**
 * 将combo设置成readonly
 * 
 * @param jq
 */
function setComboReadonly(jq){
	if(jq==null||jq.length==0){
		return;
	}
	jq.combo('readonly',true);
	jq.combo('textbox')[0].style.background='#E2E2E2'; 
	jq.combo('textbox')[0].style.color='#000'; 
}

/**
 * 将combo的readonly取消
 * 
 * @param jq
 */
function setComboUnReadonly(jq){
	if(jq==null||jq.length==0){
		return;
	}
	jq.combo('readonly',false);
	jq.combo('textbox')[0].style.background='#fff'; 
	jq.combo('textbox')[0].style.color='#000'; 
}

//校验失败，面板自动展开
function searchPanelExpand(pageId){
	// 可折叠的查询条件区域，点击头部来展开/折叠
	var searchPanel = $('#'+pageId).find('.search-panel');
	// 查询的panel展开
	if(searchPanel){
		searchPanel.panel('expand');
	}
}

// 面板收缩
function searchPanelCollapse(pageId){
	// 可折叠的查询条件区域，点击头部来展开/折叠
	var searchPanel = $('#'+pageId).find('.search-panel');
	// 查询的panel展开
	if(searchPanel){
		searchPanel.panel('collapse');
	}
}

/*  
 * MAP对象，实现MAP功能  
 *  
 * 接口：  
 * size()     获取MAP元素个数  
 * isEmpty()    判断MAP是否为空  
 * clear()     删除MAP所有元素  
 * put(key, value)   向MAP中增加元素（key, value)   
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False  
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL  
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL  
 * containsKey(key)  判断MAP中是否含有指定KEY的元素  
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素  
 * values()    获取MAP中所有VALUE的数组（ARRAY）  
 * keys()     获取MAP中所有KEY的数组（ARRAY）  
 *  
 * 例子：  
 * var map = new Map();  
 *  
 * map.put("key", "value");  
 * var val = map.get("key")  
 * ……  
 *  
 */   
function Map() {   
    this.elements = new Array();   
     
    //获取MAP元素个数   
    this.size = function() {   
        return this.elements.length;   
    }   
     
    //判断MAP是否为空   
    this.isEmpty = function() {   
        return(this.elements.length < 1);   
    }   
     
    //删除MAP所有元素   
    this.clear = function() {   
        this.elements = new Array();   
    }   
     
    //向MAP中增加元素（key, value)    
    this.set = function(_key, _value) {   
        this.elements.push( {   
            key : _key,   
            value : _value   
        });   
    }   
     
    //删除指定KEY的元素，成功返回True，失败返回False   
    this.remove = function(_key) {   
        var bln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    this.elements.splice(i, 1);   
                    return true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    }   
     
    //获取指定KEY的元素值VALUE，失败返回NULL   
    this.get = function(_key) {   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    return this.elements[i].value;   
                }   
            }   
        } catch(e) {   
            return null;   
        }   
    }   
     
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL   
    this.element = function(_index) {   
        if(_index < 0 || _index >= this.elements.length) {   
            return null;   
        }   
        return this.elements[_index];   
    }   
     
    //判断MAP中是否含有指定KEY的元素   
    this.containsKey = function(_key) {   
        var bln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    bln = true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    }   
     
    //判断MAP中是否含有指定VALUE的元素   
    this.containsValue = function(_value) {   
        var bln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].value == _value) {   
                    bln = true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    }   
     
    //获取MAP中所有VALUE的数组（ARRAY）   
    this.values = function() {   
        var arr = new Array();   
        for(i = 0; i < this.elements.length; i++) {   
            arr.push(this.elements[i].value);   
        }   
        return arr;   
    }   
     
    //获取MAP中所有KEY的数组（ARRAY）   
    this.keys = function() {   
        var arr = new Array();   
        for(i = 0; i < this.elements.length; i++) {   
            arr.push(this.elements[i].key);   
        }   
        return arr;   
    }   
}   


/**
 * <pre>
 * 储位和区域，跟仓库联动。
 * 没有传入仓库的数据不作查询
 * </pre>
 * @since 2017/12/12	bls
 * @author lee
 * 
 * @param param	combo的 param
 * @param warehouseId	条件仓库
 */
function warehouseIdConditionToZoneAndLocation(param,warehouseId){
	if (hasText(warehouseId)) {
        param.condition = [{
            'value': warehouseId,
            'field': 'warehouse_id'
        }];
    } else {
    	param.noSelect = {
            'noSelect': true
        };
    }
}
})