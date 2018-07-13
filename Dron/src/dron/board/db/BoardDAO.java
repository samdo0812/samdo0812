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
  
    //�� ���� ���� //�� ���ڵ� ��ȣ�� �μ��� �޾ƿ´�. 
    public BoardBean getDetail(int num) throws Exception{ 
        BoardBean board = null; 
        try{ 
            pstmt = con.prepareStatement( 
                    "select * from board where BOARD_NUM = ?"); 
            pstmt.setInt(1, num); 
             
            rs= pstmt.executeQuery(); 
             
            if(rs.next()){ 
                board = new BoardBean(); 
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
            } 
            return board; 
        }catch(Exception ex){ 
            System.out.println("getDetail ���� : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt !=null)try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
    
  //��ȸ�� ������Ʈ(�� ������ Ȯ���ϴ� ���� ȣ��ȴ�) 
    public void setReadCountUpdate(int num) throws Exception{ 
        String sql="update board set BOARD_READCOUNT = "+ 
            "BOARD_READCOUNT+1 where BOARD_NUM = "+num; 
         
        try{ 
            pstmt=con.prepareStatement(sql); 
            pstmt.executeUpdate(); 
        }catch(SQLException ex){ 
            System.out.println("setReadCountUpdate ���� : "+ex); 
        } 
    } 
    
  //�� ����. 
    public boolean boardModify(BoardBean modifyboard) throws Exception{ 
        String sql="update board set BOARD_SUBJECT=?,"; 
        sql+="BOARD_CONTENT=? where BOARD_NUM=?"; 
         
        try{ 
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, modifyboard.getBOARD_SUBJECT()); 
            pstmt.setString(2, modifyboard.getBOARD_CONTENT()); 
            pstmt.setInt(3, modifyboard.getBOARD_NUM()); 
            pstmt.executeUpdate(); 
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardModify ���� : " + ex); 
        }finally{ 
            if(rs!=null)try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){} 
            } 
        return false; 
    } 
    
  //�۾������� Ȯ��(�۾��̸� Ȯ���� ���� ������ ��´�.) 
    public boolean isBoardWriter(int num,String id){ 
        String board_sql="select * from board where BOARD_NUM=?"; 
         
        try{ 
            pstmt=con.prepareStatement(board_sql); 
            pstmt.setInt(1, num); 
            rs=pstmt.executeQuery(); 
            rs.next(); 
             
            if(id.equals(rs.getString("BOARD_NAME"))){ 
                return true; 
            } 
        }catch(SQLException ex){ 
            System.out.println("isBoardWriter ���� : "+ex); 
        } 
        return false; 
    }
    
 // �� ����(�׼� Ŭ�������� ��й�ȣ ��ġ ���� Ȯ���� �� �޼��带 �����Ѵ�.) 
    public boolean boardDelete(int num){ 
        String board_delete_sql="delete from board where BOARD_num=?"; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement(board_delete_sql); 
            pstmt.setInt(1, num); 
            result=pstmt.executeUpdate(); 
            if(result==0)return false; 
             
            return true; 
        }catch(Exception ex){ 
            System.out.println("boardDelete ���� : "+ex); 
        }finally{ 
            try{ 
                if(pstmt!=null)pstmt.close(); 
            }catch(Exception ex) {} 
        } 
         
        return false; 
    } 
     
} 

