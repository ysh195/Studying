// String 타입의 완성된 하나의 계산식을 입력 받아서 String 타입의 계산 결과를 출력하는, 계산기를 구성하는 코드입니다.

package one;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Calculator extends JFrame{
	
	public static String CalcProcess(String str){

		String inputStr = str; // 입력된 계산식을 저장할 문자열
		int inputStr_length = inputStr.length(); // 편의를 위해 문자열의 길이를 변수에 저장합니다.
		String[] resultStr = new String[inputStr_length]; // 문자열로 이루어진 계산식을 숫자와 기호로 분리하고 저장하는 문자열의 배열
		int check=0; // 문자열을 어디까지 저장했는지 체크하기 위한 변수
		int resultStr_count = 1; // 문자열의 배열 중 어디까지 채워넣었는지 체크하기 위한 변수
		
		 for(int i=0; i<inputStr_length; i++){ // 기호와 숫자 분리하는 작업		 

			 	if((i==inputStr_length-1) && !inputStr.substring(i,i+1).equals("=")){
			 		// 사용자가 계산식의 끝에 "="를 사용할 것이라는 보장이 없으니, 문자열의 끝에 도달했는데 "="가 없을 경우, 숫자들만 저장하도록 합니다. 
			 		resultStr[resultStr_count-1] = inputStr.substring(check+1,i+1);
			 		check=i;
			 		resultStr_count--; // 보통 숫자에 한 자리, 기호에 한 자리를 배정하는데, 이 경우에는 숫자만 저장하므로 기호의 자리는 불필요해서 숫자를 하나 낮춤.
			 		break;
			 	}

			 	switch(inputStr.substring(i,i+1)){

			 		case "*":
			 		case "/":
			 		case "+":
			 		case "-":
			 		case "%":
			 			if(check==0) { // 보통 숫자는 check+1 ~ i로 저장해야 하는데, 첫 계산에 한해 check=0 ~ i로 저장해야 해서 예외를 만들어둠.
			 				resultStr[resultStr_count-1] = inputStr.substring(check,i);
			 			}
			 			else {
			 				resultStr[resultStr_count-1] = inputStr.substring(check+1,i);
			 			} // 숫자 문자열 저장

			 			resultStr[resultStr_count] = inputStr.substring(i,i+1); // 기호 문자열 저장
			 			resultStr_count += 2; // 숫자에 한 자리, 기호에 한 자리를 할당하면서 2자리를 사용했으니 그만큼 늘려줌
			 			check = i; // 어디까지 저장되었는지 기록
			 			break;
			 			
			 		case "=": // 다른 기호일 때와 거의 유사하나, "="이면 그 뒤가 없으므로 resultStr_count를 늘릴 필요가 없다는 점에서 차이가 있음
			 			if(check==0) { 
			 				resultStr[resultStr_count-1] = inputStr.substring(check,i);
			 			}
			 			else {
			 				resultStr[resultStr_count-1] = inputStr.substring(check+1,i);
			 			}

			 			check = i;
			 			break;

			 		default:
			 			break;
			 	}

			 }

		 int signCount=0; // 우선적으로 계산할 기호의 위치를 저장할 변수

		 for(int i = 0; i < resultStr_count; i++){ // 기호에 따라 계산 순서 정하는 작업.
		 	if(resultStr[i].equals("*") || resultStr[i].equals("/") || resultStr[i].equals("%")){
		 		signCount=i;
		 	} // *, /, %를 우선적으로 계산하는 것이 사칙 연산에 알맞고, 맨 뒤부터 계산해야 오류가 적으니, 맨 뒤에서부터 계산함.
		 }

		if(signCount == 0){ // 앞에서 *, /, %를 찾아봤는데도 하나도 없으면 이제 +, -를 앞에서부터 찾아서 계산
			 sign_count : for(int i = 0; i < resultStr_count; i++){ 
			 	if(resultStr[i].equals("+") || resultStr[i].equals("-")){
			 		signCount=i;
			 		break sign_count;
			 	}
			 }
		}

		 double convertStringToNum_1 = Double.parseDouble(resultStr[signCount-1]);
		 double convertStringToNum_2 = Double.parseDouble(resultStr[signCount+1]);
		 double CalcNum = 0; // signCount는 연산자 기호의 위치이므로, 그 앞(signCount-1)과 뒤(signCount+1)는 필연적으로 숫자임.

		 switch(resultStr[signCount]){
		 case "*":
		 	CalcNum = convertStringToNum_1 * convertStringToNum_2;
		 	break;
		 case "/":
		 	CalcNum = convertStringToNum_1 / convertStringToNum_2;
		 	break;
		 case "+":
		 	CalcNum = convertStringToNum_1 + convertStringToNum_2;
		 	break;
		 case "-":
		 	CalcNum = convertStringToNum_1 - convertStringToNum_2;
		 	break;
		 case "%":
		 	CalcNum = convertStringToNum_1 % convertStringToNum_2;
		 	break;
		 default:
		 	break;
		 }

		 resultStr[signCount-1] = ""; // 계산이 끝난 숫자와 기호는 모두 지우고, 결과값을 그 자리에 넣어줌
		 resultStr[signCount] = String.valueOf(CalcNum);
		 resultStr[signCount+1] = "";
		 inputStr = "";

		 for(int i=0; i<inputStr_length; i++) { // 서로 배열로 분리한 문자와 기호를 다시 합치면서, 계산 과정에서 발생한 null을 지우는 과정

			 inputStr += (resultStr[i]==null) ? "" : resultStr[i];
		 }

		 return inputStr;

		}

	public static String Process(String calc) {

		String input = calc;

		String save_input;

		int input_length = input.length();

		if(input.contains("=")) { // 계산식의 마지막에 "="이 들어가면 출력의 형태가 달라지기 때문에 한 번 보정하는 것
			save_input = input.substring(0,input_length-1);
		}
		else {
			save_input = input.substring(0,input_length);
		}	

		 int frontCount = 0, backCount = 0;

		 while(input.contains("(")) { // 계산식 내에서 괄호가 모두 없어질 때까지 계속 반복

			 String CalcLine;

			 frontCount = input.lastIndexOf("(") + 1; // 가장 뒤에 있는 "("의 위치를 찾습니다.
			 // 거기에 1을 더함으로써, 이것을 이용한 문자 출력 시 "("가 포함되지 않도록 합니다.
			 backCount = input.indexOf(")", frontCount); // 가장 뒤에 있는 "("로부터 가장 가까운 ")"의 위치를 찾습니다.

			 CalcLine = input.substring(frontCount, backCount); // 가장 뒤에 있는 괄호 사이에 들어가 있는 계산식만 추출
			 
			 while((CalcLine.contains("*"))||(CalcLine.contains("/"))||(CalcLine.contains("+"))||(CalcLine.contains("-"))||(CalcLine.contains("%"))) {
			 CalcLine = CalcProcess(CalcLine);
			 } // CalcLine 내의 모든 계산을 다 끝낸 다음에 input과 결합합니다. 다 끝내지 않을 경우, 덜 계산된 채로 괄호만 사라지기 때문에 계산 순서가 꼬이게 됩니다.
			 
			 input = input.substring(0, frontCount-1) + CalcLine + input.substring(backCount+1, input.length());
		 }
		 

		 while((input.contains("*"))||(input.contains("/"))||(input.contains("+"))||(input.contains("-"))||(input.contains("%"))){
			 input = CalcProcess(input);
		 }

		 return input;

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setTitle("계산기 만들기"); // 제목
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(310,400); // 화면 사이즈
		
		Font font = new Font("Serif",Font.BOLD,20);
		Font font1 = new Font("Serif",Font.BOLD,13);
		
		Container contentPane = frame.getContentPane(); // 컨텐트팬과 연결
		contentPane.setLayout(null); // 레이아웃 설정을 사용자에게 맡기는 기능 추가
		
		JButton Button_one = new JButton("1");
		Button_one.setBounds(50,80,50,50);
		Button_one.setFont(font);
		contentPane.add(Button_one);
		
		JButton Button_two = new JButton("2");
		Button_two.setBounds(100,80,50,50);
		Button_two.setFont(font);
		contentPane.add(Button_two);
		
		JButton Button_three = new JButton("3");
		Button_three.setBounds(150,80,50,50);
		Button_three.setFont(font);
		contentPane.add(Button_three);
		
		JButton Button_four = new JButton("4");
		Button_four.setBounds(50,130,50,50);
		Button_four.setFont(font);
		contentPane.add(Button_four);
		
		JButton Button_five = new JButton("5");
		Button_five.setBounds(100,130,50,50);
		Button_five.setFont(font);
		contentPane.add(Button_five);
		
		JButton Button_six = new JButton("6");
		Button_six.setBounds(150,130,50,50);
		Button_six.setFont(font);
		contentPane.add(Button_six);
		
		JButton Button_seven = new JButton("7");
		Button_seven.setBounds(50,180,50,50);
		Button_seven.setFont(font);
		contentPane.add(Button_seven);
		
		JButton Button_eight = new JButton("8");
		Button_eight.setBounds(100,180,50,50);
		Button_eight.setFont(font);
		contentPane.add(Button_eight);
		
		JButton Button_nine = new JButton("9");
		Button_nine.setBounds(150,180,50,50);
		Button_nine.setFont(font);
		contentPane.add(Button_nine);
		
		JButton Button_zero = new JButton("0");
		Button_zero.setBounds(50,230,50,50);
		Button_zero.setFont(font);
		contentPane.add(Button_zero);
		
		JButton Button_equal = new JButton("=");
		Button_equal.setBounds(200,80,50,50);
		Button_equal.setFont(font);
		contentPane.add(Button_equal);
		
		JButton Button_spare = new JButton("%");
		Button_spare.setBounds(100,230,50,50);
		Button_spare.setFont(font1);
		contentPane.add(Button_spare);
		
		JButton Button_plus = new JButton("+");
		Button_plus.setBounds(200,130,50,50);
		Button_plus.setFont(font);
		contentPane.add(Button_plus);
		
		JButton Button_minus = new JButton("-");
		Button_minus.setBounds(200,180,50,50);
		Button_minus.setFont(font);
		contentPane.add(Button_minus);
		
		JButton Button_multiple = new JButton("*");
		Button_multiple.setBounds(150,230,50,50);
		Button_multiple.setFont(font);
		contentPane.add(Button_multiple);
		
		JButton Button_divide = new JButton("/");
		Button_divide.setBounds(200,230,50,50);
		Button_divide.setFont(font);
		contentPane.add(Button_divide);
		
		JButton Button_erase = new JButton("E");
		Button_erase.setBounds(50,280,50,40);
		Button_erase.setFont(font);
		contentPane.add(Button_erase);
		
		JButton Button_Allerase = new JButton("AE");
		Button_Allerase.setBounds(100,280,50,40);
		Button_Allerase.setFont(font1);
		contentPane.add(Button_Allerase);
		
		JButton Button_frontBlock = new JButton("(");
		Button_frontBlock.setBounds(150,280,50,40);
		Button_frontBlock.setFont(font);
		contentPane.add(Button_frontBlock);
		
		JButton Button_backBlock = new JButton(")");
		Button_backBlock.setBounds(200,280,50,40);
		Button_backBlock.setFont(font);
		contentPane.add(Button_backBlock);
		
		JTextField textField = new JTextField(""); // 텍스트 입력란
		textField.setBounds(50, 40, 200, 30);
		textField.setFont(font);
		contentPane.add(textField);
		textField.setColumns(30);
		
		JLabel resultText = new JLabel("결과값 : ");
		resultText.setFont(font);
		resultText.setBounds(50, 290, 100, 100);
		contentPane.add(resultText);
		
		JLabel resultout = new JLabel("");
		resultout.setFont(font);
		resultout.setBounds(130, 290, 100, 100);
		contentPane.add(resultout);
		
		Button_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText()+"1");
            }
        });
		
		Button_two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"2");
            }
        });
		
		Button_three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"3");
            }
        });
		
		Button_four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"4");
            }
        });
		
		Button_five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"5");
            }
        });
		
		Button_six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"6");
            }
        });
		
		Button_seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"7");
            }
        });
		
		Button_eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"8");
            }
        });
		
		Button_nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"9");
            }
        });
		
		Button_zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"0");
            }
        });
		
		Button_plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"+");
            }
        });
		
		Button_minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"-");
            }
        });
		
		Button_multiple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"*");
            }
        });
		
		Button_divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"/");
            }
        });
		
		Button_spare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"%");
            }
        });
		
		Button_equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultout.setText( Process( textField.getText() ) );
                textField.setText("");
            }
        });
		
		Button_erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String temp = textField.getText();
	            	if(temp.length()>0)
	            	{
	            	temp = temp.substring(0,temp.length()-1);
	                textField.setText(temp);
	            	}
            }
        });
		
		Button_Allerase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
		
		Button_frontBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+"(");
            }
        });
		
		Button_backBlock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	textField.setText(textField.getText()+")");
            }
        });
		
		frame.setVisible(true);
	}

}
