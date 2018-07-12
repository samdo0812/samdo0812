package dron.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dron.commons.action.Action;
import dron.commons.action.ActionForward;
import dron.member.action.MemberJoinAction;

@WebServlet("/MemberFrontController")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    							/*��û ó�� ��ü				,		���� ó�� ��ü*/
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
	        String RequestURI=request.getRequestURI(); 
	        String contextPath=request.getContextPath(); /*����: ���ؽ�Ʈ �н��� WAS���� �� ���ø����̼��� �����ϱ� ���� path�̴�. Servers->server.xml�� �ִ�*/
	        String command = RequestURI.substring(contextPath.length()); 
	        
	        Action action = null; 	/*Action -> �������̽�, ~Action Ŭ������ �������ε� �ҷ���*/
	        ActionForward forward = null;	/*ActionForward -> �ؿ��� ���۹�� ������ ���� �ʵ���� ����, isRedirect�� Path�� set,get*/
	        
	        System.out.println(RequestURI);
	        System.out.println(contextPath);
	        System.out.println(command);
	        
	        /*����ȭ��*/
	        if(command.equals("/main.dron")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./main/main.jsp");
	        }
	        /*ȸ������ ȭ��*/
	        else if(command.equals("/MemberJoin.dron")){ 
	            forward = new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Member/register.jsp");
	        }
	        /*ȸ������ ������*/
	        else if(command.equals("/MemberJoinAction.dron")){ 
	            action=new MemberJoinAction(); 
	            try { 
	                forward=action.execute(request, response); 
	            } catch (Exception e) { 
	                e.printStackTrace(); 
	            }
	        }
	        /*�α��� ȭ��*/
	        else if(command.equals("/MemberLogin.dron")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Member/login.jsp"); 
	        }
	        /*�α��� ������*/
	        else if(command.equals("/MemberLoginAction.dron")) {
	        	action = new MemberLoginAction();
	        	try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        /*�α׾ƿ�*/
	        else if(command.equals("/MemberLogoutAction.dron")) {
	        	action = new MemberLogoutAction();
	        	try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        
	        /*���� �Ұ� ȭ��*/
	        else if(command.equals("/MemberIntro.dron")) {
	        	forward=new ActionForward();
	        	forward.setRedirect(false);
	        	forward.setPath("./main/memberIntro.jsp");
	        }
	        
	       
	        
	        /*�����̷�Ʈ�� ������ ����̳� ���⼭ ������*/
	        if(forward!=null){ 
	            if(forward.isRedirect()){ 
	            	/*�����̷�Ʈ�� ���������� URL�� ���õ� �ּҷ� �ٲٸ�, �� �ּҷ� �̵�, ���ο����������� request�� response��ü�� ���Ӱ� ������*/
	                response.sendRedirect(forward.getPath()); /*ActionForward�� Path�ʵ忡�� ���ڿ� ������*/
	            }else{ 
	            	/*ActionForward�� �ʵ忡 ����� �ʵ� Path(���ڿ�)�� �����ͼ� dispatcher�� ������ �Ѵ�
	    			������ �Ǵ��� �ּҰ� ������� �ʴ´�. ���� request ������ ������*/
	            	RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
	            	dispatcher.forward(request, response); /*��û�� ���� ������ �ѱ��*/
	            } 
	        }   
	        
	 }      
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		doProcess(request, response); 
	}

}
