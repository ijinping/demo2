import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;


import javax.swing.JFrame;

public class lianxi extends JFrame{
	Label labRule=new Label();//����������ǩ
	Label labA=new Label();//��һ�����ֱ�ǩ
	Label labOp=new Label();//�Ӽ��˳���ǩ
	Label labB=new Label();//�ڶ������ֱ�ǩ
	Label label5=new Label();//���ںš�������ǩ
	Label labWarn=new Label();//����Ϸ���У����ʾ��ǩ
	Label labQues=new Label();//�����б��ǩ
	Label labResult=new Label();//�зֱ�ǩ
	TextField txtAnswer=new TextField();//����������
	
	int total=0;//��¼��������
	int right=0;//��¼������ȷ����
	int error=0;//��¼�����������
	int score=0;//��¼�����ܷ�
	boolean isOver=false;//һ�ֽ������ʶ
	boolean isFirst=true;//�����һ�����б�ʶ
	
	long startTime;//ÿһ�����п�ʼʱ��
	
	List listDisp=new List();//�����б�չʾ��
	List listScore=new List();//����չʾ��
	
	public static void main(String[] args){
		lianxi score=new lianxi();
	}
	
	public lianxi(){
		init();
		setSize(450,630);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
		
	public void init(){
		setLayout(null);
		setSize(450,630);
	
		labRule.setText("����ÿ���ʮ���⣬ÿ��5�֣���ENTER����ʼ��С������2λ");
		labRule.setBounds(36, 10, 390, 72);
		labRule.setFont(new Font("Dialog",Font.PLAIN,12));
		getContentPane().add(labRule);
		
		labA.setText("x");
		labA.setBounds(36,82,36,36);
		labA.setFont(new Font("Dialog",Font.PLAIN,24));
		getContentPane().add(labA);
		
		labOp.setText("+");
		labOp.setFont(new Font("Dialog",Font.PLAIN,24));
		labOp.setBounds(72, 82, 45, 36);
		getContentPane().add(labOp);
		
		labB.setText("y");
		labB.setFont(new Font("Dialog",Font.PLAIN,24));
		labB.setBounds(118, 82, 33, 36);
		getContentPane().add(labB);
		
		label5.setText("=");
		label5.setFont(new Font("Dialog",Font.PLAIN,24));
		label5.setBounds(168, 82, 24, 36);
		getContentPane().add(label5);
		
		
		labWarn.setFont(new Font("Dialog",Font.PLAIN,12));
		labWarn.setBackground(Color.RED);
		labWarn.setBounds(320, 82, 80, 36);
		labWarn.setVisible(false);
		getContentPane().add(labWarn);
		
		labQues.setText("�����б�");
		labQues.setFont(new Font("Dialog",Font.PLAIN,12));
		labQues.setBounds(36, 148, 100, 20);
		getContentPane().add(labQues);
		
		labResult.setText("����ͳ�ƣ�");
		labResult.setFont(new Font("Dialog",Font.PLAIN,12));
		labResult.setBounds(36,420,100,20);
		labResult.setVisible(false);
		getContentPane().add(labResult);
		
		txtAnswer.setFont(new Font("Dialog",Font.PLAIN,24));
		txtAnswer.setBounds(216, 82, 100, 36);
		getContentPane().add(txtAnswer);
		
		
		listDisp.setFont(new Font("Dialog",Font.PLAIN,16));
		listDisp.setBounds(36, 174, 282, 230);
		getContentPane().add(listDisp);
		
		listScore.setFont(new Font("Dialog",Font.PLAIN,16));
		listScore.setBounds(36,450,282,135);
		
		MyKey myKey=new MyKey();// ������򣬼��̰���������
		txtAnswer.addKeyListener(myKey);
		
	}
	
	class MyKey implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getSource()==txtAnswer){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(isOver || isFirst){
						updateQuestion(null);
					}else if("".equals(txtAnswer.getText())){
						labWarn.setText("������𰸣�");
						labWarn.setVisible(true);
					}else{
						labWarn.setVisible(false);
						if(!isNumber(txtAnswer.getText())){
							labWarn.setText("���������֣�");
							labWarn.setVisible(true);
						}else if(total<19){
							judge(null);
							updateQuestion(null);
						}else{
							judge(null);
							labResult.setVisible(true);
							scorePerformed(null);
						}
					}
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	
	int a=0,b=0;
	String op="";
	double result=0;
	DecimalFormat df=new DecimalFormat("#.00");
	/**
	 * ���ⷽ��
	 * @param e
	 */
	public void updateQuestion(ActionEvent e){
		if(isFirst){
			startTime=System.currentTimeMillis();
		}
		if(isOver==true){
			listDisp.clear();
			listScore.clear();
			labResult.setVisible(false);
			listScore.setVisible(false);
		}
		isOver=false;
		a=(int)(Math.random()*9+1);
		b=(int)(Math.random()*9+1);
		int c=(int)(Math.random()*4);
		switch (c) {
		case 0:
			op="+";
			result=a+b;
			break;
		case 1:
			op="-";
			result=a-b;
			break;
		case 2:
			op="*";
			result=a*b;
			break;
		case 3:
			op="/";
			result=(a*1.0)/b;
			//�������������������������뱣��2λС��
			if(String.valueOf(result).length()>10){
				result=Double.parseDouble(df.format((a*1.0)/b));
			}
			break;
		}
		labA.setText(String.valueOf(a));
		labB.setText(String.valueOf(b));
		labOp.setText(op);
		label5.setText("=");
		txtAnswer.setText("");
		isFirst=false;
		
	}
	/**
	 * �жϽ��
	 * @param e
	 */
	public void judge(ActionEvent e){
		try{
			double value=Double.parseDouble(txtAnswer.getText());
			String resultStr=(total+1)+"�� "+a+op+b+"="+value;
			if(value==result){
				resultStr+=" \t��ȷ";
				right++;
				score+=5;
			}else{
				resultStr+=" \t���� ��ȷ�𰸣�"+result;
				error++;
			}
			listDisp.add(resultStr);
			total++;
		}catch(NumberFormatException ignored){
		}
	}
	/**
	 * ͳ�Ʒ���
	 * @param e
	 */
	public void scorePerformed(ActionEvent e){
		isOver=true;
		listScore.clear();
		listScore.setVisible(true);
		String exitStr="���ι�����"+total+"��";
		listScore.add(exitStr);
		listScore.add("�ۼ���ʱ��"+(System.currentTimeMillis()-startTime)/1000+"��");
		listScore.add("��ԣ�"+right+"��");
		listScore.add("���"+error+"��");
		listScore.add("�÷֣�"+score+"��");
		getContentPane().add(listScore);
		score=0;
		right=0;
		error=0;
		total=0;
		
	}
	
	/**
	 * У�������Ƿ�λ����
	 * @param numberStr
	 * @return
	 */
	public boolean isNumber(String numberStr){
		boolean isNumber=true;
		try{
			Double.parseDouble(numberStr);
		}catch(NumberFormatException e){
			isNumber=false;
		}
		return isNumber;
	}
}