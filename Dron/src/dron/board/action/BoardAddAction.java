package dron.board.action; 

import java.io.IOException; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import com.oreilly.servlet.MultipartRequest; 
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy; 

import dron.board.db.BoardBean; 
import dron.board.db.BoardDAO; 
import dron.commons.action.Action; 
import dron.commons.action.ActionForward; 

public class BoardAddAction implements Action{ 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
        BoardDAO boarddao = new BoardDAO(); 
        BoardBean boarddata = new BoardBean(); 
        ActionForward forward = new ActionForward(); 
         
        String realFolder="";
        String saveFolder="boardupload";         
        int fileSize=10*1024*1024; 
         
        realFolder=request.getRealPath(saveFolder);
        
        boolean result=false; 
         
        MultipartRequest multi; 
        try { 
            multi = new MultipartRequest(request, realFolder, fileSize, "euc-kr", new DefaultFileRenamePolicy()); 
            boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME")); 
            boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT")); 
            boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT")); 
            boarddata.setBOARD_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement())); 
            result=boarddao.boardInsert(boarddata); 
             
            if(result==false){ 
                System.out.println("게시판 등록 실패"); 
                return null; 
            } 
            System.out.println("게시판 등록 성공?");             
            forward.setRedirect(true); 
            forward.setPath("./BoardList.bo"); 
            return forward; 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }         
        return null; 
    } 
}

