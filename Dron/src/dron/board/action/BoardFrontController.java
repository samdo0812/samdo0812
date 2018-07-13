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
import dron.board.action.BoardDeleteAction;
import dron.board.action.BoardModifyAction;
import dron.board.action.BoardDetailAction;
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
				forward = action.execute(request,  response); /*./board/qna_board_list.jsp의 문자열에 set된 상태로 리턴되어서 왔음, 
                												  전송 방식에서 리다이렉트는 false, path는 문자열이니 보드리스트로 갈 거임*/
			}catch(Exception e){ 
				e.printStackTrace(); 
			}
		}
		/*글 작성하기 버튼 누르면*/
		else if(command.equals("/BoardWrite.bo")){ 
	            forward=new ActionForward(); 
	            forward.setRedirect(false); 
	            forward.setPath("./Board/boardWrite.jsp"); 
		}
		/*등록 하면*/
		else if(command.equals("/BoardAddAction.bo")){ 
            action = new BoardAddAction(); 
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        }
		/*글 보기*/
		else if(command.equals("/BoardDetailAction.bo")){ 
            action = new BoardDetailAction();  
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
        } 
		/*글 수정*/
		else if(command.equals("/BoardModify.bo")){ 
            action = new BoardModifyFormAction(); 
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }      
	}
		/*글 수정 할 때*/
		else if(command.equals("/BoardModifyAction.bo")){ 
            action = new BoardModifyAction(); 
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }             
		}
		
		/*삭제*/
		
		else if(command.equals("/BoardDelete.bo")){ 
            action = new BoardDeleteAction(); 
            try{ 
                forward = action.execute(request,  response); 
            }catch(Exception e){ 
                e.printStackTrace(); 
            }
		}
		
		
		
		
			/*-----------------------------------커멘드---------------------------------------------------*/

			/*전송방식*/
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


