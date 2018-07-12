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
		HttpSession session = request.getSession(); //ȸ�� ���� ������ ���̵� ���ǿ� ����� ���� ��ü ���� 


    
         
        session.invalidate();
        forward.setRedirect(true);//���Ӳ����ٰ� �ٽÿ����ϸ鼭 ���ο� ������ �����ش�. 
        forward.setPath("./MemberLogin.dron"); //�α��� ȭ������
        return forward; 
        
		
	}
}
