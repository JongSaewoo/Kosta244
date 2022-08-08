package com.my.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.dto.Board;
import com.my.dto.PageBean;
import com.my.dto.ResultBean;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.service.BoardService;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("board/*")  //http://localhost:8888/backboard/board/*
public class BoardRestController {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private BoardService service;

	@Autowired
	private ServletContext sc;
	
	//GET/backboard/board/list
	//GET/backboard/board/list/페이지번호
	@GetMapping(value = {"list", "list/{optCp}"})
	public ResultBean<PageBean<Board>> list(@PathVariable //int currentPage) {
														  Optional<Integer> optCp) {
		//맵핑에 optCp인자를 @PathVariable로 전달받는다 
		
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			int currentPage;
			if(optCp.isPresent()) { //currentPage의 값 중 null이 존재하면
				currentPage = optCp.get();
			}else {
				currentPage = 1;
			}
			PageBean<Board> pb = service.boardList(currentPage);
			rb.setStatus(1);
			rb.setT(pb);
		}catch(FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}

		return rb;
	}
	
	//GET/backboard/board/search/검색어/페이지번호	/search/답/3
	//GET/backboard/board/search/검색어			/search/답
	//GET/backboard/board/search/페이지번호 	    /search/3  (X) : 3을 검색어로 판다
	//GET/backboard/board/search//페이지번호 		/search//3 (X) : 중간경로생략 불가
	//GET/backboard/board/search/				/search
	@GetMapping(value = {"search/{optWord}", "search/{optWord}/{optCp}"})
	public ResultBean<PageBean<Board>> search(
			@PathVariable Optional<Integer> optCp, 
			@PathVariable Optional<String> optWord) {
		ResultBean<PageBean<Board>> rb = new ResultBean<>();
		try {
			PageBean<Board> pb;
			String word;
			if(optWord.isPresent()) {
				word=optWord.get();
			}else {
				word = "";
			}
			
			int currentPage = 1;
			if(optCp.isPresent()) {
				currentPage = optCp.get();
			}	
			
			if("".equals(word)) {
				pb = service.boardList(currentPage);
			}else {
				pb = service.searchBoard(word, currentPage);
			}
			rb.setStatus(1);
			rb.setT(pb);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}

	//GET/backboard/board/view/글번호
	//@GetMapping("view/{boardNo}")
	
	//GET/backboard/board/글번호
	@GetMapping("{boardNo}")
	public ResultBean<Board> viewBoard(@PathVariable int boardNo) {
		ResultBean<Board> rb = new ResultBean<>();
		try {
			Board b = service.veiwBoard(boardNo);
			rb.setStatus(1);
			rb.setT(b);
		} catch (FindException e) {
			e.printStackTrace();
			rb.setStatus(0);
			rb.setMsg(e.getMessage());
		}
		return rb;
	}
	
	//POST/backboard/board/write/글제목/글내용
	@PostMapping("write") // 글입력에서 이미지까지 업로드하므로 굳이 @PathVariable안씀
						  // 파일업로드를 하려면 반드시 폼데이터가 필요.	
	public ResponseEntity<?> write(
			@RequestPart(required = false) List<MultipartFile> letterFiles
			,@RequestPart(required = false) MultipartFile imageFile
			,Board board
			,String greeting
			,HttpSession session){

		logger.info("요청전달데이터 title=" + board.getBoardTitle() + ", content=" + board.getBoardContent());
		logger.info("파일개수 : letterFiles.size()=" + letterFiles.size());
		logger.info("파일크기 : imageFile.getSize()=" + imageFile.getSize() + ", 업로드된 파일 이름 : imageFile.getOriginalFileName()=" + imageFile.getOriginalFilename());
		logger.info(greeting);

		//게시글 내용 DB에 저장
		try {
			//String loginedId = (String)session.getAttribute("loginedId");
			//------샘플데이터 id------
			String loginedId = "id1";
			//----------------------
			board.setBoardId(loginedId);
			service.writeBoard(board);
			//			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//파일 경로 생성
		//		String saveDirectory = "c:\\files";
		String saveDirectory = sc.getInitParameter("filePath");  //WEB.XML파일에 param태그의 filePath값을 가져옴	
		if ( ! new File(saveDirectory).exists()) {
			logger.info("업로드 실제경로생성");
			new File(saveDirectory).mkdirs();
		}

		int wroteBoardNo = board.getBoardNo();//저장된 글의 글번호

		//-----------letterFiles 저장-------------
		int savedletterFileCnt = 0;//서버에 저장된 파일수
		if(letterFiles != null) {
			for(MultipartFile letterFile: letterFiles) {
				long letterFileSize = letterFile.getSize();
				if(letterFileSize > 0) {
					String letterOriginFileName = letterFile.getOriginalFilename(); //자소서 파일원본이름얻기
					//지원서 파일들 저장하기
					logger.info("지원서 파일이름: " + letterOriginFileName +" 파일크기: " + letterFile.getSize());
					//저장할 파일이름을 지정한다 ex) 글번호_letter_XXXX_원본이름
					String letterfileName = wroteBoardNo + "_letter_" + UUID.randomUUID() + "_" + letterOriginFileName;
					File savevdLetterFile = new File(saveDirectory, letterfileName);//파일생성
					try {
						FileCopyUtils.copy(letterFile.getBytes(), savevdLetterFile); //파일내용복붙FileCopyUtils
						logger.info("지원서 파일저장:" + savevdLetterFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
					savedletterFileCnt++;
				}//end if(letterFileSize > 0)
			}
		}//end if(letterFiles != null)
		logger.info("저장된 letter 파일개수: " + savedletterFileCnt);

		//-----------이미지파일 저장--------------
		File thumbnailFile = null;
		long imageFileSize = imageFile.getSize();  //파일크기
		int imageFileCnt = 0;  //서버에 저장된 이미지파일 수

		if(imageFileSize > 0) {
			//이미지파일 저장하기
			String imageOrignFileName = imageFile.getOriginalFilename(); //이미지파일원본이름얻기
			logger.info("이미지 파일이름:" + imageOrignFileName +", 파일크기: " + imageFile.getSize());

			//저장할 파일이름을 지정한다 ex) 글번호_image_XXXX_원본이름
			String imageFileName = wroteBoardNo + "_image_" + UUID.randomUUID() + "_" + imageOrignFileName;
			//이미지파일생성
			File savedImageFile = new File(saveDirectory, imageFileName);

			try {
				FileCopyUtils.copy(imageFile.getBytes(), savedImageFile);
				logger.info("이미지 파일저장:" + savedImageFile.getAbsolutePath());

				//파일형식 확인
				String contentType = imageFile.getContentType();
				if(!contentType.contains("image/")) { //이미지파일형식이 아닌 경우
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

				//이미지파일인 경우 섬네일파일을 만듦
				String thumbnailName =  "s_"+imageFileName; //섬네일 파일명은 s_글번호_XXXX_원본이름
				thumbnailFile = new File(saveDirectory,thumbnailName);
				FileOutputStream thumbnailOS;
				thumbnailOS = new FileOutputStream(thumbnailFile);
				InputStream imageFileIS = imageFile.getInputStream();
				int width = 100;
				int height = 100;
				Thumbnailator.createThumbnail(imageFileIS, thumbnailOS, width, height);
				logger.info("섬네일파일 저장:" + thumbnailFile.getAbsolutePath() + ", 섬네일파일 크기:" + thumbnailFile.length());
				//이미지 썸네일다운로드하기
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set(HttpHeaders.CONTENT_LENGTH, thumbnailFile.length()+"");
				responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(thumbnailFile.toPath()));
				responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename="+URLEncoder.encode("a", "UTF-8"));
				logger.info("섬네일파일 다운로드");
				return new ResponseEntity<>(FileCopyUtils.copyToByteArray(thumbnailFile), 
						responseHeaders, 
						HttpStatus.OK);

			} catch (IOException e2) {
				e2.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}//end if(imageFileSize > 0) 
		else {
			logger.error("이미지파일이 없습니다");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//게시글을 저장할때 올린 이미지도 게시글과함께 수정하고싶으면 form-data객체(@RequestMultiPart) 이용
	@PutMapping(value="{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> modify( 
			//ResponseEntity<>로 리턴할경우 JSON문자열로 제대로 응답 안될수있는데 특히 한글은 
			// 더더욱 인코딩 응답이 안될수 있기에 produces속성을 지정해 JSON으로 응답하겠다 명확히 지정
			// ResponseEntity<Object>나 ResponseEntity<?>는 똑같음.
			@PathVariable int boardNo,
			@RequestBody Board b){
		try {
			//글 내용이 비어있는지 유효성 검사
			if(b.getBoardContent() == null || b.getBoardContent().equals("")) {
				return new ResponseEntity<>("글내용은 반드시 입력하세요", HttpStatus.BAD_REQUEST);
			}
			
			b.setBoardNo(boardNo);
			service.modifyBoard(b);
			return new ResponseEntity<>(HttpStatus.OK);
			//200번 에러가 아닌 200번 OK 성공메시지를 반환
		}catch(ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			//500번 서버 에러로 반환
			// t> HttpStatus.NOT_FOUND는 404 연결자원문제를 나타냄
		}
	}
	
	//게시글을 저장할때 올린 이미지도 게시글과함께 삭제하고싶으면 file객체 사용
	@DeleteMapping(value="{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> remove(@PathVariable int boardNo) {
		try {
			service.removeBoard(boardNo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RemoveException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "reply/{boardParentNo}", produces = MediaType.APPLICATION_JSON_VALUE)
												//produces = "text/plain;charset=utf-8" 
	public ResponseEntity<String> reply(@PathVariable int boardParentNo,
			@RequestBody Board b) {
		if(b.getBoardTitle() == null || b.getBoardTitle().equals("") ||
				b.getBoardContent() == null || b.getBoardContent().equals("")){
			return new ResponseEntity<>("글제목이나 글내용은 반드시 입력하세요", HttpStatus.BAD_REQUEST);   
		}
//		String loginedId = (String)session.getAttribute("loginInfo");
		//---로그인대신할 샘플데이터--
		String loginedId = "id1";
		
		b.setBoardId(loginedId);
		b.setBoardParentNo(boardParentNo);
		try {
			service.replyBoard(b);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}