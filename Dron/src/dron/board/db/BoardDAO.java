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
            System.out.println("DB 연결 실패 : " + ex); 
            return; 
        } 
    } 
     
    /*글의 개수 구하기(전체 글의 개수를 반환한다) 
     * 글의 개수를 표시 및 페이징처리를 할때 사용된다.*/ 
    public int getListCount() { 
        int x= 0; 
         
        try{ 
            pstmt=con.prepareStatement("select count(*) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()){ 
                x=rs.getInt(1);  //count(*)한값을 x에 저장한다. 
            } 
        }catch(Exception ex){ 
            System.out.println("getListCount 에러: " + ex);             
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return x; 
    } 
     
    /*글 목록 보기 
     * List객체로 반환한다. 인수1) 출력할 페이지, 인수2) 한 페이지당 표시할 글 수*/ 
    public List getBoardList(int page,int limit){ 
        String board_list_sql="select * from "+ 
        "(select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"+ 
        "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"+ 
        "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "+ 
        "(select * from board order by "+ 
        "BOARD_RE_REF desc,BOARD_RE_SEQ desc)) "+ 
        "where rnum>=? and rnum<=?"; 
         
        List list = new ArrayList(); 
         
        int startrow=(page-1)*10+1; //읽기 시작할 row 번호. 
        int endrow=startrow+limit-1; //읽을 마지막 row 번호.         
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
            System.out.println("getBoardList 에러 : " + ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return null; 
    } 
     
    /*글 등록 
     * 1) board_num필드의 최대값을 얻어온다. 이유는 중복되지 않은 값을 가져오기 위해 
     * 2) 글등록쿼리 : 글쓰기는 원글이므로 답변과 관련된 필드는 모두 0으로..ref값(글그룹번호만 새로운 번호로 지정)*/ 
    public boolean boardInsert(BoardBean board){ 
        int num =0; 
        String sql=""; 
         
        int result=0; 
         
        try{ 
            pstmt=con.prepareStatement( 
                    "select max(board_num) from board"); 
            rs = pstmt.executeQuery(); 
             
            if(rs.next()) 
                num =rs.getInt(1)+1;  //글이 등록되어있으면 글 번호 +1 
            else 
                num=1;//글 등록이 되어있지 않으면 num=1로 
             
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
            System.out.println("boardInsert 에러 : "+ex); 
        }finally{ 
            if(rs!=null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
        } 
        return false; 
    } 
   
} 

