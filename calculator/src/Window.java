import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Window extends JFrame implements ActionListener
{
	JTextField text;
	JButton []button=new JButton[5];
	JButton []button1=new JButton[5];
	JButton []button2=new JButton[5];
	JButton []button3=new JButton[5];
	JButton []button4=new JButton[3];
	JButton bu1,bu2;
	JTextArea textArea;
	JPanel panel1,panel2;
	
	//字符串数组定义
	String name[]={"1","2","3","+","C"};
	String name1[]={"4","5","6","-","退格"};
	String name2[]={"7","8","9","*","%"};
	String name3[]={"0","+/-",".","/","="};
	String name4[]={"二进制","八进制","十六进制"};
	
	Window()
	{
		init();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1200, 200,550,800);
		setVisible(true);
	}
	void init()
	{
		text=new JTextField(20);
		text.setEditable(false);
		text.setFont(new Font("宋体",Font.BOLD,30));
		textArea=new JTextArea(14,30);
		textArea.setFont(new Font("宋体",Font.BOLD,30));
		textArea.setEditable(false);
		textArea.setEditable(false);
		panel1=new JPanel();
		panel1.setLayout(new GridLayout(5,5));
		add(text);
		for(int i=0;i<5;i++)
		{
			button[i]=new JButton(name[i]);			//为每个按钮实例化
			button[i].addActionListener(this);     //为按钮数组的每个按钮注册监听器
			panel1.add( button[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button1[i]=new JButton(name1[i]);
			button1[i].addActionListener(this);		//为按钮数组的每个按钮注册监听器
			panel1.add( button1[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button2[i]=new JButton(name2[i]);
			button2[i].addActionListener(this);		//为按钮数组的每个按钮注册监听器
			panel1.add( button2[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button3[i]=new JButton(name3[i]);
			button3[i].addActionListener(this);		//为按钮数组的每个按钮注册监听器
			panel1.add( button3[i]);		
		}
		for(int i=0;i<3;i++)
		{
			button4[i]=new JButton(name4[i]);
			button4[i].addActionListener(this);		//为按钮数组的每个按钮注册监听器
			panel1.add( button4[i]);		
		}
		panel2=new JPanel();
		bu1=new JButton("保存");
		bu2=new JButton("清除");
		panel2.add(textArea);
		add(panel1);
		add(panel2);
		add(bu1);
		add(bu2);
		text.addActionListener(this);				//注册监听器
		bu1.addActionListener(this);
		bu2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//判断触发"="按钮时文本框 内容
		if(e.getSource()==button3[4])
		{
			String str=text.getText();
			if(str.isEmpty())		
				text.setText(""+"错误");
		}
		
		//把数字按钮上的字符复制到文半框上
		if(e.getSource()==button[0]||e.getSource()==button[1]
			||e.getSource()==button[2]||e.getSource()==button1[0]
			||e.getSource()==button1[1]||e.getSource()==button1[2]
			||e.getSource()==button2[0]||e.getSource()==button2[1]
			||e.getSource()==button2[2]||e.getSource()==button3[0]	)
			text.setText(text.getText()+e.getActionCommand());
		
		//把按钮上的字符复制到文半框上
		if(e.getSource()==button[3]||e.getSource()==button1[3]
		   ||e.getSource()==button2[3]||e.getSource()==button2[4]
		   ||e.getSource()==button3[3]||e.getSource()==button3[2])
		{
			String str=text.getText();
			
			//判断文本框的最后一个字符是否为运算符号
			if(str.endsWith("+")||str.endsWith("/")||str.endsWith("*")
				||str.endsWith("-")||str.endsWith("%")||str.endsWith("."))
					text.setText(""+str.substring(0, str.length()-1)+e.getActionCommand());
			else
				text.setText(text.getText()+e.getActionCommand());
		}
		
		//清除文本框内容
		if(e.getSource()==button[4])
			text.setText(null);
		
		//清除文本框最后一个字符
		if(e.getSource()==button1[4])
		{
			String str=text.getText();
			text.setText(""+str.substring(0, str.length()-1));
		}
		
		//为最后一个数进行变号（正数变负数，负数变正数）
		if(e.getSource()==button3[1])
		{
			String str=text.getText();
				Scanner scanner=new Scanner(text.getText());
				scanner.useDelimiter("[^0123456789.]+");
				if(str.endsWith(")"))
				{
					while(scanner.hasNext())
					{
						try
						{
							String str1=scanner.next();				  //获取最后一个数的字符串
							double number=Double.parseDouble(str1);
							String ss=str.substring(str.indexOf("("),str.indexOf(")"));		//获取最后"("到")"之间的字符串
							text.setText(""+str.substring(0, str.length()-ss.length()-1)+number);
						}
						catch(Exception ee)
						{
							
						}
					}
				}
			else
			{
				while(scanner.hasNext())
				{
					try
					{
						String str1=scanner.next();
						double number=Double.parseDouble(str1);
						number=-number;
						text.setText(""+str.substring(0, str.length()-str1.length())+"("+number+")");
					}
					catch(Exception ee)
					{
						
					}
				}
			}
		}
		
		//对文本框内的两个数进行运算	
		if(e.getSource()==button3[4])
		{
			String str=text.getText();
			try
			{
				
				  String ss=str.replace("(", "!");
				  ss=ss.replace(")", "!");
					 Scanner scanner=new Scanner(ss);
				scanner.useDelimiter("[^0123456789.]+");
				while(scanner.hasNext())
				{
					double results=0;
					double number1=scanner.nextDouble();
					double number2=scanner.nextDouble();
					if(ss.contains("*"))
					{
						results=number1*(number2);
						if(ss.endsWith("!"))
							results=-results;
						if(ss.startsWith("!")||ss.startsWith("-"))
							results=-results;
					}
					else if(str.contains("/"))
					{
						results=number1/number2;
						if(str.endsWith(")"))
							results=-results;
						if(ss.startsWith("!")||ss.startsWith("-"))
							results=-results;
					}
					else if(str.contains("+")&&str.endsWith(")"))
					{
						results=number1-number2;
						if(ss.startsWith("!")||ss.startsWith("-"))
							results=-results;
					}
						
					else if(str.contains("-")&&str.endsWith(")"))
						results=number1+number2;
					else if(str.contains("+"))
					{
						if(ss.startsWith("!")||ss.startsWith("-"))
							results=-number1+number2;
						else
							results=number1+number2;
					}
					else if(str.contains("-"))
					{
						if(ss.startsWith("!")||ss.startsWith("-"))
							results=-(number1+number2);
						else
							results=number1-number2;
					}
					else if(str.contains("%"))
						results=number1%number2; 
					textArea.append(str);
					text.setText(null);
					text.setText(""+results);
					textArea.append("="+results+"\n");		
			}
			}
			catch(Exception ee)
			{
				text.setText(str);
			}
		}
		
		//二进制运算
		if(e.getSource()==button4[0])
		{
			try
			{
				String str=text.getText();
				int m=Integer.parseInt(str);
				String str1=Long.toString(m, 2);
				textArea.append(str);
				text.setText(null);
				text.setText(str1);
				textArea.append("的二进制为："+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("错误");
			}
		}
		
		//八进制运算
		if(e.getSource()==button4[1])
		{
			try
			{
				String str=text.getText();
				int m=Integer.parseInt(str);
				String str1=Long.toString(m, 8);
				textArea.append(str);
				text.setText(null);
				text.setText(str1);
				textArea.append("的八进制为："+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("错误");
			}
		}
		
		//十六进制运算
		if(e.getSource()==button4[2])
		{
			try
			{
				String str=text.getText();
				int m=Integer.parseInt(str);
				String str1=Long.toString(m, 16);
				textArea.append(str);
				text.setText(null);
				text.setText(str1);
				textArea.append("的十六进制为："+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("错误");
			}
		}

		//将文本框的内容写到一个记事本
		if(e.getSource()==bu1)
		{
			String str=textArea.getText();
			String str1[]=str.split("\\n"); //将str的内容分为元素，放到数组内。
			File file2=new File("my.txt");
			try
			{
				FileWriter out=new FileWriter(file2,true);
				BufferedWriter writer=new BufferedWriter(out);
				
				//获取本地时间
				Calendar calendar=Calendar.getInstance();
				int year=calendar.get(Calendar.YEAR),
						month=calendar.get(Calendar.MONTH)+1,
						day=calendar.get(Calendar.DAY_OF_MONTH);
				int hour=calendar.get(Calendar.HOUR_OF_DAY),		
				 miute=calendar.get(Calendar.MINUTE),
				 second=calendar.get(Calendar.SECOND);
				//把时间转化为字符串
				String str11=String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日"
							+"   "+String.valueOf(hour)+":"+String.valueOf(miute)+":"+String.valueOf(miute);
				for(String s:str1)
				{
					writer.append(s);
					writer.newLine();
				}
				writer.append("      保存时间： "+str11);
				writer.newLine();
				writer.close();
				JOptionPane.showMessageDialog(this, "保存成功","提示",JOptionPane.PLAIN_MESSAGE);
			}
			catch(Exception ee)
			{
			}
		}
		
		//清除文本区内容
		if(e.getSource()==bu2)
		{
			textArea.setText("");
		}
	}
}
	
