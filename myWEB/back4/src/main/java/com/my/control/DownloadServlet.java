package com.my.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일이름 요청전달데이터 얻기(1.다운로드 요청)
		String fileName = request.getParameter("filename");
		//다운로드할 파일의 실제 경로 얻기
		String saveDirectoryName = "c:\\files";
		//(2.파일을 찾는과정)
		//파일의 형식을 찾는과정(3.파일을 읽는과정)
		Path path = Paths.get(saveDirectoryName, fileName);
		String contentType = Files.probeContentType(path);
		System.out.println(fileName+"파일의 contentType:" + contentType);
		

		File f = path.toFile();
		if(contentType.contains("image/")) {  //타입이 이미지파일일 경우
			response.setContentType(contentType);	//이미지파일로 타입 지정
			
			//이미지파일을 웹에서 보여지기위한 과정
			response.setHeader("Content-Length", String.valueOf(f.length()));
			response.setHeader("Content-Disposition", "inline; filename="+URLEncoder.encode(fileName, "UTF-8"));
		}else {
			//응답형식 : application/octet-stream(8비트 단위의 binary data)(기본형식임)
			//이미지파일이 아니면 다운로드를 진행함
			response.setContentType("application/octet-stream;charset=UTF-8");

			//다운로드시 파일이름 결정
			//response.setHeader("Content-Disposition", "attachment;filename=" + name);
			response.setHeader("Content-Disposition", "attachment;filename=" 
					+ URLEncoder.encode(fileName, "UTF-8"));
					  //URLEncoder.encode(fileName, "UTF-8")은 한글이 안깨지도록하기위해
		}

		//응답출력스트림(4.파일을 출력하는과정)
		//PrintWriter out = response.getWriter(); (X) --문자형태로 응답출력
		ServletOutputStream sos = response.getOutputStream(); //--바이트형태로 파일을 출력

		FileInputStream fis = null;
		fis = new FileInputStream(f);
		IOUtils.copy(fis, sos); //파일복사붙여넣기 = int v = -1;
								//				while((v=fis.read())! = -1){
								//					sos.write(v);
								//				} 랑 같은코드임 : -1값이 나오지않을때까지 파일을 출력함
		fis.close();
		sos.close();
	}
}
