import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class suanfa {
	
	double money,time,rate,fee,income;
	//ÿ�»�Ϣ�����ڻ���
	public void fulijisuan(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//����Ϣ=����x������
						double month=money*(rate/12);
						double income=month*time-money*fee/12+money;
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"������������" , "�������", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						for(int i=1;i<=time;i++){
							System.out.println("��"+i+"����Ӧ�ı�ϢΪ"+month);
						}
					
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"����ȷ���룡" , "�������", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//�ȶϢ����
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
							JOptionPane.showMessageDialog(null,"������������" , "�������", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						for(int i=1;i<=time;i++){
							System.out.println("��"+i+"����Ӧ�ı�ϢΪ"+month);
						}
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"����ȷ���룡" , "�������", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//�ȶ��
	public void dengebenjin(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//ÿ�»�����=(�����/��������)+(����-�ѹ黹�����ۼƶ�)��ÿ������
						for(int i=1;i<=time;i++){
							double sum=0;
							double a=money/time;
							double b=a*(i-1);
							sum=sum+b;
							double month;
							month=(money/time)+(money-sum)*rate/12;
						    double income=0;
						    income=income+money+(money-sum)*rate/12;
							
							
							System.out.println("��"+i+"����Ӧ�ı�ϢΪ"+month);
						
						
						
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"������������" , "�������", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						}
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"����ȷ���룡" , "�������", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	//���ڻ���Ϣ
	public void benxi(JTextField textField,JTextField textField_1,
			JTextField textField_2,	JTextField textField_3,JTextField textField_5) {
		   
					try {
						
						money = Double.parseDouble(textField.getText());
						time= Double.parseDouble(textField_1.getText());
						rate = Double.parseDouble(textField_2.getText());
						fee  =Double.parseDouble(textField_3.getText());
						//����ӱ�Ϣ=������ϱ���x������-����ѣ�
					
						double income=money*(rate/100/12)*(1-fee/100)+money;
						if(money<=0||rate<=0||time<=0){
							income = 0;
							JOptionPane.showMessageDialog(null,"������������" , "�������", JOptionPane.ERROR_MESSAGE);
						}
						
						String s = String.valueOf(income); 
						textField_5.setText(s);
						
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,"����ȷ���룡" , "�������", JOptionPane.ERROR_MESSAGE); 
					}
					
					
					

				}
	

	

}
