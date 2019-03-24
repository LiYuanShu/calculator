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
	
	//�ַ������鶨��
	String name[]={"1","2","3","+","C"};
	String name1[]={"4","5","6","-","�˸�"};
	String name2[]={"7","8","9","*","%"};
	String name3[]={"0","+/-",".","/","="};
	String name4[]={"������","�˽���","ʮ������"};
	
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
		text.setFont(new Font("����",Font.BOLD,30));
		textArea=new JTextArea(14,30);
		textArea.setFont(new Font("����",Font.BOLD,30));
		textArea.setEditable(false);
		textArea.setEditable(false);
		panel1=new JPanel();
		panel1.setLayout(new GridLayout(5,5));
		add(text);
		for(int i=0;i<5;i++)
		{
			button[i]=new JButton(name[i]);			//Ϊÿ����ťʵ����
			button[i].addActionListener(this);     //Ϊ��ť�����ÿ����ťע�������
			panel1.add( button[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button1[i]=new JButton(name1[i]);
			button1[i].addActionListener(this);		//Ϊ��ť�����ÿ����ťע�������
			panel1.add( button1[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button2[i]=new JButton(name2[i]);
			button2[i].addActionListener(this);		//Ϊ��ť�����ÿ����ťע�������
			panel1.add( button2[i]);		
		}
		for(int i=0;i<5;i++)
		{
			button3[i]=new JButton(name3[i]);
			button3[i].addActionListener(this);		//Ϊ��ť�����ÿ����ťע�������
			panel1.add( button3[i]);		
		}
		for(int i=0;i<3;i++)
		{
			button4[i]=new JButton(name4[i]);
			button4[i].addActionListener(this);		//Ϊ��ť�����ÿ����ťע�������
			panel1.add( button4[i]);		
		}
		panel2=new JPanel();
		bu1=new JButton("����");
		bu2=new JButton("���");
		panel2.add(textArea);
		add(panel1);
		add(panel2);
		add(bu1);
		add(bu2);
		text.addActionListener(this);				//ע�������
		bu1.addActionListener(this);
		bu2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//�жϴ���"="��ťʱ�ı��� ����
		if(e.getSource()==button3[4])
		{
			String str=text.getText();
			if(str.isEmpty())		
				text.setText(""+"����");
		}
		
		//�����ְ�ť�ϵ��ַ����Ƶ��İ����
		if(e.getSource()==button[0]||e.getSource()==button[1]
			||e.getSource()==button[2]||e.getSource()==button1[0]
			||e.getSource()==button1[1]||e.getSource()==button1[2]
			||e.getSource()==button2[0]||e.getSource()==button2[1]
			||e.getSource()==button2[2]||e.getSource()==button3[0]	)
			text.setText(text.getText()+e.getActionCommand());
		
		//�Ѱ�ť�ϵ��ַ����Ƶ��İ����
		if(e.getSource()==button[3]||e.getSource()==button1[3]
		   ||e.getSource()==button2[3]||e.getSource()==button2[4]
		   ||e.getSource()==button3[3]||e.getSource()==button3[2])
		{
			String str=text.getText();
			
			//�ж��ı�������һ���ַ��Ƿ�Ϊ�������
			if(str.endsWith("+")||str.endsWith("/")||str.endsWith("*")
				||str.endsWith("-")||str.endsWith("%")||str.endsWith("."))
					text.setText(""+str.substring(0, str.length()-1)+e.getActionCommand());
			else
				text.setText(text.getText()+e.getActionCommand());
		}
		
		//����ı�������
		if(e.getSource()==button[4])
			text.setText(null);
		
		//����ı������һ���ַ�
		if(e.getSource()==button1[4])
		{
			String str=text.getText();
			text.setText(""+str.substring(0, str.length()-1));
		}
		
		//Ϊ���һ�������б�ţ������为����������������
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
							String str1=scanner.next();				  //��ȡ���һ�������ַ���
							double number=Double.parseDouble(str1);
							String ss=str.substring(str.indexOf("("),str.indexOf(")"));		//��ȡ���"("��")"֮����ַ���
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
		
		//���ı����ڵ���������������	
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
		
		//����������
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
				textArea.append("�Ķ�����Ϊ��"+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("����");
			}
		}
		
		//�˽�������
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
				textArea.append("�İ˽���Ϊ��"+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("����");
			}
		}
		
		//ʮ����������
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
				textArea.append("��ʮ������Ϊ��"+str1+"\n");
			}
			catch(Exception ee)
			{
				text.setText("����");
			}
		}

		//���ı��������д��һ�����±�
		if(e.getSource()==bu1)
		{
			String str=textArea.getText();
			String str1[]=str.split("\\n"); //��str�����ݷ�ΪԪ�أ��ŵ������ڡ�
			File file2=new File("my.txt");
			try
			{
				FileWriter out=new FileWriter(file2,true);
				BufferedWriter writer=new BufferedWriter(out);
				
				//��ȡ����ʱ��
				Calendar calendar=Calendar.getInstance();
				int year=calendar.get(Calendar.YEAR),
						month=calendar.get(Calendar.MONTH)+1,
						day=calendar.get(Calendar.DAY_OF_MONTH);
				int hour=calendar.get(Calendar.HOUR_OF_DAY),		
				 miute=calendar.get(Calendar.MINUTE),
				 second=calendar.get(Calendar.SECOND);
				//��ʱ��ת��Ϊ�ַ���
				String str11=String.valueOf(year)+"��"+String.valueOf(month)+"��"+String.valueOf(day)+"��"
							+"   "+String.valueOf(hour)+":"+String.valueOf(miute)+":"+String.valueOf(miute);
				for(String s:str1)
				{
					writer.append(s);
					writer.newLine();
				}
				writer.append("      ����ʱ�䣺 "+str11);
				writer.newLine();
				writer.close();
				JOptionPane.showMessageDialog(this, "����ɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
			}
			catch(Exception ee)
			{
			}
		}
		
		//����ı�������
		if(e.getSource()==bu2)
		{
			textArea.setText("");
		}
	}
}
	
