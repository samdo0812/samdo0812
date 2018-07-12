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
       
    							/*요청 처리 객체				,		응답 처리 객체*/
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
	        String RequestURI=request.getRequestURI(); 
	        String contextPath=request.getContextPath(); /*참고: 컨텍스트 패스는 WAS에서 웹 어플리케이션을 구분하기 위한 path이다. Servers->server.xml에 있다*/
	        String command = RequestURI.substring(contextPath.length()); 
	        
	        Action action = null; 	/*Action -> 인터페이스, ~Action 클래스들 동적바인딩 할려고*/
	        ActionForward forward = null;	/*ActionForward -> 밑에서 전송방식 구분을 위한 필드들이 있음, isRedirect와 Path를 set,get*/
	        
	        System.out.println(RequestURI);
	        System.out.println(contextPath);
	        System.out.println(command);
	        
	        /*메인화면*/
	        if(command.equals("/main.dron")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./main/main.jsp");
	        }
	        /*회원가입 화면*/
	        else if(command.equals("/MemberJoin.dron")){ 
	            forward = new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Member/register.jsp");
	        }
	        /*회원가입 누르면*/
	        else if(command.equals("/MemberJoinAction.dron")){ 
	            action=new MemberJoinAction(); 
	            try { 
	                forward=action.execute(request, response); 
	            } catch (Exception e) { 
	                e.printStackTrace(); 
	            }
	        }
	        /*로그인 화면*/
	        else if(command.equals("/MemberLogin.dron")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Member/login.jsp"); 
	        }
	        /*로그인 누르면*/
	        else if(command.equals("/MemberLoginAction.dron")) {
	        	action = new MemberLoginAction();
	        	try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        /*로그아웃*/
	        else if(command.equals("/MemberLogoutAction.dron")) {
	        	action = new MemberLogoutAction();
	        	try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        
	        /*조원 소개 화면*/
	        else if(command.equals("/MemberIntro.dron")) {
	        	forward=new ActionForward();
	        	forward.setRedirect(false);
	        	forward.setPath("./main/memberIntro.jsp");
	        }
	        
	       
	        
	        /*리다이렉트냐 포워드 방식이냐 여기서 구분함*/
	        if(forward!=null){ 
	            if(forward.isRedirect()){ 
	            	/*리다이렉트는 웹브라우저는 URL을 지시된 주소로 바꾸며, 그 주소로 이동, 새로운페이지에서 request와 response객체가 새롭게 생성됨*/
	                response.sendRedirect(forward.getPath()); /*ActionForward의 Path필드에서 문자열 가져옴*/
	            }else{ 
	            	/*ActionForward의 필드에 저장된 필드 Path(문자열)를 가져와서 dispatcher로 포워딩 한다
	    			포워드 되더라도 주소가 변경되지 않는다. 같은 request 영역을 공유함*/
	            	RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
	            	dispatcher.forward(request, response); /*요청과 응답 권한을 넘긴다*/
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
