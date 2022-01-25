package myApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;


public class GUI extends JPanel {
    static JTextArea regnestykkeTextField;
    static JTextField inputField;
    static JTextArea stopwatchShow;
    static JTextArea antallOppgv;

    //Initialize array of operations
    static boolean[] selectedOperations = {true, true, true, false};

    //Create ranNums object
    static myApp.MyMath ranNums = new MyMath();

    //Timers
    static long before = 0;
    static long after = 0;

    //Min and max limits of generated numbers [min, max>
    static int min = 1;
    static int max = 10;

    //Amount of solved tasks
    static int counter = 1;

    //Random numbers and operation
    static int ranNum1;// = ranNums.getNum(min, max);
    static int ranNum2;// = ranNums.getNum(min, max);
    static String ranOperation;// = ranNums.getOperator(selectedOperations);
    

    //Displays the excercise on the screen
    public static void displayText(){
        ranNum1 = ranNums.getNum(min, max);
        ranNum2 = ranNums.getNum(min, max);
        ranOperation = ranNums.getOperator(selectedOperations);
        regnestykkeTextField.setText(ranNum1 + ranOperation + ranNum2 + " =");
    }

    //GUI
    GUI() {
        //construct components
        regnestykkeTextField = new JTextArea (5, 5);
        regnestykkeTextField.setFont(new Font("Dialog", Font.PLAIN, 80));
        regnestykkeTextField.setEditable(false);

        inputField = new JTextField (5);
        inputField.setFont(new Font("Dialog", Font.PLAIN, 60));

        stopwatchShow = new JTextArea(1, 1);
        stopwatchShow.setEditable(false);
        stopwatchShow.setFont(new Font("Dialog", Font.PLAIN, 20));

        antallOppgv = new JTextArea(1, 1);
        antallOppgv.setEditable(false);
        antallOppgv.setFont(new Font("Dialog", Font.PLAIN, 20));

        //Text
        JLabel Tid = new JLabel("Time:");
        Tid.setFont(new Font("Dialog", Font.PLAIN, 18));
        JLabel numOfTasks = new JLabel("Solved:");
        numOfTasks.setFont(new Font("Dialog", Font.PLAIN, 18));

        //Buttons
        JButton submit = new JButton("Submit");
        JButton start = new JButton("Start time");
        JButton stop = new JButton("Reset time");
        
        //Checkboxes
        JCheckBox addition = new JCheckBox("Addition (+)");
        addition.setSelected(true);
        addition.setEnabled(false);
        JCheckBox subtraction = new JCheckBox("Subtraction (-)");
        subtraction.setSelected(true);
        JCheckBox multiplication = new JCheckBox("Multiplication (*)");
        multiplication.setSelected(true);
        JCheckBox division = new JCheckBox("Division (/)");
        division.setSelected(false);
        
        
        //adjust size and set layout
        setPreferredSize(new Dimension (944, 574));
        setLayout(null);

        //add components
        add(regnestykkeTextField);
        add(inputField);
        add(stopwatchShow);
        add(antallOppgv);
        add(submit);
        add(addition);
        add(subtraction);
        add(multiplication);
        add(division);
        add(stop);
        add(start);
        add(Tid);
        add(numOfTasks);

        //set component bounds (only needed by Absolute Positioning)
        regnestykkeTextField.setBounds (265, 80, 440, 110);
        inputField.setBounds (375, 245, 195, 65);
        stopwatchShow.setBounds(750, 225, 160, 28);
        antallOppgv.setBounds(750, 290, 160, 28);
        submit.setBounds(390, 400, 80*2, 14*2);
        start.setBounds(750, 100, 160, 28);
        start.setBounds(750, 100, 160, 28);
        stop.setBounds(750, 150, 160, 28);
        addition.setBounds(100, 100, 100, 28);
        subtraction.setBounds(100, 120, 150, 28);
        multiplication.setBounds(100, 140, 150, 28);
        division.setBounds(100, 160, 100, 28);
        Tid.setBounds(750, 185, 100, 50);
        numOfTasks.setBounds(750, 240, 100, 80);


        //Event Listeners

        //When the start button is pressed
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                before = System.currentTimeMillis();
            };
        });

        //When the stop button is pressed
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                after = 0;
                before = 0;
                stopwatchShow.setText("");
                antallOppgv.setText("");
            };
        });
        
        //When the checkboxes are selected
        addition.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectedOperations = new boolean[]{addition.isSelected(), subtraction.isSelected(), multiplication.isSelected(), division.isSelected()};
                if (!addition.isSelected())
                    addition.setSelected(false);
            }
        });
        subtraction.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectedOperations = new boolean[]{addition.isSelected(), subtraction.isSelected(), multiplication.isSelected(), division.isSelected()};         
                if (!subtraction.isSelected())
                    subtraction.setSelected(false);
            }
        });
        multiplication.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectedOperations = new boolean[]{addition.isSelected(), subtraction.isSelected(), multiplication.isSelected(), division.isSelected()};
                if (!multiplication.isSelected())
                    multiplication.setSelected(false);
            }
        });
        division.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                selectedOperations = new boolean[]{addition.isSelected(), subtraction.isSelected(), multiplication.isSelected(), division.isSelected()};
                if (!division.isSelected())
                    division.setSelected(false);
            }
        });


        //When the sumbit button is pressed
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    //Checks if answer is correct
                    double answer = Double.parseDouble(inputField.getText());
                    if(ranNums.utregn(ranNum1, ranNum2, answer, ranOperation)){
                        
                        displayText();
                        inputField.setBackground(Color.GREEN);
                    }else{
                        inputField.setBackground(Color.RED);
                    }
                } catch (Exception f) {
                    showMessageDialog(null, "Invalid Answer");
                }
                
                //Cleans inputfield of input
                inputField.setText("");
                //Updates time and number of excercises done
                after = System.currentTimeMillis();
                if (after == 0 || before == 0){}
                else{
                    stopwatchShow.setText((after - (double)before)/1000 + "s");
                    antallOppgv.setText(Integer.toString(counter));
                    counter++;
                }
            };
        });
        
        //Når enter blir trykket
        KeyAdapter Enter = new KeyAdapter(){
            //@Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    submit.doClick();
                }
            }
        };
        inputField.addKeyListener(Enter);
    }


    public static void main (String[] args) throws IOException {
        JFrame frame = new JFrame ("MatteMagi");
        
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.pack();
        frame.setVisible (true);
        frame.setIconImage(ImageIO.read(new File("C:/Users/Mathias/Pictures/icon.jpg")));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        displayText();

        //Focus the mousepointer on the inputfield
        inputField.requestFocusInWindow();
    }
}