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
        super("성적관리 프로그램");
        
        try {
            /* Look and Feel 기능을 사용하여 윈도우 테마로 변경 */
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e){ e.printStackTrace(); }
        
        /* 모든 패널을 나열 해놓은 패널 (BoxLayout 세로) */
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        /* 타이틀 레이블을 넣어놓은 패널 */
        titlePanel = new JPanel();
        lblTitle = new JLabel("성적관리 프로그램");
        lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        titlePanel.add(lblTitle);

        /* 기능 버튼을 나열해놓은 패널 */
        menuPanel = new JPanel();
        btnMenuInput      = new JButton("추가"); //버튼객체를 생성
        btnMenuPrint      = new JButton("출력");
        btnMenuSearch       = new JButton("검색");
        btnMenuDelete     = new JButton("삭제");
        btnMenuModify     = new JButton("수정");
        menuPanel.add(btnMenuInput);         //버튼을 추가함
        menuPanel.add(btnMenuPrint);
        menuPanel.add(btnMenuSearch);
        menuPanel.add(btnMenuDelete);
        menuPanel.add(btnMenuModify);
        btnMenuInput.addActionListener(this);    //액션 리스너를 등록시켜줌
        btnMenuPrint.addActionListener(this);
        btnMenuSearch.addActionListener(this);
        btnMenuDelete.addActionListener(this);
        btnMenuModify.addActionListener(this);
        
        /* 추가 기능을 담당하는 패널 */
        dataInputPanel = new JPanel();
        txtStudentID   = new JTextField(8);
        txtName        = new JTextField(10);
        txtKor         = new JTextField(5);
        txtEng         = new JTextField(5);
        txtMath        = new JTextField(5);
        btnSubmit      = new JButton("입력");
        dataInputPanel.add(new JLabel("학번 : "));
        dataInputPanel.add(txtStudentID);
        dataInputPanel.add(new JLabel("이름 : "));
        dataInputPanel.add(txtName);
        dataInputPanel.add(new JLabel("국어 : "));
        dataInputPanel.add(txtKor);
        dataInputPanel.add(new JLabel("영어 : "));
        dataInputPanel.add(txtEng);
        dataInputPanel.add(new JLabel("수학 : "));
        dataInputPanel.add(txtMath);
        dataInputPanel.add(btnSubmit);
        dataInputPanel.setBorder(BorderFactory.createTitledBorder("정보 입력"));
        
        /* 람다식을 배웠으니 써먹어보자! (단 Java8이상의 버젼에서만 지원됨)
           예) txtMath에서 Return키를 누르면 input()메서드를 호출함 */
        txtStudentID.addActionListener( ae -> { txtName.requestFocus(); } ); //txtStudentID에서 Return키를 누르면 다음칸으로 이동
        txtName.addActionListener     ( ae -> { txtKor.requestFocus(); } ); 
        txtKor.addActionListener      ( ae -> { txtEng.requestFocus(); } );
        txtEng.addActionListener      ( ae -> { txtMath.requestFocus(); } );
        txtMath.addActionListener     ( ae -> { input(); } );
        btnSubmit.addActionListener   (this);
        dataInputPanel.setVisible(false); //일단 숨겨두고 추가 버튼을 클릭했을 때 보이게 설정

        /* 데이터 출력 기능을 담당하는 패널 */
        dataViewPanel = new JPanel();
        txtData       = new JTextArea(10, 80); //JTextArea(rows, columns)
        txtScrollPane = new JScrollPane(txtData);
        txtData.setFont(new Font("굴림체", Font.PLAIN, 18)); //예쁘게 보이기 위해서는 고정폭 글꼴을 사용해야하므로 굴림체로 글꼴 변경
        txtData.setEnabled(false); //txtData를 수정 불가능하게 잠그기
        txtData.setDisabledTextColor(Color.BLACK); //TextArea, TextField에서는 Enabled를 false로 변경시 글씨색이 회색으로 바뀌기 때문에 검정색으로 바꿔줌
        dataViewPanel.add(txtScrollPane);
        dataViewPanel.setBorder(BorderFactory.createTitledBorder("학생 정보"));
        dataViewPanel.setVisible(false);


        /* 데이터 검색 기능을 담당하는 패널 */
        dataSearchPanel  = new JPanel();
        txtKeyword       = new JTextField(5);
        btnSearchStudent = new JButton("검색하기");
        rbName           = new JRadioButton("이름");
        rbSum            = new JRadioButton("총점");
        rbKor            = new JRadioButton("국어");
        rbEng            = new JRadioButton("영어");
        rbMath           = new JRadioButton("수학");
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
        dataSearchPanel.setBorder(BorderFactory.createTitledBorder("정보 검색"));
        btnSearchStudent.addActionListener(this);
        dataSearchPanel.setVisible(false);
        
        /* 데이터 삭제 기능을 담당하는 패널 */
        dataDeletePanel  = new JPanel();
        txtDelIDNum   = new JTextField(8);
        btnDeleteStudent = new JButton("삭제하기");
        dataDeletePanel.add(new JLabel("삭제할 학생의 학번 : "));
        dataDeletePanel.add(txtDelIDNum);
        dataDeletePanel.add(btnDeleteStudent);
        txtDelIDNum.addActionListener( ae -> { delete(); });
        dataDeletePanel.setBorder(BorderFactory.createTitledBorder("정보 삭제"));
        btnDeleteStudent.addActionListener(this);
        dataDeletePanel.setVisible(false);
        
        /* 데이터 수정 기능을 담당하는 패널 */
        dataModifyPanel  = new JPanel();
        txtModStuIDNum   = new JTextField(8);
        txtModKor        = new JTextField(4);
        txtModEng        = new JTextField(4);
        txtModMath       = new JTextField(4);
        btnModSearch     = new JButton("찾기");
        btnModStudent    = new JButton("수정하기");
        
        dataModifyPanel.add(new JLabel("수정할 학생의 학번 : "));
        dataModifyPanel.add(txtModStuIDNum);
        dataModifyPanel.add(btnModSearch);
        dataModifyPanel.add(new JLabel("국어 : "));
        dataModifyPanel.add(txtModKor);
        dataModifyPanel.add(new JLabel("영어 : "));
        dataModifyPanel.add(txtModEng);
        dataModifyPanel.add(new JLabel("수학 : "));
        dataModifyPanel.add(txtModMath);
        dataModifyPanel.add(btnModStudent);
        txtModStuIDNum.addActionListener ( ae -> { search(txtModStuIDNum.getText()); });
        txtModKor.addActionListener      ( ae -> { modify(); });
        txtModEng.addActionListener      ( ae -> { modify(); });
        txtModMath.addActionListener     ( ae -> { modify(); });
        btnModSearch.addActionListener   ( ae -> { search(txtModStuIDNum.getText()); });
        dataModifyPanel.setBorder(BorderFactory.createTitledBorder("정보 수정"));
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

        this.setResizable(false);                       //프레임의 크기조절을 불가능하게 만들기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close버튼 클릭시 완전히 종료되도록 설정
        setFrameCenterPack();                           //프레임을 컴포넌트 크기에 맞추고 화면 중앙에 이동시키는 메서드
        setVisible(true);                               //프레임을 보이게 설정
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String btnName = e.getActionCommand();

        if(btnName.equals("추가"))
        {
            showHidePanel(true, false, false, false, false);
            txtStudentID.requestFocus(); //txtStudentID 텍스트필드에 커서를 이동
        }
        else if(btnName.equals("출력"))
        {
            if(!isEmptyMap()) //map이 empty가 아니라면
            {
                print();
                showHidePanel(false, true, false, false, false);
            }
        }
        else if(btnName.equals("검색"))
        {
            if(!isEmptyMap()) //map이 empty가 아니라면
            {
                print();
                showHidePanel(false, true, true, false, false);
                txtKeyword.requestFocus(); //txtStudentID 텍스트필드에 커서를 이동
            }
        }
        else if(btnName.equals("삭제"))
        {
            if(!isEmptyMap()) //map이 empty가 아니라면
            {
                print();
                showHidePanel(false, true, false, true, false);
                txtDelIDNum.requestFocus(); //txtStudentID 텍스트필드에 커서를 이동
            }
        }
        else if(btnName.equals("수정"))
        {
            if(!isEmptyMap()) //map이 empty가 아니라면
            {
                print();
                showHidePanel(false, true, false, false, true);
                txtModStuIDNum.requestFocus(); //txtStudentID 텍스트필드에 커서를 이동
            }
        }
        else if(btnName.equals("찾기"))
        {
            if(!isEmptyMap()) //map이 empty가 아니라면
            {
                print();
                search(txtModStuIDNum.getText());
            }
        }
        else if(btnName.equals("입력"))
        {
            input();
        }
        else if(btnName.equals("검색하기"))
        {
            search(txtKeyword.getText(), getSelectedRadioButton());
        }
        else if(btnName.equals("삭제하기"))
        {
            delete();
        }
        else if(btnName.equals("수정하기"))
        {
            modify();
        }
    }
    
    /* 프레임에서 pack()을 호출하고 프레임을 화면 중앙에 위치시키는 메서드 */
    public void setFrameCenterPack()
    {
        this.pack(); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
    }
    
    /* dataInputPanel에 들어있는 모든 텍스트 필드를 공백으로 초기화시키는 메서드 */
    public void clearTextField()
    {
        txtStudentID.setText("");
        txtName.setText(""); 
        txtKor.setText("");
        txtEng.setText("");
        txtMath.setText("");
        txtStudentID.requestFocus(); //초기화 후 커서를 학번 텍스트필드로 이동
    }
    
    /* 추가기능을 담당하는 메서드 */
    public void input()
    {
        String studentID = txtStudentID.getText();
        String name      = txtName.getText();
        int korScore     = -1;
        int engScore     = -1;
        int mathScore    = -1;
        
        if(studentID.equals("") || name.equals(""))
        {
            JOptionPane.showMessageDialog(this, "학번과 이름에는 공백을 입력하실 수 없습니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
            txtStudentID.requestFocus();
            return;
        }
        
        /* try-catch문을 사용하여 점수 입력칸에 정수형 숫자가 입력되었는지 체크 */
        try
        {
            korScore  = Integer.parseInt(txtKor.getText().trim()); //trim()은 좌우공백을 제거하는 메서드
            engScore  = Integer.parseInt(txtEng.getText().trim());
            mathScore = Integer.parseInt(txtMath.getText().trim());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "점수는 숫자만 입력 가능합니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        /*  중복되는 이름이 있는지 확인 */
        if(!map.containsKey(studentID))
        {
            //map.put(Key, Value);
            //6조는 Key값을 학번으로 설정
            map.put(studentID, new ScoreManager(studentID, name, korScore, engScore, mathScore));
            JOptionPane.showMessageDialog(this, "입력이 완료 되었습니다.", "입력 완료", JOptionPane.INFORMATION_MESSAGE);
            clearTextField(); //모든 텍스트 필드 초기화
        }
        else
        {
            JOptionPane.showMessageDialog(this, "중복 된 학번이 있습니다. 입력한 내용을 다시 확인해주세요.", "오류 발생", JOptionPane.ERROR_MESSAGE);
            txtStudentID.requestFocus();
        }
    }

    /* 출력기능을 담당하는 메서드 */
    public void print()
    {
        Set<String> keySet = map.keySet(); //Set<String> keySet에 map전체의 keySet을 저장
        Iterator<String> keyIterator = keySet.iterator();

        clearTxtData(); //txtData초기화하고 위에 학번, 이름등의 양식을 출력
        
        while(keyIterator.hasNext())
        {
            txtData.append(map.get(keyIterator.next()).toString() + "\n");
        }
    }

    /* 수정기능을 담당하는 메서드 */
    public void modify()
    {
        int korScore     = -1;
        int engScore     = -1;
        int mathScore    = -1;
        
        try
        {
            korScore  = Integer.parseInt(txtModKor.getText().trim()); //trim()은 좌우공백을 제거하는 메서드
            engScore  = Integer.parseInt(txtModEng.getText().trim());
            mathScore = Integer.parseInt(txtModMath.getText().trim());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "점수는 숫자만 입력 가능합니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
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
        
        JOptionPane.showMessageDialog(this, "수정이 완료 되었습니다.", "입력 완료", JOptionPane.INFORMATION_MESSAGE);
        
        print();
    }

    public void print(ScoreManager sm) //함수 오버로딩을 통해 함수이름 재활용
    {
            txtData.append(sm.toString() + "\n");
    }

    public void search(String sKeyword, String selected)
    {
        Set<String> keySet = map.keySet();
        Iterator<String> keyIterator = keySet.iterator();

        int iKeyword = -1;
        
        if(!selected.equals("이름")) //이름이 아닌경우 키워드를 정수형으로 전환
        {
            try
            {
                iKeyword = Integer.parseInt(sKeyword);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "이름외에는 부호를 갖지않는 정수만 입력 가능합니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
                txtKeyword.setText("");
                txtKeyword.requestFocus();
                return;
            }
        }
        
        clearTxtData();
        
        //keyIterator을 사용하여 map전체를 차례대로 순회
        while(keyIterator.hasNext())
        {
            ScoreManager temp = map.get(keyIterator.next());
            
            if(selected.equals("이름"))
            {
                if(temp.getName().equals(sKeyword)) print(temp);                
            }
            else if(selected.equals("총점"))
            {
                if(temp.getSum() >= iKeyword) print(temp);             
            }
            else if(selected.equals("국어"))
            {
                if(temp.getKor() >= iKeyword) print(temp);           
            }
            else if(selected.equals("영어"))
            {
                if(temp.getEng() >= iKeyword) print(temp);
            }
            else if(selected.equals("수학"))
            {
                if(temp.getMath() >= iKeyword) print(temp);
            }
        }
        
        JOptionPane.showMessageDialog(this, "출력이 완료 되었습니다.", "출력 완료", JOptionPane.INFORMATION_MESSAGE);
        txtKeyword.setText("");
        txtKeyword.requestFocus();
    }


    public void search(String idNum)
    {
        if(map.containsKey(idNum))
        {
            JOptionPane.showMessageDialog(this, idNum + "번의 학번을 가진 학생이 존재합니다.", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(this, idNum + "번의 학번을 가진 학생은 존재하지 않습니다.", "검색 실패", JOptionPane.ERROR_MESSAGE);
            txtModStuIDNum.requestFocus();
        }
    }

    public void delete() //함수 오버로딩을 통해 함수이름 재활용
    {
        String delTarget = txtDelIDNum.getText();
        if(map.containsKey(delTarget))
        {
            JOptionPane.showMessageDialog(this, "삭제가 완료 되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
            map.remove(delTarget);
            txtDelIDNum.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "그런 학번을 가진 학생은 존재하지 않습니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
        }
        clearTxtData(); //txtData초기화하고 위에 학번, 이름등의 양식을 출력
        txtDelIDNum.requestFocus();
        print();
    }

    
    public void showEmptyError()
    {
        JOptionPane.showMessageDialog(this, "출력 또는 수정/삭제할 내용이 없습니다.", "오류 발생", JOptionPane.ERROR_MESSAGE);
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
        txtData.append(String.format("%8s %14s %6s %6s %6s %6s %8s\n", "학번", "이름", "국어", "영어", "수학", "총점", "평균"));
        txtData.append("============================================================================\n");
    }
    
    public boolean isEmptyMap()
    {
        if(map.isEmpty())
        {
            showEmptyError(); //에러메세지를 출력
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