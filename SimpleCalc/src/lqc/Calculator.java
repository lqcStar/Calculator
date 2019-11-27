package lqc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

public class Calculator extends JFrame implements ActionListener{
	JButton btn;   
	JPanel jp;
	JMenuBar menubar;
	JTextArea text;
	Integer i;
	String operator = "=";  // 运算符，初始化为“=”
	String[] b = {"%","sqrt","x^2","1/x","0","/","C","*","7","8","9","+","4","5","6","-","1","2","3","=",".","刘","全","才"};
	boolean ifNum = true;  //判断是否为数字
	double resultNum;   
	boolean operateValidFlag = true;
	
	public Calculator() {
		super("计算器");
		this.setBounds(200,200,300,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//文本区域
		text = new JTextArea(5,100);
		this.add(text,"North");
		
		//添加按钮
		jp = new JPanel(new GridLayout(6,4));
		for(i = 0; i < b.length; i++) {
			btn = new JButton(b[i]);
			btn.addActionListener(this);
			jp.add(btn);
		}
		
		//菜单栏
		menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		JMenu menu_file=new JMenu("文件");
		JMenu menu_help=new JMenu("帮助");
		menubar.add(menu_file);	
		menubar.add(menu_help);
		// 添加菜单项
		JMenuItem mi1=new JMenuItem("打开");
		JMenuItem mi2=new JMenuItem("保存");
		JMenuItem mi3=new JMenuItem("关闭");
		JCheckBoxMenuItem mi4=new JCheckBoxMenuItem("工具1");
		JCheckBoxMenuItem mi5=new JCheckBoxMenuItem("工具2");
		JRadioButtonMenuItem mi6=new JRadioButtonMenuItem("格式1");
		JRadioButtonMenuItem mi7=new JRadioButtonMenuItem("格式2");
			ButtonGroup bg=new ButtonGroup();
			bg.add(mi6);
			bg.add(mi7);
			menu_file.add(mi1);
			menu_file.add(mi2);
			menu_file.add(mi3);
			menu_file.addSeparator();
			menu_file.add(mi4);
			menu_file.add(mi5);
			menu_file.addSeparator();
			menu_file.add(mi6);
			menu_file.add(mi7);	

		
		this.add(jp);
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		new Calculator();

	}
	//实现按钮
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String label = e.getActionCommand();
		if(label.equals("C")) 
			handleC();
		else if("0123456789.".indexOf(label) >= 0)
			handleNum(label);
		else if(label.equals(b[21])) 
			text.setText("欢迎使用");
		else if(label.equals(b[22])) 
			text.setText("欢迎使用");
		else if(label.equals(b[23])) 
			text.setText("欢迎使用");
		else
			handleOperator(label);
		
	}
	
	// 清零方法
	public void handleC() {
		ifNum = true;
		text.setText("0");
		operator = "=";
		
	}
	//获取输入数字
	public void handleNum(String str) {
//		System.out.println("ifNum " + ifNum);
		if(ifNum)
			text.setText(str);
		else if(str.equals(".") && text.getText().indexOf(".") < 0) {
//			System.out.println(text.getText());
			text.setText(text.getText()+".");
		}
		else if(!str.equals("."))
			text.setText(text.getText()+str);
		
		ifNum = false;
		
	}
	

	public void handleOperator(String label) {
		// TODO Auto-generated method stub
		 if (operator.equals("/")) 
		 {
		 //判断除数是否为0
			 if (getNumberFromText() == 0.0) 
			 {
				 operateValidFlag = false;
				 text.setText("除数不能为零");
			 } 
			 else 
			 {
				 resultNum /= getNumberFromText();
			 }
		 }
		 else if (operator.equals("+")) 
		 {
			 
			 resultNum += getNumberFromText();
		 } 
		 else if (operator.equals("-")) 
		 {
			 
			 resultNum -= getNumberFromText();
		 } 
		 else if (operator.equals("*"))
		 {
			 
			 resultNum *= getNumberFromText();
		 } 
		 else if (operator.equals("=")) 
		 {
			 
			 resultNum = getNumberFromText();
		 }
		 else if(operator.equals("sqrt"))
		 {
			 resultNum = Math.sqrt(resultNum);
		 }
		 else if(operator.equals("x^2")) {
			 resultNum = resultNum * resultNum;
		 }
		 else if(operator.equals("1/x"))
		 {
			 resultNum = 1 / resultNum;
		 }
		 else if(operator.equals("%")) {
			 resultNum = resultNum % getNumberFromText();
		 }
		 
		 
		 if (operateValidFlag) 
		 {
			 long t1;
			 double t2;
			 t1 = (long) resultNum;
			 t2 = resultNum - t1;
			 if (t2 == 0) 
			 {
				 text.setText(String.valueOf(t1));
				 } 
			 else 
			 {
				 text.setText(String.valueOf(resultNum));
			 }
		 }
		 operator = label;
		 ifNum = true;
	}
	
	
	//从文本区域中提取数字
	public double getNumberFromText() {
		double num = 0;
		try{
			num = Double.valueOf(text.getText());
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return num;
	}
	
	
}

