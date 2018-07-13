package testScore;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MyFrame extends JFrame implements ActionListener {
    public static Map<String, ScoreManager> map = new LinkedHashMap<String, ScoreManager>();
    
    JLabel lblTitle;
    JPanel mainPanel, titlePanel, menuPanel, dataInputPanel, dataSearchPanel, dataDeletePanel, dataModifyPanel, dataViewPanel;
    JTextField txtStudentID, txtName, txtKor, txtEng, txtMath, txtKeyword, txtDelIDNum, txtModStuIDNum,txtModKor, txtModEng, txtModMath;
    JButton btnMenuInput, btnMenuPrint, btnMenuSearch, btnMenuDelete, btnMenuModify, btnSubmit, btnSearchStudent, btnDeleteStudent, btnModSearch, btnModStudent;
    JRadioButton rbName, rbSum, rbKor, rbEng, rbMath;
    ButtonGroup rbGroup;
    
    JTextArea txtData;
    JScrollPane txtScrollPane;

    MyFrame()
    {
        super("�������� ���α׷�");
        
        try {
            /* Look and Feel ����� ����Ͽ� ������ �׸��� ���� */
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e){ e.printStackTrace(); }
        
        /* ��� �г��� ���� �س��� �г� (BoxLayout ����) */
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        /* Ÿ��Ʋ ���̺��� �־���� �г� */
        titlePanel = new JPanel();
        lblTitle = new JLabel("�������� ���α׷�");
        lblTitle.setFont(new Font("���� ���", Font.BOLD, 28));
        titlePanel.add(lblTitle);

        /* ��� ��ư�� �����س��� �г� */
        menuPanel = new JPanel();
        btnMenuInput      = new JButton("�߰�"); //��ư��ü�� ����
        btnMenuPrint      = new JButton("���");
        btnMenuSearch       = new JButton("�˻�");
        btnMenuDelete     = new JButton("����");
        btnMenuModify     = new JButton("����");
        menuPanel.add(btnMenuInput);         //��ư�� �߰���
        menuPanel.add(btnMenuPrint);
        menuPanel.add(btnMenuSearch);
        menuPanel.add(btnMenuDelete);
        menuPanel.add(btnMenuModify);
        btnMenuInput.addActionListener(this);    //�׼� �����ʸ� ��Ͻ�����
        btnMenuPrint.addActionListener(this);
        btnMenuSearch.addActionListener(this);
        btnMenuDelete.addActionListener(this);
        btnMenuModify.addActionListener(this);
        
        /* �߰� ����� ����ϴ� �г� */
        dataInputPanel = new JPanel();
        txtStudentID   = new JTextField(8);
        txtName        = new JTextField(10);
        txtKor         = new JTextField(5);
        txtEng         = new JTextField(5);
        txtMath        = new JTextField(5);
        btnSubmit      = new JButton("�Է�");
        dataInputPanel.add(new JLabel("�й� : "));
        dataInputPanel.add(txtStudentID);
        dataInputPanel.add(new JLabel("�̸� : "));
        dataInputPanel.add(txtName);
        dataInputPanel.add(new JLabel("���� : "));
        dataInputPanel.add(txtKor);
        dataInputPanel.add(new JLabel("���� : "));
        dataInputPanel.add(txtEng);
        dataInputPanel.add(new JLabel("���� : "));
        dataInputPanel.add(txtMath);
        dataInputPanel.add(btnSubmit);
        dataInputPanel.setBorder(BorderFactory.createTitledBorder("���� �Է�"));
        
        /* ���ٽ��� ������� ��Ծ��! (�� Java8�̻��� ���������� ������)
           ��) txtMath���� ReturnŰ�� ������ input()�޼��带 ȣ���� */
        txtStudentID.addActionListener( ae -> { txtName.requestFocus(); } ); //txtStudentID���� ReturnŰ�� ������ ����ĭ���� �̵�
        txtName.addActionListener     ( ae -> { txtKor.requestFocus(); } ); 
        txtKor.addActionListener      ( ae -> { txtEng.requestFocus(); } );
        txtEng.addActionListener      ( ae -> { txtMath.requestFocus(); } );
        txtMath.addActionListener     ( ae -> { input(); } );
        btnSubmit.addActionListener   (this);
        dataInputPanel.setVisible(false); //�ϴ� ���ܵΰ� �߰� ��ư�� Ŭ������ �� ���̰� ����

        /* ������ ��� ����� ����ϴ� �г� */
        dataViewPanel = new JPanel();
        txtData       = new JTextArea(10, 80); //JTextArea(rows, columns)
        txtScrollPane = new JScrollPane(txtData);
        txtData.setFont(new Font("����ü", Font.PLAIN, 18)); //���ڰ� ���̱� ���ؼ��� ������ �۲��� ����ؾ��ϹǷ� ����ü�� �۲� ����
        txtData.setEnabled(false); //txtData�� ���� �Ұ����ϰ� ��ױ�
        txtData.setDisabledTextColor(Color.BLACK); //TextArea, TextField������ Enabled�� false�� ����� �۾����� ȸ������ �ٲ�� ������ ���������� �ٲ���
        dataViewPanel.add(txtScrollPane);
        dataViewPanel.setBorder(BorderFactory.createTitledBorder("�л� ����"));
        dataViewPanel.setVisible(false);


        /* ������ �˻� ����� ����ϴ� �г� */
        dataSearchPanel  = new JPanel();
        txtKeyword       = new JTextField(5);
        btnSearchStudent = new JButton("�˻��ϱ�");
        rbName           = new JRadioButton("�̸�");
        rbSum            = new JRadioButton("����");
        rbKor            = new JRadioButton("����");
        rbEng            = new JRadioButton("����");
        rbMath           = new JRadioButton("����");
        rbGroup          = new ButtonGroup();
        rbGroup.add(rbName);
        rbGroup.add(rbSum);
        rbGroup.add(rbKor);
        rbGroup.add(rbEng);
        rbGroup.add(rbMath);
        dataSearchPanel.add(rbName);
        dataSearchPanel.add(rbSum);
        dataSearchPanel.add(rbKor);
        dataSearchPanel.add(rbEng);
        dataSearchPanel.add(rbMath);
        dataSearchPanel.add(txtKeyword);
        dataSearchPanel.add(btnSearchStudent);
        rbName.setSelected(true);
        txtKeyword.addActionListener( 
                ae -> {
                    search(txtKeyword.getText(), getSelectedRadioButton());
                });
        dataSearchPanel.setBorder(BorderFactory.createTitledBorder("���� �˻�"));
        btnSearchStudent.addActionListener(this);
        dataSearchPanel.setVisible(false);
        
        /* ������ ���� ����� ����ϴ� �г� */
        dataDeletePanel  = new JPanel();
        txtDelIDNum   = new JTextField(8);
        btnDeleteStudent = new JButton("�����ϱ�");
        dataDeletePanel.add(new JLabel("������ �л��� �й� : "));
        dataDeletePanel.add(txtDelIDNum);
        dataDeletePanel.add(btnDeleteStudent);
        txtDelIDNum.addActionListener( ae -> { delete(); });
        dataDeletePanel.setBorder(BorderFactory.createTitledBorder("���� ����"));
        btnDeleteStudent.addActionListener(this);
        dataDeletePanel.setVisible(false);
        
        /* ������ ���� ����� ����ϴ� �г� */
        dataModifyPanel  = new JPanel();
        txtModStuIDNum   = new JTextField(8);
        txtModKor        = new JTextField(4);
        txtModEng        = new JTextField(4);
        txtModMath       = new JTextField(4);
        btnModSearch     = new JButton("ã��");
        btnModStudent    = new JButton("�����ϱ�");
        
        dataModifyPanel.add(new JLabel("������ �л��� �й� : "));
        dataModifyPanel.add(txtModStuIDNum);
        dataModifyPanel.add(btnModSearch);
        dataModifyPanel.add(new JLabel("���� : "));
        dataModifyPanel.add(txtModKor);
        dataModifyPanel.add(new JLabel("���� : "));
        dataModifyPanel.add(txtModEng);
        dataModifyPanel.add(new JLabel("���� : "));
        dataModifyPanel.add(txtModMath);
        dataModifyPanel.add(btnModStudent);
        txtModStuIDNum.addActionListener ( ae -> { search(txtModStuIDNum.getText()); });
        txtModKor.addActionListener      ( ae -> { modify(); });
        txtModEng.addActionListener      ( ae -> { modify(); });
        txtModMath.addActionListener     ( ae -> { modify(); });
        btnModSearch.addActionListener   ( ae -> { search(txtModStuIDNum.getText()); });
        dataModifyPanel.setBorder(BorderFactory.createTitledBorder("���� ����"));
        btnModStudent.addActionListener(this);
        txtModKor.setEnabled(false);
        txtModEng.setEnabled(false);
        txtModMath.setEnabled(false);
        btnModStudent.setEnabled(false);
        dataModifyPanel.setVisible(false);
        
        
        mainPanel.add(titlePanel);
        mainPanel.add(menuPanel);
        mainPanel.add(dataInputPanel);
        mainPanel.add(dataViewPanel);
        mainPanel.add(dataSearchPanel);
        mainPanel.add(dataDeletePanel);
        mainPanel.add(dataModifyPanel);

        add(mainPanel);

        this.setResizable(false);                       //�������� ũ�������� �Ұ����ϰ� �����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close��ư Ŭ���� ������ ����ǵ��� ����
        setFrameCenterPack();                           //�������� ������Ʈ ũ�⿡ ���߰� ȭ�� �߾ӿ� �̵���Ű�� �޼���
        setVisible(true);                               //�������� ���̰� ����
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String btnName = e.getActionCommand();

        if(btnName.equals("�߰�"))
        {
            showHidePanel(true, false, false, false, false);
            txtStudentID.requestFocus(); //txtStudentID �ؽ�Ʈ�ʵ忡 Ŀ���� �̵�
        }
        else if(btnName.equals("���"))
        {
            if(!isEmptyMap()) //map�� empty�� �ƴ϶��
            {
                print();
                showHidePanel(false, true, false, false, false);
            }
        }
        else if(btnName.equals("�˻�"))
        {
            if(!isEmptyMap()) //map�� empty�� �ƴ϶��
            {
                print();
                showHidePanel(false, true, true, false, false);
                txtKeyword.requestFocus(); //txtStudentID �ؽ�Ʈ�ʵ忡 Ŀ���� �̵�
            }
        }
        else if(btnName.equals("����"))
        {
            if(!isEmptyMap()) //map�� empty�� �ƴ϶��
            {
                print();
                showHidePanel(false, true, false, true, false);
                txtDelIDNum.requestFocus(); //txtStudentID �ؽ�Ʈ�ʵ忡 Ŀ���� �̵�
            }
        }
        else if(btnName.equals("����"))
        {
            if(!isEmptyMap()) //map�� empty�� �ƴ϶��
            {
                print();
                showHidePanel(false, true, false, false, true);
                txtModStuIDNum.requestFocus(); //txtStudentID �ؽ�Ʈ�ʵ忡 Ŀ���� �̵�
            }
        }
        else if(btnName.equals("ã��"))
        {
            if(!isEmptyMap()) //map�� empty�� �ƴ϶��
            {
                print();
                search(txtModStuIDNum.getText());
            }
        }
        else if(btnName.equals("�Է�"))
        {
            input();
        }
        else if(btnName.equals("�˻��ϱ�"))
        {
            search(txtKeyword.getText(), getSelectedRadioButton());
        }
        else if(btnName.equals("�����ϱ�"))
        {
            delete();
        }
        else if(btnName.equals("�����ϱ�"))
        {
            modify();
        }
    }
    
    /* �����ӿ��� pack()�� ȣ���ϰ� �������� ȭ�� �߾ӿ� ��ġ��Ű�� �޼��� */
    public void setFrameCenterPack()
    {
        this.pack(); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
    }
    
    /* dataInputPanel�� ����ִ� ��� �ؽ�Ʈ �ʵ带 �������� �ʱ�ȭ��Ű�� �޼��� */
    public void clearTextField()
    {
        txtStudentID.setText("");
        txtName.setText(""); 
        txtKor.setText("");
        txtEng.setText("");
        txtMath.setText("");
        txtStudentID.requestFocus(); //�ʱ�ȭ �� Ŀ���� �й� �ؽ�Ʈ�ʵ�� �̵�
    }
    
    /* �߰������ ����ϴ� �޼��� */
    public void input()
    {
        String studentID = txtStudentID.getText();
        String name      = txtName.getText();
        int korScore     = -1;
        int engScore     = -1;
        int mathScore    = -1;
        
        if(studentID.equals("") || name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "�й��� �̸����� ������ �Է��Ͻ� �� �����ϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
            txtStudentID.requestFocus();
            return;
        }
        
        /* try-catch���� ����Ͽ� ���� �Է�ĭ�� ������ ���ڰ� �ԷµǾ����� üũ */
        try
        {
            korScore  = Integer.parseInt(txtKor.getText().trim()); //trim()�� �¿������ �����ϴ� �޼���
            engScore  = Integer.parseInt(txtEng.getText().trim());
            mathScore = Integer.parseInt(txtMath.getText().trim());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "������ ���ڸ� �Է� �����մϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*  �ߺ��Ǵ� �̸��� �ִ��� Ȯ�� */
        if(!map.containsKey(studentID))
        {
            //map.put(Key, Value);
            //6���� Key���� �й����� ����
            map.put(studentID, new ScoreManager(studentID, name, korScore, engScore, mathScore));
            JOptionPane.showMessageDialog(this, "�Է��� �Ϸ� �Ǿ����ϴ�.", "�Է� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
            clearTextField(); //��� �ؽ�Ʈ �ʵ� �ʱ�ȭ
        }
        else
        {
            JOptionPane.showMessageDialog(this, "�ߺ� �� �й��� �ֽ��ϴ�. �Է��� ������ �ٽ� Ȯ�����ּ���.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
            txtStudentID.requestFocus();
        }
    }

    /* ��±���� ����ϴ� �޼��� */
    public void print()
    {
        Set<String> keySet = map.keySet(); //Set<String> keySet�� map��ü�� keySet�� ����
        Iterator<String> keyIterator = keySet.iterator();

        clearTxtData(); //txtData�ʱ�ȭ�ϰ� ���� �й�, �̸����� ����� ���
        
        while(keyIterator.hasNext())
        {
            txtData.append(map.get(keyIterator.next()).toString() + "\n");
        }
    }

    /* ��������� ����ϴ� �޼��� */
    public void modify()
    {
        int korScore     = -1;
        int engScore     = -1;
        int mathScore    = -1;
        
        try
        {
            korScore  = Integer.parseInt(txtModKor.getText().trim()); //trim()�� �¿������ �����ϴ� �޼���
            engScore  = Integer.parseInt(txtModEng.getText().trim());
            mathScore = Integer.parseInt(txtModMath.getText().trim());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "������ ���ڸ� �Է� �����մϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
            return;
        }

        map.get(txtModStuIDNum.getText()).setKor(korScore);
        map.get(txtModStuIDNum.getText()).setEng(engScore);
        map.get(txtModStuIDNum.getText()).setMath(mathScore);
        
        btnModSearch.setEnabled(true);
        txtModStuIDNum.setEnabled(true);
        txtModKor.setEnabled(false);
        txtModEng.setEnabled(false);
        txtModMath.setEnabled(false);
        btnModStudent.setEnabled(false);

        txtModStuIDNum.setText("");
        txtModKor.setText("");
        txtModEng.setText("");
        txtModMath.setText("");
        
        txtModStuIDNum.requestFocus();
        
        JOptionPane.showMessageDialog(this, "������ �Ϸ� �Ǿ����ϴ�.", "�Է� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
        
        print();
    }

    public void print(ScoreManager sm) //�Լ� �����ε��� ���� �Լ��̸� ��Ȱ��
    {
            txtData.append(sm.toString() + "\n");
    }

    public void search(String sKeyword, String selected)
    {
        Set<String> keySet = map.keySet();
        Iterator<String> keyIterator = keySet.iterator();

        int iKeyword = -1;
        
        if(!selected.equals("�̸�")) //�̸��� �ƴѰ�� Ű���带 ���������� ��ȯ
        {
            try
            {
                iKeyword = Integer.parseInt(sKeyword);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "�̸��ܿ��� ��ȣ�� �����ʴ� ������ �Է� �����մϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
                txtKeyword.setText("");
                txtKeyword.requestFocus();
                return;
            }
        }
        
        clearTxtData();
        
        //keyIterator�� ����Ͽ� map��ü�� ���ʴ�� ��ȸ
        while(keyIterator.hasNext())
        {
            ScoreManager temp = map.get(keyIterator.next());
            
            if(selected.equals("�̸�"))
            {
                if(temp.getName().equals(sKeyword)) print(temp);                
            }
            else if(selected.equals("����"))
            {
                if(temp.getSum() >= iKeyword) print(temp);             
            }
            else if(selected.equals("����"))
            {
                if(temp.getKor() >= iKeyword) print(temp);           
            }
            else if(selected.equals("����"))
            {
                if(temp.getEng() >= iKeyword) print(temp);
            }
            else if(selected.equals("����"))
            {
                if(temp.getMath() >= iKeyword) print(temp);
            }
        }
        
        JOptionPane.showMessageDialog(this, "����� �Ϸ� �Ǿ����ϴ�.", "��� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
        txtKeyword.setText("");
        txtKeyword.requestFocus();
    }


    public void search(String idNum)
    {
        if(map.containsKey(idNum))
        {
            JOptionPane.showMessageDialog(this, idNum + "���� �й��� ���� �л��� �����մϴ�.", "�˻� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
            btnModSearch.setEnabled(false);
            txtModStuIDNum.setEnabled(false);
            txtModKor.setEnabled(true);
            txtModEng.setEnabled(true);
            txtModMath.setEnabled(true);
            btnModStudent.setEnabled(true);
            txtModKor.setText (Integer.toString(map.get(idNum).getKor()));
            txtModEng.setText (Integer.toString(map.get(idNum).getEng()));
            txtModMath.setText(Integer.toString(map.get(idNum).getMath()));
            txtModKor.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, idNum + "���� �й��� ���� �л��� �������� �ʽ��ϴ�.", "�˻� ����", JOptionPane.ERROR_MESSAGE);
            txtModStuIDNum.requestFocus();
        }
    }

    public void delete() //�Լ� �����ε��� ���� �Լ��̸� ��Ȱ��
    {
        String delTarget = txtDelIDNum.getText();
        if(map.containsKey(delTarget))
        {
            JOptionPane.showMessageDialog(this, "������ �Ϸ� �Ǿ����ϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
            map.remove(delTarget);
            txtDelIDNum.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "�׷� �й��� ���� �л��� �������� �ʽ��ϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
        }
        clearTxtData(); //txtData�ʱ�ȭ�ϰ� ���� �й�, �̸����� ����� ���
        txtDelIDNum.requestFocus();
        print();
    }

    
    public void showEmptyError()
    {
        JOptionPane.showMessageDialog(this, "��� �Ǵ� ����/������ ������ �����ϴ�.", "���� �߻�", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showHidePanel(boolean dataInputPanel, boolean dataViewPanel, boolean dataSearchPanel, boolean dataDeletePanel, boolean dataModifyPanel)
    {
        this.dataInputPanel.setVisible(dataInputPanel);
        this.dataViewPanel.setVisible(dataViewPanel);
        this.dataSearchPanel.setVisible(dataSearchPanel);
        this.dataDeletePanel.setVisible(dataDeletePanel);
        this.dataModifyPanel.setVisible(dataModifyPanel);
        setFrameCenterPack();
    }
    
    public void clearTxtData()
    {
        txtData.setText("");
        txtData.append(String.format("%8s %14s %6s %6s %6s %6s %8s\n", "�й�", "�̸�", "����", "����", "����", "����", "���"));
        txtData.append("============================================================================\n");
    }
    
    public boolean isEmptyMap()
    {
        if(map.isEmpty())
        {
            showEmptyError(); //�����޼����� ���
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getSelectedRadioButton()
    {
        for (Enumeration<AbstractButton> buttons = rbGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}