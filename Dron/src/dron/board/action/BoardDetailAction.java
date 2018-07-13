package dron.board.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.board.db.BoardBean; 
import dron.board.db.BoardDAO; 
import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 

public class BoardDetailAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        request.setCharacterEncoding("euc-kr"); 
        BoardDAO boarddao=new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
         
        int num = Integer.parseInt(request.getParameter("num")); 
        boarddao.setReadCountUpdate(num); 
        boarddata = boarddao.getDetail(num); 
         
        if(boarddata==null){ 
            System.out.println("�󼼺��� ����"); 
            return null; 
        } 
        System.out.println("�󼼺��� ����"); 
         
        request.setAttribute("boarddata", boarddata); 
        ActionForward forward = new ActionForward(); 
        forward.setRedirect(false); 
        forward.setPath("./Board/boardView.jsp"); 
        return forward; 
    } 
}
