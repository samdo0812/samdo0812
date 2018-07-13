package dron.board.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import dron.board.db.BoardBean; 
import dron.board.db.BoardDAO; 
import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 

public class BoardModifyView implements Action{ 

    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception { 
        ActionForward forward = new ActionForward(); 
        request.setCharacterEncoding("euc-kr"); 
         
        BoardDAO boarddao = new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
         
        int num=Integer.parseInt(request.getParameter("num")); 
        boarddata=boarddao.getDetail(num); 
         
        if(boarddata ==null){ 
            System.out.println("(수정_상세보기 실패"); 
            return null; 
        } 
        System.out.println("수정_상세보기 성공"); 
        request.setAttribute("boarddata", boarddata); 
        forward.setRedirect(false); 
        forward.setPath("./Board/boardModify.jsp"); 
        return forward; 
    } 
}
