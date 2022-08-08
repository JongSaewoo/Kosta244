package com.my.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//업로드 요청한 파일 내용 읽기

@WebServlet("/upload")	//3.0버전 이전에는 web.xml파일에 urlMapping 기술했음
@MultipartConfig // 이게 있어야 part객체의 getParts()메소드를 요청(request) 할 수 있음
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String saveDirectoryName = "c:\\files";
		File saveDirectory = new File(saveDirectoryName);

		try {   			
			if(!saveDirectory.exists()) {
				System.out.println("업로드 실제경로("+ saveDirectoryName +")생성");
				saveDirectory.mkdirs();
			}
			System.out.println("saveDirectory.getAbsolutePath()=" + saveDirectory.getAbsolutePath());
			Collection<Part> parts = request.getParts();

			for(Part part: parts) {
				String paramName = part.getName();
				System.out.println("part.getName()=" + paramName +", part.getSubmittedFileName()="+ part.getSubmittedFileName()+", part.getSize()=" + part.getSize());
				if("foodFile".equals(paramName) || "drinkFiles".equals(paramName)||"recipe".equals(paramName)) {
					
					String originFileName = part.getSubmittedFileName();  
					if(!originFileName.equals("")) {  //if로 조건문 한 이유가 temp2_이름의 파일이 생성되서 이렇게함
						//String fileName =  originFileName;
						//Universal Unique Identifier
						//업로드된 원본파일이름을 다른 이름으로 저장하고싶다면(랜덤이름으로 저장됨 .randomUUID() 
						//String fileName = UUID.randomUUID() + "_" + originFileName;
						String fileName = "temp2_" + originFileName;
						part.write( saveDirectory.getAbsolutePath()+"\\"+fileName); 
					}
				}

			}

			System.out.println("-----request.getParameter(\"greeting\")----");
			System.out.println(request.getParameter("greeting"));
			System.out.println("-----request.getParameter(\"recipes\")----");
			System.out.println(request.getParameter("recipes"));		      

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
