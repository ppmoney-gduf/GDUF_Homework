function onLoad(){
	getMachineTime();
	showTime();
	calculation();
}

//格式转换
function checkTime(i)  
{  
	if(i<10){
		i="0"+i;}
	return i  ;
}

//获取本地时间并赋值到借出日期
function getMachineTime(){ 
	var today=new Date();
	var day = today.getDate();
	var month = today.getMonth() + 1;
	var year = today.getFullYear();
	month=checkTime(month);
	day=checkTime(day);
	date1.value=year+"-"+month+"-"+day;
}

//显示本地时间年月日时秒分
function showTime(){
	var today=new Date();
	var day = today.getDate();
	var month = today.getMonth() + 1;
	var year = today.getFullYear();
	var h=today.getHours();
	var m=today.getMinutes();
	var s=today.getSeconds();
	m=checkTime(m);
	s=checkTime(s);
	document.getElementById('date').innerHTML=year+"/"+month+"/"+day+" "+h+":"+m+":"+s;	
	t=setTimeout('showTime()',1000);
}

//按下拉选项不同方法计算
function calculation()
{
	var selection1=document.getElementById("selection");
		
	if(selection1.value==0){
		method1();	
	}
	
	if(selection1.value==1){
		method2();	
	}
	
	if(selection1.value==2){
		method3();	
	}
	
	if(selection1.value==5){
		method4();	
	}
	
	t=setTimeout('calculation()',100)    ;
	
}

//获得当年的天数
function getDayOfYear(years){
	var day=0;
	if (years%4==0&&years%100!=0||years%400==0)
		day=366;
	else
		day=365;
	return day;
}

//获得当月的天数
function getDayOfMonth(month,yDay){
	var day=0;
	if (month==2&&yDay==366)
		day=29;
	if (month==2&&yDay==365)
		day=28;
	if (month==4||month==6||month==9||month==11)
		day=30;
	if (month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		day=31;
	return day;
}

//月还息到期还本
function method1(){
	//借出日期数组a
	var a = date1.value.split("-");
	var date3=a[0]+"-"+a[1]*1+"-"+a[2]/1;
	//初始化还款日期date2
	var date2="";
	//借出本金capital
	var capital0=capital.value;
	//借出期限
	var deadline0=deadline.value;
	//给radios赋值单选按钮组月日
	var radios=document.getElementsByName('RadioGroup1')
	//给radios2赋值单选按钮组年利率日利率
	var radios2=document.getElementsByName('RadioGroup2')
	
	//如果借出期限选择单选框日，提示用户不可选
	if( radios[1].checked==true )
	{
		alert("当前还款方式下按日借出期限不可选");
		radios[0].checked = 'checked';		
	}
	
	//限制借出期限大于0
	if(deadline0*1==0){
		alert("期限必须大于0");
		deadline0=1;
		deadline.value=1;
	}
	
	//如果借出期限选择单选框月
	if( radios[0].checked==true )
	{
		//用二位数组存储每一期相关数据最后输出
		//给二位数组初始化
		var arr=new Array();
		for(var k=0;k<deadline0*1;k++){    
			arr[k]=new Array();  
			for(var j=0;j<6;j++){
				arr[k][j]="";
			} 
		} 
		
		//赋值每期的期号
		for(var k=0;k<deadline0*1;k++){ 
			arr[k][0]=(k+1)+"/"+deadline0*1;
		}
		
		//赋值每期的本金
		for(var k=0;k<deadline0*1-1;k++){ 
			arr[k][2]=0;
		}
		arr[deadline0*1-1][2]=capital0;
	
		//获取利率的值
		var rate1=rate.value;
		//初始化利息
		var interest=0;
		//利率按年利率
		if( radios2[0].checked==true)
			interest=capital0*rate1/(12*100);
	
		else {
			var Day=getDayOfYear(a[0]);
			interest=capital0*rate1*Day/(12*100);
	
		}
		daishou=capital0*1+interest*1;
		
		//赋值每期的利息
		for(var k=0;k<deadline0*1;k++){ 
			arr[k][3]=interest*1;
		}
		
		//赋值每期的代收
		for(var k=0;k<deadline0*1-1;k++){ 
			arr[k][1]=interest*1;
		}
		arr[deadline0*1-1][1]=daishou;
		
		//赋值每期的借出日期
		for(var k=0;k<deadline0*1;k++){ 
			arr[k][4]=date3;
		}
		
		//赋值每期的还款日期
		for(var k=0;k<deadline0*1;k++){ 
			a[1]++;
			if(a[1]>12){
				a[0]=a[0]*1+1;
				a[1]=a[1]*1-12;				
			}
			date2=a[0]+"-"+a[1]*1+"-"+a[2]*1;
			arr[k][5]=date2;
		}
		
		println(arr,deadline0);
		
	}
	
	
}

//输出
function println(arr,deadline0){
	var str="<tr><td class='table_sty1'>期号</td><td class='table_sty1'>代收(含本金)</td><td class='table_sty1'>本金</td><td class='table_sty1'>利息</td><td class='table_sty1'>借出日期</td><td class='table_sty1'>还款日期</td></tr>"
	var st=new Array();
	for(var k=0;k<deadline0*1;k++){ 
		st[k]="<tr><td class='table_sty2'>"+arr[k][0]+"</td><td class='table_sty2'>"+arr[k][1].toFixed(2)+"</td class='table_sty2'><td class='table_sty2'>"+arr[k][2]+"</td><td class='table_sty2'>"+arr[k][3].toFixed(2)+"</td><td class='table_sty2'>"+arr[k][4]+"</td><td class='table_sty2'>"+arr[k][5]+"</td></tr>";
	}
	document.getElementById('tables').innerHTML=str;
	for(var k=0;k<deadline0*1;k++)
	document.getElementById('tables').innerHTML+=st[k];
}

//到期还本息
function method2(){
	//用二位数组存储每一期相关数据最后输出
	//给二位数组初始化
	var arr=new Array();
	for(var k=0;k<1;k++){    
		arr[k]=new Array();  
		for(var j=0;j<6;j++){
			arr[k][j]="";
		} 
	} 
	//借出日期数组a
	var a = date1.value.split("-");
	arr[0][4]=a[0]+"-"+a[1]*1+"-"+a[2]/1;
	//初始化还款日期date2
	var date2="";
	arr[0][0]=1;
	//借出本金capital
	capital0=capital.value;
	arr[0][2]=capital0;
	//借出期限
	var deadline0=deadline.value;
	//给radios赋值单选按钮组月日
	var radios=document.getElementsByName('RadioGroup1')
	//给radios2赋值单选按钮组年利率日利率
	var radios2=document.getElementsByName('RadioGroup2')
	//如果借出期限选择单选框月
	if( radios[0].checked==true )
	{
		//利率rate
		var rate1=rate.value;
		var interest=0;
		//利率按年利率
		if( radios2[0].checked==true)
			interest=capital0*rate1*deadline0/(12*100);
	
		else {
			//获得当年的天数Day
			var Day=getDayOfYear(a[0]);
			var	month=a[1];
			var mday=0;
			//计算借款几个月的总天数
			for(var k=0;k<deadline0*1;k++){ 
				mday=mday+getDayOfMonth(month,Day);
				month++;
			}
			interest=capital0*rate1*mday/(100);
		}
		
		arr[0][1]=capital0*1+interest*1;
		arr[0][3]=interest;
		//还款日期
		a[1]=a[1]*1+deadline0*1;
		while(a[1]>12){
			a[0]=a[0]*1+1;
			a[1]=a[1]*1-12;				
		}
		arr[0][5]=a[0]+"-"+a[1]*1+"-"+a[2]*1;
	}
	//借出期限选择日
	if ( radios[1].checked==true ){
	
		var rate1=rate.value;
		var interest=0;
		var years=a[0];
		var Day=getDayOfYear(a[0]);
				
		if (radios2[0].checked==true)
			interest=capital0*rate1*deadline0/(Day*100);
	
		else 
			interest=capital0*rate1*deadline0/100;
			
		arr[0][1]=capital0*1+interest*1;
		arr[0][3]=interest;
		//还款日期
		a[2]=a[2]*1+deadline0*1;
		while((a[1]==4||a[1]==6||a[1]==9||a[1]==11)&&a[2]>30||a[1]==2&&a[2]>29&&Day==366||a[1]==2&&a[2]>28&&Day==365||(a[1]==1||a[1]==3||a[1]==5||a[1]==7||a[1]==8||a[1]==10||a[1]==12)&&a[2]>31){
			if((a[1]==4||a[1]==6||a[1]==9||a[1]==11)&&a[2]>30){
				a[1]=a[1]*1+1;
				a[2]=a[2]*1-30;
			}
			if(a[1]==2){
				if(a[2]>29&&Day==366){
					a[1]=a[1]*1+1;
					a[2]=a[2]*1-29;
				}
				if(a[2]>28&&Day==365){
					a[1]=a[1]*1+1;
					a[2]=a[2]*1-28;
				}
			}
			if((a[1]==1||a[1]==3||a[1]==5||a[1]==7||a[1]==8||a[1]==10||a[1]==12)&&a[2]>31){
				a[1]=a[1]*1+1;
				a[2]=a[2]*1-31;
				if(a[1]>12){
					a[0]=a[0]*1+1;
					a[1]=a[1]*1-12;
				}
			}
		}
		arr[0][5]=a[0]+"-"+a[1]*1+"-"+a[2]*1;
	}

	println(arr,1);
	
	
}

//按日息算，每月付息，到期还本
function method3(){
	//借出日期数组a
	var a = date1.value.split("-");
	var date3=a[0]+"-"+a[1]*1+"-"+a[2]/1;
	//初始化还款日期date2
	var date2="";
	//借出本金capital
	var capital0=capital.value;
	//借出期限
	var deadline0=deadline.value;
	//给radios赋值单选按钮组月日
	var radios=document.getElementsByName('RadioGroup1')
	//给radios2赋值单选按钮组年利率日利率
	var radios2=document.getElementsByName('RadioGroup2')
	
	//如果借出期限选择单选框日，提示用户不可选
	if( radios[1].checked==true )
	{
		alert("当前还款方式下按日借出期限不可选");
		radios[0].checked = 'checked';		
	}
	
	//限制借出期限大于0
	if(deadline0*1==0){
		alert("期限必须大于0");
		deadline0=1;
		deadline.value=1;
	}
	
	//如果借出期限选择单选框月
	if( radios[0].checked==true )
	{
		//用二位数组存储每一期相关数据最后输出
		//给二位数组初始化
		var arr=new Array();
		for(var k=0;k<deadline0*1;k++){    
			arr[k]=new Array();  
			for(var j=0;j<6;j++){
				arr[k][j]="";
			} 
		} 
		
		//赋值每期的期号
		for(var k=0;k<deadline0*1;k++){ 
			arr[k][0]=(k+1)+"/"+deadline0*1;
		}
		
		//赋值每期的本金
		for(var k=0;k<deadline0*1-1;k++){ 
			arr[k][2]=0;
		}
		arr[deadline0*1-1][2]=capital0;
	
		//获取利率的值
		var rate1=rate.value;
		//初始化利息
		var interest=0;
		var Day=getDayOfYear(a[0]);
		var month=a[1];
		//利率按年利率
		if( radios2[0].checked==true){
			//赋值每期的利息
			for(var k=0;k<deadline0*1;k++){ 
				var mday=getDayOfMonth(month,Day);
				arr[k][3]=capital0*rate1*mday/(Day*100);
				month++;
				if(month>12)	
					month=month-12;
			}
		}
		else {
			for(var k=0;k<deadline0*1;k++){ 
				var mday=getDayOfMonth(month,Day);
				arr[k][3]=capital0*rate1*mday/(100);
				month++;
				if(month>12)	
					month=month-12;
			}
		}
		daishou=capital0*1+arr[deadline0*1-1][3]*1;
		
		//赋值每期的代收
		for(var k=0;k<deadline0*1-1;k++){ 
			arr[k][1]=arr[k][3];
		}
		arr[deadline0*1-1][1]=daishou;
		
		//赋值每期的借出日期
		for(var k=0;k<deadline0*1;k++){ 
			arr[k][4]=date3;
		}
		
		//赋值每期的还款日期
		for(var k=0;k<deadline0*1;k++){ 
			a[1]++;
			if(a[1]>12){
				a[0]=a[0]*1+1;
				a[1]=a[1]*1-12;				
			}
			date2=a[0]+"-"+a[1]*1+"-"+a[2]*1;
			arr[k][5]=date2;
		}
		
		println(arr,deadline0);
		
	}
}

//标满付息，到期还本
function method4(){
	//用二位数组存储每一期相关数据最后输出
	//给二位数组初始化
	var arr=new Array();
	for(var k=0;k<2;k++){    
		arr[k]=new Array();  
		for(var j=0;j<6;j++){
			arr[k][j]="";
		} 
	} 
	//借出日期数组a
	var a = date1.value.split("-");
	arr[0][4]=a[0]+"-"+a[1]*1+"-"+a[2]/1;
	arr[0][5]=arr[0][4];
	arr[1][4]=arr[0][4];
	//初始化还款日期date2
	var date2="";
	arr[0][0]=1+"/"+2;
	arr[1][0]=2+"/"+2;
	//借出本金capital
	capital0=capital.value;
	arr[0][2]=0;
	arr[1][2]=capital0;
	//借出期限
	var deadline0=deadline.value;
	//给radios赋值单选按钮组月日
	var radios=document.getElementsByName('RadioGroup1')
	//给radios2赋值单选按钮组年利率日利率
	var radios2=document.getElementsByName('RadioGroup2')
	//如果借出期限选择单选框月
	if( radios[0].checked==true )
	{
		//利率rate
		var rate1=rate.value;
		var interest=0;
		//利率按年利率
		if( radios2[0].checked==true)
			interest=capital0*rate1*deadline0/(12*100);
	
		else {
			//获得当年的天数Day
			var Day=getDayOfYear(a[0]);
			var	month=a[1];
			var mday=0;
			//计算借款几个月的总天数
			for(var k=0;k<deadline0*1;k++){ 
				mday=mday+getDayOfMonth(month,Day);
				month++;
			}
			interest=capital0*rate1*mday/(100);
		}
		arr[1][1]=capital0*1;
		arr[0][3]=interest*1;
		arr[0][1]=arr[0][3];
		arr[1][3]=0;
		//还款日期
		a[1]=a[1]*1+deadline0*1;
		while(a[1]>12){
			a[0]=a[0]*1+1;
			a[1]=a[1]*1-12;				
		}
		arr[1][5]=a[0]+"-"+a[1]*1+"-"+a[2]*1;
	}
	//借出期限选择日
	if ( radios[1].checked==true ){
	
		var rate1=rate.value;
		var interest=0;
		var years=a[0];
		var Day=getDayOfYear(a[0]);
				
		if (radios2[0].checked==true)
			interest=capital0*rate1*deadline0/(Day*100);
	
		else 
			interest=capital0*rate1*deadline0/100;
			
		arr[0][3]=interest;
		arr[0][1]=arr[0][3];
		arr[1][1]=capital0*1;
		arr[1][3]=0;
		//还款日期
		a[2]=a[2]*1+deadline0*1;
		while((a[1]==4||a[1]==6||a[1]==9||a[1]==11)&&a[2]>30||a[1]==2&&a[2]>29&&Day==366||a[1]==2&&a[2]>28&&Day==365||(a[1]==1||a[1]==3||a[1]==5||a[1]==7||a[1]==8||a[1]==10||a[1]==12)&&a[2]>31){
			if((a[1]==4||a[1]==6||a[1]==9||a[1]==11)&&a[2]>30){
				a[1]=a[1]*1+1;
				a[2]=a[2]*1-30;
			}
			if(a[1]==2){
				if(a[2]>29&&Day==366){
					a[1]=a[1]*1+1;
					a[2]=a[2]*1-29;
				}
				if(a[2]>28&&Day==365){
					a[1]=a[1]*1+1;
					a[2]=a[2]*1-28;
				}
			}
			if((a[1]==1||a[1]==3||a[1]==5||a[1]==7||a[1]==8||a[1]==10||a[1]==12)&&a[2]>31){
				a[1]=a[1]*1+1;
				a[2]=a[2]*1-31;
				if(a[1]>12){
					a[0]=a[0]*1+1;
					a[1]=a[1]*1-12;
				}
			}
		}
		arr[1][5]=a[0]+"-"+a[1]*1+"-"+a[2]*1;
	}

	println(arr,2);
}