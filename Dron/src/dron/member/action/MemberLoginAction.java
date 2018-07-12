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
		HttpSession session = request.getSession(); //ȸ�� ���� ������ ���̵� ���ǿ� ����� ���� ��ü ���� 
		MemberDAO memberdao = new MemberDAO(); 
		MemberBean memberBean = new MemberBean(); 
		
		int result=-1; // �⺻ ��� ���� -1(���̵� �������� �ʴ´ٴ� �ǹ̷� ����)
		
		memberBean.setUsername(request.getParameter("username")); 
        memberBean.setPassword(request.getParameter("password")); 
        result=memberdao.isMember(memberBean); 	//dao���� ���̵� ���� ���� ���� �� result�� -1, ��ġ  1 ����ġ 0�� ��ȯ��
		
      //�α��� ������ ��� 
        if(result==0){ //��й�ȣ Ʋ���ٴ� ����� ������ ��â ���� �ٽ� �α���â 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.');"); 
            out.println("location.href='./MemberLogin.dron';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }else if(result==-1){ //���̵� �������� ������ ����â �� �ٽ� �α���â 
            response.setContentType("text/html;charset=euc-kr"); 
            PrintWriter out = response.getWriter(); 
            out.println("<script>"); 
            out.println("alert('���̵� �������� �ʽ��ϴ�.');"); 
            out.println("location.href='./MemberLogin.dron';"); 
            out.println("</script>"); 
            out.close(); 
            return null; 
        }        
        
        //�α��� ������ ��� 
        session.setAttribute("username", memberBean.getUsername()); //���ǿ� id��� 
        forward.setRedirect(true);//���Ӳ����ٰ� �ٽÿ����ϸ鼭 ���ο� ������ �����ش�. 
        forward.setPath("./main.dron"); //���� ȭ������ ����
        return forward; 
        
		
	}
}
