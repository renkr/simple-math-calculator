import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class SimpleMathCalculator extends JFrame implements ActionListener {
    // ボタンの定義
    JButton button1 = new JButton("7");
    JButton button2 = new JButton("8");
    JButton button3 = new JButton("9");
    JButton button4 = new JButton("+");
    JButton button5 = new JButton("4");
    JButton button6 = new JButton("5");
    JButton button7 = new JButton("6");
    JButton button8 = new JButton("-");
    JButton button9 = new JButton("1");
    JButton button10 = new JButton("2");
    JButton button11 = new JButton("3");
    JButton button12 = new JButton("*");
    JButton button13 = new JButton("0");
    JButton button14 = new JButton(".");
    JButton button15 = new JButton("/");
    JButton button16 = new JButton("=");

    BinaryOperationCalculator calc; // 電卓のインスタンス

    // コンストラクタ
    public SimpleMathCalculator() {
        setTitle("Simple Math Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new GridLayout(5, 1));

        // 1行目のパネル
        JPanel row1 = new JPanel();
        row1.setLayout(new GridLayout(1, 4));
        row1.add(button1);
        row1.add(button2);
        row1.add(button3);
        row1.add(button4);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        getContentPane().add(row1);

        // 2行目のパネル
        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1, 4));
        row2.add(button5);
        row2.add(button6);
        row2.add(button7);
        row2.add(button8);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        getContentPane().add(row2);

        // 3行目のパネル
        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(1, 4));
        row3.add(button9);
        row3.add(button10);
        row3.add(button11);
        row3.add(button12);
        button9.addActionListener(this);
        button10.addActionListener(this);
        button11.addActionListener(this);
        button12.addActionListener(this);
        getContentPane().add(row3);

        // 4行目のパネル
        JPanel row4 = new JPanel();
        row4.setLayout(new GridLayout(1, 2));
        JPanel row4_1 = new JPanel();
        row4_1.setLayout(new GridLayout(1, 2));

        // 4行目のパネルの左側
        row4_1.add(button13);
        row4_1.add(button14);
        button13.addActionListener(this);
        button14.addActionListener(this);

        // 4行目のパネルの右側
        JPanel row4_2 = new JPanel();
        row4_2.setLayout(new GridLayout(0, 2));
        row4_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        row4_2.add(button15);
        button15.addActionListener(this);

        row4.add(row4_1);
        row4.add(row4_2);
        getContentPane().add(row4);

        // 5行目のパネル
        JPanel row5 = new JPanel();
        row5.setLayout(new GridLayout(1, 1));
        row5.add(button16);
        button16.addActionListener(this);
        getContentPane().add(row5);

        // ウィンドウのサイズを設定
        setSize(300, 375);
        setVisible(true);

        // 電卓のインスタンスを生成
        calc = new BinaryOperationCalculator();
    }

    // イベント処理
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            calc.putNumber(7);
        } else if (e.getSource() == button2) {
            calc.putNumber(8);
        } else if (e.getSource() == button3) {
            calc.putNumber(9);
        } else if (e.getSource() == button4) {
            calc.setOperator('+');
        } else if (e.getSource() == button5) {
            calc.putNumber(4);
        } else if (e.getSource() == button6) {
            calc.putNumber(5);
        } else if (e.getSource() == button7) {
            calc.putNumber(6);
        } else if (e.getSource() == button8) {
            calc.setOperator('-');
        } else if (e.getSource() == button9) {
            calc.putNumber(1);
        } else if (e.getSource() == button10) {
            calc.putNumber(2);
        } else if (e.getSource() == button11) {
            calc.putNumber(3);
        } else if (e.getSource() == button12) {
            calc.setOperator('*');
        } else if (e.getSource() == button13) {
            calc.putNumber(0);
        } else if (e.getSource() == button14) {
            calc.setDecimalPoint();
        } else if (e.getSource() == button15) {
            calc.setOperator('/');
        } else if (e.getSource() == button16) {
            calc.pushEqual();
        }
    }

    // メインメソッド
    public static void main(String[] args) {
        new SimpleMathCalculator();
    }
}

// 電卓（二項演算）のクラス
class BinaryOperationCalculator {
    private double result; // 結果
    private double num1; // 一つ目の数
    private double num2; // 二つ目の数
    private char operator; // 演算子
    // 以下、フラグ。これらを用いて、電卓の状態を管理する
    private boolean isDecimalPointSet; // 小数点が設定されているかどうか
    private boolean isOperatorSet; // 演算子が確定されているかどうか
    private boolean expectNumber; // 数字が入力されることを期待しているかどうか
    private boolean expectOperator; // 演算子が入力されることを期待しているかどうか

    // コンストラクタ
    public BinaryOperationCalculator() {
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = ' ';
        isOperatorSet = false;
        isDecimalPointSet = false;
        expectNumber = true;
        expectOperator = true;
    }

    // 演算子を設定する
    public void setOperator(char operator) {
        if (!isOperatorSet && expectNumber && expectOperator) { // 一つ目の数が入力されていない
            this.operator = operator;
            num1 = result;
            expectOperator = false;
        } else if (!isOperatorSet && !expectNumber && expectOperator) { // 一つ目の数が入力されている
            this.operator = operator;
            expectNumber = true;
            expectOperator = false;
        } else if (!isOperatorSet && expectNumber && !expectOperator) { // 二つ目の数が入力されていない
            this.operator = operator;
        } else if (isOperatorSet && !expectNumber && expectOperator) { // 二つ目の数が入力されている
            calculate(); // 計算
            this.operator = operator;
            isOperatorSet = true;
            expectNumber = true;
            expectOperator = false;
            if ((int) result == result) {
                System.out.printf("\n->(%d)", (int) result); // 整数の場合
            } else {
                System.out.printf("\n->(%f)", result); // 小数の場合
            }
        } else if (isOperatorSet && expectNumber && !expectOperator) { // 演算子が入力されている（更新）
            this.operator = operator;
        }
    }

    // 1桁の数字を入力して項を更新する
    public void putNumber(int num) {
        if (!isOperatorSet && expectNumber && expectOperator) { // 一つ目の項の入力
            num1 = num;
            expectNumber = false;
            System.out.printf("%d", (int) num1);
        } else if (!isOperatorSet && !expectNumber && expectOperator && !isDecimalPointSet) { // 一つ目の項の入力（数字がすでに入力されている場合）
            num1 = num1 * 10 + num;
            System.out.printf("%d", (int) num);
        } else if (!isOperatorSet && !expectNumber && expectOperator && isDecimalPointSet) { // 一つ目の項の入力（少数点が設定されている場合）
            num1 = num1 + num * Math.pow(10, -1);
            System.out.printf("%d", (int) num);
        } else if (!isOperatorSet && expectNumber && !expectOperator) { // 二つ目の項の入力
            num2 = num;
            expectNumber = false;
            expectOperator = true;
            isOperatorSet = true;
            System.out.print(" " + operator + " ");
            System.out.printf("%d", (int) num2);
        } else if (isOperatorSet && !expectNumber && expectOperator && !isDecimalPointSet) { // 二つ目の項の入力（数字がすでに入力されている場合）
            num2 = num2 * 10 + num;
            System.out.printf("%d", (int) num);
        } else if (isOperatorSet && !expectNumber && expectOperator && isDecimalPointSet) { // 二つ目の項の入力（少数点が設定されている場合）
            num2 = num2 + num * Math.pow(10, -1);
            System.out.printf("%d", (int) num);
        } else if (isOperatorSet && expectNumber && !expectOperator) { // 三つ目以降の項の入力（二つ目の項の計算結果を一つ目の項にする）
            num2 = num;
            expectNumber = false;
            expectOperator = true;
            System.out.print(" " + operator + " ");
            System.out.printf("%d", (int) num2);
        }
    }

    // 小数点を入力して項を更新する
    public void setDecimalPoint() {
        if (!isOperatorSet && expectNumber && expectOperator) { // 一つ目の項の入力
            num1 = 0;
            isDecimalPointSet = true;
            expectNumber = false;
            System.out.print("0.");
        } else if (!isOperatorSet && !expectNumber && expectOperator) { // 一つ目の項の入力（数字がすでに入力されている場合）
            if (!isDecimalPointSet)
                System.out.print('.');
            isDecimalPointSet = true;
        } else if (!isOperatorSet && expectNumber && !expectOperator) { // 二つ目の項の入力
            num2 = 0;
            isDecimalPointSet = true;
            expectNumber = false;
            expectOperator = true;
            isOperatorSet = true;
            System.out.print("0.");
        } else if (isOperatorSet && !expectNumber && expectOperator) { // 二つ目の項の入力（数字がすでに入力されている場合）
            if (!isDecimalPointSet)
                System.out.print('.');
            isDecimalPointSet = true;
        } else if (isOperatorSet && expectNumber && !expectOperator) { // 三つ目以降の項の入力（二つ目の項の計算結果を一つ目の項にする）
            num2 = 0;
            isDecimalPointSet = true;
            expectNumber = false;
            expectOperator = true;
            System.out.print("0.");
        }
    }

    // '='を入力して計算を行う
    public void pushEqual() {
        calculate();
        if ((int) result == result)
            System.out.print("\n = " + (int) result + "\n"); // 小数点以下が0の場合は整数として表示する
        else
            System.out.print("\n = " + result + "\n"); // 小数点以下が0でない場合は小数点以下を表示する
        expectNumber = true;
        expectOperator = true;
    }

    // 計算を行う
    void calculate() {
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        num1 = result; // 二つの項の計算結果を一つ目の項にする
        num2 = 0; // 二つ目の項を初期化する
        isOperatorSet = false;
        isDecimalPointSet = false;
        operator = ' ';
    }

    // 結果を返す
    public double getResult() {
        return result;
    }

}