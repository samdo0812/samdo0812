package dron.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dron.commons.action.Action;
import dron.commons.action.ActionForward;
import dron.member.db.MemberBean;
import dron.member.db.MemberDAO;

public class MemberLogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession session = request.getSession(); //회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성 


    
         
        session.invalidate();
        forward.setRedirect(true);//접속끊었다가 다시연결하면서 새로운 정보를 보여준다. 
        forward.setPath("./MemberLogin.dron"); //로그인 화면으로
        return forward; 
        
		
	}
}
