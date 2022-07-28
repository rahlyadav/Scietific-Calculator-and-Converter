import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;


public class CalculatorAndConverter {

    JFrame frame;
    JPanel calculatorPanel, converterPanel;
    JTextField tField;
    JComboBox<String> comboBoxMain, comboBoxFrom, comboBoxTo;
    JTextField tFieldInput, tFieldOutput;

    public static void main(String[] args) {
        CalculatorAndConverter obj = new CalculatorAndConverter();

        obj.startGUI();
    }

    void startGUI() {
        frame = new JFrame("Scientific Calculator and Converter");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JTabbedPane tabbedPane = new JTabbedPane();

        calculatorPanel = new JPanel(new BorderLayout());
        converterPanel = new JPanel(new BorderLayout());

        tabbedPane.addTab("Calculator", calculatorPanel);
        tabbedPane.addTab("Converter", converterPanel);

        setCalculator();

        setConverter();

        frame.add(tabbedPane);

        frame.setSize(365, 525);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void setCalculator() {
        calculatorPanel.setLayout(new BoxLayout(calculatorPanel, BoxLayout.Y_AXIS));

        GridLayout gridLayoutH = new GridLayout();
        gridLayoutH.setHgap(5);

        GridLayout gridLayoutV = new GridLayout(7, 1);
        gridLayoutV.setVgap(5);

        JPanel wrapperTField = new JPanel(new BorderLayout());
        JPanel wrapperButtons = new JPanel(gridLayoutV);
        JPanel wrapperButtons1 = new JPanel(gridLayoutH);
        JPanel wrapperButtons2 = new JPanel(gridLayoutH);
        JPanel wrapperButtons3 = new JPanel(gridLayoutH);
        JPanel wrapperButtons4 = new JPanel(gridLayoutH);
        JPanel wrapperButtons5 = new JPanel(gridLayoutH);
        JPanel wrapperButtons6 = new JPanel(gridLayoutH);
        JPanel wrapperButtons7 = new JPanel(gridLayoutH);

        tField = new JTextField("");
        tField.setFont(new FontUIResource("Times New Roman", FontUIResource.PLAIN, 30));
        tField.setEditable(false);
        tField.setHorizontalAlignment(SwingConstants.RIGHT);
        tField.getCaret().setVisible(true);
        tField.setBackground(Color.WHITE);

        JButton btn0 = new JButton("0");
        JButton btn00 = new JButton("00");
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btnDot = new JButton(".");
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMultiply = new JButton("*");
        JButton btnDivide = new JButton("/");
        JButton btnModulus = new JButton("%");
        JButton btnPower = new JButton("^");
        JButton btnEqual = new JButton("=");
        JButton btnDelete = new JButton("<~");
        JButton btnClear = new JButton("C");
        JButton btnSin = new JButton("sin");
        JButton btnCos = new JButton("cos");
        JButton btnTan = new JButton("tan");
        JButton btnInv = new JButton("INV");
        JButton btnLog = new JButton("log");
        JButton btnLn = new JButton("ln");
        JButton btnLeftB = new JButton("(");
        JButton btnRightB = new JButton(")");
        JButton btnLeftA = new JButton("<");
        JButton btnRightA = new JButton(">");

        ActionListener calculatorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = tField.getText();

                if (e.getSource() == btnClear) {
                    tField.setText("");
                    tField.getCaret().setVisible(true);
                } else if (e.getSource() == btnDelete) {
                    if (temp.length() > 0){
                        try {
                            int position = tField.getCaretPosition();
                            String newText = temp.substring(0, position-1) + temp.substring(position);
                            tField.setText(newText);
                            tField.setCaretPosition(position-1);
                        } catch (Exception exception) {}
                    } else {
                        tField.setText("");
                    }
                    tField.getCaret().setVisible(true);
                } else if (e.getSource() == btnSin || e.getSource() == btnCos || e.getSource() == btnTan || e.getSource() == btnLog || e.getSource() == btnLn) {
                    try {
                        int position = tField.getCaretPosition();
                        String text = ((JButton)e.getSource()).getText()+"()";
                        String newText = temp.substring(0, position) + text + temp.substring(position);
                        tField.setText(newText);
                        tField.setCaretPosition(position+text.length()-1);
                        tField.getCaret().setVisible(true);
                    } catch (Exception exception) {}
                } else if (e.getSource() == btnInv) {
                    if (btnSin.getText() == "sin") {
                        btnSin.setText("asin");
                        btnCos.setText("acos");
                        btnTan.setText("atan");
                    } else {
                        btnSin.setText("sin");
                        btnCos.setText("cos");
                        btnTan.setText("tan");
                    }
                    tField.getCaret().setVisible(true);
                } else if (e.getSource() == btnLeftA) {
                    try {
                        tField.setCaretPosition(tField.getCaretPosition()-1);
                        tField.getCaret().setVisible(true);
                    } catch (IllegalArgumentException exception) {}
                } else if (e.getSource() == btnRightA) {
                    try {
                        tField.setCaretPosition(tField.getCaretPosition()+1);
                        tField.getCaret().setVisible(true);
                    } catch (IllegalArgumentException exception) {}
                } else if (e.getSource() == btnEqual) {
                    try {
                        tField.setText(evaluateAdvance(temp));
                    } catch (Exception exception) {
                        tField.setText("Invalid Equation");
                    }
                    tField.getCaret().setVisible(true);
                } else {
                    int position = tField.getCaretPosition();
                    String text = ((JButton)e.getSource()).getText();
                    String newText = temp.substring(0, position) + text + temp.substring(position);
                    tField.setText(newText);
                    tField.setCaretPosition(position+text.length());
                    tField.getCaret().setVisible(true);
                }
                
            }
        };

        btn0.addActionListener(calculatorListener);
        btn00.addActionListener(calculatorListener);
        btn1.addActionListener(calculatorListener);
        btn2.addActionListener(calculatorListener);
        btn3.addActionListener(calculatorListener);
        btn4.addActionListener(calculatorListener);
        btn5.addActionListener(calculatorListener);
        btn6.addActionListener(calculatorListener);
        btn7.addActionListener(calculatorListener);
        btn8.addActionListener(calculatorListener);
        btn9.addActionListener(calculatorListener);
        btnDot.addActionListener(calculatorListener);
        btnAdd.addActionListener(calculatorListener);
        btnSub.addActionListener(calculatorListener);
        btnMultiply.addActionListener(calculatorListener);
        btnDivide.addActionListener(calculatorListener);
        btnModulus.addActionListener(calculatorListener);
        btnPower.addActionListener(calculatorListener);
        btnEqual.addActionListener(calculatorListener);
        btnDelete.addActionListener(calculatorListener);
        btnClear.addActionListener(calculatorListener);
        btnSin.addActionListener(calculatorListener);
        btnCos.addActionListener(calculatorListener);
        btnTan.addActionListener(calculatorListener);
        btnLog.addActionListener(calculatorListener);
        btnInv.addActionListener(calculatorListener);
        btnLn.addActionListener(calculatorListener);
        btnLeftB.addActionListener(calculatorListener);
        btnRightB.addActionListener(calculatorListener);
        btnLeftA.addActionListener(calculatorListener);
        btnRightA.addActionListener(calculatorListener);
        
        wrapperButtons1.add(btnClear);
        wrapperButtons1.add(btnInv);
        wrapperButtons1.add(btnLeftA);
        wrapperButtons1.add(btnRightA);
        wrapperButtons1.add(btnDelete);
        wrapperButtons2.add(btnSin);
        wrapperButtons2.add(btnCos);
        wrapperButtons2.add(btnTan);
        wrapperButtons2.add(btnLog);
        wrapperButtons2.add(btnLn);
        wrapperButtons3.add(btnLeftB);
        wrapperButtons3.add(btnRightB);
        wrapperButtons3.add(btnPower);
        wrapperButtons3.add(btnModulus);
        wrapperButtons3.add(btnDivide);
        wrapperButtons4.add(btn7);
        wrapperButtons4.add(btn8);
        wrapperButtons4.add(btn9);
        wrapperButtons4.add(btnMultiply);
        wrapperButtons5.add(btn4);
        wrapperButtons5.add(btn5);
        wrapperButtons5.add(btn6);
        wrapperButtons5.add(btnSub);
        wrapperButtons6.add(btn1);
        wrapperButtons6.add(btn2);
        wrapperButtons6.add(btn3);
        wrapperButtons6.add(btnAdd);
        wrapperButtons7.add(btn00);
        wrapperButtons7.add(btn0);
        wrapperButtons7.add(btnDot);
        wrapperButtons7.add(btnEqual);

        wrapperTField.add(tField);

        wrapperButtons.add(wrapperButtons1);
        wrapperButtons.add(wrapperButtons2);
        wrapperButtons.add(wrapperButtons3);
        wrapperButtons.add(wrapperButtons4);
        wrapperButtons.add(wrapperButtons5);
        wrapperButtons.add(wrapperButtons6);
        wrapperButtons.add(wrapperButtons7);

        calculatorPanel.add(wrapperTField);
        calculatorPanel.add(wrapperButtons);
    }

    void setConverter() {
        converterPanel.setLayout(new BoxLayout(converterPanel, BoxLayout.Y_AXIS));

        Color bgColor = new Color(182, 208, 226);

        GridLayout gridLayoutH = new GridLayout();
        gridLayoutH.setHgap(5);

        GridLayout gridLayoutV = new GridLayout(4, 1);
        gridLayoutV.setVgap(5);

        String[] dimensions = {"Length", "Area", "Volume", "Mass", "Time", "Speed", "Temperature", "Power", "Pressure", "Energy", "Data Sizes", "Number System"};
        String[] lengthUnits = {"Nanometres", "Micrometres", "Millimetres", "Centimetres", "Decimetres", "Metres", "Kilometres", "Angstrom", "Inches", "Feet", "Yards", "Miles", "Nautic Miles"};
        String[] areaUnits = {"Square Millimetres", "Square Centimetres", "Square Metres", "Hectares", "Square Kilometres", "Square Inches", "Square Feet", "Square Yards", "Acres", "Square Miles"};
        String[] volumeUnits = {"Cubic Centimetres", "MilliLitres", "Litres", "Cubic Decimetres", "Hectolitres", "Cubic Metres", "Cubic Inches", "Cubic Feet", "Cubic Yards", "Gallons (Imp)", "Gallons (US)", "Barrels"};
        String[] massUnits = {"Micrograms", "Milligrams", "Grams", "Kilograms", "Tonnes", "Ounces", "Pounds", "Carats"};
        String[] timeUnits = {"Microseconds", "Milliseconds", "Seconds", "Minutes", "Hours", "Days", "Weeks", "Years"};
        String[] speedUnits = {"Metres per Second", "Metres per Hour", "Kilometres per Second", "Kilometres per Hour", "Feet per Second", "Miles per Second", "Miles per Hour", "Knots"};
        String[] temperatureUnits = {"Degrees Celsius", "Kelvins", "Degrees Fahrenheit", "Degrees Rankine", "Degrees Newton"};
        String[] powerUnits = {"Watts", "Kilowatts", "Megawatts", "Gigawatts", "Joules per Hour", "Kilojoules per Hour", "Calories per Second", "Calories per Hour", "Kilocalories per Second", "Kilocalories per Hour", "Horsepowers (Imp)"};
        String[] pressureUnits = {"Micropascals", "Millipascals", "Pascals", "Hectopascals", "Kilopascals", "Megapascals", "Gigapascals", "Atmospheres", "Microbars", "Millibars", "Bars", "Millitorrs", "Torrs", "Pounds per Square Foot", "Millimetres of Mercury (0 C)"};
        String[] energyUnits = {"Nanojoules", "Microjoules", "Millijoules", "Joules", "Kilojoules", "Megajoules", "Newton-metres", "Watt-hours", "Kilowatt-hours", "Megawatt-hours", "Calories", "Kilocalories", "British Thermal Units (ISO)", "Ergs", "Electronvolt", "Kiloelectronvolt", "Megaelectronvolt", "Gigaelectronvolt"};
        String[] dataSizesUnits = {"Bytes", "Kilobytes", "Megabytes", "Gigabytes", "Terabytes", "Petabytes", "Bits"};
        String[] numberSystemUnits = {"Decimal", "Binary", "Octal", "Hexadecimal"};

        JPanel wrapperMain = new JPanel();
        wrapperMain.setBackground(Color.WHITE);
        JPanel wrapperInput = new JPanel();
        wrapperInput.setBackground(Color.WHITE);
        JPanel wrapperOutput = new JPanel();
        wrapperOutput.setBackground(Color.WHITE);
        JPanel wrapperButtons = new JPanel(gridLayoutV);
        wrapperButtons.setBackground(bgColor);
        JPanel wrapperButtons1 = new JPanel(gridLayoutH);
        wrapperButtons1.setBackground(bgColor);
        JPanel wrapperButtons2 = new JPanel(gridLayoutH);
        wrapperButtons2.setBackground(bgColor);
        JPanel wrapperButtons3 = new JPanel(gridLayoutH);
        wrapperButtons3.setBackground(bgColor);
        JPanel wrapperButtons4 = new JPanel(gridLayoutH);
        wrapperButtons4.setBackground(bgColor);

        DefaultComboBoxModel<String> cBoxModelDimension = new DefaultComboBoxModel<>(dimensions);

        DefaultComboBoxModel<String> cBoxModelLengthF = new DefaultComboBoxModel<>(lengthUnits);
        DefaultComboBoxModel<String> cBoxModelLengthT = new DefaultComboBoxModel<>(lengthUnits);
        DefaultComboBoxModel<String> cBoxModelAreaF = new DefaultComboBoxModel<>(areaUnits);
        DefaultComboBoxModel<String> cBoxModelAreaT = new DefaultComboBoxModel<>(areaUnits);
        DefaultComboBoxModel<String> cBoxModelVolumeF = new DefaultComboBoxModel<>(volumeUnits);
        DefaultComboBoxModel<String> cBoxModelVolumeT = new DefaultComboBoxModel<>(volumeUnits);
        DefaultComboBoxModel<String> cBoxModelMassF = new DefaultComboBoxModel<>(massUnits);
        DefaultComboBoxModel<String> cBoxModelMassT = new DefaultComboBoxModel<>(massUnits);
        DefaultComboBoxModel<String> cBoxModelTimeF = new DefaultComboBoxModel<>(timeUnits);
        DefaultComboBoxModel<String> cBoxModelTimeT = new DefaultComboBoxModel<>(timeUnits);
        DefaultComboBoxModel<String> cBoxModelSpeedF = new DefaultComboBoxModel<>(speedUnits);
        DefaultComboBoxModel<String> cBoxModelSpeedT = new DefaultComboBoxModel<>(speedUnits);
        DefaultComboBoxModel<String> cBoxModelTemperatureF = new DefaultComboBoxModel<>(temperatureUnits);
        DefaultComboBoxModel<String> cBoxModelTemperatureT = new DefaultComboBoxModel<>(temperatureUnits);
        DefaultComboBoxModel<String> cBoxModelPowerF = new DefaultComboBoxModel<>(powerUnits);
        DefaultComboBoxModel<String> cBoxModelPowerT = new DefaultComboBoxModel<>(powerUnits);
        DefaultComboBoxModel<String> cBoxModelPressureF = new DefaultComboBoxModel<>(pressureUnits);
        DefaultComboBoxModel<String> cBoxModelPressureT = new DefaultComboBoxModel<>(pressureUnits);
        DefaultComboBoxModel<String> cBoxModelEnergyF = new DefaultComboBoxModel<>(energyUnits);
        DefaultComboBoxModel<String> cBoxModelEnergyT = new DefaultComboBoxModel<>(energyUnits);
        DefaultComboBoxModel<String> cBoxModelDataSizeF = new DefaultComboBoxModel<>(dataSizesUnits);
        DefaultComboBoxModel<String> cBoxModelDataSizeT = new DefaultComboBoxModel<>(dataSizesUnits);
        DefaultComboBoxModel<String> cBoxModelNumberSystemF = new DefaultComboBoxModel<>(numberSystemUnits);
        DefaultComboBoxModel<String> cBoxModelNumberSystemT = new DefaultComboBoxModel<>(numberSystemUnits);

        comboBoxMain = new JComboBox<>(cBoxModelDimension);
        comboBoxMain.setPreferredSize(new Dimension(325, comboBoxMain.getPreferredSize().height));
        comboBoxMain.setBackground(Color.WHITE);

        comboBoxFrom = new JComboBox<>();
        comboBoxFrom.setPreferredSize(new Dimension(175, comboBoxFrom.getPreferredSize().height));
        comboBoxFrom.setBackground(Color.WHITE);

        comboBoxTo = new JComboBox<>();
        comboBoxTo.setPreferredSize(new Dimension(175, comboBoxTo.getPreferredSize().height));
        comboBoxTo.setBackground(Color.WHITE);

        tFieldInput = new JTextField("", 14);
        tFieldInput.setPreferredSize(new Dimension(tFieldInput.getPreferredSize().width, comboBoxFrom.getPreferredSize().height));
        tFieldInput.setEditable(false);
        tFieldInput.setBackground(Color.WHITE);

        tFieldOutput = new JTextField("", 14);
        tFieldOutput.setPreferredSize(new Dimension(tFieldOutput.getPreferredSize().width, comboBoxTo.getPreferredSize().height));
        tFieldOutput.setEditable(false);
        tFieldOutput.setBackground(Color.WHITE);

        wrapperMain.add(comboBoxMain);
        wrapperInput.add(comboBoxFrom);
        wrapperInput.add(tFieldInput);
        wrapperOutput.add(comboBoxTo);
        wrapperOutput.add(tFieldOutput);

        JButton btn0 = new JButton("0");
        btn0.setBorderPainted(false);
        btn0.setBackground(bgColor);
        JButton btn00 = new JButton("00");
        btn00.setBorderPainted(false);
        btn00.setBackground(bgColor);
        JButton btn1 = new JButton("1");
        btn1.setBorderPainted(false);
        btn1.setBackground(bgColor);
        JButton btn2 = new JButton("2");
        btn2.setBorderPainted(false);
        btn2.setBackground(bgColor);
        JButton btn3 = new JButton("3");
        btn3.setBorderPainted(false);
        btn3.setBackground(bgColor);
        JButton btn4 = new JButton("4");
        btn4.setBorderPainted(false);
        btn4.setBackground(bgColor);
        JButton btn5 = new JButton("5");
        btn5.setBorderPainted(false);
        btn5.setBackground(bgColor);
        JButton btn6 = new JButton("6");
        btn6.setBorderPainted(false);
        btn6.setBackground(bgColor);
        JButton btn7 = new JButton("7");
        btn7.setBorderPainted(false);
        btn7.setBackground(bgColor);
        JButton btn8 = new JButton("8");
        btn8.setBorderPainted(false);
        btn8.setBackground(bgColor);
        JButton btn9 = new JButton("9");
        btn9.setBorderPainted(false);
        btn9.setBackground(bgColor);
        JButton btnDot = new JButton(".");
        btnDot.setBorderPainted(false);
        btnDot.setBackground(bgColor);
        JButton btnDel = new JButton("<~");
        btnDel.setBorderPainted(false);
        btnDel.setBackground(bgColor);
        JButton btnClear = new JButton("C");
        btnClear.setBorderPainted(false);
        btnClear.setBackground(bgColor);
        JButton btnBuffer1 = new JButton();
        btnBuffer1.setBorderPainted(false);
        btnBuffer1.setBackground(bgColor);
        JButton btnBuffer2 = new JButton();
        btnBuffer2.setBorderPainted(false);
        btnBuffer2.setBackground(bgColor);

        ActionListener converterListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboBoxMain) {
                    String s = (String)comboBoxMain.getSelectedItem();
                    switch (s) {
                        case "Length" : {
                            comboBoxFrom.setModel(cBoxModelLengthF);
                            comboBoxTo.setModel(cBoxModelLengthT);
                            break;
                        }
                        case "Area" : {
                            comboBoxFrom.setModel(cBoxModelAreaF);
                            comboBoxTo.setModel(cBoxModelAreaT);
                            break;
                        }
                        case "Volume" : {
                            comboBoxFrom.setModel(cBoxModelVolumeF);
                            comboBoxTo.setModel(cBoxModelVolumeT);
                            break;
                        }
                        case "Mass" : {
                            comboBoxFrom.setModel(cBoxModelMassF);
                            comboBoxTo.setModel(cBoxModelMassT);
                            break;
                        }
                        case "Time" : {
                            comboBoxFrom.setModel(cBoxModelTimeF);
                            comboBoxTo.setModel(cBoxModelTimeT);
                            break;
                        }
                        case "Speed" : {
                            comboBoxFrom.setModel(cBoxModelSpeedF);
                            comboBoxTo.setModel(cBoxModelSpeedT);
                            break;
                        }
                        case "Temperature" : {
                            comboBoxFrom.setModel(cBoxModelTemperatureF);
                            comboBoxTo.setModel(cBoxModelTemperatureT);
                            break;
                        }
                        case "Power" : {
                            comboBoxFrom.setModel(cBoxModelPowerF);
                            comboBoxTo.setModel(cBoxModelPowerT);
                            break;
                        }
                        case "Pressure" : {
                            comboBoxFrom.setModel(cBoxModelPressureF);
                            comboBoxTo.setModel(cBoxModelPressureT);
                            break;
                        }
                        case "Energy" : {
                            comboBoxFrom.setModel(cBoxModelEnergyF);
                            comboBoxTo.setModel(cBoxModelEnergyT);
                            break;
                        }
                        case "Data Sizes" : {
                            comboBoxFrom.setModel(cBoxModelDataSizeF);
                            comboBoxTo.setModel(cBoxModelDataSizeT);
                            break;
                        }
                        case "Number System" : {
                            comboBoxFrom.setModel(cBoxModelNumberSystemF);
                            comboBoxTo.setModel(cBoxModelNumberSystemT);
                            btnDot.setEnabled(false);
                            break;
                        }
                        default : break;
                    }
                } else if (e.getSource() == btnClear) {
                    tFieldInput.setText("");
                    tFieldOutput.setText("");
                    btnDot.setEnabled(true);
                } else if (e.getSource() == btnDel) {
                    String text = tFieldInput.getText();
                    if (text.length() > 0) {
                        tFieldInput.setText(text.substring(0, text.length()-1));
                        if (text.charAt(text.length()-1) == '.') {
                            btnDot.setEnabled(true);
                        }
                        try {
                            convertData();
                        } catch (Exception exception) {
                            tFieldOutput.setText("");
                        }
                    } else {
                        tFieldInput.setText("");
                    }
                } else if (e.getSource() == btnDot) {
                    tFieldInput.setText(tFieldInput.getText()+((JButton)e.getSource()).getText());
                    btnDot.setEnabled(false);
                } else {
                    tFieldInput.setText(tFieldInput.getText()+((JButton)e.getSource()).getText());
                    if (comboBoxFrom.getSelectedIndex() != -1 && comboBoxTo.getSelectedIndex() != -1 && tFieldInput.getText().length() >= 0) {
                        convertData();
                    }
                }
            }
        };

        comboBoxMain.addActionListener(converterListener);
        btnClear.addActionListener(converterListener);
        btnDel.addActionListener(converterListener);
        btn0.addActionListener(converterListener);
        btn00.addActionListener(converterListener);
        btn1.addActionListener(converterListener);
        btn2.addActionListener(converterListener);
        btn3.addActionListener(converterListener);
        btn4.addActionListener(converterListener);
        btn5.addActionListener(converterListener);
        btn6.addActionListener(converterListener);
        btn7.addActionListener(converterListener);
        btn8.addActionListener(converterListener);
        btn9.addActionListener(converterListener);
        btnDot.addActionListener(converterListener);

        wrapperButtons1.add(btn7);
        wrapperButtons1.add(btn8);
        wrapperButtons1.add(btn9);
        wrapperButtons1.add(btnClear);
        wrapperButtons2.add(btn4);
        wrapperButtons2.add(btn5);
        wrapperButtons2.add(btn6);
        wrapperButtons2.add(btnBuffer1);
        wrapperButtons3.add(btn1);
        wrapperButtons3.add(btn2);
        wrapperButtons3.add(btn3);
        wrapperButtons3.add(btnBuffer2);
        wrapperButtons4.add(btn00);
        wrapperButtons4.add(btn0);
        wrapperButtons4.add(btnDot);
        wrapperButtons4.add(btnDel);

        wrapperButtons.add(wrapperButtons1);
        wrapperButtons.add(wrapperButtons2);
        wrapperButtons.add(wrapperButtons3);
        wrapperButtons.add(wrapperButtons4);

        converterPanel.add(wrapperMain);
        converterPanel.add(wrapperInput);
        converterPanel.add(wrapperOutput);
        converterPanel.add(wrapperButtons);
    }

    String evaluateAdvance(String expression) {
        if (isSimple(expression)) {
            return evaluateBasic(expression).toString();
        } else {
            if (expression.contains("sin")) {
                return evaluateTrig(expression, "sin");
            }
            if (expression.contains("cos")) {
                return evaluateTrig(expression, "cos");
            }
            if (expression.contains("tan")) {
                return evaluateTrig(expression, "tan");
            }
            if (expression.contains("asin")) {
                return evaluateTrig(expression, "asin");
            }
            if (expression.contains("acos")) {
                return evaluateTrig(expression, "acos");
            }
            if (expression.contains("atan")) {
                return evaluateTrig(expression, "atan");
            }
            if (expression.contains("log")) {
                return evaluateTrig(expression, "log");
            }
            if (expression.contains("ln")) {
                return evaluateTrig(expression, "ln");
            }
        }
        return "";
    }

    String evaluateTrig(String expression, String advance) {
        int s = expression.indexOf(advance);
        int start = s + advance.length();
        String inside = getInsideBracket(expression, start);
        String exp;
        if (isSimple(inside)) {
            Double result = performAdvanceOP(inside, advance);
            exp = expression.substring(0, start-advance.length()) + result + expression.substring(start+inside.length()+2);
        } else {
            exp = expression.substring(0, start+1)+ evaluateAdvance(inside) +expression.substring(start+inside.length()+1);
        }
        return evaluateAdvance(exp);
    }

    Double performAdvanceOP(String expression, String type) {
        Double result = 0.0;
        if (type.equals("sin")) {
            result = Math.sin(Math.toRadians(evaluateBasic(expression)));
        } if (type.equals("cos")) {
            result = Math.cos(Math.toRadians(evaluateBasic(expression)));
        } if (type.equals("tan")) {
            result = Math.tan(Math.toRadians(evaluateBasic(expression)));
        } if (type.equals("asin")) {
            result = Math.toDegrees(Math.asin(evaluateBasic(expression)));
        } if (type.equals("acos")) {
            result = Math.toDegrees(Math.acos(evaluateBasic(expression)));
        } if (type.equals("atan")) {
            result = Math.toDegrees(Math.atan(evaluateBasic(expression)));
        } if (type.equals("log")) {
            result = Math.log10(evaluateBasic(expression));
        } if (type.equals("ln")) {
            result = Math.log(evaluateBasic(expression));
        }
        return Double.parseDouble(new DecimalFormat("##.#####").format(result));
    }

    Double evaluateBasic(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                double num = 0;
                String numString = "";

                while (Character.isDigit(c) || c == '.') {
                    numString += c;
                    i++;

                    if (i < expression.length()) {
                        c = expression.charAt(i);
                    } else {
                        break;
                    }
                }
                num = Double.parseDouble(numString);
                i--;

                numbers.push(num);

            } else if (c == '(') {
                operators.push(c);

            } else if (c == ')') {
                while (operators.peek() != '(') {
                    double result = performBasicOP(numbers, operators);
                    numbers.push(result);
                }
                operators.pop();

            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    double result = performBasicOP(numbers, operators);
                    numbers.push(result);
                }
                operators.push(c);

            }
        }
        while (!operators.isEmpty()) {
            double result = performBasicOP(numbers, operators);
            numbers.push(result);
        }
        return numbers.pop();
        
    }

    Double performBasicOP(Stack<Double> numbers, Stack<Character> operators) {
        double var2 = numbers.pop();
        double var1 = 0.0;
        try {
            var1 = numbers.pop();
        } catch (EmptyStackException e) {
        }
        char operator = operators.pop();

        switch (operator) {
            case '^':
                return Math.pow(var1, var2);
            case '+':
                return var1 + var2;
            case '-':
                return var1 - var2;
            case '%':
                return var1 % var2;
            case '*':
                return var1 * var2;
            case '/':
                return var1 / var2;
        }
        return 0.0;
    }

    boolean isSimple(String expression) {
        if (expression.contains("sin")) {
            return false;
        } else if (expression.contains("cos")) {
            return false;
        } else if (expression.contains("tan")) {
            return false;
        } else if (expression.contains("log")) {
            return false;
        } else if (expression.contains("ln")) {
            return false;
        }
        return true;
    }

    String getInsideBracket(String expression, int startBracket) {
        int index = startBracket;
        int bracket = 0;
        String result = "";

        index++;
        while (true) {
            char c = expression.charAt(index);
            if (c == ')' && bracket == 0) {
                break;
            } else if (c == '(') {
                bracket += 1;
            } else if (c == ')') {
                bracket -= 1;
            }
            result += c;
            index++;
        }
        return result;
    }

    int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^');
    }

    void convertData() {
        String dimension = (String) comboBoxMain.getSelectedItem();
        String selectionFrom = (String) comboBoxFrom.getSelectedItem();
        String selectionTo = (String) comboBoxTo.getSelectedItem();
        Double value = Double.parseDouble(tFieldInput.getText());

        double result = 0.0;

        if (dimension.equals("Temperature")) {
            result = convertTemp();
        } else if (dimension.equals("Number System")) {
            result = convertNumber();
        } else {
            result = value / getConvertData(selectionFrom);
            result = result * getConvertData(selectionTo);
        }

        double fpart = result - (long)result;

        if (fpart == 0.0) {
            tFieldOutput.setText((long)result + "");
        } else {
            tFieldOutput.setText(result+"");
        }
    }

    Double convertTemp() {
        String selectionFrom = (String) comboBoxFrom.getSelectedItem();
        String selectionTo = (String) comboBoxTo.getSelectedItem();
        Double value = Double.parseDouble(tFieldInput.getText());

        Double result = 0.0;

        if (selectionFrom.equals("Degrees Celsius")) {
            result = value + 273.15;
        } else if (selectionFrom.equals("Kelvins")) {
            result = value;
        } else if (selectionFrom.equals("Degrees Fahrenheit")) {
            result = (value - 32) * ((double)5/9) + 273.15;
        } else if (selectionFrom.equals("Degrees Rankine")) {
            result = value * ((double)5/9);
        } else if (selectionFrom.equals("Degrees Newton")) {
            result = (value / ((double)33/100)) + 273.15;
        }

        if (selectionTo.equals("Degrees Celsius")) {
            result = result - 273.15;
        } else if (selectionTo.equals("Kelvins")) {
            result = result*1;
        } else if (selectionTo.equals("Degrees Fahrenheit")) {
            result = (result-273.15) * ((double)9/5) + 32;
        } else if (selectionTo.equals("Degrees Rankine")) {
            result = result * ((double)9/5);
        } else if (selectionTo.equals("Degrees Newton")) {
            result = (result - 273.15) * ((double)33/100);
        }

        return result;
    }

    Double convertNumber() {
        String selectionFrom = (String) comboBoxFrom.getSelectedItem();
        String selectionTo = (String) comboBoxTo.getSelectedItem();
        Integer value = Integer.parseInt(tFieldInput.getText());

        Integer result = 0;

        if (selectionFrom.equals("Decimal")) {
            result = value;
        } else if (selectionFrom.equals("Binary")) {
            result = Integer.parseInt(value+"", 2);
        } else if (selectionFrom.equals("Octal")) {
            result = Integer.parseInt(value+"", 8);
        } else if (selectionFrom.equals("Hexadecimal")) {
            result = Integer.parseInt(value+"", 16);
        }

        if (selectionTo.equals("Decimal")) {
            result = result*1;
        } else if (selectionTo.equals("Binary")) {
            result = Integer.parseInt(Integer.toBinaryString(result));
        } else if (selectionTo.equals("Octal")) {
            result = Integer.parseInt(Integer.toOctalString(result));
        } else if (selectionTo.equals("Hexadecimal")) {
            result = Integer.parseInt(Integer.toHexString(result));
        }

        return (double)result;
    }

    Double getConvertData(String selection) {
        final double metres = 1;
        final double deciMetres = 10;
        final double centiMetres = 100;
        final double milliMetres = 1000;
        final double microMetres = 1000000;
        final double nanoMetres = 1000000000.0;
        final double angstrom = 10000000000.0;
        final double kiloMetres = 0.001;
        final double yards = 1.09361329833771;
        final double inches = 39.3700787401575;
        final double feet = 3.28083989501312;
        final double miles = 0.00062137119224;
        final double nauticMiles = 0.00053995680346;

        final double squareMetres = 1;
        final double squareCentiMetres = 10000;
        final double squareMilliMetres = 1000000;
        final double hectares = 0.0001;
        final double squareKiloMetres = 0.000001;
        final double squareInches = 1550.0031000062;
        final double squareFeet = 10.7639104167097;
        final double squareYards = 1.19599004630108;
        final double Acres = 0.00024710538147;
        final double squareMiles = 0.000000386102158542446;

        final double litres = 1;
        final double milliLitres = 1000;
        final double cubicCentiMetres = 1000;
        final double cubicDeciMetres = 1;
        final double hectoLitres = 0.01;
        final double cubicMetres = 0.001;
        final double cubicInches = 61.0237440947323;
        final double cubicFeet = 0.03531466672149;
        final double cubicYards = 0.00130795061931;
        final double barrels = 0.00628981077043;
        final double gallonsUS = 0.26417205235815;
        final double gallonsIMP = 0.21996924829909;

        final double grams = 1;
        final double milliGrams = 1000;
        final double microGrams = 1000000;
        final double kiloGrams = 0.001;
        final double tonnes = 0.000001;
        final double ounces = 0.03527396194958;
        final double pounds = 0.00220462262185;
        final double carats = 5;

        final double seconds = 1;
        final double milliSeconds = 1000;
        final double microSeconds = 1000000;
        final double minutes = 0.01666666666666666666;
        final double hours = 0.0002777777777777777777;
        final double days = 0.00001157407407407407407;
        final double weeks = 0.0000016534391534391534;
        final double years = 0.0000000317097919837646;

        final double metresPSecond = 1;
        final double metresPHour = 1;
        final double kiloMetresPSecond = 1;
        final double kiloMetresPHour = 1;
        final double feetPSecond = 1;
        final double milesPSecond = 1;
        final double milesPHour = 1;
        final double knots = 1;

        final double watts = 1;
        final double kiloWatts = 0.001;
        final double megaWatts = 0.000001;
        final double gigaWatts = 0.000000001;
        final double joulesPHour = 3600;
        final double kiloJoulesPHour = 3.6;
        final double caloriesPSecond = 0.2388458966275;
        final double caloriesPHour = 859.845227858985;
        final double kiloCaloriesPSecond = 0.00023884589663;
        final double kiloCaloriesPHour = 0.85984522785899;
        final double horsePowerIMP = 0.00134102208884;

        final double pascals = 1;
        final double milliPascals = 1000;
        final double microPascals = 1000000;
        final double hectoPascals = 0.01;
        final double kiloPascals = 0.001;
        final double megaPascals = 0.000001;
        final double gigaPascals = 0.000000001;
        final double atmospheres = 0.00000986923266716013;
        final double bars = 0.00001;
        final double milliBars = 0.01;
        final double microBars = 10;
        final double torrs = 0.00750061682704;
        final double milliTorrs = 7.5006168270417;
        final double poundsPSqFoot = 0.02088543423315;
        final double millisOfMercury = 0.00750061575846;

        final double joules = 1;
        final double milliJoules = 1000;
        final double microJoules = 1000000;
        final double nanoJoules = 1000000000;
        final double kiloJoules = 0.001;
        final double megaJoules = 0.000001;
        final double newtonMetres = 1;
        final double wattHours = 0.000277777777777;
        final double kiloWattHours = 0.000000277777777;
        final double megaWattHours = 0.000000000277777;
        final double calories = 0.2388458966275;
        final double kiloCalories = 0.00023884589663;
        final double britishThermalUnits = 0.00094781712031;
        final double ergs = 10000000;
        final double electronvolt = 6241509343260180000.0;
        final double kiloElectronvolt = 6241509343260180.0;
        final double megaElectronvolt = 6241509343260.18;
        final double gigaElectronvolt = 6241509343.26018;

        final double bytes = 1;
        final double kiloBytes = 0.001;
        final double megaBytes = 0.000001;
        final double gigaBytes = 0.000000001;
        final double teraBytes = 0.000000000001;
        final double petaBytes = 0.000000000000001;
        final double bits = 8;

        switch (selection) {
            case "Metres" : return metres;
            case "Decimetres" : return deciMetres;
            case "Centimetres" : return centiMetres;
            case "Millimetres" : return milliMetres;
            case "Micrometres" : return microMetres;
            case "Nanometres" : return nanoMetres;
            case "Angstrom" : return angstrom;
            case "Kilometres" : return kiloMetres;
            case "Yards" : return yards;
            case "Inches" : return inches;
            case "Feet" : return feet;
            case "Miles" : return miles;
            case "Nautic Miles" : return nauticMiles;

            case "Square Metres" : return squareMetres;
            case "Square Centimetres" : return squareCentiMetres;
            case "Square Millimetres" : return squareMilliMetres;
            case "Hectares" : return hectares;
            case "Square Kilometres" : return squareKiloMetres;
            case "Square Inches" : return squareInches;
            case "Square Feet" : return squareFeet;
            case "Square Yards" : return squareYards;
            case "Acres" : return Acres;
            case "Square Miles" : return squareMiles;

            case "Litres" : return litres;
            case "MilliLitres" : return milliLitres;
            case "Cubic Centimetres" : return cubicCentiMetres;
            case "Cubic Decimetres" : return cubicDeciMetres;
            case "Hectolitres" : return hectoLitres;
            case "Cubic Metres" : return cubicMetres;
            case "Cubic Inches" : return cubicInches;
            case "Cubic Feet" : return cubicFeet;
            case "Cubic Yards" : return cubicYards;
            case "Barrels" : return barrels;
            case "Gallons (US)" : return gallonsUS;
            case "Gallons (Imp)" : return gallonsIMP;

            case "Grams" : return grams;
            case "Milligrams" : return milliGrams;
            case "Micrograms" : return microGrams;
            case "Kilograms" : return kiloGrams;
            case "Tonnes" : return tonnes;
            case "Ounces" : return ounces;
            case "Pounds" : return pounds;
            case "Carats" : return carats;

            case "Seconds" : return seconds;
            case "Milliseconds" : return milliSeconds;
            case "Microseconds" : return microSeconds;
            case "Minutes" : return minutes;
            case "Hours" : return hours;
            case "Days" : return days;
            case "Weeks" : return weeks;
            case "Years" : return years;

            case "Metres per Second" : return metresPSecond;
            case "Metres per Hour" : return metresPHour;
            case "Kilometres per Second" : return kiloMetresPSecond;
            case "Kilometres per Hour" : return kiloMetresPHour;
            case "Feet per Second" : return feetPSecond;
            case "Miles per Second" : return milesPSecond;
            case "Miles per Hour" : return milesPHour;
            case "Knots" : return knots;

            case "Watts" : return watts;
            case "Kilowatts" : return kiloWatts;
            case "Megawatts" : return megaWatts;
            case "Gigawatts" : return gigaWatts;
            case "Joules per Hour" : return joulesPHour;
            case "Kilojoules per Hour" : return kiloJoulesPHour;
            case "Calories per Second" : return caloriesPSecond;
            case "Calories per Hour" : return caloriesPHour;
            case "Kilocalories per Second" : return kiloCaloriesPSecond;
            case "Kilocalories per Hour" : return kiloCaloriesPHour;
            case "Horsepowers (Imp)" : return horsePowerIMP;

            case "Pascals" : return pascals;
            case "Millipascals" : return milliPascals;
            case "Micropascals" : return microPascals;
            case "Hectopascals" : return hectoPascals;
            case "Kilopascals" : return kiloPascals;
            case "Megapascals" : return megaPascals;
            case "Gigapascals" : return gigaPascals;
            case "Atmospheres" : return atmospheres;
            case "Bars" : return bars;
            case "Millibars" : return milliBars;
            case "Microbars" : return microBars;
            case "Torrs" : return torrs;
            case "Millitorrs" : return milliTorrs;
            case "Pounds per Square Foot" : return poundsPSqFoot;
            case "Millimetres of Mercury (0 C)" : return millisOfMercury;

            case "Joules" : return joules;
            case "Millijoules" : return milliJoules;
            case "Microjoules" : return microJoules;
            case "Nanojoules" : return nanoJoules;
            case "Kilojoules" : return kiloJoules;
            case "Megajoules" : return megaJoules;
            case "Newton-metres" : return newtonMetres;
            case "Watt-hours" : return wattHours;
            case "Kilowatt-hours" : return kiloWattHours;
            case "Megawatt-hours" : return megaWattHours;
            case "Calories" : return calories;
            case "Kilocalories" : return kiloCalories;
            case "British Thermal Units (ISO)" : return britishThermalUnits;
            case "Ergs" : return ergs;
            case "Electronvolt" : return electronvolt;
            case "Kiloelectronvolt" : return kiloElectronvolt;
            case "Megaelectronvolt" : return megaElectronvolt;
            case "Gigaelectronvolt" : return gigaElectronvolt;

            case "Bytes" : return bytes;
            case "Kilobytes" : return kiloBytes;
            case "Megabytes" : return megaBytes;
            case "Gigabytes" : return gigaBytes;
            case "Terabytes" : return teraBytes;
            case "Petabytes" : return petaBytes;
            case "Bits" : return bits;

            default : 
                return 1.0;
        }
    }

}
