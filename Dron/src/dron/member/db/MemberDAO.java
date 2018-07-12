package dron.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dron.member.db.MemberBean;

public class MemberDAO {
	Connection con; 
	PreparedStatement pstmt; 
	ResultSet rs; 

	//생성자에서 DB연결 
	public MemberDAO() { 
		try{ 
			Context init = new InitialContext(); 
			DataSource ds =  
					(DataSource) init.lookup("java:comp/env/jdbc/OracleDB"); 
			con = ds.getConnection(); 
		}catch(Exception ex){ 
			System.out.println("DB 연결 실패 : " + ex); 
			return; 
		} 
	} 


	//회원 가입 
	public boolean joinMember(MemberBean memberBean){ 
		String sql="INSERT INTO DRONMEMBER VALUES (?,?,?,?)"; 
		int result=0; 

		try{ /*MemberBean에서 get으로 가져온 값을 pstmt에 set함*/
			pstmt=con.prepareStatement(sql); 
			pstmt.setString(1, memberBean.getUsername()); 
			pstmt.setString(2, memberBean.getEmail()); 
			pstmt.setString(3, memberBean.getPassword()); 
			pstmt.setString(4, memberBean.getPasswordCheck()); 
			result=pstmt.executeUpdate(); 

			if(result!=0){ 
				return true; 
			} 
		}catch(Exception ex){ 
			System.out.println("joinMember 에러: " + ex);             
		}finally{ 
			if(rs!=null) try{rs.close();}catch(SQLException ex){} 
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		} 

		return false; 
	} 

	//회원인증(id와 passwd확인) 
	public int isMember(MemberBean memberBean){ 
		String sql ="select password from dronmember where username=?"; 
		int result=-1; 

		try{ 
			pstmt=con.prepareStatement(sql); 
			pstmt.setString(1, memberBean.getUsername()); 
			rs = pstmt.executeQuery(); 

			if(rs.next()){ 
				if(rs.getString("password").equals( 
						memberBean.getPassword())){ 
						result=1;//일치. 
				}else{ 
					result=0;//불일치. 
				} 
			}else{ 
				result=-1;//아이디 존재하지 않음. 
			} 
		}catch(Exception ex){ 
			System.out.println("isMember 에러: " + ex);             
		}finally{ 
			if(rs!=null) try{rs.close();}catch(SQLException ex){} 
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		} 

		return result; 
	} 
}
