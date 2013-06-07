package testing;

import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
/*
class MyThread extends Thread{
	public void run(){
		for (int i = 10;i>=0;i--){
			System.out.println(i + " ");
		}
	}
}*/
class MyRunnable implements Runnable{
	public void run(){
		for(int i=10;i>=0;i--){
			System.out.println(i+ " ");
		}
	}
}

public class Testing extends JFrame{

	/**
	 * @param args
	 */
	
	private JLabel label;
	
	class MyThread extends Thread{
		String name;
		
		MyThread(String n){
			this.name = n;
		}
		
		public void run(){
			Date date;
			for(int i=10;i>=0;i--){
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				label.setText(i + "");
				date = new Date();
				System.out.println(date+" "+name);
			}
			label.setText("End");
		}
	}
	public Testing(){
		
		setTitle("ī��Ʈ�ٿ�");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label = new JLabel("Start");
		label.setFont(new Font("Serif",Font.BOLD,100));
		add(label);
		
		//�����带 �����Ѵ� �� run �޼ҵ尡 ȣ��ȴ�. 
		
		Thread t1 = new MyThread("�ȳ��ϼ���?");
		Thread t = new MyThread("�ȳ��� �輼��.");
		t.start();
		t1.start();
		
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Testing test = new Testing();

		
	}

}
