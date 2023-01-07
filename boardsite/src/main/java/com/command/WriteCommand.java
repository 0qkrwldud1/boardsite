package com.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.FimageDTO;
import com.beans.boardDAO;
import com.beans.boardDTO;
import com.controller.boardcontroller;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteCommand implements Command {
	//다중 이미지 파일 업로드

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// int cnt = 0;
		int boardNum = boardcontroller.boardNum;
		
		// 처음에 해당 게시글을 작성시, 이미지 파일을 저장하는 테이블의 부모글이 처음에 없음.
		// 그래서, 임시로 해당 부모글의 갯수를 저장하는 변수를 공유 변수 형식으로 사용. 
		// 단점. 서버가 리로드 될때 마다 갱신되어서, 작업이 불편함. -> 해당 테이블을 삭제후 생성을 반복. 
		// 테이블을 하나 만들어서 따로 분리해서 관리 할수도 있음. . 해당 게시글의 번호만 저장하는 역할.
		
		String realFolder = "C:\\Users\\박지영\\git\\boardsite\\boardsite\\src\\main\\webapp\\board_images\\"; 
		//웹 어플리케이션상의 절대 경로
		String encType = "utf-8"; //인코딩 타입
		int maxSize = 100 * 1024 * 1024; //최대 업로드될 파일의 크기 100Mb
		
		
		MultipartRequest multi;
		
		try {
			 multi = new MultipartRequest
					(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			 	//new DefaultFileRenamePolicy() : 파일 중복을 처리하는 기본 정책 
				//예) jsp.jpg, 2번째 파일명: jsp1.jpg, jsp2.jpg
				// multi 관련 객체 샘플로 사용하기위해서 가지고 옴.
			 
			 	// dao 게시판에 관련된 crud 메서드들이 있다.
				// 싱글톤 패턴.
				boardDAO dao = boardDAO.getInstance();
				
				//getboardCount 해당 메서드는 board 테이블의 몇개의 행이있는지 알려주는 메서드., 
				//게시글이 몇개이냐? 카운트 세어주기.
				int test = dao.getboardCount(null, null);
				// 자식 테이블(파일이미지 테이블) 여기에 값을 입력시, 부모글의 num 번호가 필요해요.
				// 게시글을 처음 작성시에, 부모글이 없습니다. -> 처리를 어떻게 할것인지.?
				// 예) 첫번째 글 , 운영자, 필독 공지사항 처럼 하나의 게시글임의로 작성
				// 예) 처음 게시글의 번호 여부를 처리하든지. -> 선택. 
				
				System.out.println("test 값확인 :"+ test);
				
				//게시판 첫 시작시, 임의의 이미지 없는 글 올리기 , 축하글 또는 공지사항등 등록.
				if(test == 0 ) {
					
					// 부모 글이 없다 -> 그래서, 기본값을 1로 설정.
					boardNum = 1;
				
				} else {
					
					// 부모글이 있고, 해당 파일이미지 테이블이 작성이 된다면.
					// 해당 부모글의 게시글 번호 카운트를 따라 감. = boardNum
					boardNum += 1;
				}

				
				// 사용자가 작성한 글의 내용을 담을 임시 객체.
				// 임시 객체는 해당 db에 전달할 형식(DTO)
				// 해당 게시글을 담기 위한 객체.
				boardDTO dto = new boardDTO();
				
				// 파일의 이미지를 담을 객체.
				FimageDTO fileDTO = new FimageDTO();
				
				// 여러 파일 이미지 객체들을 담을 컬렉션 객체.
				ArrayList<FimageDTO> fileLists = new ArrayList<FimageDTO>();

				// num을 받아오는 작업이 없습니다.
				
				// 사용자로부터 입력 받은 내용을 임시 객체에 담아두는 작업.
				// 일반 데이터를 받는 부분. -> 사용자가 입력된 글.
				// 일반 파일 이미지 업로드를 안하는 게시판에서는 받는 객체를 : request 사용 했다면.
				// 파일 이미지 업로드 가능한 게시판이므로 :MultipartRequest형식 multi 사용해야함. 
				
				
				dto.setTitle(multi.getParameter("title"));
				dto.setContent(multi.getParameter("content"));
				dto.setUser_ID(multi.getParameter("user_ID"));
				dto.setCategory(multi.getParameter("category"));
				
				// 콘솔 상에 출력하기(해당 값을 잘 받아 오고 있는지 여부를 확인하는 용도. )
				System.out.println(multi.getParameter("title"));
				System.out.println(multi.getParameter("content"));
				System.out.println(multi.getParameter("user_ID"));
				System.out.println(multi.getParameter("category"));
			
				java.text.SimpleDateFormat formatter = 
						new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String regdate = formatter.format(new java.util.Date());
				System.out.println(regdate);
				
				dto.setRegDate(regdate);
				
				
				//========================================================
				//파일 이미지를 업로드 하는 부분. Controller 라는 부분에 해당 기능(service) 코드가 섞여 있다. 
				// 유지보수 및 확장 불편함. -> service 역할 부분을 따로 빼어내서 해당 기능만 있는 파일을 만들기.
				// 인터페이스를 이용해서 작업.

				// 임시 객체 board(DTO) 사용자가 글쓰기시 입력한 정보와 보이지 않는 정보를 같이 전달함.
				// 임시 객체 board(DTO) 내용에는 num 의 정보가 없고, 기본 자동 생성 번호를 사용.
				// 글만 작성.

				//===================================================================
				
				// 글만 작성.
				
				//cnt = new boardDAO().insert(dto);
				
				dao.insertBoard(dto);
				
				// 해당 이미지를 저장하는 메서드를 만들기.
				// 매개변수에는 해당 게시글의 번호를 넣을 예정.
				// 하나의 게시글에 첨부된 이미지들의 목록도 있음.
				// dto 에서 이미지를 넣는 경우.
				// 1) 한개  또는 
				// 2) 두개 이상이 들어갈수도 있음.
				// 3) 파일 이미지가 없이 글을 등록 경우.
				
				// 파일 데이터를 받아서 Enumeration 형식에 files 에 담기. 여러개 담았음.
				Enumeration files = multi.getFileNames();
				
				// 반복문으로 Enumeration 안에 객체를 꺼내는 작업을 함.
				
				while (files.hasMoreElements()) {
					
					//파일명 중복 막기위해서, 파일명 앞에 붙은 랜덤한 숫자.
					// 예)4024bf24-4db3-458b-83b5-23399c8f4a72_bread2.jpg
					UUID uuid = UUID.randomUUID();
					
					// 반복문으로 해당파일을 하나씩 담기 위한 임시 객체.
					FimageDTO fileDTO2 = new FimageDTO();
					
					//파일을 첨부하는 뷰에서 file1, file2, file3 name 해당하는 부분.
					String fname = (String) files.nextElement();
					
					String fileName = multi.getFilesystemName(fname);
					// 변경된 파일명 : 아래 형식으로 파일명 중복을 방지.
					// 171f45c0-38fa-42fd-bd4c-63cb8c4847a1_라바1.jfif
					
					String uploadFileName = uuid.toString() + "_" + fileName;
					
					fileDTO2.setFileName(uploadFileName);
					fileDTO2.setRegDate(regdate);
					fileDTO2.setNum(boardNum);
					
					// 받아온 이미지를 임시 객체인 fileDTO2 에담아서, 여러 객체를 담을 컬렉션에 담는 작업. 
					fileLists.add(fileDTO2);
					// 업로드된 파일명을 변경하는 작업.
					// MultipartRequest 를 사용할 경우 파일 이름을 변경 하여 업로드 할 수 없다.
					//이유는 multi 생성시 바로 업로드.
					//그래서 업로드 후에 파일명을 변경 하는 방법.
				    
					// MultipartRequest 특징
					// 해당 객체를 생성하는 순간, 저장 경로에 해당 파일명으로 바로 생성됨. 
					// 그래서, 생성된 파일명을 제가 원하는 파일명으로 변경하는 작업. 
					
						// if("fileName".equals(fileName))
						
						if(!fileName.equals("fileName")){
						// fileName : 원본의 파일이름.
					    // 원본이 업로드된 절대경로와 파일명를 구한다.
					 
						String fullFileName = realFolder + "/" + fileName;
					 	// 파일 객체 생성
					     
						File f1 = new File(fullFileName);
					     
						if(f1.exists()) {    
					    	 
							// 업로드된 파일명이 존재하면 Rename한다.
					    	//변경하고 싶은 파일명, 해당 경로와 해당 파일 그리고 확장자 포함. 
					          
							File newFile = new File(realFolder + "/" + uploadFileName);
					        
							// 파일이름 변경.
					        f1.renameTo(newFile);  
					        // rename...
					     }
					}
					// 테스트 하기위해서 콘솔에 찍어 본 내용입니다. 참고.
					System.out.println("uploadFileName : 반복문안에 파일명" + uploadFileName);
					System.out.println("해당 파일 위치 경로가 찍히는지 여부 : " + realFolder);
				}	
				
				// 테스트 컬렉션 여러 이미지가 잘 담아지고, 잘 출력이 되는지 확인. 
//				for (int i = 0; i < fileLists.size(); i++) {
//					FileImageDTO ex = fileLists.get(i);
//					String ex2 = ex.getFileName();
//					System.out.println("ex2 밖에 반복문 테스트" + ex2);
//				}
					
				//파일이미지가 존재한다면, 해당 파일들을 디비 테이블에 추가함.
				if(fileLists != null) {
				
					dao.insertImage(fileLists);	
				
				//확인하는 샘플. 
				//부모글 의 갯수와, 해당 내가 카운트하는 숫자가 일치하는 지 확인 하기 위해서.
				System.out.println("boardNum:" + boardNum);
				}
				
				//request.setAttribute("result", cnt);
				//request.setAttribute("dto", dto);
				request.setAttribute("boardNum", boardNum);
				
				} catch(IOException e) {
						e.printStackTrace();
				}	
					
			}
	
		}
		
		// cnt 값이 1이라면 데이터베이스에 데이터가 제대로 insert가 되었다는 뜻.
		// 또한 게시글이 올바르게 등록되었다면 해당 게시글을 view 하는 창으로 바로 넘어갈 것이므로 해당 글의 num이 필요함. 
		// dto에 담긴 num를 사용할 것이므로 dto도 attribute에 set해서 넘겨준다.

