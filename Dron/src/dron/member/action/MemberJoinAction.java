package dron.member.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 
import dron.member.db.MemberBean; 
import dron.member.db.MemberDAO; 

public class MemberJoinAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); //�ѱ�ó�� 
        ActionForward forward = new ActionForward(); 
        MemberDAO memberdao = new MemberDAO(); 
        MemberBean memberBean = new MemberBean(); 
        boolean result=false; 

        String username;
    	String email;
    	String password;
    	String passwordCheck;
    	
        /*�Է� ������ memberbean��ü�� ����*/ 
        memberBean.setUsername(request.getParameter("username")); 
        memberBean.setEmail(request.getParameter("email")); 
        memberBean.setPassword(request.getParameter("password")); 
        memberBean.setPasswordCheck(request.getParameter("passwordCheck")); 
        result=memberdao.joinMember(memberBean); // dao�� joinmember�޼��带 �����ؼ� ȸ������ó��         

        //ȸ������ ���н� null��ȯ 
        if(result==false){ 
            System.out.println("ȸ������ ����"); 
            return null; 
        }     
        //ȸ������ ���� 
        forward.setRedirect(true); 
        forward.setPath("./MemberLogin.dron");         
        return forward; 
    } 
}