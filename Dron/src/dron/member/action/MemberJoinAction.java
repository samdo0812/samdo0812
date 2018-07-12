package dron.member.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 
import dron.member.db.MemberBean; 
import dron.member.db.MemberDAO; 

public class MemberJoinAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); //한글처리 
        ActionForward forward = new ActionForward(); 
        MemberDAO memberdao = new MemberDAO(); 
        MemberBean memberBean = new MemberBean(); 
        boolean result=false; 

        String username;
    	String email;
    	String password;
    	String passwordCheck;
    	
        /*입력 정보를 memberbean객체에 저장*/ 
        memberBean.setUsername(request.getParameter("username")); 
        memberBean.setEmail(request.getParameter("email")); 
        memberBean.setPassword(request.getParameter("password")); 
        memberBean.setPasswordCheck(request.getParameter("passwordCheck")); 
        result=memberdao.joinMember(memberBean); // dao에 joinmember메서드를 실행해서 회원가입처리         

        //회원가입 실패시 null반환 
        if(result==false){ 
            System.out.println("회원가입 실패"); 
            return null; 
        }     
        //회원가입 성공 
        forward.setRedirect(true); 
        forward.setPath("./MemberLogin.dron");         
        return forward; 
    } 
}