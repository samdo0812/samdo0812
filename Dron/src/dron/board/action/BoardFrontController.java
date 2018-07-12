package dron.board.action; 

import java.io.IOException; 

import javax.servlet.RequestDispatcher; 
import javax.servlet.Servlet; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.commons.action.Action; 
import dron.commons.action.ActionForward;
import dron.board.action.BoardAddAction;
import dron.board.action.BoardListAction; 

public class BoardFrontController extends HttpServlet implements Servlet{ 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
		String RequestURI = request.getRequestURI(); 
		String contextPath=request.getContextPath(); 
		String command=RequestURI.substring(contextPath.length()); 

		ActionForward forward=null; 
		Action action=null; 

		System.out.println(RequestURI);
		System.out.println(contextPath);
		System.out.println(command);

		if(command.equals("/BoardList.bo")){ 
			action = new BoardListAction(); 
			try{ 
				forward = action.execute(request,  response); /*./board/qna_board_list.jsp�� ���ڿ��� set�� ���·� ���ϵǾ ����, 
                												  ���� ��Ŀ��� �����̷�Ʈ�� false, path�� ���ڿ��̴� ���帮��Ʈ�� �� ����*/
			}catch(Exception e){ 
				e.printStackTrace(); 
			}
		}
		/*�� �ۼ��ϱ� ��ư ������*/
		else if(command.equals("/BoardWrite.bo")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Board/boardWrite.jsp"); 
		}
		/*��� �ϸ�*/
		else if(command.equals("/BoardAddAction.bo")){ 
            action = new BoardAddAction(); 
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }

			/*-----------------------------------Ŀ���---------------------------------------------------*/

			/*���۹��*/
			if(forward !=null){ 
				if(forward.isRedirect()){ 
					response.sendRedirect(forward.getPath()); 
				}else{ 
					RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
					dispatcher.forward(request,response);                 
				} 
			}         
		}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
			doProcess(request, response); 
		} 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
			doProcess(request, response); 
		} 
	} 


