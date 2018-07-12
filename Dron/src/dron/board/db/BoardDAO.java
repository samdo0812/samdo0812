package dron.board.db; 

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource;

import dron.board.db.BoardBean; 

public class BoardDAO { 
    Connection con; 
    PreparedStatement pstmt; 
    ResultSet rs; 
    public BoardDAO() { 
        try{ 
            Context init = new InitialContext(); 
              DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB"); 
              con = ds.getConnection(); 
        }catch(Exception ex){ 
            System.out.println("DB ���� ���� : " + ex); 
            return; 
        } 
    } 
     
    /*���� ���� ���ϱ�(��ü ���� ������ ��ȯ�Ѵ�) 
     * ���� ������ ǥ�� �� ����¡ó���� �Ҷ� ���ȴ�.*/ 
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)�Ѱ��� x�� �����Ѵ�. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount ����: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
     
    /*�� ��� ���� 
     * List��ü�� ��ȯ�Ѵ�. �μ�1) ����� ������, �μ�2) �� �������� ǥ���� �� ��*/ 
    public List getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from board order by "+ 
        "BOARD_RE_REF desc,BOARD_RE_SEQ desc)) "+ 
        "where rnum>=? and rnum<=?"; 
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*10+1; //�б� ������ row ��ȣ. 
        int endrow=startrow+limit-1; //���� ������ row ��ȣ.         
        try{ 
            pstmt = con.prepareStatement(board_list_sql); 
            pstmt.setInt(1, startrow); 
            pstmt.setInt(2, endrow); 
            rs = pstmt.executeQuery(); 
             
            while(rs.next()){ 
                BoardBean board = new BoardBean(); 
                board.setBOARD_NUM(rs.getInt("BOARD_NUM")); 
                board.setBOARD_NAME(rs.getString("BOARD_NAME")); 
                board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT")); 
                board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT")); 
                board.setBOARD_FILE(rs.getString("BOARD_FILE")); 
                board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); 
                board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); 
                board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); 
                board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT")); 
                board.setBOARD_DATE(rs.getDate("BOARD_DATE")); 
                list.add(board); 
            } 
             
            return list; 
        }catch(Exception ex){ 
            System.out.println("getBoardList ���� : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    /*�� ��� 
     * 1) board_num�ʵ��� �ִ밪�� ���´�. ������ �ߺ����� ���� ���� �������� ���� 
     * 2) �۵������ : �۾���� �����̹Ƿ� �亯�� ���õ� �ʵ�� ��� 0����..ref��(�۱׷��ȣ�� ���ο� ��ȣ�� ����)*/ 
    public boolean boardInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //���� ��ϵǾ������� �� ��ȣ +1 
            else 
                num=1;//�� ����� �Ǿ����� ������ num=1�� 
             
            sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"; 
            sql+="BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,"+ 
                "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"+ 
                "BOARD_DATE) values(?,?,?,?,?,?,?,?,?,sysdate)"; 
             
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, num); 
            pstmt.setString(2, board.getBOARD_NAME()); 
            pstmt.setString(3, board.getBOARD_SUBJECT()); 
            pstmt.setString(4, board.getBOARD_CONTENT()); 
            pstmt.setString(5, board.getBOARD_FILE()); 
            pstmt.setInt(6, num); 
            pstmt.setInt(7, 0); 
            pstmt.setInt(8, 0); 
            pstmt.setInt(9, 0); 
             
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardInsert ���� : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
   
} 

