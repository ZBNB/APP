<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HTTP接入</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th style="width:5%;">序号</th>
			<th>用户名</th>
			<th>接入类型</th>
			<th style="width:30%;">秘钥</th>
			<th>消息头</th>
			<th>是否加密</th>
			<th style="width:30%;">申请理由</th>
			<th>剩余次数</th>
			<th>审批状态</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty httpList}">
				<c:forEach items="${httpList}" var="http" varStatus="vs">
				<tr class="main_info" id="tr${http.jr_id }">
				<td>${vs.index+1}</td>
				<td>${http.username }</td>
				<td>${http.api_type }</td>
				<td>${http.app_key }</td>
				<td>${http.msg_head }</td>
				<td>
					<c:if test="${http.is_encode == '0'}">不加密</c:if>
					<c:if test="${http.is_encode == '1'}">加密</c:if>
				</td>
				<td>${http.apply_reason }</td>
				<td>${http.msg_count }</td>
				<td>
					<c:if test="${http.audit_state == '0'}">审核中</c:if>
					<c:if test="${http.audit_state == '1'}">通过</c:if>
					<c:if test="${http.audit_state == '2'}">驳回</c:if>
				</td>
				<td><a href="###" onclick="openClose(${http.jr_id },this,${vs.index })">展开</a> | 
				<a href="javascript:editmenu('${http.jr_id}');" >修改</a> | 
				<a href="javascript:delmenu('${http.jr_id }');" >删除</a></td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
				<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<div class="page_and_btn">
		<div>
			<a href="javascript:addmenu();" class="myBtn"><em>新增</em></a>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function addmenu(){
			var dg = new $.dialog({
				title:'新增接入',
				id:'http_join_new',
				width:330,
				height:360,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'httpAPIJoin/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editmenu(jr_id){
			var dg = new $.dialog({
				title:'修改菜单',
				id:'http_join_edit',
				width:330,
				height:360,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'httpAPIJoin/edit.html?jr_id='+jr_id
				});
    		dg.ShowDialog();
		}
		
		function delmenu(jr_id){
			if(confirm("确定要删除该配置吗？")){
				var url = 'httpAPIJoin/del.html?jr_id='+jr_id;
				$.get(url,function(data){
					document.location.reload();
				});
			}
		}
		
		function openClose(menuId,curObj,trIndex){
			var txt = $(curObj).text();
			if(txt=="展开"){
				$(curObj).text("折叠");
				$("#tr"+menuId).after("<tr class='main_info' id='tempTr"+menuId+"'><td colspan='4'>数据载入中</td></tr>");
				if(trIndex%2==0){
					$("#tempTr"+menuId).addClass("main_table_even");
				}
				var url = "menu/sub.html?menuId="+menuId;
				$.get(url,function(data){
					//alert(data);
					if(data.length>0){
						var html = "";
						$.each(data,function(i){
							html = "<tr style='height:24px;line-height:24px;' name='subTr"+menuId+"'>";
							html += "<td></td>";
							html += "<td><span style='width:80px;display:inline-block;'></span>";
							if(i==data.length-1)
								html += "<img src='images/joinbottom.gif' style='vertical-align: middle;'/>";
							else
								html += "<img src='images/join.gif' style='vertical-align: middle;'/>";
							html += "<span style='width:100px;text-align:left;display:inline-block;'>"+this.menuName+"</span>";
							html += "</td>";
							html += "<td>"+this.menuUrl+"</td>";
							html += "<td><a href='###' onclick='editmenu("+this.menuId+")'>修改</a> | <a href='###' onclick='delmenu("+this.menuId+",false)'>删除</a></td>";
							html += "</tr>";
							$("#tempTr"+menuId).before(html);
						});
						$("#tempTr"+menuId).remove();
						if(trIndex%2==0){
							$("tr[name='subTr"+menuId+"']").addClass("main_table_even");
						}
						//alert($(".main_table").html());
					}else{
						$("#tempTr"+menuId+" > td").html("没有相关数据");
					}
				},"json");
			}else{
				$("#tempTr"+menuId).remove();
				$("tr[name='subTr"+menuId+"']").remove();
				$(curObj).text("展开");
			}
		}
		
	</script>	
</body>
</html>