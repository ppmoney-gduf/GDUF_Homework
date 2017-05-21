import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class sjsjsjsjsj extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	double money,time,rate,fee;
	String income;
	private JTextField textField_5;
	suanfa r=new suanfa();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sjsjsjsjsj frame = new sjsjsjsjsj();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public sjsjsjsjsj() {
		setTitle("\u7F51\u8D37\u8BA1\u7B97\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u501F\u51FA\u91D1\u989D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(21, 10, 81, 28);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(94, 14, 130, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5143");
		label_1.setBounds(234, 17, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u501F\u51FA\u65F6\u957F\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(21, 48, 81, 15);
		contentPane.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 45, 130, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		ButtonGroup bg= new ButtonGroup();
		
		JLabel label_3 = new JLabel("\u5229\u7387    \uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(21, 80, 81, 15);
		contentPane.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 77, 130, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_4 = new JLabel("%");
		label_4.setBounds(234, 80, 54, 15);
		contentPane.add(label_4);
		
		ButtonGroup bg1= new ButtonGroup();
		
		JLabel label_5 = new JLabel("\u7BA1\u7406\u8D39  \uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(21, 114, 81, 15);
		contentPane.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(94, 111, 130, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_6 = new JLabel("%");
		label_6.setBounds(234, 114, 54, 15);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u8FD8\u6B3E\u65B9\u5F0F\uFF1A");
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(21, 152, 81, 15);
		contentPane.add(label_7);
		
		
		
		String str[]={"每月还息，到期还本","等额本息","等额本金","到期还本息"};
		final JComboBox comboBox = new JComboBox(str);
		comboBox.setBounds(94, 149, 228, 21);
		comboBox.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
            	if(e.getSource()==comboBox){
					
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null); 
					textField_5.setText(null);
            	}
            }  
        });  
		contentPane.add(comboBox);
		
		JButton button = new JButton("\u8BA1\u7B97");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				money=Double.parseDouble(textField.getText());
				time =Double.parseDouble(textField_1.getText());
				rate =Double.parseDouble(textField_2.getText());
				fee  =Double.parseDouble(textField_3.getText());
				if(money<=0 ||time<=0 ||rate<=0 ||fee<=0)
				{
					income ="0";
					
					JOptionPane.showMessageDialog(null,"请输入正数！" , "输入错误", JOptionPane.ERROR_MESSAGE);
				}
				String str = (String) comboBox.getSelectedItem();
				String str1 = new String("每月还息，到期还本");
				String str2 = new String("等额本息");
				String str3 = new String("等额本金");
				String str4 = new String("到期还本息");
				boolean boo = str1.equals(str);
				boolean boo1 = str2.equals(str);
				boolean boo2= str3.equals(str);
				boolean boo3=str4.equals(str);
				if(boo)
				{
					r.fulijisuan(textField, textField_1, textField_2, textField_3, textField_5);
				}
				if(boo1)
				{
					r.dengebenxi(textField, textField_1, textField_2, textField_3, textField_5);
				}if(boo2)
				{
					r.dengebenjin(textField, textField_1, textField_2, textField_3, textField_5);
				}if(boo3)
				{
					r.benxi(textField, textField_1, textField_2, textField_3, textField_5);
				}
			}
		});
		button.setBounds(94, 222, 81, 23);
		contentPane.add(button);
		
		JButton button1 = new JButton("\u91CD\u7F6E");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_5.setText(null);
			}
		});
		button1.setBounds(215, 222, 93, 23);
		contentPane.add(button1);
		
		JLabel label_8 = new JLabel("\u6536\u76CA    \uFF1A");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(21, 189, 81, 15);
		contentPane.add(label_8);
		
		textField_5 = new JTextField();
		textField_5.setBounds(94, 189, 130, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setEditable(false);
		JLabel label_9 = new JLabel("\u6708");
		label_9.setBounds(234, 48, 54, 15);
		contentPane.add(label_9);
		
		
		
	}

		
	
}
