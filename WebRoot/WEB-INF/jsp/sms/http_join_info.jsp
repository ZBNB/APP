<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="../css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
</head>
<body>
	<form  action="save.html" name="httpJoinForm" id="httpJoinForm" target="result" method="post" onsubmit="return checkInfo();">
		<input type="hidden" name="jr_id" id="jrId" value="${httpJoin.jr_id }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>接入类型:</th>
			<td>
				<select name="api_type" id="apiType" class="input_txt">
					<option value="">请选择</option>
					<c:forEach items="${apiTypeList}" var="apiType">
					<option value="${apiType.api_type }" <c:if test="${joinType == apiType.api_type}">selected</c:if>>${apiType.describe }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>用户名:</th>
			<td><input type="text" name="username" id="username" class="input_txt input_disabled" readonly="true" value="${httpJoin.username }"/></td>
		</tr>
		<tr class="info">
			<th>秘钥:</th>
			<c:if test="${opType == 'add'}">
				<td><input type="text" name="app_key" id="appKey" class="input_txt input_disabled" readonly="true" value="申请后系统生成"/></td>
			</c:if>
				<c:if test="${opType == 'edit'}">
			<td><input type="text" name="app_key" id="appKey" class="input_txt input_disabled" readonly="true" value="${httpJoin.app_key }"/></td>
			</c:if>
		</tr>
		<tr class="info">
			<th>消息头:</th>
			<td><input type="text" name="msg_head" id="msgHead" class="input_txt" value="${httpJoin.msg_head }"/></td>
		</tr>
		<tr class="info">
			<th>是否加密:</th>
			<td><input type="text" name="is_encode" id="isEncode" class="input_txt" value="${httpJoin.is_encode }"/></td>
		</tr>
		<tr class="info">
			<th>申请理由:</th>
			<td><input type="text" name="apply_reason" id="applyReason" class="input_txt" value="${httpJoin.apply_reason }"/></td>
		</tr>
		<tr class="info">
			<th>剩余次数:</th>
			<c:if test="${opType == 'add'}">
				<td><input type="text" name="msg_count" id="msgCount" class="input_txt input_disabled" readonly="true" value="初次申请有10次免费次数"/></td>
			</c:if>
			<c:if test="${opType == 'edit'}">
				<td><input type="text" name="msg_count" id="msgCount" class="input_txt input_disabled" readonly="true" value="${httpJoin.msg_count }"/></td>
			</c:if>
		</tr>
		<tr class="info">
			<th>审核状态:</th>
			<c:if test="${httpJoin.audit_state == '0'}">
				<td><input type="hidden" name="msg_count" id="msgCount" value="0"/>&nbsp;审核中</td>
			</c:if>
			<c:if test="${httpJoin.audit_state == '1'}">
				<td><input type="hidden" name="msg_count" id="msgCount" value="1"/>&nbsp;通过</td>
			</c:if>
			<c:if test="${httpJoin.audit_state == '2'}">
				<td><input type="hidden" name="msg_count" id="msgCount" value="2"/>&nbsp;驳回</td>
			</c:if>
		</tr>
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	
	<script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#httpJoinForm").submit();
			});
			setMUR();
		});
		
		function checkInfo(){
			if($("#apiType").val()==""){
				alert("请输入菜单名称!");
				$("#apiType").focus();
				return false;
			}
			return true;
		}
		
		function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
		function failed(){
			alert("新增失败！");
		}
		
		function setMUR(){
			console.log($("#apiType option:selected").val());
			if($("#apiType").val()!=""){
				$("#apiType").attr("readonly",true);
				$("#apiType").addClass("input_disabled");
			}else{
				$("#apiType").attr("readonly",false);
				$("#apiType").removeClass("input_disabled");
			}
		}
	</script>
</body>
</html>