package dron.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dron.commons.action.Action;
import dron.commons.action.ActionForward;
import dron.member.db.MemberBean;
import dron.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession session = request.getSession(); //회원 인증 성공시 아이디를 세션에 등록할 세션 객체 생성 
		MemberDAO memberdao = new MemberDAO(); 
		MemberBean memberBean = new MemberBean(); 
		
		int result=-1; // 기본 결과 값을 -1(아이디가 존재하지 않는다는 의미로 정의)
		
		memberBean.setUsername(request.getParameter("username")); 
        memberBean.setPassword(request.getParameter("password")); 
        result=memberdao.isMember(memberBean); 	//dao에서 아이디 존재 하지 않을 시 result를 -1, 일치  1 불일치 0를 반환함
		
      //로그인 실패의 경우 
        if(result==0){ //비밀번호 틀리다는 결과값 나오면 얼럿창 띄우고 다시 로그인창 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('비밀번호가 일치하지 않습니다.');"); 
            out.println("location.href='./MemberLogin.dron';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }else if(result==-1){ //아이디가 존재하지 않으면 경고얼럿창 후 다시 로그인창 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('아이디가 존재하지 않습니다.');"); 
            out.println("location.href='./MemberLogin.dron';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }        
        
        //로그인 성공의 경우 
        session.setAttribute("username", memberBean.getUsername()); //세션에 id등록 
        forward.setRedirect(true);//접속끊었다가 다시연결하면서 새로운 정보를 보여준다. 
        forward.setPath("./main.dron"); //메인 화면으로 보냄
        return forward; 
        
		
	}
}
