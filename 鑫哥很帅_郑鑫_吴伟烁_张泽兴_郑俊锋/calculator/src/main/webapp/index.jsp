<%@ page contentType="text/html; charset=utf-8" import="java.net.*"
	language="java" errorPage=""%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网贷计算器</title>
<script src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript">
	function request() {
		var date = document.getElementById("date").value;
		var money = document.getElementById("money").value;
		var limit = document.getElementById("limit").value;
		var rate = document.getElementById("rate").value;
		var method = document.getElementById("method").value;
		var limitIsDay = document.getElementById("limitIsDay").value;
		var rateIsDay = document.getElementById("rateIsDay").value;

		$.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath }/rest/calculate",
			data : "date=" + date + "&money=" + money + "&limit=" + limit
					+ "&rate=" + rate + "&method=" + method + "&limitIsDay="
					+ limitIsDay + "&rateIsDay=" + rateIsDay,
			async : true,
			success : function(data) {
				var tableElement = document.getElementById("resultTable");
				tableElement.innerHTML = "";
				var strArray = new Array();
				strArray[0] = "期号";
				strArray[1] = "代收（含本金）";
				strArray[2] = "本金";
				strArray[3] = "利息";
				strArray[4] = "到期时间";
				var str = "<tr>";
				for (var j = 0; j < 5; j++) {
					str += "<td><div align='center'><font>";
					str += strArray[j];
					str += "</font></div></td>";
				}
				str += "</tr>";
				tableElement.innerHTML += str;
				for (var i = 0; i < data.length; i++) {
					var result = new Array();
					result[0] = data[i].num;
					result[1] = data[i].total;
					result[2] = data[i].principal;
					result[3] = data[i].interest;
					result[4] = data[i].date;
					var str = "<tr>";
					for (var j = 0; j < 5; j++) {
						str += "<td><div align='center'><font>";
						str += result[j];
						str += "</font></div></td>";
					}
					str += "</tr>";
					tableElement.innerHTML += str;
				}

			}

		})
	}
</script>
</head>

<body style="background-image:url(images/background.jpg)">
	<div style="height:10%; width:100%; float:left;" id="NameBlock">
		<font id="Name">网贷计算器</font>
	</div>
	<div style="height:90%; width:100%">
		<div id='frame1' style="float:left">
			<div id='inputBlock'>
				<form method="post" action="rest/calculate">
					<input type="hidden" name="_method" value="POST" />
					<table width="350" border="0">
						<tr>
							<td><div align="right">
									<font>借出日期</font>
								</div></td>
							<td><input id="date" name="date" type="text"
								onClick="WdatePicker()" /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出金额</font>
								</div></td>
							<td><input id="money" name="money" type="number" /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出期限</font>
								</div></td>
							<td><input id="limit" name="limit" type="number" /></td>
							<td></td>
						</tr>
						<td></td>
						<td><input id="limitIsDay" name="limitIsDay" type="radio"
							value="0" checked="checked" /><font>个月</font> <!-- <input id="date" name="limitIsDay" type="radio" value="1" /><font>天</font> -->
						</td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>利率</font>
								</div></td>
							<td><input id="rate" name="rate" type="number" /></td>
							<td><font>%</font></td>
						</tr>
						<td></td>
						<td><input id="rateIsDay" name="rateIsDay" type="radio"
							value="0" checked="checked" /><font>年利率</font> <input
							id="rateIsDay" name="rateIsDay" type="radio" value="1" /><font>日利率</font>
						</td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>还款方式</font>
								</div></td>
							<td><select name="method" id="method">
									<option value="monthInterestDueDebt">月还息到期还本</option>
									<option value="alsoDuePrincipalAndInterest">到期还本期</option>
									<option value="equalPrincipalInterest">等额本息</option>
									<option value="equalPrincipal">等额本金</option>
							</select></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td colspan="3"><div align="center">
									<input id="calculate" type="button" value="计算"
										onclick="request()" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div id='frame2' style="float:right;overflow-y:scroll">
			<table id="resultTable" width="100%" border="0">
				<tr>
					<td><div align="center">
							<font>期号</font>
						</div></td>
					<td><div align="center">
							<font>代收（含本金）</font>
						</div></td>
					<td><div align="center">
							<font>本金</font>
						</div></td>
					<td><div align="center">
							<font>利息</font>
						</div></td>
					<td><div align="center">
							<font>到期时间</font>
						</div></td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>
