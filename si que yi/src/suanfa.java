import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class suanfa {
	
	double money,time,rate,fee,income;
	//每月还息，到期还本
	public void fulijisuan(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//月利息=本金x月利率
						double month=money*(rate/12);
						double income=month*time-money*fee/12+money;
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"请输入正数！" , "输入错误", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						for(int i=1;i<=time;i++){
							System.out.println("第"+i+"个月应的本息为"+month);
						}
					
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"请正确输入！" , "输入错误", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//等额本息计算
	public void dengebenxi(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						double month = money/time+money*(rate/12);
						double income=month*time-money*(fee/12)*time;
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"请输入正数！" , "输入错误", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						for(int i=1;i<=time;i++){
							System.out.println("第"+i+"个月应的本息为"+month);
						}
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"请正确输入！" , "输入错误", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//等额本金
	public void dengebenjin(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//每月还款金额=(贷款本金/还款月数)+(本金-已归还本金累计额)×每月利率
						for(int i=1;i<=time;i++){
							double sum=0;
							double a=money/time;
							double b=a*(i-1);
							sum=sum+b;
							double month;
							month=(money/time)+(money-sum)*rate/12;
						    double income=0;
						    income=income+money+(money-sum)*rate/12;
							
							
							System.out.println("第"+i+"个月应的本息为"+month);
						
						
						
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"请输入正数！" , "输入错误", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						}
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"请正确输入！" , "输入错误", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//到期还本息
	public void benxi(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//本金加本息=本金加上本金x（利率-管理费）
					
						double income=money*(rate/100/12)*(1-fee/100)+money;
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"请输入正数！" , "输入错误", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"请正确输入！" , "输入错误", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	

	

}
