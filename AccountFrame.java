import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class AccountFrame  extends JFrame {
    JLabel accNoLBL, ownerLBL, balanceLBL, CityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT,amountTXT;
    JComboBox<City> citiesCMB;
    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB, SelectGenderRDB;
    ButtonGroup genderBTNGRP;
    JList<Account> accountLST;
    JPanel p1,p2,p3,p4,p5;
    Set<Account>accountSet = new TreeSet<>();
    Account acc, X;
    boolean newRec = true;
    //Combobox Data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;

    //Table Data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    public AccountFrame(){
        super("Account Operations");
        setLayout(null);
        setSize(600,400);

        //Adding Components to the Frame
        // 1- labels
        accNoLBL = new JLabel("Account No.");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        CityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");
        //2- TextFields
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT =  new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));
        //3 -ComboBox
        citiesCMBMDL= new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("Nairobi", "Nairobi"));
        citiesCMBMDL.addElement(new City("Thika", "Kiambu"));
        citiesCMBMDL.addElement(new City("Ruiru", "Kiambu"));
        citiesCMBMDL.addElement(new City("Juja", "Kiambu"));
        citiesCMBMDL.addElement(new City("Naivasha","Nakuru"));
        citiesCMBMDL.addElement(new City("Nakuru", "Nakuru"));
        citiesCMBMDL.addElement(new City("Machakos", "Machakos"));
        //Adding data to JCombobox

        citiesCMB = new JComboBox<City>(citiesCMBMDL);

        //4- Radio Buttons
       // SelectGenderRDB= new JRadioButton("Select Gender", true);
        maleRDB= new JRadioButton("Male");
        femaleRDB= new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
       // genderBTNGRP.add(SelectGenderRDB);
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        //5- Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        //6- Table
        accountsLSTMDL = new DefaultListModel<>();
        accountLST= new JList<>(accountsLSTMDL);

        //7- panels
        p1= new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5, 195, 600,40 );
        p3.setLayout(new FlowLayout());

        p4= new JPanel(); p4.setBounds( 305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5= new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());

        //adding components to panel
        p1.add(accNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(CityLBL);
        p1.add(citiesCMB);
       // p1.add(SelectGenderRDB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountLST);


        //adding panels to Frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

//table creation
        tableModel = new DefaultTableModel();
        table= new JTable(tableModel);
        tableModel.addColumn("TrsNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");
        JScrollPane scrollPane= new JScrollPane(table);
        p5.add(scrollPane);
        /*** Functionalities***/
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            accNoTXT.setText("");
            ownerLBL.setText("");
            citiesCMB.setSelectedIndex(0);
            maleRDB.setSelected(true);
            //SelectGenderRDB.setSelected(true);
           // SelectGenderRDB.setSelected(true);
            balanceTXT.setText("");
            amountTXT.setText("");
            newRec= true;

            }
        });
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //insertion
                if (newRec){
                    if (ownerTXT.getText().length()!=0){
                        acc= new Account(
                                ownerTXT.getText(),
                                (City) citiesCMB.getSelectedItem(),
                                maleRDB.isSelected()? 'M': 'F');
                        accNoTXT.setText(String.valueOf(acc.accNo));
                        accountSet.add(acc);
                        accountsLSTMDL.addElement(acc);
                        newRec = false;

                    }else{
                        JOptionPane.showMessageDialog(null,"Fill All Fields");
                    }
                    //updating
                }else {
                    accountSet.remove(acc);
                    int a= Integer.parseInt(accNoTXT.getText());
                    String o= ownerTXT.getText();
                    City c= (City) citiesCMB.getSelectedItem();
                    char g= maleRDB.isSelected()?'M': 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountsLSTMDL.setElementAt(acc,accountLST.getSelectedIndex());
                    newRec= false;


                }
            }
        });

    }
    public static void main (String[]args){
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
