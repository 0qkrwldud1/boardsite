package com.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;

import com.beans.FimageDTO;
import com.beans.boardDAO;
import com.beans.boardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteCommand2 implements Command {
	//멀티파트 이미지 넣는 메서드

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		
		
		String realFolder = "C:\\Users\\박지영\\git\\boardsite\\boardsite\\src\\main\\webapp\\board_images"; //웹 어플리케이션상의 절대 경로
		String encType = "utf-8"; //인코딩 타입
		int maxSize = 5 * 1024 * 1024; //최대 업로드될 파일의 크기5Mb
		int boardNum = 0;
		
		MultipartRequest multi;
		try {
			 multi = new MultipartRequest
					(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			 	// dao 게시판에 관련된 crud 메서드들이 있다.
				// 싱글톤 패턴.
				boardDAO dao = boardDAO.getInstance();
				int test = dao.getboardCount(null, null);
				System.out.println("test 값확인 :"+ test);
				
				//게시판 첫 시작시, 임의의 이미지 없는 글 올리기 , 축하글 또는 공지사항등 등록.
				if(test == 0 ) {
					boardNum = 1;
				
				} else {
					
					boardNum += 1;
				}
//				System.out.println("boardNum :"+ boardNum);
				
				// 사용자가 작성한 글의 내용을 담을 임시 객체.
				// 임시 객체는 해당 db에 전달할 형식(DTO)
				boardDTO dto = new boardDTO();
				FimageDTO fileDTO = new FimageDTO();
				ArrayList<FimageDTO> fileLists = new ArrayList<FimageDTO>();

				// num을 받아오는 작업이 없습니다.
				
				// 사용자로부터 입력 받은 내용을 임시 객체에 담아두는 작업.
				
				dto.setTitle(multi.getParameter("title"));
				dto.setContent(multi.getParameter("content"));
				dto.setUser_ID(multi.getParameter("user_ID"));
				
				
				// 콘솔 상에 출력하기(해당 값을 잘 받아 오고 있는지 여부를 확인하는 용도. )
				System.out.println(multi.getParameter("title"));
				System.out.println(multi.getParameter("content"));
				System.out.println(multi.getParameter("user_ID"));
			
				cnt = new boardDAO().insert(dto);
				
				
					
				
				Enumeration files = multi.getFileNames();
				
				while (files.hasMoreElements()) {
					
					//파일명 중복 막기위해서, 파일명 앞에 붙은 랜덤한 숫자.
					UUID uuid = UUID.randomUUID();
					// 반복문으로 해당파일을 하나씩 담기 위한 임시 객체.
					FimageDTO fileDTO2 = new FimageDTO();
					
					//파일을 첨부하는 뷰에서 file1, file2, file3 name 해당하는 부분.
					String fname = (String) files.nextElement();
					
//					System.out.println("fname" + fname);
					//원본 파일명 , 해당 name의 실제 파일명.
					String fileName = multi.getFilesystemName(fname);
					// 변경된 파일명 : 아래 형식으로 파일명 중복을 방지.
					// 171f45c0-38fa-42fd-bd4c-63cb8c4847a1_라바1.jfif
					String uploadFileName = uuid.toString() + "_" + fileName;
					
					fileDTO2.setFileName(uploadFileName);
					fileDTO2.setRedDate(encType);
					fileDTO2.setNum(boardNum);
					fileLists.add(fileDTO2);
					// 업로드된 파일명을 변경하는 작업.
					// MultipartRequest 를 사용할 경우 파일 이름을 변경 하여 업로드 할 수 없다.
					//이유는 multi 생성시 바로 업로드.
					//그래서 업로드 후에 파일명을 변경 하는 방법.
				    
					if(!fileName.equals("")) {
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
					System.out.println("uploadFileName : 반복문안에 파일명" + uploadFileName);
					System.out.println("해당 파일 위치 경로가 찍히는지 여부 : " + realFolder);
				}	
//				for (int i = 0; i < fileLists.size(); i++) {
//					FileImageDTO ex = fileLists.get(i);
//					String ex2 = ex.getFileName();
//					System.out.println("ex2 밖에 반복문 테스트" + ex2);
//				}
					
				//파일이미지가 존재한다면, 해당 파일들을 디비 테이블에 추가함.
				if(fileLists != null) {
				dao.insertImage(fileLists);	
				
				System.out.println("boardNum:" + boardNum);
				}
				request.setAttribute("result", cnt);
				request.setAttribute("dto", dto);
				
				} catch(SQLException | IOException e) {
						e.printStackTrace();
				}	
					
			}
	
	}
		
		// cnt 값이 1이라면 데이터베이스에 데이터가 제대로 insert가 되었다는 뜻 
		// 또한 게시글이 올바르게 등록되었다면 해당 게시글을 view 하는 창으로 바로 넘어갈 것이므로 해당 글의 uid가 필요함. 
		// dto에 담긴 uid를 사용할 것이므로 dto도 attribute에 set해서 넘겨준다.

