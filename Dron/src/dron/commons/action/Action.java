package dron.commons.action; 

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

/*�׼� ���� Ŭ������ Action�������̽��� �����ϰ� �����ν� ��û 
 * ActionForwardŬ������ ������ ���� �����͸� �����ϰ� ActionForward��ü�� �����ϰ� ó��*/ 
public interface Action { 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception; 
} 
